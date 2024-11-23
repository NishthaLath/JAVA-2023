//2022427833 - 니스타
// JejuAirline.java

public class JejuAirline extends ReservationSystem {
    private int totalSeats;
    private int reservedSeats;

    public JejuAirline() {
        makeSeats(10, 4); // 4 rows (D, C, B, A) and 10 columns (1, 2, 3,...10)
        totalSeats = 4 * 10;
        reservedSeats = 0;
        partition = new int[]{2,4};
    }

    @Override
    public boolean reserveSeat(String seatName) {
        boolean result = super.reserveSeat(seatName);
        if (result) {
            reservedSeats++;
            displaySeat("JejuAirline");
        }
        return result;
    }

    @Override
    public boolean cancelSeat(String seatName) {
        boolean result = super.cancelSeat(seatName);
        if (result) {
            reservedSeats--;
            displaySeat("JejuAirline");
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
        System.out.println("Jeju Airlines:");
        System.out.println("1. Reservation");
        System.out.println("2. Cancellation");
        System.out.println("3. Check reservation status");
        System.out.println("4. Return to main menu");
        System.out.println("-------------------------");
        System.out.print("Please select a menu: ");
    }
}