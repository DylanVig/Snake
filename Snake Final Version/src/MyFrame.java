import java.awt.Color;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

    // Sets up the window for the game
    MyFrame() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,510);
        this.setResizable(false);
    }

}
