import java.util.List;
import java.util.Random;

public class StockSuggestion {
    private static final Random random = new Random();

    public static Stock getSuggestion(List<Stock> stocks) {
        if (stocks.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(stocks.size());
        return stocks.get(randomIndex);
    }
}