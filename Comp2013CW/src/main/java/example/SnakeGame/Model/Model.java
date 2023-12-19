package example.SnakeGame.Model;

import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.GameObjects.EnemyObject;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.FoodObjects.RainbowDrop;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.LevelManager.LevelManager;
import example.SnakeGame.Model.LevelManager.LevelState;
import example.Utilities.SoundManager;
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
    private final SoundManager soundManager= SoundManager.getInstance();

    private EnemyObject enemyObject;

    public Model() {
        SnakeObject = new Snake(100, 100);

        foodFactory = new FoodFactory();
        score = 0;

        levelManager = new LevelManager(this);

    }

    public EnemyObject getEnemyObject() {

        return enemyObject;
    }
    public void initEnemyObject(){
        enemyObject = new EnemyObject(950 , 650 );
    }



    public void setGameLoop(GameLoop givenGameLoop){
        gameLoop = givenGameLoop;
    }
    public void pauseGame(){
        soundManager.stopBackgroundMusic();
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
    public LevelState getLevelState(){return levelManager.getCurrentLevelState();}

    public List<Food> getFoodsList() {
        return foodFactory.getFoodList();
    }
    public List<Food> getNegativeFoodsList() {
        return foodFactory.getNegativeFoodList();
    }
    public List<RainbowDrop> getrainbowDropList(){ return  foodFactory.getRainbowDropList();}

    public Food newStaticFood() {
        return FoodFactory.createNewFood(1 , 0 , 0);
    }
    public Food newNegativeFood() {
        int x = 0, y = 0;
        List<Point> bodyPoints = SnakeObject.getBodyPoints();
       int bodyPointsSize = (bodyPoints.size()/8);
        Point point = SnakeObject.getBodyPoints().get(bodyPointsSize - 1);
        if (bodyPointsSize == 1) {
            int displacement = SnakeObject.getWidth(); // Adjust this value based on your game's layout

            switch (SnakeObject.Direction) {
                case 1: y = SnakeObject.getY() + displacement; break; // UP
                case 2: y = SnakeObject.getY() - displacement; break; // DOWN
                case 3: x = SnakeObject.getX() + displacement; break; // LEFT
                case 4: x = SnakeObject.getX() - displacement; break; // RIGHT
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
    public Food newAIMoveableFood() {
        return FoodFactory.createNewFood(3, 0,0);
    }
    public RainbowDrop newRainbowRainFood(int x , int y){
        return (RainbowDrop) FoodFactory.createNewFood(4, x,y);
    }


    public void addFood(Food food) {
        foodFactory.AddList(food);
    }
    public  void addNegativeFood(Food food){foodFactory.AddNegativeList(food);}
    public void addrainbowRainFood(RainbowDrop food) {
        foodFactory.AddRainbowDropList(food);
    }

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
                    System.out.println("Ate Body");
                    break;
                }
            }
        }
    }

    public void outOfBounds() {
        boolean xOut = (SnakeObject.getX() <= 0 || SnakeObject.getX() >= (FRAME_WIDTH - SnakeObject.getWidth()));
        boolean yOut = (SnakeObject.getY() <= 0 || SnakeObject.getY() >= (FRAME_HEIGHT - SnakeObject.getHeight()));
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
    public boolean endGame(){ return EndGame;}
    public void setEndGame(boolean bool ){ EndGame = bool;}


}
