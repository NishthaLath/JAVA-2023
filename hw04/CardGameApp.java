//2022427833 니스타
// This class represents a card game application
import java.util.Scanner;

public class CardGameApp {
    // Minimum deck size to continue the game
    private static final int MIN_DECK_SIZE = 6;

    public static void main(String[] args) {
        // Create a dealer and players
        GameDealer dealer = new GameDealer();
        Player alice = new Player();
        Player bob = new Player();
        Player charlie = new Player();

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Initial Deal
        dealer.shuffle();  // Shuffle the deck

        System.out.println("<< Create and Shuffle Cards >>");
        // Print the shuffled deck
        printDeck(dealer);

        // Deal initial cards to the players
        dealer.dealInitialCards(alice, bob, charlie);

        // Print initial cards for each player
        System.out.printf("\n<< The first card the dealer dealt >>\n");
        printInitialCards(alice, "Alice");
        printInitialCards(bob, "Bob");
        printInitialCards(charlie, "Charlie");
        System.out.println();

        System.out.println("<< Step 1 >> Number of cards the Dealer has: " + dealer.getDeckSize());
        System.out.printf("- Reveal cards with the same number:\n\n");
        // Process each player's turn
        processPlayerTurn(alice, "Alice");
        processPlayerTurn(bob, "Bob");
        processPlayerTurn(charlie, "Charlie");

        int step = 2;
        // Continue the game until the deck size is less than the minimum or any player has no cards left
        while (dealer.getDeckSize() >= MIN_DECK_SIZE && alice.getHoldingCardCount() > 0 && bob.getHoldingCardCount() > 0 && charlie.getHoldingCardCount() > 0) {
            System.out.printf("\nPress Enter to proceed to the next round...\n");
            scanner.nextLine();  // Wait for user input
            dealer.deal(alice, bob, charlie);  // Deal cards to the players

            System.out.println("<< Step " + step + " >> Number of cards the Dealer has: " + dealer.getDeckSize());
            System.out.printf("- Receive two new cards each from the dealer:\n\n");
            // Display each player's cards
            displayPlayerCards(alice, "Alice");
            displayPlayerCards(bob, "Bob");
            displayPlayerCards(charlie, "Charlie");
            
            System.out.printf("- Reveal cards with the same number:\n\n");
            // Process each player's turn
            processPlayerTurn(alice, "Alice");
            processPlayerTurn(bob, "Bob");
            processPlayerTurn(charlie, "Charlie");

            step++;  // Increment the step counter
        }

        // End of the game
        System.out.println("Game Over!");
        // Print the number of open cards for each player
        System.out.println("Alice: " + alice.getOpenCardCount());
        System.out.println("Bob: " + bob.getOpenCardCount());
        System.out.println("Charlie: " + charlie.getOpenCardCount());

        scanner.close();  // Close the scanner
    }

    // This method prints the deck
    private static void printDeck(GameDealer dealer) {
        dealer.getDeck().forEach(card -> System.out.print(card + " "));
        System.out.println();
    }

    // This method displays a player's cards
    private static void displayPlayerCards(Player player, String playerName) {
        System.out.println("[" + playerName + "]");
        System.out.println("Matched Cards: " + (player.getOpenCardCount() / 2) * 2);
        player.getLastMatchedCards().forEach(card -> System.out.print(card + " "));
        System.out.println("\nCards held: " + player.getHoldingCardCount());
        player.getHoldingCards().forEach(card -> System.out.print(card + " "));
        System.out.println("\n---------------------------------------------------------------------------------");
    }

    // This method prints the initial cards of a player
    private static void printInitialCards(Player player, String playerName) {
        System.out.println("[" + playerName + "]");
        player.printInitialCards();
    }

    // This method processes a player's turn
    private static void processPlayerTurn(Player player, String playerName) {
        System.out.println("[" + playerName + "]");
        player.checkAndMoveCards();  // Check for matches and move cards
        player.printCards();  // Print the player's cards
    }
}