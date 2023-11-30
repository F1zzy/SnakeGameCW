package example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Controller implements KeyListener {
    private final Model model;

    boolean up, down, left, right = true;
    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        model.getSnake().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
