package example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Snake extends GameObject implements movable {
    private static final int SNAKE_SPEED = 2;
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

    public Snake(int x, int y) {
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.image = ImageUtil.images.get("snake-body");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();

        this.speed_XY = SNAKE_SPEED;
        this.length = 1;

        this.numOfBodies = width / speed_XY;
    }

    public int getLength() {
        return length;
    }

    public void changeLength(int length) {
        this.length = length;
    }

    public List<Point> getBodyPoints(){
        return bodyPoints;
    }
    public int getNumOfBodies(){return numOfBodies;}




    public void move() {
        //let the swarm move
        if (up) {
            y -= speed_XY;
        } else if (down) {
            y += speed_XY;
        } else if (left) {
            x -= speed_XY;
        } else if (right) {
            x += speed_XY;
        }

    }

    public void keyPressed(javafx.scene.input.KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (!down) {
                    System.out.println("Up");
                    up = true;
                    down = false;
                    left = false;
                    right = false;
                    Direction = 1;
                }
                break;

            case DOWN:
                if (!up) {
                    System.out.println("Down");
                    up = false;
                    down = true;
                    left = false;
                    right = false;
                    Direction = 3;
                }
                break;

            case LEFT:
                if (!right) {
                    System.out.println("left");
                    up = false;
                    down = false;
                    left = true;
                    right = false;
                    Direction = 4;
                }
                break;

            case RIGHT:
                if (!left) {
                    System.out.println("right");
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

}
