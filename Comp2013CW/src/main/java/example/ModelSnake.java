package example;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ModelSnake extends GameObject implements movable {
    private static final int SNAKE_SPEED = 25;
    private int speed_XY;
    private int length;
    private int numOfBodies; // ?
    public int score = 0;

    public static List<Point> bodyPoints = new LinkedList<>();

    boolean up, down, left, right = true;

    public ModelSnake(int x, int y) {
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.image = ImageUtil.images.get("snake-body");
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        this.speed_XY = SNAKE_SPEED;
        this.length = 1;

        /*
         * Attention : ?
         */
        this.numOfBodies = width / speed_XY;

    }

    public int getLength() {
        return length;
    }

    public void changeLength(int length) {
        this.length = length;
    }


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

}
