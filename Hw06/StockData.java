//2022427833 니스타
package callbackInterface;
import java.text.DecimalFormat;

public class StockData {
    private int index;
    private String stockName;
    private int currentPrice;
    private String strStockChange;
    private int intStockChange;
    private double changeRate;
    private int faceValue;
    private int capitalization;

    private static final DecimalFormat df = new DecimalFormat("#,###");

    public StockData(int index, String stockName, int currentPrice, String strStockChange, int intStockChange, double changeRate, int faceValue, int capitalization) {
        this.index = index;
        this.stockName = stockName;
        this.currentPrice = currentPrice;
        this.strStockChange = strStockChange;
        this.intStockChange = intStockChange;
        this.changeRate = changeRate;
        this.faceValue = faceValue;
        this.capitalization = capitalization;
    }

    public int getIndex() {
        return index;
    }

    public String getStockName() {
        return stockName;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public String getStrStockChange() {
        return strStockChange;
    }

    public int getIntStockChange() {
        return intStockChange;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public int getCapitalization() {
        return capitalization;
    }

    @Override
    public String toString() {
        return String.format("[%-2d] %-20s %10s %10s %10d %10.2f%% %10s %10s",
                index, stockName, format(currentPrice), strStockChange, intStockChange, changeRate, format(faceValue), format(capitalization));
    }

    private String format(int number) {
        return df.format(number);
    }
}
