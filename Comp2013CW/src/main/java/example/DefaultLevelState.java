package example;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DefaultLevelState implements LevelState {
    private LevelManager levelManager;
    private Random random;
    private boolean isFruitGenerated;

    public DefaultLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
        this.isFruitGenerated = false;
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
                model.addFood(model.newFood());
                isFruitGenerated = true;
            } else {
                // Check if the fruit is eaten by the snake
                Food fruit = model.getFruits().get(0);
                if (snake.getRectangle().intersects(fruit.getRectangle())) {
                    fruit.eaten(snake);
                    model.getFruits().remove(0);
                    isFruitGenerated = false; // Allow generating a new fruit
                }
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


}
