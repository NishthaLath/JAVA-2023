//2022427833 - 니스타
// ReservationSystem.java

import hw03_airline.ReservationInterface;

class ReservationSystem implements ReservationInterface {
    private boolean[][] seats;
    protected int[] partition = {3,6};

    @Override
    public void makeSeats(int row, int col) {
        seats = new boolean[row][col];
    }

    @Override
    public boolean reserveSeat(String seatName) {
        // Check if seatName is a single string without spaces
        if (seatName.split("\\s").length > 1) {
            System.out.println("Invalid seat name. Seat names must not contain spaces.");
            return false;
        }

        // Check if the first character of seatName is a number
        char ch = seatName.charAt(0);
        if (!Character.isDigit(ch)) {
            System.out.println(seatName + ": is not a number.");
            return false;
        }

        int[] seatArray = new int[2];
        if (!getSeatIndex(seatName, seatArray)) {
            System.out.println("[Reservation failed] Seat number: " + seatName);
            return false;
        }

        int row = seatArray[0];
        int col = seatArray[1];

        if (seats[row][col]) {
            System.out.println("[Reservation failed] Seat number: Seat already reserved");
            return false;
        }

        seats[row][col] = true;
        System.out.println("[Reservation Success] Seat Number: " + seatName);
        return true;
    }

    @Override
    public boolean cancelSeat(String seatName) {
        // Check if seatName is a single string without spaces
        if (seatName.split("\\s").length > 1) {
            System.out.println("Invalid seat name. Seat names must not contain spaces.");
            return false;
        }

        // Check if the first character of seatName is a number
        char ch = seatName.charAt(0);
        if (!Character.isDigit(ch)) {
            System.out.println(seatName + ": is not a number.");
            return false;
        }

        int[] seatArray = new int[2];
        if (!getSeatIndex(seatName, seatArray)) {
            System.out.println("[Cancellation failed]");
            System.out.println("Seat number: " + seatName + ": Invalid seat number");
            return false;
        }

        int row = seatArray[0];
        int col = seatArray[1];

        if (!seats[row][col]) {
            System.out.println("[Cancellation failed]");
            System.out.println("Seat number:" + seatName + ": Unreserved Seat");
            return false;
        }

        seats[row][col] = false;
        System.out.println("[Cancellation successful]");
        System.out.println("Seat number: " + seatName);
        return true;
    }

    @Override
    public boolean getSeatIndex(String seatName, int[] seatArray) {
        if (seatName.length() < 2) {
            return false;
        }

        char ch = seatName.charAt(0);
        if (!Character.isDigit(ch)) {
            System.out.println(seatName + ": is not a number.");
            return false;
        }

        int row = Character.getNumericValue(ch) - 1;
        int col = seatName.charAt(1) - 'A';

        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return false;
        }

        seatArray[0] = row;
        seatArray[1] = col;
        return true;
    }

    @Override
    public void displaySeat(String airline) {
        int reservedSeats = 0;
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (seat) {
                    reservedSeats++;
                }
            }
        }
    
        System.out.println(airline + " Reservation System");
        System.out.println("[Reservation Status] Reserved Seats: " + reservedSeats + "/ Total Seats: " + (seats.length * seats[0].length));
        System.out.println("-----------------------------------------------------------");
    
        for (int i = 0; i < seats[0].length; i++) {
            boolean flag = false;
            for(int j=0;j<partition.length;j++){
                if(i==partition[j]) flag = true;
            }
            if (flag) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("Front                 Between                      Back");
                System.out.println("-----------------------------------------------------------");
            }
    
            for (int j = 0; j < seats.length; j++) {
                System.out.print("[" + (j + 1) + (char)('A' + seats[0].length - i - 1) + "]\t");
            }
            System.out.println();
    
            for (int j = 0; j < seats.length; j++) {
                System.out.print(seats[j][seats[0].length - i - 1] ? " O\t" : "  \t");
            }
            System.out.println();
        }
    
        System.out.println("-----------------------------------------------------------");
    }

    public void displayMenu(){
        System.out.println("--------------------------------------------------------");
        System.out.println("Welcome to integrated airline reservation system.");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Swiss Airline Reservation System");
        System.out.println("2. Taiwan Airline reservation system");
        System.out.println("3. Jeju Airline reservation system");
        System.out.println("4. Check overall reservation status");
        System.out.println("0. Reservation system closed");
        System.out.println("--------------------------------------------------------");
        System.out.print("Enter your choice: ");
    }
}