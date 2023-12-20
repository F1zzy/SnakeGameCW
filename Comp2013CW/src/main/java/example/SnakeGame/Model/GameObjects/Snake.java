package example.SnakeGame.Model.GameObjects;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * The Snake class represents the main player-controlled character in the Snake Game.
 * It manages the snake's movement, length, direction, and visibility.
 */
public class Snake extends GameObject {
    private static final int SNAKE_SPEED = 4;
    private int speed_XY;
    private int length;
    int numOfBodies;
    public int score = 0;

    //Using North(1) East(2) South(3) West(4).
    public int Direction = 2;
    public  List<Point> bodyPoints = new LinkedList<>();
    //private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");
    private static BufferedImage newImgSnakeHead;
    boolean up, down, left, right = true;
    boolean isVisible;

    /**
     * Constructs a new Snake object with an initial position.
     *
     * @param x x-coordinate of the snake.
     * @param y y-coordinate of the snake.
     */
    public Snake(int x, int y) {
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.image = ImageUtil.images.get("snake-body");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();

        this.speed_XY = SNAKE_SPEED;
        this.length = 1;
        this.isVisible = true;
        System.out.println("BP Size : " + bodyPoints.size());
        System.out.println("GET BODY POINTS: "+ getBodyPointsLength());
        this.numOfBodies = width / speed_XY;
    }
    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return length;
    }
    /**
     * Changes the length of the snake.
     *
     * @param length The new length of the snake.
     */
    public void changeLength(int length) {
        this.length = length;
    }
    /**
     * Gets the list of body points representing the snake's segments.
     *
     * @return The list of body points.
     */
    public List<Point> getBodyPoints(){
        return bodyPoints;
    }
    /**
     * Gets the number of bodies in the snake.
     *
     * @return The number of bodies.
     */
    public int getNumOfBodies(){return numOfBodies;}
    /**
     * Gets the original speed of the snake.
     *
     * @return The original speed of the snake.
     */
    public int getOriginalSpeed() {
        return SNAKE_SPEED;
    }

    /**
     * Sets the speed of the snake.
     *
     * @param boostedSpeed The new speed of the snake.
     */
    public void setSpeed(int boostedSpeed) {

        this.speed_XY = boostedSpeed;
    }
    /**
     * Sets the visibility of the snake.
     *Used for Invisibility Level State
     * @param bool The visibility status of the snake.
     */
    public void setVisible(boolean bool ){
        this.isVisible = bool;
    }
    /**
     * Gets the visibility status of the snake.
     *
     * @return The visibility status of the snake.
     */
    public boolean getVisible(){ return this.isVisible;}


    /**
     * Moves the snake based on the current direction.
     */
    public void move() {

        //let the swarm move
        if (up) {
            y -=  speed_XY;
        } else if (down) {
            y += speed_XY;
        } else if (left) {
            x -= speed_XY;
        } else if (right) {
            x += speed_XY;
        }

    }
    /**
     * Handles key presses to change the direction of the snake.
     *
     * @param event The KeyEvent representing the key press.
     */
    public void keyPressed(javafx.scene.input.KeyEvent event) {

        switch (event.getCode()) {

            case UP:
                if (!down) {

                    up = true;
                    down = false;
                    left = false;
                    right = false;
                    Direction = 1;
                }
                break;

            case DOWN:
                if (!up) {

                    up = false;
                    down = true;
                    left = false;
                    right = false;
                    Direction = 3;
                }
                break;

            case LEFT:
                if (!right) {

                    up = false;
                    down = false;
                    left = true;
                    right = false;
                    Direction = 4;
                }
                break;

            case RIGHT:
                if (!left) {

                    up = false;
                    down = false;
                    left = false;
                    right = true;
                    Direction = 2;
                }
                break;

            default:
                break;
        }
    }

    /**
     * Gets the length of the body points list.
     *
     * @return The length of the body points list.
     */
    public int getBodyPointsLength() {
        int length = 0;
        Iterator<Point> iterator = bodyPoints.iterator();

        while (iterator.hasNext()) {
            length++;
            iterator.next();
        }

        return length;
    }

}
