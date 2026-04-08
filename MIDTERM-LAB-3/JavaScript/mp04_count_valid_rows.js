const fs = require('fs');
const readline = require('readline');

function prompt(question) {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
    return new Promise(resolve => rl.question(question, answer => {
        rl.close();
        resolve(answer.trim());
    }));
}

function parseCsvLine(line) {
    const fields = [];
    let current = '';
    let inQuotes = false;

    for (let i = 0; i < line.length; i++) {
        const ch = line[i];
        if (ch === '"') {
            if (inQuotes && line[i + 1] === '"') {
                current += '"';
                i++;
            } else {
                inQuotes = !inQuotes;
            }
        } else if (ch === ',' && !inQuotes) {
            fields.push(current.trim());
            current = '';
        } else {
            current += ch;
        }
    }

    fields.push(current.trim());
    while (fields.length > 0 && fields[fields.length - 1] === '') {
        fields.pop();
    }
    return fields;
}

function readCsv(path) {
    try {
        const data = fs.readFileSync(path, 'utf8');
        const rows = [];
        let headerFound = false;

        for (const rawLine of data.split(/\r?\n/)) {
            const line = rawLine.trim();
            if (line.length === 0) {
                continue;
            }
            const values = parseCsvLine(line);
            if (values.length === 0) {
                continue;
            }
            if (!headerFound) {
                if (values[0].toLowerCase() === 'candidate') {
                    rows.push(values);
                    headerFound = true;
                }
                continue;
            }
            rows.push(values);
        }

        if (!headerFound) {
            console.log('Header row not found in CSV.');
            return null;
        }

        return rows;
    } catch (error) {
        console.log('Error reading file:', error.message);
        return null;
    }
}

async function main() {
    console.log('MP04 - Count valid dataset rows');

    let path = process.argv[2];
    if (!path) {
        path = await prompt('Enter the CSV dataset file path: ');
    }

    const rows = readCsv(path);
    if (!rows || rows.length <= 1) {
        console.log('No valid rows were loaded from the file.');
        return;
    }

    const header = rows[0];
    const expectedFields = header.length;
    let validCount = 0;
    let invalidCount = 0;

    for (let i = 1; i < rows.length; i++) {
        const row = rows[i];
        if (row.length === expectedFields) {
            validCount++;
        } else {
            invalidCount++;
        }
    }

    console.log(`\nTotal records in dataset: ${rows.length - 1}`);
    console.log(`Valid rows: ${validCount}`);
    console.log(`Invalid rows: ${invalidCount}`);
}

main();
