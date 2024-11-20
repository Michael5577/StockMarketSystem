public class Stock {
    private String tickerSymbol;
    private String stockName;
    private double price;

    public Stock(String tickerSymbol, String stockName, double price) {
        this.tickerSymbol = tickerSymbol;
        this.stockName = stockName;
        this.price = price;
    }

    // Getters and setters
    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }
    public String getStockName() { return stockName; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Ticker Symbol: " + tickerSymbol + ", Stock Name: " + stockName + ", Price: $" + price;
    }
}