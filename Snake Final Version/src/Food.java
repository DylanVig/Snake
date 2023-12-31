import java.awt.*;
import java.awt.Graphics;
import java.util.Random;

public class Food {

    // x and y values for food
    private int x;
    private int y;

    // Records whether the head of the snake has collided with the food
    private boolean eaten;

    //
    private int score;

    // Determines if a new food has spawned
    private boolean foodSet;

    // Random number
    private Random r = new Random();

    // Set the food color to red, and then randomly spawn the food at the beginning
    // of the game and after every collision with the head of the snake
    public void paintFood(Graphics g) {
        g.setColor(Color.RED);
        if (!foodSet) {
            x = r.nextInt(39) * 20 + 2;
            y = r.nextInt(24) * 20 + 2;
            foodSet = true;
        }
        else if (eaten) {
            respawn();
        }
        g.fillRect(x, y, 16, 16);
    }

    // Respawns the food in a random location on the screen
    public void respawn () {
        x = r.nextInt(39) * 20 + 2;
        y = r.nextInt(24) * 20 + 2;
        eaten = false;
    }

    // Returns x and y
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
