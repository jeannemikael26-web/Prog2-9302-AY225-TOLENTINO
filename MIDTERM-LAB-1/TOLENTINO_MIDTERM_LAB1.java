import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TOLENTINO_MIDTERM_LAB1 {

    public static String getValidFilePath(Scanner scanner) {
        while (true) {
            System.out.print("\nEnter dataset file path: ");
            String path = scanner.nextLine().trim();

            if (path.startsWith("\"") && path.endsWith("\"")) {
                path = path.substring(1, path.length() - 1);
            }

            File file = new File(path);

            if (!file.exists()) {
                System.out.println("[ERROR] File not found. Please check the path and try again.");
                continue;
            }
            if (!file.canRead()) {
                System.out.println("[ERROR] File cannot be read. Check file permissions.");
                continue;
            }
            if (!path.toLowerCase().endsWith(".csv")) {
                System.out.println("[ERROR] File is not a CSV file. Please provide a .csv file.");
                continue;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String header = br.readLine();
                if (header == null || !header.contains(",")) {
                    System.out.println("[ERROR] File does not appear to be a valid CSV format.");
                    continue;
                }
                System.out.println("[OK] File validated successfully!");
                return path;
            } catch (IOException e) {
                System.out.println("[ERROR] Could not read file: " + e.getMessage());
            }
        }
    }

    public static int findColumn(String[] headers, String... candidates) {
        for (String candidate : candidates) {
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().toLowerCase().equals(candidate.toLowerCase())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static List<DataRecord> loadData(String filePath) throws IOException {
        Map<String, Double> salesMap = new HashMap<>();
        int titleIdx = -1;
        int salesIdx = -1;
        int lineCount = 0;
        int errorCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) throw new IOException("CSV file is empty.");
            String[] headers = headerLine.split(",", -1);

            titleIdx = findColumn(headers, "title", "Title", "game", "Game", "name", "Name");
            salesIdx = findColumn(headers, "total_sales", "Total_Sales", "global_sales",
                                  "Global_Sales", "sales", "Sales");

            if (titleIdx == -1) throw new IOException("Could not find a title/game column in CSV.");
            if (salesIdx == -1) throw new IOException("Could not find a sales column in CSV.");

            System.out.println("[INFO] Title column : " + headers[titleIdx].trim());
            System.out.println("[INFO] Sales column : " + headers[salesIdx].trim());

            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                try {
                    String[] cols = line.split(",", -1);
                    if (cols.length <= Math.max(titleIdx, salesIdx)) continue;

                    String title = cols[titleIdx].trim().replace("\"", "");
                    String salesStr = cols[salesIdx].trim().replace("\"", "");

                    if (title.isEmpty() || salesStr.isEmpty()) continue;

                    double sales = Double.parseDouble(salesStr);
                    salesMap.put(title, salesMap.getOrDefault(title, 0.0) + sales);

                } catch (NumberFormatException e) {
                    errorCount++;
                }
            }
        }

        System.out.println("[INFO] Rows processed : " + lineCount);
        System.out.println("[INFO] Rows skipped   : " + errorCount);

        List<DataRecord> records = new ArrayList<>();
        for (Map.Entry<String, Double> entry : salesMap.entrySet()) {
            records.add(new DataRecord(entry.getKey(), entry.getValue()));
        }
        records.sort((a, b) -> Double.compare(b.getTotalSales(), a.getTotalSales()));
        return records;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================================");
        System.out.println("   MIDTERM LAB 1 - Detect Low Performing Products");
        System.out.println("   Student: TOLENTINO | Prog2-9302");
        System.out.println("   University of Perpetual Help - Molino Campus");
        System.out.println("============================================================");

        String filePath = getValidFilePath(scanner);

        try {
            System.out.println("\n[INFO] Loading dataset...");
            List<DataRecord> records = loadData(filePath);
            System.out.println("[INFO] Unique products found: " + records.size());

            if (records.isEmpty()) {
                System.out.println("[ERROR] No valid records found in the dataset.");
                return;
            }

            double totalSalesSum = 0;
            for (DataRecord r : records) {
                totalSalesSum += r.getTotalSales();
            }
            double average = totalSalesSum / records.size();

            System.out.println("\n============================================================");
            System.out.printf("  Dataset Average Total Sales: %.2f million units%n", average);
            System.out.println("============================================================");

            List<DataRecord> lowPerformers = new ArrayList<>();
            for (DataRecord r : records) {
                if (r.getTotalSales() < average) {
                    lowPerformers.add(r);
                }
            }
            lowPerformers.sort((a, b) -> Double.compare(a.getTotalSales(), b.getTotalSales()));

            System.out.println("\n============================================================");
            System.out.println("  FLAGGED LOW PERFORMING PRODUCTS");
            System.out.printf("  (Sales below average: %.2f M)%n", average);
            System.out.println("============================================================");
            System.out.printf("%-55s %10s   %s%n", "Product Title", "Sales (M)", "Status");
            System.out.println("------------------------------------------------------------");

            for (DataRecord r : lowPerformers) {
                System.out.printf("%s   %s%n", r.toString(), "FLAGGED");
            }

            System.out.println("\n============================================================");
            System.out.println("  SUMMARY");
            System.out.println("============================================================");
            System.out.println("  Total Products Analyzed  : " + records.size());
            System.out.printf("  Average Sales            : %.2f million units%n", average);
            System.out.println("  Low Performing (Flagged) : " + lowPerformers.size());
            System.out.println("  Good Performing          : " + (records.size() - lowPerformers.size()));
            System.out.println("============================================================");
            System.out.println("\n[DONE] Inventory team can now review flagged products.\n");

        } catch (IOException e) {
            System.out.println("[ERROR] Failed to process file: " + e.getMessage());
        }

        scanner.close();
    }
}
