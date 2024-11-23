// 2022427833 니스타

import java.util.*;

public class LocationManagerApp {
    // HashMap to store city data
    private static HashMap<String, Location> cityMap;

    public static void main(String[] args) {
        // Initialize the HashMap
        cityMap = new HashMap<>();

        // Adding cities to the HashMap
        cityMap.put("Seoul", new Location("Seoul", 37.56, 126.99, "South Korea", 23016000));
        cityMap.put("Tokyo", new Location("Tokyo", 35.68, 139.69, "Japan", 37732000));
        cityMap.put("Beijing", new Location("Beijing", 39.90, 116.40, "China", 18522000));
        cityMap.put("London", new Location("London", 51.50, -0.12, "United Kingdom", 11262000));
        cityMap.put("Canberra", new Location("Canberra", -35.29, 149.12, "Australia", 381488));
        cityMap.put("Mexico City", new Location("Mexico City", 19.43, -99.13, "Mexico", 21804000));

        // Initialize the Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Main loop to handle user choices
        while (choice != 7) {
            // Get a valid choice from the user
            choice = Location.getValidChoice(scanner);
            
            // Switch case to handle different choices
            switch (choice) {
                case 1:
                    // Print all city data
                    Location.printAllCities(cityMap);
                    break;
                case 2:
                    // Print all city populations
                    Location.printAllPopulations(cityMap);
                    break;
                case 3:
                    // Print coordinates of a specific city
                    System.out.print("Enter the city name: ");
                    String cityName = scanner.nextLine();
                    Location.printCityCoordinates(cityMap, cityName);
                    break;
                case 4:
                    // Change the population of a specific city
                    System.out.print("Enter the city name: ");
                    cityName = scanner.nextLine();
                    Location.changeCityPopulation(scanner, cityMap, cityName);
                    break;
                case 5:
                    // Delete information of a specific city
                    System.out.print("Enter the city name: ");
                    cityName = scanner.nextLine();
                    Location.deleteCity(cityMap, cityName);
                    break;
                case 6:
                    // Display populations in ascending order
                    Location.printPopulationAscending(cityMap);
                    break;
                case 7:
                    // Exit the program
                    System.out.println("Thankyou! Exiting the program...");
                    break;
                default:
                    // Handle invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        // Close the scanner after use
        scanner.close();
    }
}