public class Game {
    private Dice player1;
    private Dice player2;

    public Game(Dice player1, Dice player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        while (player1.getSum() < 50 && player2.getSum() < 50) {
            rollDiceAndCalculateScore(player1, "Alice");
            rollDiceAndCalculateScore(player2, "Bob");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Alice: " + player1.getSum() + " / Bob: " + player2.getSum() + ": --> " + (player1.getSum() > player2.getSum() ? "Alice Win" : "Bob Win"));
        System.out.println("-------------------------------------------------------");
    }

    private void rollDiceAndCalculateScore(Dice player, String playerName) {
        player.rollDice();
        if(playerName.equals("Alice")){
            System.out.print("[" + playerName + "] " + player);
            if (player.isOddDouble()) {
                System.out.println(" Roll Again!");
                player.rollDice();
                System.out.print("[" + playerName + "] (" + player + ")");
            } else if (player.isEvenDouble()) {
                System.out.println(" OOPS!");
                player.resetSum();
            }
        }
        else{
            System.err.printf("\t\t\t");
            System.out.print(String.format("[" + playerName + "] " + player));
            if (player.isOddDouble()) {
                System.out.println(" Roll Again!");
                player.rollDice();
                System.err.printf("\t\t\t");
                System.out.print("[" + playerName + "] (" + player + ")");
            } else if (player.isEvenDouble()) {
                System.out.println(" OOPS!");
                player.resetSum();
            }
        }
        System.out.println();
    }
}