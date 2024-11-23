//2022427833 니스타

// Importing JLabel from javax.swing package
import javax.swing.JLabel;

// TextShiftThread class that extends Thread class
public class TextShiftThread extends Thread {
    private JLabel label;  // JLabel instance variable
    private int speed;  // Speed of the text shift

    // Constructor of TextShiftThread class
    public TextShiftThread(JLabel label) {
        this.label = label;  // Assigning label to the instance variable
        this.speed = 500;  // Setting default speed
    }

    // Method to set the speed of the text shift
    public void setSpeed(int speed) {
        this.speed = speed;  // Assigning speed to the instance variable
    }

    // Overriding run method of Thread class
    public void run() {
        // Infinite loop until the thread is interrupted
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String text = label.getText();  // Getting the text from the label
                // If the length of the text is more than 1
                if (text.length() > 1) {
                    // Shifting the text to the left
                    text = text.substring(1) + text.charAt(0);
                    label.setText(text);  // Setting the shifted text to the label
                }
                Thread.sleep(speed);  // Making the thread sleep for the specified speed
            } catch (InterruptedException e) {
                // If the thread is interrupted, break the loop
                break;
            }
        }
    }
}