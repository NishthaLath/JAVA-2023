import java.util.Random;

public class Dice {
    private String name;
    private int dice1;
    private int dice2;
    private int sum;

    public Dice(String name) {
        this.name = name;
    }

    public void rollDice() {
        Random rand = new Random();
        dice1 = rand.nextInt(6) + 1;
        dice2 = rand.nextInt(6) + 1;
        sum += dice1 + dice2;
    }

    public boolean isDouble() {
        return dice1 == dice2;
    }

    public boolean isOddDouble() {
        return isDouble() && dice1 % 2 != 0;
    }

    public boolean isEvenDouble() {
        return isDouble() && dice1 % 2 == 0;
    }

    public int getSum() {
        return sum;
    }

    public void resetSum() {
        sum = 0;
    }

    @Override
    public String toString() {
        return name + "(" + dice1 + "," + dice2 + "):" + sum;
    }
}