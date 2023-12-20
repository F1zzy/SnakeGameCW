package example.SnakeGame.Model;

import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.GameObjects.EnemyObject;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.LevelManager.LevelManager;
import example.SnakeGame.Model.LevelManager.LevelState;
import example.Utilities.SoundManager;
import javafx.application.Platform;

import java.awt.*;
import java.util.List;
import java.util.Observable;
/**
 * The Model class represents the core logic of the Snake game.
 * It manages the game state, including the snake, food, levels-state.
 * This class extends Observable to notify View when the game state changes.
 */
public class Model extends Observable {
    private Snake SnakeObject;



    boolean EndGame = false;

    private FoodFactory foodFactory;
    private LevelManager levelManager;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;

    private static final int DEFAULT_SPEED = 1;

    private GameLoop gameLoop;
    private final SoundManager soundManager= SoundManager.getInstance();

    private EnemyObject enemyObject;
    /**
     * Constructor for the Model class.
     * Initializes the snake, food factory, and level manager.
     */
    public Model() {
        SnakeObject = new Snake(100, 100);

        foodFactory = new FoodFactory();

        levelManager = new LevelManager(this);

    }
    /**
     * Gets the enemy object.
     *
     * @return The enemy object.
     */
    public EnemyObject getEnemyObject() {

        return enemyObject;
    }
    /**
     * Initializes the enemy object.
     */
    public void initEnemyObject(){
        enemyObject = new EnemyObject(950 , 650 );
    }


    /**
     * Sets the game loop for the model.
     *
     * @param givenGameLoop The game loop to set.
     */
    public void setGameLoop(GameLoop givenGameLoop){
        gameLoop = givenGameLoop;
    }

    /**
     * Pauses the game by stopping the background music and game loop.
     */
    public void pauseGame(){
        soundManager.stopBackgroundMusic();
        gameLoop.stopGameLoop();

    }
    /**
     * Resumes the game by starting a countdown.
     */
    public void resumeGame(){

        gameLoop.countdownStart();
    }
    /**
     * Updates the game state, including moving the snake and notifying view.
     */
    public void updateGame() {
        levelManager.update();


        SnakeObject.move();
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });

    }
    /**
     * Gets the snake object.
     *
     * @return The snake object.
     */
    public Snake getSnake() {
        return SnakeObject;
    }

    /**
     * Gets the current level state from level manager.
     *
     * @return The current level state.
     */
    public LevelState getLevelState(){return levelManager.getCurrentLevelState();}
    /**
     * Gets the list of food objects in FoodFactory.
     */

    public List<Food> getFoodsList() {
        return foodFactory.getFoodList();
    }

    public List<Food> getNegativeFoodsList() {
        return foodFactory.getNegativeFoodList();
    }


    /**
     * Creates New Food from list in FoodFactory.
     */

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



    public void addFood(Food food) {
        foodFactory.addList(food);
    }
    public  void addNegativeFood(Food food){foodFactory.addNegativeList(food);}


    /**
     * Remove Food from FoodFactory List
     */
    public void removeFood(Food food) {
        foodFactory.removeList(food);
    }

    public int getScore() {
        return SnakeObject.score;
    }
    /**
     *  Checks if Snakes head touchs Snakes Body
     */
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

    /**
     *  Checks if Snake is within Boundaries of Screen
     */
    public void outOfBounds() {
        boolean xOut = (SnakeObject.getX() <= 0 || SnakeObject.getX() >= (FRAME_WIDTH - SnakeObject.getWidth()));
        boolean yOut = (SnakeObject.getY() <= 0 || SnakeObject.getY() >= (FRAME_HEIGHT - SnakeObject.getHeight()));
        if (xOut || yOut) {
            SnakeObject.isAlive = false;
        }
    }
    /**
     *  Obseravble Function
     */
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    public void clearFoods() {
        foodFactory.clearList();
    }
    /**
     * get the end game state.
     */
    public boolean endGame(){ return EndGame;}
    /**
     * Sets the end game state.
     *
     * @param bool True to end the game, false otherwise.
     */
    public void setEndGame(boolean bool ){ EndGame = bool;}


}
