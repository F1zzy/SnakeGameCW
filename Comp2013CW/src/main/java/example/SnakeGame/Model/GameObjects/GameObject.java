package example.SnakeGame.Model.GameObjects;

import javafx.scene.image.Image;

import java.awt.*;

/**
 * Abstract class representing a generic game object.
 * This class serves as the base for specific game objects like the Snake and Food.
 */
public abstract class GameObject {
    protected int x;        // X-coordinate of the game object.
    protected int y;        // Y-coordinate of the game object.
    protected Image image;  // Image representing the game object.
    protected int width;    // Width of the game object.
    protected int height;   // Height of the game object.

    public boolean isAlive; // Flag indicating whether the game object


    /**
     * Gets the bounding rectangle of the game object.
     *
     * @return The bounding rectangle.
     */
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    //Getter and Setters

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    public  void setY(int y){
        this.y = y;
    }


    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Image getImage(){return image;}

    public void setAlive(boolean isVisisible ){ isAlive = isVisisible;}


}
