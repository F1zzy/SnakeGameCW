package example.SnakeGame.Model.LevelManager;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NegativeFoodLevelState implements LevelState {
    private LevelManager levelManager;
    private Random random;
    private boolean isFruitGenerated;
    private Model model;
    private ScheduledExecutorService scheduler;
    LevelStageType levelStageType = LevelStageType.NEGATIVEFOOD;

    private SoundManager soundManager = SoundManager.getInstance();
    public NegativeFoodLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
        this.isFruitGenerated = false;
        this.scheduler = Executors.newScheduledThreadPool(1);

        this.model = levelManager.getModel();
        model.getFoodsList().clear();
        Snake snake = model.getSnake();
        snake.setSpeed(snake.getOriginalSpeed());
        snake.setVisible(true);

    }

    @Override
    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        Food food;
        model.outOfBounds();
        model.eatBody();

        // Determine the state of the game.
        if (snake.isAlive) {
            if (!isFruitGenerated) {
                // If no fruit, generate a new fruit
                model.addFood(model.newStaticFood());
                isFruitGenerated = true;
            } else {
                // Check if the fruit is eaten by the snake
                Food fruit = model.getFoodsList().get(0);

                // If Snake Interacts with Fruit
                if (snake.getRectangle().intersects(fruit.getRectangle())) {
                    soundManager.PlayEatFood();
                    fruit.eaten(snake);
                    model.getFoodsList().remove(0);

                    // Schedule the appearance of negative food after a delay
                    scheduler.schedule(() -> {
                        model.addNegativeFood(model.newNegativeFood());
                        soundManager.PlayFart();
                    }, 50, TimeUnit.MILLISECONDS);

                    isFruitGenerated = false; // Allow generating a new fruit
                }
                List<Food> negFood = model.getNegativeFoodsList();
                // If Snakes Interacts With Negative Fruit
                checkNegativeFoodCollision();
            }
        } else {
            model.setEndGame(true);
        }
    }

    @Override
    public LevelStageType getType() {
        return levelStageType;
    }

    @Override
    public String getName() {
        return "Negative Food";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("NegativeFoodLevelState-background");
    }



    private void checkNegativeFoodCollision() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();

        List<Food> eatenFruits = new ArrayList<>();

        for (Food fruit : model.getNegativeFoodsList()) {
            if (snake.getRectangle().intersects(fruit.getRectangle())) {
                fruit.eaten(snake);
                eatenFruits.add(fruit);
            }
        }

        // Remove eaten fruits from the model
        model.getNegativeFoodsList().removeAll(eatenFruits);
    }
}
