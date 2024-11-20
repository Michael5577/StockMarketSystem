import java.util.ArrayList;
import java.util.List;

public class StockInventory {
    private List<Stock> stocks;

    public StockInventory() {
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void removeStock(String tickerSymbol) {
        stocks.removeIf(stock -> stock.getTickerSymbol().equalsIgnoreCase(tickerSymbol));
    }

    public Stock searchByTicker(String tickerSymbol) {
        return stocks.stream()
                .filter(stock -> stock.getTickerSymbol().equalsIgnoreCase(tickerSymbol))
                .findFirst()
                .orElse(null);
    }

    public List<Stock> searchByName(String stockName) {
        List<Stock> result = new ArrayList<>();
        for (Stock stock : stocks) {
            if (stock.getStockName().toLowerCase().contains(stockName.toLowerCase())) {
                result.add(stock);
            }
        }
        return result;
    }

    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks);
    }
}