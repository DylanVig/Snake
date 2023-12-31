import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;


// The main class for where the game brought together
public class GameScreen extends JPanel implements KeyListener {

    // Stores whether the game has started or not
    private boolean started;

    // Stores whether you are still playing or lost
    private boolean inGame;

    // Used to prevent movement bug (being able to go right when going left)
    private boolean moved;

    // Score
    private int count;

    // Delay in between each snake update
    private int targetTimeMillis = 100;

    // Creates a Food Box
    private Food f = new Food();

    // Creates a Snake
    private Snake s = new Snake();

    // Creates a timer used with targetTimeMillis to help create a delay with each snake update
    private Timer timer;

    // Gets the head of the snake
    private Body head = s.getHead();

    // Sets up timer and keyListener
    GameScreen() {
        timer = new Timer(targetTimeMillis, (ActionEvent e) -> timeout());
        timer.setInitialDelay(0);
        timer.setCoalesce(true);

        this.addKeyListener(this);
        this.setFocusable(true);
    }

    // updates the snake's position when the game is ongoing
    private void timeout() {
        if (inGame) {
            s.update();
            moved = true;
            repaint();
        }
    }

    // starts the game when the start button is pressed in Main
    public void startGame() {
        started = true;
        inGame = true;
        count = 0;
        timer.restart();
        timer.setDelay(targetTimeMillis);
    }

    // stops the game
    public void stopGame() {
        inGame = false;
        timer.stop();
        timer.setDelay(0);
    }

    // Draws everything on the screen, calls other paint methods
    // If the game hasn't started, paint the start screen, otherwise paint the game screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        if (!started) {
            paintStartScreen(g);
        }
        else {
            paintGameScreen(g);
        }
    }

    // Draws the SNAKE letters on the Starting Screen
    public void paintStartScreen(Graphics g) {
        // All horizontal lines
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                g.fillRect(156 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(156 + 10 * i, 200 + 10 * j, 8, 8);
                g.fillRect(156 + 10 * i, 260 + 10 * j, 8, 8);

                g.fillRect(356 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(356 + 10 * i, 200 + 10 * j, 8, 8);

                g.fillRect(556 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(556 + 10 * i, 200 + 10 * j, 8, 8);
                g.fillRect(556 + 10 * i, 260 + 10 * j, 8, 8);
            }
        }
        // All vertical lines
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 15; j++) {
                g.fillRect(256 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(316 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(356 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(416 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(456 + 10 * i, 140 + 10 * j, 8, 8);
                g.fillRect(556 + 10 * i, 140 + 10 * j, 8, 8);
            }
        }
        // Fill in the S
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g.fillRect(156 + 10 * i, 170 + 10 * j, 8, 8);
                g.fillRect(216 + 10 * i, 230 + 10 * j, 8, 8);
            }
        }
        // The middle section of N
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                g.fillRect(286 + 10 * i, 160 + 30 * i + 10 * j, 8, 8);
            }
        }
        // Middle and Right section of K
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 6; k++) {
                    g.fillRect(466 + 10 * i + 10 * k, 210 - 10 * j - 10 * k, 8, 8);
                    g.fillRect(466 + 10 * i + 10 * k, 210 + 10 * j + 10 * k, 8, 8);
                }
            }
        }
    }

    // Displays the actual game
    public void paintGameScreen(Graphics g) {
        // Displays the food and the snake
        f.paintFood(g);
        s.paintSnake(g);
        repaint();
        // If the head of the snake collides with the food, add to the score, respawn the
        // food, and add to the body of the snake
        if (head.getX() == f.getX() && head.getY() == f.getY()) {
            addCount();
            System.out.println("Score : " + count);
            f.respawn();
            s.addBody();
            repaint();
        }

        // if the snake goes out of bounds, stop the game
        if (head.getX() > 800 || head.getX() < 0 || head.getY() > 500 || head.getY() < 0) {
            stopGame();
        }

        // if any part of the snake collides with the head, stop the game
        for (Body x : s.getSnake()) {
            if (x != head) {
                if (head.getX() == x.getX() && head.getY() == x.getY()) {
                    stopGame();
                    head.paintCollision(g);
                }
            }
        }

        // if any part of the snake besides the head collides with the food, respawn it elsewhere
        for (Body x : s.getSnake()) {
            if (x != head) {
                if (f.getX() == x.getX() && f.getY() == x.getY()) {
                    f.respawn();
                    repaint();
                }
            }
        }
    }

    // returns the count variable
    public int getCount() {
        return count;
    }

    // adds to the counter
    public void addCount() {
        count++;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Handles all key presses (WASD)
    @Override
    public void keyPressed(KeyEvent e) {
        if (started) {
            if (e.getKeyChar() == 'w' && !head.getDown() && moved) {
                head.setRight(false);
                head.setLeft(false);
                head.setUp(true);
                head.setDown(false);
                moved = false;
                repaint();
            }
            else if (e.getKeyChar() == 'a'  && !head.getRight()  && moved) {
                head.setRight(false);
                head.setLeft(true);
                head.setUp(false);
                head.setDown(false);
                moved = false;
                repaint();
            }
            else if (e.getKeyChar() == 's'  && !head.getUp()  && moved) {
                head.setRight(false);
                head.setLeft(false);
                head.setUp(false);
                head.setDown(true);
                moved = false;
                repaint();
            }
            else if (e.getKeyChar() == 'd'  && !head.getLeft()  && moved) {
                head.setRight(true);
                head.setLeft(false);
                head.setUp(false);
                head.setDown(false);
                moved = false;
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
