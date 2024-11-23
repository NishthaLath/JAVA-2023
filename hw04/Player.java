//2022427833 니스타
// This class represents a player in a card game

// Importing the necessary classes
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    // List of cards that have been matched and are open on the table
    private List<Card> openCardList;
    // List of cards that the player is currently holding
    private List<Card> holdingCardList;
    // List of cards that the player received in the last round
    private List<Card> lastReceivedCards;
    // List of cards that the player matched in the last round
    private List<Card> lastMatchedCards;

    // Constructor for the Player class
    // It initializes the lists
    public Player() {
        openCardList = new ArrayList<>();
        holdingCardList = new ArrayList<>();
        lastReceivedCards = new ArrayList<>();
        lastMatchedCards = new ArrayList<>();
    }

    // This method is called when the player receives a card
    public void receiveCard(Card card) {
        holdingCardList.add(card);
        lastReceivedCards.add(card);
    }

    // This method checks for matches in the player's hand and moves matched cards to the open card list
    public void checkAndMoveCards() {
        Map<String, List<Card>> cardMap = new HashMap<>();
        List<Card> toRemove = new ArrayList<>();

        // Group cards by their number
        for (Card card : holdingCardList) {
            cardMap.putIfAbsent(card.getNumString(), new ArrayList<>());
            cardMap.get(card.getNumString()).add(card);
        }

        // For each group, move pairs of cards to the open card list
        for (List<Card> cardList : cardMap.values()) {
            while (cardList.size() >= 2) {
                Card card1 = cardList.remove(0);
                Card card2 = cardList.remove(0);
                toRemove.add(card1);
                toRemove.add(card2);
            }
        }

        // Remove matched cards from the holding card list and add them to the open card list
        holdingCardList.removeAll(toRemove);
        openCardList.addAll(toRemove);
        lastMatchedCards.clear();
        lastMatchedCards.addAll(toRemove);
        lastReceivedCards.clear();  // Clear last received cards after moving matched cards
    }

    // Getter for the last received cards
    public List<Card> getLastReceivedCards() {
        return lastReceivedCards;
    }

    // Getter for the last matched cards
    public List<Card> getLastMatchedCards() {
        return lastMatchedCards;
    }

    // This method prints the initial state of the player's hand
    public void printInitialCards() {
        System.out.println("Matched Cards: 0");
        System.out.println("Cards held: " + holdingCardList.size());
        for (Card card : holdingCardList) {
            System.out.print(card + " ");
        }
        System.out.println("\n---------------------------------------------------------------------------------");
    }

    // This method prints the current state of the player's hand and open cards
    public void printCards() {
        System.out.println("Matched Cards: " + openCardList.size());  // Individual cards, not pairs
        for (Card card : openCardList) {
            System.out.print(card + " ");
        }
        System.out.println();
        System.out.println("Cards held: " + holdingCardList.size());
        for (Card card : holdingCardList) {
            System.out.print(card + " ");
        }
        System.out.println("\n---------------------------------------------------------------------------------");
    }

    // Getter for the count of holding cards
    public int getHoldingCardCount() {
        return holdingCardList.size();
    }

    // Getter for the count of open cards
    public int getOpenCardCount() {
        return openCardList.size();  // Individual cards, not pairs
    }

    // Getter for the holding cards
    public List<Card> getHoldingCards() {
        return holdingCardList;
    }
}