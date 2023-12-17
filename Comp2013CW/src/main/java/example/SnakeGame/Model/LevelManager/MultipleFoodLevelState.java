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

public class MultipleFoodLevelState implements LevelState {
    private LevelManager levelManager;
    private Random random;
    LevelStageType levelStageType = LevelStageType.MULTIPLEFOOD;

    private Model model;
    private SoundManager soundManager = SoundManager.getInstance();
    public MultipleFoodLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
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
        model.outOfBounds();
        model.eatBody();

        // Determine the state of the game.
        if (snake.isAlive) {
            if (model.getFoodsList().isEmpty()) {
                // If no fruits, generate a new set of random fruits
                soundManager.PlayEatFood();
                generateRandomFruits();
            } else {
                // Check if any fruit is eaten by the snake
                checkFruitCollision();
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
        return "Level 3 - Muliple Food";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("MultipleFoodLevelState-background");
    }

    @Override
    public void setStartState() {

    }

    private void generateRandomFruits() {
        Model model = levelManager.getModel();
        int numFruits = random.nextInt(3) + 1; // Generate 1 to 3 fruits

        for (int i = 0; i < numFruits; i++) {
            Food food = model.newStaticFood();
            model.addFood(food);
        }
    }

    private void checkFruitCollision() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();

        List<Food> eatenFruits = new ArrayList<>();

        for (Food fruit : model.getFoodsList()) {
            if (fruit.eaten(snake)) {
                eatenFruits.add(fruit);
            }
        }

        // Remove eaten fruits from the model
        model.getFoodsList().removeAll(eatenFruits);

        // Add new fruits to maintain a certain number on the screen
        while (model.getFoodsList().size() < 3) {
            Food food = model.newStaticFood();
            model.addFood(food);
        }
    }
}
