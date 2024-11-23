// 2022427833 니스타
import java.util.*;

public class Location {
    String city;
    double latitude;
    double longitude;
    String country;
    long population;

    // Constructor to initialize a Location object
    public Location(String city, double latitude, double longitude, String country, long population) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.population = population;
    }

    // Method to change the population of a Location object
    public void setPopulation(long population) {
        this.population = population;
    }

    // toString method overridden for formatted output of a Location object
    @Override
    public String toString() {
        return String.format("[%-20s/%-20s] Latitude: %8.2f, Longitude: %8.2f, Population: %,15d",
                             city, country, latitude, longitude, population);
    }

    // Method to print the menu options
    public static void printMenu() {
        System.out.println("----------------------------------------");
        System.out.println("1. Display all city data");
        System.out.println("2. Display all city populations");
        System.out.println("3. Display coordinates of a specific city");
        System.out.println("4. Change the population of a specific city");
        System.out.println("5. Delete information of a specific city");
        System.out.println("6. Display populations in ascending order");
        System.out.println("7. Exit the program");
        System.out.println("----------------------------------------");
        System.out.print("=> ");
    }

    // Method to get a valid choice from the user
    public static int getValidChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            printMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 7) {
                    System.out.println("Invalid choice. Please enter a number between 1-7.");
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

    // Method to print all cities in the cityMap
    public static void printAllCities(HashMap<String, Location> cityMap) {
        System.out.println("----------------------------------------");
        System.out.println("[HashMap] Data");
        System.out.println("----------------------------------------");
        for (Location location : cityMap.values()) {
            System.out.println(location);
        }
    }

    // Method to print the population of all cities in the cityMap
    public static void printAllPopulations(HashMap<String, Location> cityMap) {
        System.out.println("----------------------------------------");
        for (Location location : cityMap.values()) {
            System.out.printf("%-20s: %,15d\n", location.city, location.population);
        }
    }

    // Method to print the coordinates of a specific city
    public static void printCityCoordinates(HashMap<String, Location> cityMap, String cityName) {
        Location location = cityMap.get(cityName);
        if (location != null) {
            System.out.println("----------------------------------------");
            System.out.printf("%s: Latitude: %.2f, Longitude: %.2f\n", cityName, location.latitude, location.longitude);
        } else {
            System.out.printf("%s is not in the HashMap.\n", cityName);
        }
    }

    // Method to change the population of a specific city
    public static void changeCityPopulation(Scanner scanner, HashMap<String, Location> cityMap, String cityName) {
        if(cityMap.containsKey(cityName)) {
            boolean validPopulation = false;
            while (!validPopulation) {
                System.out.print("Enter the new population: ");
                try {
                    long newPopulation = scanner.nextLong();
                    scanner.nextLine();
                    if (newPopulation < 0) {
                        System.out.println("Invalid input. Population cannot be negative.");
                    } else {
                        validPopulation = true;
                        Location location = cityMap.get(cityName);
                        location.setPopulation(newPopulation);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // discard the invalid input
                }
            }
        } else {
            System.out.printf("%s is not in the HashMap.\n", cityName);
        }
    }

    // Method to delete a specific city from the cityMap
    public static void deleteCity(HashMap<String, Location> cityMap, String cityName) {
        if (cityMap.remove(cityName) != null) {
            System.out.printf("Deleted data for %s.\n", cityName);
        } else {
            System.out.printf("%s is not in the HashMap.\n", cityName);
        }
    }

    // Method to print the population of all cities in ascending order
    public static void printPopulationAscending(HashMap<String, Location> cityMap) {
        List<Location> locations = new ArrayList<>(cityMap.values());
        locations.sort(Comparator.comparingLong(loc -> loc.population));
        System.out.println("----------------------------------------");
        for (Location location : locations) {
            System.out.printf("%-20s: %,15d\n", location.city, location.population);
        }
    }
}