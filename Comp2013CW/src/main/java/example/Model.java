package example;
import javafx.application.Platform;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Model extends Observable {
    private Snake SnakeObject;
    private Food food;
    private int score;

    boolean EndGame = false;

    private LevelManager levelManager;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private boolean isAlive;

    private static final int DEFAULT_SPEED = 1;


    private int speed;
    private long speedBoostStartTime;
    public Model() {
        levelManager = new LevelManager(this);

        SnakeObject = new Snake(100, 100);
        food = FoodFactory.createNewFood(false);
        score = 0;



    }

    public void updateGame() {
        levelManager.update();

        SnakeObject.move();
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }




    public Snake getSnake() {
        return SnakeObject;
    }
    public Food getFood() {
        return food;
    }


    public  Food NewFood(){
        this.food = FoodFactory.createNewFood(false);
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
    void outofBounds()
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

