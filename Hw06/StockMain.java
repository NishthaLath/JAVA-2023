//2022427833 니스타

package callbackInterface;

import java.util.Scanner;

public class StockMain {
    public static void main(String[] args) {
        StockManager service = new StockManager();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            choice = service.getValidChoice(scanner);
            switch (choice) {
                case 1:
                    service.printAllStocks();
                    break;
                case 2:
                    service.searchByStockName(scanner);
                    break;
                case 3:
                    service.sortByCurrentPrice();
                    break;
                case 4:
                    service.sortByChangeRate();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-5.");
                    break;
            }
        }
        service.stopCrawling(); // Stop the crawling thread before exiting
        scanner.close();
        System.out.println("Program terminated.");
    }
}
