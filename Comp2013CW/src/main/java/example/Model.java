package example;

import javafx.application.Platform;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private Snake SnakeObject;
    private List<Food> foods;
    private int score;

    boolean EndGame = false;

    private LevelManager levelManager;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private static final int DEFAULT_SPEED = 1;

    private GameLoop gameLoop;
    public Model() {
        SnakeObject = new Snake(100, 100);
        foods = new ArrayList<>();
        score = 0;

        levelManager = new LevelManager(this);
    }
    public void setGameLoop(GameLoop givenGameLoop){
        gameLoop = givenGameLoop;
    }
    public void pauseGame(){
        gameLoop.stop();

    }
    public void resumeGame(){
        gameLoop.CountdownStart();
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

    public List<Food> getFruits() {
        return foods;
    }

    public Food newFood() {
        return FoodFactory.createNewFood(false , false);
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public int getScore() {
        return SnakeObject.score;
    }

    public void eatBody() {
        if (SnakeObject.getLength() == 1) return;
        for (Point point : SnakeObject.bodyPoints) {
            for (Point point2 : SnakeObject.bodyPoints) {
                if (point.equals(point2) && point != point2) {
                    SnakeObject.isAlive = false;
                }
            }
        }
    }

    void outOfBounds() {
        boolean xOut = (SnakeObject.x <= 0 || SnakeObject.x >= (FRAME_WIDTH - SnakeObject.width));
        boolean yOut = (SnakeObject.y <= 0 || SnakeObject.y >= (FRAME_HEIGHT - SnakeObject.height));
        if (xOut || yOut) {
            SnakeObject.isAlive = false;
        }
    }

    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    public void clearFoods() {
        foods.clear();
    }
}
