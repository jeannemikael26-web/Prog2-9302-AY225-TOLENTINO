import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MP04_CountValidRows {

    static List<String[]> readCsv(String path) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean headerFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] values = splitCsvLine(line);
                if (values.length == 0) {
                    continue;
                }
                if (!headerFound) {
                    if (values[0].equalsIgnoreCase("Candidate")) {
                        rows.add(values);
                        headerFound = true;
                    }
                    continue;
                }
                rows.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }

        if (!rows.isEmpty() && rows.get(0).length == 0) {
            return null;
        }
        return rows;
    }

    static String[] splitCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                fields.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        fields.add(current.toString().trim());
        while (!fields.isEmpty() && fields.get(fields.size() - 1).isEmpty()) {
            fields.remove(fields.size() - 1);
        }
        return fields.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MP04 - Count valid dataset rows");

        String path = args.length > 0 ? args[0].trim() : "";
        if (path.isEmpty()) {
            System.out.print("Enter the CSV dataset file path: ");
            path = scanner.nextLine().trim();
        }

        List<String[]> rows = readCsv(path);
        if (rows == null || rows.size() <= 1) {
            System.out.println("No valid rows were loaded from the file.");
            scanner.close();
            return;
        }

        String[] header = rows.get(0);
        int expectedFields = header.length;
        int validCount = 0;
        int invalidCount = 0;

        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            if (row.length == expectedFields) {
                validCount++;
            } else {
                invalidCount++;
            }
        }

        System.out.println("\nTotal records in dataset: " + (rows.size() - 1));
        System.out.println("Valid rows: " + validCount);
        System.out.println("Invalid rows: " + invalidCount);
        scanner.close();
    }
}
