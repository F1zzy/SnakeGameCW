package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class ModelSnake extends GameObject implements movable {
    private static final int SNAKE_SPEED = 5;
    private int speed_XY;
    private int length;
    int numOfBodies;
    public int score = 0;

    //Using North(1) East(2) South(3) West(4).
    public int Direction = 2;
    public static List<Point> bodyPoints = new LinkedList<>();
    private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");
    private static BufferedImage newImgSnakeHead;
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

        this.numOfBodies = width / speed_XY;
        newImgSnakeHead = IMG_SNAKE_HEAD;
        //System.out.println("Snake Has been Created " + x  + " " + y);
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
    public void keyPressed(KeyEvent e)
    {
        // Check The Key pressed. if not arrow keys ignore
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if (!down)
                {
                    System.out.println("Down");
                    up = true;
                    down = false;
                    left = false;
                    right = false;


                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);

                    Direction = 1;
                }
                break;

            case KeyEvent.VK_DOWN:
                if (!up)
                {
                    System.out.println("UP");
                    up = false;
                    down = true;
                    left = false;
                    right = false;


                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);

                    Direction = 3;
                }
                break;

            case KeyEvent.VK_LEFT:
                if (!right)
                {
                    System.out.println("Right");
                    up = false;
                    down = false;
                    left = true;
                    right = false;

                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

                    Direction = 4;

                }
                break;

            case KeyEvent.VK_RIGHT:
                if (!left)
                {
                    System.out.println("Left");
                    up = false;
                    down = false;
                    left = false;
                    right = true;
                    //Change Line to Change Model Direction Instead of Image.
                    //newImgSnakeHead =

                    Direction = 2;
                }

            default:
                break;
        }
    }

    public void draw(Graphics g)
    {

        bodyPoints.add(new Point(x, y));

        if (bodyPoints.size() == (this.length + 1) * numOfBodies)
        {
            bodyPoints.remove(0);
        }

        switch (Direction){
            case 1:
                newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                break;
            case 2:
                newImgSnakeHead = IMG_SNAKE_HEAD;
                break;
            case 3:
                newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
                break;
            case 4:
                newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);
                break;
            default:
                break;
        }
        g.drawImage(newImgSnakeHead, x, y, null);
        drawBody(g);

    }

    public void drawBody(Graphics g)
    {
        int length = bodyPoints.size() - 1 - numOfBodies;

        for (int i = length; i >= numOfBodies; i -= numOfBodies)
        {
            Point point = bodyPoints.get(i);
            g.drawImage(this.image, point.x, point.y, null);
        }
    }



}
