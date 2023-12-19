package example.SnakeGame.Model.LevelManager;

import example.SnakeGame.Model.GameObjects.EnemyObject;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.Model;
import example.Utilities.ImageUtil;
import example.Utilities.SoundManager;
import javafx.scene.image.Image;

import java.util.Random;

public class EvilAIEnemyLevelState implements LevelState{
    private LevelManager levelManager;
    private Model model;
    private Random random;
    private boolean isFruitGenerated;
    private EnemyObject  enemy;

    private LevelStageType levelStageType = LevelStageType.ENENMY;
    private final SoundManager  soundManager = SoundManager.getInstance();
    public EvilAIEnemyLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
        this.isFruitGenerated = false;
        this.model = levelManager.getModel();

        model.getFoodsList().clear();
        Snake snake = model.getSnake();
        snake.setSpeed(snake.getOriginalSpeed());
        snake.setVisible(true);

        model.initEnemyObject();

        enemy = model.getEnemyObject();
        model.initEnemyObject();
        enemy.setAlive(true);

    }


    @Override
    public void update() {
        this.model = levelManager.getModel();
        Snake snake = model.getSnake();
        Food food;
        model.outOfBounds();
        model.eatBody();
        enemy = model.getEnemyObject();

        enemy.eaten(model);

        // Determine the state of the game.
        if (snake.isAlive) {
            if (!isFruitGenerated) {
                // If no fruit, generate a new fruit
                model.addFood(model.newStaticFood());
                isFruitGenerated = true;
            } else {
                // Check if the fruit is eaten by the snake
                Food fruit = model.getFoodsList().get(0);
                if (snake.getRectangle().intersects(fruit.getRectangle())) {
                    soundManager.PlayEatFood();
                    fruit.eaten(snake);
                    model.getFoodsList().remove(0);
                    isFruitGenerated = false; // Allow generating a new fruit
                }
            }
        } else {
            model.setEndGame(true);
        }
        enemy.move(snake.getX() , snake.getY());

    }

    @Override
    public LevelStageType getType() {
        return this.levelStageType;
    }


    @Override
    public String getName() {
        return "Evil State";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("DefaultLevelState-background");
    }


}
