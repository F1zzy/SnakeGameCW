package example;

import javafx.application.Platform;

import java.awt.*;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private Snake SnakeObject;

    private int score;

    boolean EndGame = false;

    private FoodFactory foodFactory;
    private LevelManager levelManager;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private static final int DEFAULT_SPEED = 1;

    private GameLoop gameLoop;
    public Model() {
        SnakeObject = new Snake(100, 100);

        foodFactory = new FoodFactory();
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

    public List<Food> getFoodsList() {
        return foodFactory.getFoodList();
    }
    public List<Food> getNegativeFoodsList() {
        return foodFactory.getNegativeFoodList();
    }

    public Food newStaticFood() {
        return FoodFactory.createNewFood(1 , 0 , 0);
    }
    public Food newNegativeFood() {
        int x = 0, y = 0;
        List<Point> bodyPoints = SnakeObject.getBodyPoints();
       int bodyPointsSize = (bodyPoints.size()/8);
        Point point = SnakeObject.getBodyPoints().get(bodyPointsSize - 1);
        if (bodyPointsSize == 1) {
            int displacement = SnakeObject.width; // Adjust this value based on your game's layout

            switch (SnakeObject.Direction) {
                case 1: y = SnakeObject.y + displacement; break; // UP
                case 2: y = SnakeObject.y - displacement; break; // DOWN
                case 3: x = SnakeObject.x + displacement; break; // LEFT
                case 4: x = SnakeObject.x - displacement; break; // RIGHT
                default:
                    System.out.println("ERROR"); break;
            }
        } else {
            x = point.x ;
            y = point.y ;
        }


        System.out.println("Body Point Size: "+ bodyPoints.size());
        return FoodFactory.createNewFood(2, x, y );
    }

    public void addFood(Food food) {
        foodFactory.AddList(food);
    }
    public  void addNegativeFood(Food food){foodFactory.AddNegativeList(food);}

    public void removeFood(Food food) {
        foodFactory.RemoveList(food);
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
                    break;
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
        foodFactory.clearList();
    }
}
