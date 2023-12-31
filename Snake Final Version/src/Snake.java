import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;

public class Snake {

    // LinkedList that represents the snake
    LinkedList<Body> s = new LinkedList<>();

    // Head of the snake and a variable for the new body part that gets added on
    private Body head;
    private Body extra;

    // An iterator that allows me to iterate through the s LinkedList backwards
    Iterator<Body> backwards;

    // Default snake starts with 3 body parts
    Snake() {
        head = new Body(102, 242, null);
        Body a = new Body(head.getX() - 20, head.getY(), head);
        Body b = new Body(head.getX() - 40, head.getY(), a);
        s.add(head);
        s.add(a);
        s.add(b);
        backwards = s.descendingIterator();
    }

    // Paints all body parts of the snake
    public void paintSnake(Graphics g) {
        for (Body x : s) {
            x.paintBody(g);
        }
    }

    // Updates the snake by reverse iterating through the LinkedList and setting each
    // Body to the previous body, allowing it to move
    public void update() {
        while (backwards.hasNext()) {
            backwards.next().update();
        }
        backwards = s.descendingIterator();
    }

    // Adds a new body onto the end of the snake
    public void addBody() {
        extra = new Body(1000, 1000, backwards.next());
        s.add(extra);
        backwards = s.descendingIterator();
    }

    // returns the head
    public Body getHead() {
        return head;
    }

    // returns the snake LinkedList
    public LinkedList<Body> getSnake() {
        return s;
    }

}