package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;


public class View extends JPanel implements Observer {
    private Model model;
    private Controller controller;
    JPanel panel;

    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = ImageUtil.images.get("Fail-Scene");

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;


    @Override
    public void update(Observable o, Object arg) {

        panel.repaint();
    }

    public View(Model model , Controller controller) {
        this.model = model;
        this.controller = controller;
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
                draw(g);
            }
        };

        panel.setFocusable(true);
        panel.addKeyListener(this.controller);

        // Set up the frame
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Set up the panel



        // Add the panel to the frame
        frame.add(this.panel);

        // Add a window listener to handle closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);

        model.addObserver(this);
    }

    public void draw(Graphics g) {

        ModelSnake snake = model.getSnake();
        Food food = model.getFood();

        // Draw the snake and food based on the game state
        if (!model.EndGame) {
            food.draw(g);
            snake.draw(g);
            snake.drawBody(g);

        } else {
            // Handle end of the game
            g.drawImage(fail, 0, 0, FRAME_WIDTH,FRAME_HEIGHT, null);
        }


        // Draw score
        drawScore(g);
    }


    public void drawScore(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.setColor(Color.MAGENTA);
        g.drawString("SCORE : " + model.getSnake().score, 20, 40);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }


}
