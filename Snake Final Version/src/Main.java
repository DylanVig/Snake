import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main (String args[]) {
        createGui();
    }

    private static void createGui() {

        // Creates frame for the game
        MyFrame frame = new MyFrame();

        GameScreen gScreen = new GameScreen();
        frame.add(gScreen);

        JButton startButton = new JButton("Start");
        Font fButton = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        startButton.setFont(fButton);
        frame.add(startButton, BorderLayout.PAGE_END);

        // Starts Game, makes the game start and the start button invisible
        startButton.addActionListener(e -> {
            gScreen.startGame();
            startButton.setVisible(false);
            System.out.println("Score : " + gScreen.getCount());
        });

        frame.setVisible(true);
    }

}
