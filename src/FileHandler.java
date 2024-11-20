import java.io.*;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "stocks.csv";

    public static void loadInventory(StockInventory inventory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }
                String[] parts = line.split(","); // Use comma as delimiter
                if (parts.length == 3) {
                    String tickerSymbol = parts[0].trim();
                    String stockName = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    inventory.addStock(new Stock(tickerSymbol, stockName, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    public static void saveInventory(List<Stock> stocks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("tickerSymbol,stockName,price"); // CSV header
            for (Stock stock : stocks) {
                writer.println(stock.getTickerSymbol() + "," + stock.getStockName() + "," + stock.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }
}