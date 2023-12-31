import java.awt.Graphics;
import java.awt.Color;

public class Body {

    // x and y values of the body
    private int x;
    private int y;

    // Previous body that the current body turns into after every update
    private Body prev;

    // Direction in which the head and the bodies are traveling
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;

    // Sets the x and y values for a body, as well as the previous body
    Body(int x, int y, Body prev) {
        this.x = x;
        this.y = y;
        this.prev = prev;
        right = true;
        left = false;
        up = false;
        down = false;
    }

    // Paints one body
    public void paintBody(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 16, 16);
    }

    // Paints what happens when two bodies collide (turns blue)
    public void paintCollision(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 16, 16);
    }

    // Updates the movements of the head specifically
    public void headUpdate() {
        if (right) {
            x += 20;
        }
        else if (left) {
            x -= 20;
        }
        else if (up) {
            y -= 20;
        }
        else if (down) {
            y += 20;
        }
    }

    // updates each individual body of the snake
    // if the head is updated, move it in a direction
    // if a body part is updated, set it equal to the previous body part
    public void update() {
        if (prev == null) {
            headUpdate();
        }
        else {
            x = prev.getX();
            y = prev.getY();
        }
    }

    // Returns x
    public int getX() {
        return x;
    }

    // Returns y
    public int getY() {
        return y;
    }

    // Returns each direction
    public boolean getRight() { return right; }
    public boolean getLeft() { return left; }
    public boolean getUp() { return up; }
    public boolean getDown() { return down; }

    // Sets each direction
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }

}
