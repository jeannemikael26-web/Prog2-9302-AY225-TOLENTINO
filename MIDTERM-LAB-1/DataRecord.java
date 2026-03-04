public class DataRecord {
    private final String title;
    private final double totalSales;

    public DataRecord(String title, double totalSales) {
        this.title = title;
        this.totalSales = totalSales;
    }

    public String getTitle() {
        return title;
    }

    public double getTotalSales() {
        return totalSales;
    }

    @Override
    public String toString() {
        // align title to 55 characters, show sales with 2 decimal places
        return String.format("%-55s %10.2f", title, totalSales);
    }
}
