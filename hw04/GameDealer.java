//2022427833 니스타
// This class represents a dealer in a card game

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDealer {
    // The deck of cards
    private List<Card> deck;

    // Constructor for the GameDealer class
    // It initializes the deck with a standard 52-card deck
    public GameDealer() {
        String[] suits = {"♣", "♠", "♦", "♥"}; 
        //String[] suits = {"C", "S", "D", "H"};  // Use this line for non-Unicode output
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        deck = new ArrayList<>();
        for (String suit : suits) {
            for (String number : numbers) {
                deck.add(new Card(suit, number));
            }
        }
    }

    // This method shuffles the deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // This method deals the initial cards to the players
    // Each player is dealt 8 cards
    public void dealInitialCards(Player... players) {
        for (int i = 0; i < 8; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {
                    player.receiveCard(deck.remove(0));
                }
            }
        }
    }

    // This method deals cards to the players
    // Each player is dealt 2 cards
    public void deal(Player... players) {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                if (!deck.isEmpty()) {
                    player.receiveCard(deck.remove(0));
                }
            }
        }
    }

    // This method returns the size of the deck
    public int getDeckSize() {
        return deck.size();
    }

    // This method returns the deck
    public List<Card> getDeck() {
        return deck;
    }
}