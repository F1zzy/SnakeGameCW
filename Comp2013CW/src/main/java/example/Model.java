package example;
import javafx.application.Platform;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private ModelSnake SnakeObject;
    private Food food;
    private int score;

    boolean EndGame = false;


    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final int SNAKE_SPEED = 5;
    private boolean isAlive;

    public Model() {
        SnakeObject = new ModelSnake(100, 100);
        food = new Food();
        score = 0;

    }
    public void updateGame() {
        outofBounds();
        eatBody();

        // Determine the state of the game.
        if (SnakeObject.isAlive) {
            if (food.isAlive) {
                food.eaten(SnakeObject);
            } else {
                food = new Food();
                //score++;

            }
        } else {

            EndGame = true;
        }

        SnakeObject.move();
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }



    public ModelSnake getSnake() {
        return SnakeObject;
    }
    public Food getFood() {
        return food;
    }

    public List<Point> getBodyPoints(){return ModelSnake.bodyPoints;}

    public int getNumOfBodies(){return SnakeObject.numOfBodies;}

    public  Food NewFood(){
        this.food = new Food();
        return this.food;
    }



    public int getScore() {
        return SnakeObject.score;
    }

    public void eatBody()
    {
        if(SnakeObject.getLength() == 1) return;
        for (Point point : SnakeObject.bodyPoints)
        {
            for (Point point2 : SnakeObject.bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    SnakeObject.isAlive = false;
                }
            }
        }
    }
    private void outofBounds()
    {

        boolean xOut = (SnakeObject.x <= 0 || SnakeObject.x >= (FRAME_WIDTH - SnakeObject.width));
        boolean yOut = (SnakeObject.y <= 0 || SnakeObject.y  >= (FRAME_HEIGHT - SnakeObject.height));
        if (xOut || yOut)
        {
            SnakeObject.isAlive = false;
        }
    }

    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}

