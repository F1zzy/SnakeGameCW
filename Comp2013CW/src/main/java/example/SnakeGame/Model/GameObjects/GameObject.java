package example.SnakeGame.Model.GameObjects;

import javafx.scene.image.Image;

import java.awt.*;

/**
 * Abstract class representing a generic Snake object.
 */
public abstract class GameObject {
    protected int x;
    protected int y;
    protected Image image;
    protected int width;
    protected int height;

    public boolean isAlive;

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
