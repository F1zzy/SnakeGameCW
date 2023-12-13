package example;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleFoodLevelState implements LevelState {
    private LevelManager levelManager;
    private Random random;

    public MultipleFoodLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
    }

    @Override
    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        model.outOfBounds();
        model.eatBody();

        // Determine the state of the game.
        if (snake.isAlive) {
            if (model.getFruits().isEmpty()) {
                // If no fruits, generate a new set of random fruits
                generateRandomFruits();
            } else {
                // Check if any fruit is eaten by the snake
                checkFruitCollision();
            }
        } else {
            model.EndGame = true;
        }
    }

    @Override
    public String getName() {
        return "Level 1 - Default";
    }

    @Override
    public Image getLevelBackground() {
        return null;
    }

    private void generateRandomFruits() {
        Model model = levelManager.getModel();
        int numFruits = random.nextInt(3) + 1; // Generate 1 to 3 fruits

        for (int i = 0; i < numFruits; i++) {
            Food food = model.newFood();
            model.addFood(food);
        }
    }

    private void checkFruitCollision() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();

        List<Food> eatenFruits = new ArrayList<>();

        for (Food fruit : model.getFruits()) {
            if (snake.getRectangle().intersects(fruit.getRectangle())) {
                fruit.eaten(snake);
                eatenFruits.add(fruit);
            }
        }

        // Remove eaten fruits from the model
        model.getFruits().removeAll(eatenFruits);

        // Add new fruits to maintain a certain number on the screen
        while (model.getFruits().size() < 3) {
            Food food = model.newFood();
            model.addFood(food);
        }
    }
}
