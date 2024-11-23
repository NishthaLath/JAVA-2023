//2022427833 - 니스타
// TaiwanAirline.java

public class TaiwanAirline extends ReservationSystem {
    private int totalSeats;
    private int reservedSeats;

    public TaiwanAirline() {
        makeSeats(10, 6); // 6 rows (F, E, D, C, B, A) and 10 columns (1, 2, 3,...10)
        totalSeats = 6 * 10;
        reservedSeats = 0;
        partition = new int[]{3,6};
    }

    @Override
    public boolean reserveSeat(String seatName) {
        boolean result = super.reserveSeat(seatName);
        if (result) {
            reservedSeats++;
            displaySeat("TaiwanAirline");
        }
        return result;
    }

    @Override
    public boolean cancelSeat(String seatName) {
        boolean result = super.cancelSeat(seatName);
        if (result) {
            reservedSeats--;
            displaySeat("TaiwanAirline");
        }
        return result;
    }

    @Override
    public void displaySeat(String airline) {
        super.displaySeat(airline);
        System.out.println("Reserved seats: " + reservedSeats + "/ Total seats: " + totalSeats);
    }
    
    @Override
    public void displayMenu() {
        System.out.println("-------------------------");
        System.out.println("Taiwan Airlines:");
        System.out.println("1. Reservation");
        System.out.println("2. Cancellation");
        System.out.println("3. Check reservation status");
        System.out.println("4. Return to main menu");
        System.out.println("-------------------------");
        System.out.print("Please select a menu: ");
    }
}