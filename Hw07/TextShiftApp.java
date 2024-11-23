//2022427833 니스타

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextShiftApp extends JFrame {
    private JLabel label;  // Label to display the text
    private JTextField textField;  // Text field to input the text
    private JLabel keyInfoLabel;  // Label to display the key pressed info
    private TextShiftThread textShiftThread;  // Thread to shift the text
    private int speed = 500;  // Speed of the text shift

    public TextShiftApp() {

        setTitle("LED Text Shifter");  // Set the title of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set the default close operation
        setSize(800, 400);  // Set the size of the frame
        setLayout(new BorderLayout());  // Set the layout of the frame

        textField = new JTextField("Please Type Text");  // Initialize the text field
        textField.setFont(new Font("TimesRoman", Font.PLAIN, 50));  // Set the font of the text field
        textField.addActionListener(new ActionListener() {  // Add an action listener to the text field
            
            public void actionPerformed(ActionEvent e) {
                label.setText(textField.getText());  // Set the text of the label to the text of the text field
                label.setFocusable(true);  // Make the label focusable
                label.requestFocus();  // Request focus for the label
                keyInfoLabel.setText("VK_Enter Pressed");  // Update the key info label when Enter is pressed
            }

        });

        add(textField, BorderLayout.NORTH);  // Add the text field to the north of the frame

        label = new JLabel("Please Type Text", JLabel.CENTER);  // Initialize the label
        label.setFont(new Font("TimesRoman", Font.PLAIN, 50));  // Set the font of the label
        label.setForeground(Color.BLACK);  // Set the foreground color of the label
        label.setOpaque(true);  // Make the label opaque
        label.setBackground(Color.WHITE);  // Set the background color of the label
        add(label, BorderLayout.CENTER);  // Add the label to the center of the frame

        textShiftThread = new TextShiftThread(label);  // Initialize the text shift thread
        textShiftThread.start();  // Start the text shift thread

        label.addKeyListener(new MyKeyListener());  // Add a key listener to the label

        keyInfoLabel = new JLabel("Key Pressed: ", JLabel.CENTER);  // Initialize the key info label
        keyInfoLabel.setFont(new Font("TimesRoman", Font.PLAIN, 30));  // Set the font of the key info label
        add(keyInfoLabel, BorderLayout.SOUTH);  // Add the key info label to the south of the frame

        setVisible(true);  // Make the frame visible
    }

    private class MyKeyListener extends KeyAdapter {  // Key listener class
        public void keyPressed(KeyEvent e) {  // Method to handle key pressed event
            String keyText = KeyEvent.getKeyText(e.getKeyCode());  // Get the text of the key pressed
            keyInfoLabel.setText("Key Event: " + keyText + " Pressed");  // Update the key info label
            switch (e.getKeyCode()) {  // Switch on the key code
                case KeyEvent.VK_UP:  // If the up arrow key is pressed
                    speed = Math.max(speed - 50, 50);  // Decrease the speed
                    textShiftThread.setSpeed(speed);  // Set the new speed
                    break;
                case KeyEvent.VK_DOWN:  // If the down arrow key is pressed
                    speed = Math.min(speed + 50, 2000);  // Increase the speed
                    textShiftThread.setSpeed(speed);  // Set the new speed
                    break;
                case KeyEvent.VK_R:  // If the R key is pressed
                    label.setForeground(Color.RED);  // Set the foreground color of the label to red
                    break;
                case KeyEvent.VK_G:  // If the G key is pressed
                    label.setForeground(Color.GREEN);  // Set the foreground color of the label to green
                    break;
                case KeyEvent.VK_B:  // If the B key is pressed
                    label.setForeground(Color.BLUE);  // Set the foreground color of the label to blue
                    break;
                case KeyEvent.VK_L:  // If the L key is pressed
                    label.setForeground(Color.BLACK);  // Set the foreground color of the label to black
                    break;
                case KeyEvent.VK_X:  // If the X key is pressed
                    textShiftThread.interrupt();  // Interrupt the text shift thread
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new TextShiftApp();  // Create a new instance of TextShiftApp
    }
}