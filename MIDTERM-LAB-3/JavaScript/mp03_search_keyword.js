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
    console.log('MP03 - Search for a keyword in the dataset');

    let path = process.argv[2];
    let keyword = process.argv[3];

    if (!path) {
        path = await prompt('Enter the CSV dataset file path: ');
    }

    const rows = readCsv(path);
    if (!rows || rows.length === 0) {
        console.log('No data was loaded from the file.');
        return;
    }

    const header = rows[0];
    console.log('Available columns:');
    header.forEach((column, index) => console.log(`${index + 1}: ${column}`));

    if (!keyword) {
        keyword = await prompt('Enter keyword to search: ');
    }
    keyword = keyword.toLowerCase();

    let found = 0;
    console.log('\nMatching rows:');
    console.log(header.join(' | '));

    for (let i = 1; i < rows.length; i++) {
        const row = rows[i];
        const match = row.some(value => value.toLowerCase().includes(keyword));
        if (match) {
            found++;
            console.log(row.join(' | '));
        }
    }

    console.log(`\nTotal matching rows: ${found}`);
}

main();
