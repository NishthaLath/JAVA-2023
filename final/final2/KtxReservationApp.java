//2022427833 니스타
//Ques. no 2

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import javax.swing.border.Border;

public class KtxReservationApp extends JFrame {
    private final Seat[][] seats = new Seat[4][10];
    private final JLabel[][] seatLabels = new JLabel[4][10];
    private final JButton startButton = new JButton("Start");
    private final JButton replayButton = new JButton("Replay");
	private final JFrame frame;
	
    public KtxReservationApp() {
		frame = new JFrame("KTX Reservation App");
		initComponents();
	}

    private void initComponents() {
		frame.setLayout(new BorderLayout());
		JLabel header = new JLabel("KTX Seat Status", JLabel.CENTER);
		frame.add(header, BorderLayout.NORTH);
	
		JPanel seatPanel = new JPanel(new GridLayout(4, 10));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1); // Create a border
	
		for (int i = 0; i < 40; i++) {
			char row = (char) ('D' - (i / 10)); 
			int col = i % 10;
			seatLabels[i / 10][col] = new JLabel((col + 1) + "" + row, JLabel.CENTER);
			seatLabels[i / 10][col].setOpaque(true);
			seatLabels[i / 10][col].setBackground(Color.WHITE);
			seatLabels[i / 10][col].setBorder(border); // Apply the border to each label
			seatPanel.add(seatLabels[i / 10][col]);
			seats[i / 10][col] = new Seat();
		}
		frame.add(seatPanel, BorderLayout.CENTER);
	
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(startButton);
		buttonPanel.add(replayButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);
	
		startButton.addActionListener(new StartButtonListener());
		replayButton.addActionListener(new ReplayButtonListener());
	
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 5; i++) {
                new Thread(new Person("P" + (i + 1), getPersonColor(i + 1))).start();
            }
        }
    }

    private class ReplayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            replayReservations();
        }
    }

	private synchronized boolean reserveSeat(String threadName, Color color, int row, int col) {
		if (!seats[row][col].reserved) {
				seats[row][col].reserved = true;
				seats[row][col].threadName = threadName;
				seats[row][col].color = color;
				updateSeatLabel(row, col, threadName, color);
				return true;
			}
			return false;
		}

		private synchronized void saveReservation(int row, int col, String threadName) {
		int remainingSeats = 0;
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if (!seats[i][j].reserved) {
					remainingSeats++;
				}
			}
		}
		String reservationDetails = String.format("[Reservation Success] %s: %d%s, Remained seats: %d",
												threadName, col + 1, getRowLetter(row), remainingSeats);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("reservation.out", true))) {
			bw.write(reservationDetails);
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

    private void replayReservations() {
        clearSeats();
        try (BufferedReader br = new BufferedReader(new FileReader("reservation.out"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                String threadName = parts[2];
                Color color = getPersonColor(Integer.parseInt(threadName.substring(1)));
                updateSeatLabel(row, col, threadName, color);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void clearSeats() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = new Seat();
                seatLabels[i][j].setText((j + 1) + getRowLetter(i));
                seatLabels[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private void updateSeatLabel(int row, int col, String threadName, Color color) {
        String htmlText = String.format("<html><center>%d%s<br>%s</center></html>", col + 1, getRowLetter(row), threadName);
        seatLabels[row][col].setText(htmlText);
        seatLabels[row][col].setOpaque(true);
        seatLabels[row][col].setBackground(color);
    }

    private String getRowLetter(int row) {
        return switch (row) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            default -> "";
        };
    }

    private Color getPersonColor(int personNumber) {
        return switch (personNumber) {
            case 1 -> Color.ORANGE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.CYAN;
            case 4 -> Color.MAGENTA;
            case 5 -> Color.PINK;
            default -> Color.WHITE;
        };
    }

    class Person implements Runnable {
        private final String name;
        private final Color color;
        private final Random rand = new Random();

        Person(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        @Override
        public void run() {
            while (true) {
                int row = rand.nextInt(4);
                int col = rand.nextInt(10);
                if (reserveSeat(name, color, row, col)) {
                    System.out.println("[Reservation Success] " + name + ": " + (col + 1) + getRowLetter(row));
                    saveReservation(row, col, name);
                } else {
                    System.out.println("[Reservation Fail] " + name + ": " + (col + 1) + getRowLetter(row) + " was already reserved.");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                synchronized (KtxReservationApp.this) {
                    boolean allReserved = true;
                    for (Seat[] seatRow : seats) {
                        for (Seat seat : seatRow) {
                            if (!seat.reserved) {
                                allReserved = false;
                                break;
                            }
                        }
                    }
                    if (allReserved) {
                        System.out.println("All Threads Terminated");
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new KtxReservationApp();
    }
}

