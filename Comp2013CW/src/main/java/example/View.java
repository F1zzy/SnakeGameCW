package example;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;


public class View extends JPanel implements Observer {
    private Model model;
    JPanel panel;

    public Image background = ImageUtil.images.get("UI-background");
    public Image fail = ImageUtil.images.get("Fail-Scene");

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;


    @Override
    public void update(Observable o, Object arg) {

        panel.repaint();
    }

    public View(Model model) {
        this.model = model;
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
                //g.drawImage(fail, 0, 0, getWidth(), getHeight(), null);
                //g.drawImage(ImageUtil.images.get("Fail-Scene"), 0, 0, getWidth(), getHeight(), null);
                draw(g);
            }
        };
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
