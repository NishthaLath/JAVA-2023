//2022427833 니스타
// This class represents a playing card

public class Card {
    // The suit of the card (e.g., hearts, diamonds, clubs, spades)
    private String suit;
    // The number or face of the card represented as a string (e.g., "2", "3", ..., "10", "J", "Q", "K", "A")
    private String numString;

    // Constructor for the Card class
    // It initializes the suit and number of the card
    public Card(String suit, String numString) {
        this.suit = suit;
        this.numString = numString;
    }

    // Getter for the number of the card
    // It returns the number of the card as a string
    public String getNumString() {
        return numString;
    }

    // Getter for the suit of the card
    // It returns the suit of the card as a string
    public String getSuit() {
        return suit;
    }

    // This method returns a string representation of the card
    // The string representation is in the format "(<suit> <number>)"
    @Override
    public String toString() {
        return "(" + suit + " " + numString + ")";
    }
}