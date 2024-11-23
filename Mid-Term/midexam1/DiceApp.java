public class DiceApp {
    public static void main(String[] args) {
        Dice Alice = new Dice("Alice");
        Dice Bob = new Dice("Bob");
        Game game = new Game(Alice, Bob);
        game.play();
    }
}