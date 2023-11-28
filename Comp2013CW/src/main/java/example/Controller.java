package example;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Controller {
    private Model model;

    boolean up, down, left, right = true;
    public Controller(Model model) {
        this.model = model;
    }

    public void keyPressed(KeyEvent e)
    {
        // Check The Key pressed. if not arrow keys ignore
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if (!down)
                {
                    up = true;
                    down = false;
                    left = false;
                    right = false;


                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (!up)
                {
                    up = false;
                    down = true;
                    left = false;
                    right = false;


                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!right)
                {
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!left)
                {
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = IMG_SNAKE_HEAD;
                }

            default:
                break;
        }
    }
}
