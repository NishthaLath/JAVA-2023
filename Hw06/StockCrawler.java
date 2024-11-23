//2022427833 니스타

package callbackInterface;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StockCrawler {
    private ConcurrentHashMap<String, StockData> stockMap = new ConcurrentHashMap<>();
    private String url = "https://finance.naver.com/sise/sise_market_sum.naver";
    private HttpGetThread httpGetThread;
    private HttpGetCallbackInterface httpCallback;

    public StockCrawler() {
        httpGetThread = new HttpGetThread(url);

        httpCallback = new HttpGetCallbackInterface() {
            @Override
            public void updateProgress(String line) {
                parseStockData(line);
            }

            @Override
            public void updateDate(String date) {
                System.out.println("Latest update date: " + date);
            }
        };
        httpGetThread.setCallback(httpCallback);
        httpGetThread.start();
    }

    public void stopCrawling() {
        httpGetThread.stopRunning();
    }

    private void parseStockData(String line) {
        // "onMouseOver" 검색 및 20라인 읽기
        if (line.contains("onMouseOver")) {
            StringBuilder lines = new StringBuilder(line);
            for (int i = 0; i < 20; i++) {
                try {
                    line = httpGetThread.readNextLine();
                    if (line == null) break; // Check for end of lines
                    lines.append(line.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String source = lines.toString();
            Pattern pattern = Pattern.compile(
                "<td class=\"no\">(\\d+)</td>\\s+" +
                "<td><a href=\"/item/main.naver\\?code=\\d+\" class=\"tltle\">(.*?)</a></td>\\s+" +
                "<td class=\"number\">([\\d,]+)</td>\\s+" +
                "(<td class=\"number\">\\s*<em class=\".*\"><span class=\"blind\">(.*?)</span></em><span class=\"tah p11 .*\">\\s*([\\d,]+)\\s*</span>\\s*</td>\\s+)?" +
                "<td class=\"number\">\\s*<span class=\"tah p11 .*\">\\s*(-?\\d+\\.\\d+)%\\s*</span>\\s*</td>\\s+" +
                "<td class=\"number\">([\\d,]+)</td>\\s+" +
                "<td class=\"number\">([\\d,]+)</td>"
            );

            Matcher matcher = pattern.matcher(source);
            while (matcher.find()) {
                try {
                    int index = Integer.parseInt(matcher.group(1));
                    String stockName = matcher.group(2);
                    int currentPrice = Integer.parseInt(matcher.group(3).replace(",", ""));
                    String strStockChange = matcher.group(5) == null ? "보합" : matcher.group(5);
                    int intStockChange = matcher.group(6) == null ? 0 : Integer.parseInt(matcher.group(6).replace(",", ""));
                    double changeRate = Double.parseDouble(matcher.group(7));
                    int faceValue = Integer.parseInt(matcher.group(8).replace(",", ""));
                    int capitalization = Integer.parseInt(matcher.group(9).replace(",", ""));

                    if ("하락".equals(strStockChange)) {
                        intStockChange = -intStockChange;
                    }

                    StockData stock = new StockData(index, stockName, currentPrice, strStockChange, intStockChange, changeRate, faceValue, capitalization);
                    stockMap.put(stockName, stock);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ConcurrentHashMap<String, StockData> getStockMap() {
        return stockMap;
    }
}
