//2022427833 - 니스타
// ReservationApp.java

import java.util.Scanner;

public class ReservationApp {
    private ReservationSystem Reserve;
    private SwissAirline swissAirline;
    private TaiwanAirline taiwanAirline;
    private JejuAirline jejuAirline;
    private Scanner scanner;

    public ReservationApp(Scanner scanner) {
        this.scanner = scanner;
        Reserve = new ReservationSystem();
        swissAirline = new SwissAirline();
        taiwanAirline = new TaiwanAirline();
        jejuAirline = new JejuAirline();
    }

    public void run() {
        while (true) {
                try{
                    Reserve.displayMenu();    // Display method to show Main menu
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
            
                    if (choice == 0) {
                        System.out.println("Program Ends here,Thankyou!");
                        break;
                    } else if (choice == 1 || choice == 2 || choice == 3) {
                        ReservationSystem selectedAirline;
                        if (choice == 1) {
                            selectedAirline = swissAirline;
                        } else if (choice == 2) {
                            selectedAirline = taiwanAirline;
                        } else {
                            selectedAirline = jejuAirline;
                        }
            
                        while (true) {
                            selectedAirline.displayMenu();    // Display method to show Airline menu
                            int subChoice = scanner.nextInt();
                            scanner.nextLine(); // consume newline
            
                            if (subChoice == 1) {
                                System.out.print("Seat Number: ");
                                String seatNumber = scanner.nextLine();
                                selectedAirline.reserveSeat(seatNumber);
                            } else if (subChoice == 2) {
                                System.out.print("Seat Number: ");
                                String seatNumber = scanner.nextLine();
                                selectedAirline.cancelSeat(seatNumber);
                            } else if (subChoice == 3) {
                                selectedAirline.displaySeat(selectedAirline.getClass().getSimpleName());
                            } else if (subChoice == 4) {
                                break;
                            } else {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else if (choice == 4) {
                        swissAirline.displaySeat("SwissAirline");
                        taiwanAirline.displaySeat("TaiwanAirline");
                        jejuAirline.displaySeat("JejuAirline");
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }catch(Exception e){
                    System.out.println("Invalid choice. Please try again.");
                    scanner.nextLine();
                }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationApp app = new ReservationApp(scanner);
        app.run();
    }
}
