package example;

import java.awt.*;

/**
 * Abstract class representing a generic Snake object.
 */
public abstract class GameObject {
    int x;
    int y;
    Image image;
    int width;
    int height;

    public boolean isAlive;

    /**
     * Abstract method to draw the SnakeObject on the screen.
     */


    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
