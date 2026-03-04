const fs = require('fs');
const readline = require('readline');
const path = require('path');

function splitCsvLine(line) {
  const result = [];
  let cur = '';
  let inQuotes = false;
  for (let i = 0; i < line.length; i++) {
    const ch = line[i];
    if (ch === '"') {
      if (inQuotes && line[i + 1] === '"') { cur += '"'; i++; } else { inQuotes = !inQuotes; }
      continue;
    }
    if (ch === ',' && !inQuotes) {
      result.push(cur);
      cur = '';
      continue;
    }
    cur += ch;
  }
  result.push(cur);
  return result;
}

function findColumn(headers, candidates) {
  for (const candidate of candidates) {
    for (let i = 0; i < headers.length; i++) {
      if (headers[i].trim().toLowerCase() === candidate.toLowerCase()) return i;
    }
  }
  return -1;
}

async function prompt(question) {
  const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
  return new Promise(resolve => rl.question(question, ans => { rl.close(); resolve(ans); }));
}

async function getValidFilePath() {
  while (true) {
    const input = (await prompt('\nEnter dataset file path: ')).trim();
    let p = input;
    if (p.startsWith('"') && p.endsWith('"')) p = p.slice(1, -1);
    if (!fs.existsSync(p)) { console.log('[ERROR] File not found.'); continue; }
    try { fs.accessSync(p, fs.constants.R_OK); } catch (e) { console.log('[ERROR] File not readable.'); continue; }
    if (path.extname(p).toLowerCase() !== '.csv') { console.log('[ERROR] Not a .csv file.'); continue; }
    const firstLine = fs.readFileSync(p, { encoding: 'utf8' }).split(/\r?\n/)[0] || '';
    if (!firstLine.includes(',')) { console.log('[ERROR] File does not look like CSV.'); continue; }
    console.log('[OK] File validated successfully!');
    return p;
  }
}

function loadData(filePath) {
  const text = fs.readFileSync(filePath, { encoding: 'utf8' });
  const lines = text.split(/\r?\n/).filter(Boolean);
  if (lines.length === 0) throw new Error('CSV file is empty.');
  const headers = splitCsvLine(lines[0]);

  const titleIdx = findColumn(headers, ['title','game','name']);
  const salesIdx = findColumn(headers, ['total_sales','global_sales','sales']);
  if (titleIdx === -1) throw new Error('Could not find a title/game column in CSV.');
  if (salesIdx === -1) throw new Error('Could not find a sales column in CSV.');

  console.log('[INFO] Title column :', headers[titleIdx].trim());
  console.log('[INFO] Sales column :', headers[salesIdx].trim());

  const salesMap = new Map();
  let lineCount = 0, errorCount = 0;
  for (let i = 1; i < lines.length; i++) {
    lineCount++;
    try {
      const cols = splitCsvLine(lines[i]);
      if (cols.length <= Math.max(titleIdx, salesIdx)) continue;
      const title = cols[titleIdx].trim().replace(/"/g, '');
      const salesStr = cols[salesIdx].trim().replace(/"/g, '');
      if (!title || !salesStr) continue;
      const sales = parseFloat(salesStr);
      if (Number.isNaN(sales)) { errorCount++; continue; }
      salesMap.set(title, (salesMap.get(title) || 0) + sales);
    } catch (e) { errorCount++; }
  }

  console.log('[INFO] Rows processed :', lineCount);
  console.log('[INFO] Rows skipped   :', errorCount);

  const records = Array.from(salesMap.entries()).map(([title, totalSales]) => ({ title, totalSales }));
  records.sort((a,b) => b.totalSales - a.totalSales);
  return records;
}

async function main() {
  console.log('============================================================');
  console.log('   MIDTERM LAB 1 - Detect Low Performing Products');
  console.log('   Student: TOLENTINO | Prog2-9302');
  console.log('   Converted to Node.js');
  console.log('============================================================');

  const filePath = await getValidFilePath();
  try {
    console.log('\n[INFO] Loading dataset...');
    const records = loadData(filePath);
    console.log('[INFO] Unique products found:', records.length);
    if (records.length === 0) { console.log('[ERROR] No valid records found.'); return; }
    const totalSalesSum = records.reduce((s,r) => s + r.totalSales, 0);
    const average = totalSalesSum / records.length;

    console.log('\n============================================================');
    console.log(`  Dataset Average Total Sales: ${average.toFixed(2)} million units`);
    console.log('============================================================');

    const lowPerformers = records.filter(r => r.totalSales < average).sort((a,b) => a.totalSales - b.totalSales);

    console.log('\n============================================================');
    console.log('  FLAGGED LOW PERFORMING PRODUCTS');
    console.log(`  (Sales below average: ${average.toFixed(2)} M)`);
    console.log('============================================================');
    console.log(`${'Product Title'.padEnd(55)} ${'Sales (M)'.padStart(10)}   Status`);
    console.log('------------------------------------------------------------');
    for (const r of lowPerformers) {
      console.log(`${r.title.padEnd(55)} ${r.totalSales.toFixed(2).padStart(10)}   FLAGGED`);
    }

    console.log('\n============================================================');
    console.log('  SUMMARY');
    console.log('============================================================');
    console.log('  Total Products Analyzed  :', records.length);
    console.log('  Average Sales            :', average.toFixed(2), 'million units');
    console.log('  Low Performing (Flagged) :', lowPerformers.length);
    console.log('  Good Performing          :', records.length - lowPerformers.length);
    console.log('============================================================');
    console.log('\n[DONE] Inventory team can now review flagged products.\n');

  } catch (e) { console.log('[ERROR] Failed to process file:', e.message); }
}

if (require.main === module) main();
