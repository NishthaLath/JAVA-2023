// Assignment 2: Employee management system
// 2022427833_니스타

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class EmployeeApp {
    public static void main(String[] args){
        Employee[] employee = new Employee[5];

        // Initialize the Employee objects
        for (int i = 0; i < employee.length; i++) {
            employee[i] = new Employee();
        }

        employee[0].setAge(34);
        employee[0].setName("Alan", "Cooper");
        employee[0].setPosition("Senior Engineer");
        employee[0].setSalary(6500000);

        employee[1].setAge(26);
        employee[1].setName("Brendan", "Eich");
        employee[1].setPosition("Junior Engineer");
        employee[1].setSalary(5000000);

        employee[2].setAge(38);
        employee[2].setName("Dennis", "Richie");
        employee[2].setPosition("Chief Engineer");
        employee[2].setSalary(7800000);

        employee[3].setAge(42);
        employee[3].setName("Larry", "Wall");
        employee[3].setPosition("Team Leader");
        employee[3].setSalary(8200000);

        employee[4].setAge(46);
        employee[4].setName("Richard", "Stallman");
        employee[4].setPosition("Project Manager");
        employee[4].setSalary(9000000);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayPositions(employee);
                    break;
                case 2:
                    displaySalaries(employee);
                    break;
                case 3:
                    displayAllInfo(employee);
                    break;
                case 0:
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Wrong number. Type again Please!");
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("========================================");
        System.out.println("1. Display all positions");
        System.out.println("2. Display all salaries");
        System.out.println("3. Display all information");
        System.out.println("0. Quit");
        System.out.print("-> ");
    }

    private static void displayPositions(Employee[] employee) {
        System.out.println("----------------------------------------");
        System.out.println(String.format("%-20s %-20s", "Name", "Position"));
        System.out.println("----------------------------------------");
        for (Employee employees : employee) {
            System.out.println(String.format("%-20s : %-20s", employees.getName(), employees.getPosition()));
        }
    }
    
    private static void displaySalaries(Employee[] employee) {
        System.out.println("----------------------------------------");
        System.out.println(String.format("%-20s %-20s", "Name", "Salary"));
        System.out.println("----------------------------------------");
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        for (Employee employees : employee) {
            System.out.println(String.format("%-20s : %-20s", employees.getName(), formatter.format(employees.getSalary())));
        }
    }
    
    private static void displayAllInfo(Employee[] employee) {
        System.out.println("----------------------------------------");
        System.out.println(String.format("%-20s %-5s %-20s %-10s", "Name", "Age", "Position", "Salary"));
        System.out.println("----------------------------------------");
        for (Employee employees : employee) {
            employees.printEmployee();
        }
        System.out.println("========================================");
    }
}