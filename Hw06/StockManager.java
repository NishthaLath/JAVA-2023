//2022427833 니스타

package callbackInterface;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class StockManager {
    private ConcurrentHashMap<String, StockData> stockMap = new ConcurrentHashMap<>();
    private StockCrawler crawler = new StockCrawler();

    public StockManager() {
        this.stockMap = crawler.getStockMap();
    }

    public void printMenu() {
        System.out.println("------------------------------------------------");
        System.out.println("1. Print all stocks");
        System.out.println("2. Search by stock name");
        System.out.println("3. Sort by current price (descending)");
        System.out.println("4. Sort by change rate (ascending)");
        System.out.println("5. Exit");
        System.out.println("------------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    public int getValidChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            printMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please enter a number between 1-5.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // discard the invalid input
            }
        }
        return choice;
    }

    public void printAllStocks() {
        System.out.println("------------------------------------------------");
        System.out.println("Top 10 Kospi Stock Information");
        System.out.println("Updated: " + new Date());
        System.out.println("------------------------------------------------");
        stockMap.values().forEach(stock -> {
            System.out.println("------------------------------------------------");
            System.out.println(stock);
        });
    }

    public void searchByStockName(Scanner scanner) {
        System.out.print("Enter stock name to search: ");
        String name = scanner.nextLine();
        StockData stock = stockMap.get(name);
        if (stock != null) {
            System.out.println(stock);
        } else {
            System.out.println("Stock not found.");
        }
    }

    public void sortByCurrentPrice() {
        stockMap.values().stream()
                .sorted(Comparator.comparingInt(StockData::getCurrentPrice).reversed())
                .forEach(System.out::println);
    }

    public void sortByChangeRate() {
        stockMap.values().stream()
                .sorted(Comparator.comparingDouble(StockData::getChangeRate))
                .forEach(System.out::println);
    }

    public void stopCrawling() {
        crawler.stopCrawling();
    }
}
