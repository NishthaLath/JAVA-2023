//2022427833 니스타

import java.awt.*;
import javax.swing.*;

class KtxSeat {
    private int[] seats = new int[40];
    private String[] owners = new String[40];
    private JLabel[] seatLabels;

    public KtxSeat(JLabel[] seatLabels) {
        this.seatLabels = seatLabels;
        for (int i = 0; i < seats.length; i++) {
            seats[i] = 0; // 0 means seat is free
            owners[i] = "";
        }
    }

    public synchronized boolean reserveSeat(int seatNumber, String person) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println("[Reservation Failed] Invalid seat number.");
            return false;
        }
        if (seats[seatNumber] == 0 || seats[seatNumber] == 2) {
            seats[seatNumber] = 1;
            owners[seatNumber] = person;
            char row = (char) ('D' - seatNumber / 10);
            int seatNumInRow = seatNumber % 10 + 1;
            seatLabels[seatNumber].setBackground(person.startsWith("broker") ? Color.RED : Color.YELLOW);
            seatLabels[seatNumber].setText(seatNumInRow + "" + row + " (" + person + ")");
            System.out.println("[Seat Reserved] " + person + ": Seat " + (seatNumInRow + "" + row) + ", Available Seats: " + getAvailableSeats());
            return true;
        } else {
            System.out.println("[Reservation Failed] Seat: " + (seatNumber + 1) + " is already reserved.");
            return false;
        }
    }

    public synchronized void cancelSeat(int seatNumber, String person) {
        if (seatNumber < 0 || seatNumber >= seats.length) {
            System.out.println("[Cancellation Failed] Invalid seat number.");
            return;
        }
        if (seats[seatNumber] == 1 && (owners[seatNumber].equals("broker1") || owners[seatNumber].equals("broker2"))) {
            seats[seatNumber] = 2;
            owners[seatNumber] = "";
            char row = (char) ('D' - seatNumber / 10);
            int seatNumInRow = seatNumber % 10 + 1;
            seatLabels[seatNumber].setBackground(Color.GREEN);
            seatLabels[seatNumber].setText(seatNumInRow + "" + row);
            System.out.println("[Seat Canceled] " + person + ": Seat " + (seatNumInRow + "" + row) + ", Available Seats: " + getAvailableSeats());
        } else {
            System.out.println("[Cancellation Failed] Seat: " + (seatNumber + 1) + " cannot be canceled.");
        }
    }

    public int getAvailableSeats() {
        int count = 0;
        for (int seat : seats) {
            if (seat == 0 || seat == 2) count++;
        }
        return count;
    }
}