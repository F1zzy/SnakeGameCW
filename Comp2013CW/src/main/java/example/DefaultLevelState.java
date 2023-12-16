package example;

import javafx.scene.image.Image;

import java.util.Random;

public class DefaultLevelState implements LevelState {

    private LevelManager levelManager;
    private Model model;
    private Random random;
    private boolean isFruitGenerated;

    private  SoundManager soundManager;

    public DefaultLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
        this.isFruitGenerated = false;
        this.model = levelManager.getModel();
        this.soundManager = SoundManager.getInstance();

        


    }

        public void setStartState() {
            //Get LevelState
        Snake snake = model.getSnake();
        snake.setSpeed(snake.getOriginalSpeed());
        snake.setVisible(true);

    }

    @Override
    public void update() {
        this.model = levelManager.getModel();
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
                if (snake.getRectangle().intersects(fruit.getRectangle())) {
                    soundManager.PlayEatFood();
                    fruit.eaten(snake);
                    model.getFoodsList().remove(0);

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
        return ImageUtil.images.get("DefaultLevelState-background");
    }


}
