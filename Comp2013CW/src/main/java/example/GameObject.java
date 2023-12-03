package example;

import javafx.scene.image.Image;

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

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
