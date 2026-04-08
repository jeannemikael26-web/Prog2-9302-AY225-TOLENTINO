# MIDTERM-LAB-3 Program Explanations

**Student:** TOLENTINO, JEANNE MIKAEL B.  
**Assigned MPs:** MP03, MP04, MP05

---

## JAVA PROGRAMS

### MP03 - Search for a Keyword (Java)

This program lets you search for a keyword in the CSV dataset. First, you tell it where the CSV file is located. The program shows you all the available columns so you know what data you're working with. Then you type in a keyword (like "python"), and the program searches through every row and every column to find matches. Whenever it finds a match, it displays the entire row so you can see all the information. At the end, it tells you how many rows matched your keyword. It's like using Ctrl+F to search in a spreadsheet, but for a CSV file.

### MP04 - Count Valid Dataset Rows (Java)

This program checks if your CSV data is properly formatted. It reads the CSV file and looks at the header row to see how many columns there should be. Then it goes through each data row and checks if it has the same number of columns as the header. If a row has the correct number of columns, it's "valid." If it has too many or too few columns, it's "invalid." The program then tells you the total number of records, how many are valid, and how many are invalid. This helps you spot problems in your data before using it for analysis.

### MP05 - Extract Values from a Column (Java)

This program pulls out all the values from a single column in your CSV file. It starts by showing you all the available column names so you can pick which one you want. Then you type in the column name you're interested in (like "Exam" or "Result"). The program finds that column and prints out every single value from it, one per line. This is useful when you want to analyze just one column of data—for example, to see all the exam names or all the test results without looking at the entire spreadsheet.

---

## JAVASCRIPT PROGRAMS

### MP03 - Search for a Keyword (JavaScript)

This is the JavaScript version of the search program. It works the same way as the Java version—you provide a keyword, and it searches through the CSV file to find all matching rows. The main difference is that this version uses Node.js to read the file and handle the data. You can run it from the command line and it will search through every field in every row. It shows you all the matching rows and tells you how many rows matched your search. It's the same functionality, just written in JavaScript instead of Java.

### MP04 - Count Valid Dataset Rows (JavaScript)

This is the JavaScript version of the data validation program. Just like the Java version, it checks if your CSV data is properly formatted by comparing each row to the header. It counts how many rows are valid (have the right number of columns) and how many are invalid (have too many or too few columns). Then it displays the total records, valid rows, and invalid rows. This helps you quickly spot formatting problems in your CSV file without having to check manually.

### MP05 - Extract Values from a Column (JavaScript)

This is the JavaScript version of the column extraction program. It lets you pick a column from your CSV file and pulls out all the values from that column. You tell it which column you want (like "Result" or "Exam"), and it displays every single value from that column, one per line. This is handy when you want to focus on just one piece of data—for example, to see all test results or all exam types without dealing with the whole spreadsheet. It's the same functionality as the Java version, just written in JavaScript.

---

## How to Run

### Java Compilation and Execution
```bash
cd Java

# Compile
javac MP03_SearchKeyword.java MP04_CountValidRows.java MP05_ExtractColumnValues.java

# Run MP03
java MP03_SearchKeyword "..\Sample_Data-Prog-2-csv.csv"

# Run MP04
java MP04_CountValidRows "..\Sample_Data-Prog-2-csv.csv"

# Run MP05
java MP05_ExtractColumnValues "..\Sample_Data-Prog-2-csv.csv"
```

### JavaScript Execution
```bash
cd JavaScript

# Run MP03
node mp03_search_keyword.js "..\Sample_Data-Prog-2-csv.csv" python

# Run MP04
node mp04_count_valid_rows.js "..\Sample_Data-Prog-2-csv.csv"

# Run MP05
node mp05_extract_column_values.js "..\Sample_Data-Prog-2-csv.csv" Result
```

---

## Key Features

✅ CSV file parsing with quoted field support  
✅ Case-insensitive keyword search  
✅ Data validation and row counting  
✅ Column extraction and display  
✅ Clear formatted output with pipe separators  
✅ Error handling for file I/O operations  
✅ Support for command-line arguments and user input  
✅ Both Java and JavaScript implementations side-by-side
