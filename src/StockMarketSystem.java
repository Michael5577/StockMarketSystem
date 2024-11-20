import java.util.List;
import java.util.Scanner;

public class StockMarketSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StockInventory inventory = new StockInventory();

    public static void main(String[] args) {
        FileHandler.loadInventory(inventory);

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> searchByTicker();
                case 2 -> searchByName();
                case 3 -> addStock();
                case 4 -> removeStock();
                case 5 -> lookupPrice();
                case 6 -> {
                    FileHandler.saveInventory(inventory.getAllStocks());
                    System.out.println("Inventory saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Stock Market System ---");
        System.out.println("1. Search by Ticker Symbol");
        System.out.println("2. Search by Stock Name");
        System.out.println("3. Add a Stock");
        System.out.println("4. Remove a Stock");
        System.out.println("5. Look Up Stock Price");
        System.out.println("6. Save and Exit");
    }

    private static void searchByTicker() {
        String tickerSymbol = getStringInput("Enter Ticker Symbol: ");
        Stock stock = inventory.searchByTicker(tickerSymbol);
        if (stock != null) {
            System.out.println("Found: " + stock);
        } else {
            System.out.println("No stock found with that Ticker Symbol.");
        }
    }

    private static void searchByName() {
        String name = getStringInput("Enter Stock Name: ");
        List<Stock> stocks = inventory.searchByName(name);
        printStocks(stocks);
    }

    private static void addStock() {
        String tickerSymbol = getStringInput("Enter Ticker Symbol: ");
        String stockName = getStringInput("Enter Stock Name: ");
        double price = getDoubleInput("Enter Price: ");

        Stock newStock = new Stock(tickerSymbol, stockName, price);
        inventory.addStock(newStock);
        System.out.println("Stock added successfully.");
    }

    private static void removeStock() {
        String tickerSymbol = getStringInput("Enter Ticker Symbol to remove: ");
        inventory.removeStock(tickerSymbol);
        System.out.println("If it existed, the stock has been removed.");
    }

    private static void lookupPrice() {
        String tickerSymbol = getStringInput("Enter Ticker Symbol to look up price: ");
        Stock stock = inventory.searchByTicker(tickerSymbol);

        if (stock != null) {
            System.out.printf("The current price of %s (%s) is $%.2f%n",
                    stock.getStockName(),
                    stock.getTickerSymbol(),
                    stock.getPrice());
        } else {
            System.out.println("No stock found with that Ticker Symbol.");
        }
    }

    private static void printStocks(List<Stock> stocks) {
        if (stocks.isEmpty()) {
            System.out.println("No stocks found.");
        } else {
            for (Stock stock : stocks) {
                System.out.println(stock);
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }
    }

}