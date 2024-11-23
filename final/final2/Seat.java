//2022427833 니스타
import java.awt.Color;

public class Seat {
    boolean reserved;
    String threadName;
    Color color;

    Seat() {
        this.reserved = false;
        this.threadName = "";
        this.color = Color.WHITE;
    }

    public void reserve(String threadName, Color color) {
        this.reserved = true;
        this.threadName = threadName;
        this.color = color;
    }
}