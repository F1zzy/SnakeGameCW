package example;

import javafx.scene.image.Image;

public class InvisibleSnakeLevelState implements LevelState {
    private LevelManager levelManager;
    private int speedBoostStartTime;

    private int invisibleDuration;
    private boolean invisibleTime;
    private boolean isFruitGenerated;
    public InvisibleSnakeLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;

        this.invisibleDuration = 200;
        this.isFruitGenerated = false;
    }

    @Override
    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        Food food;
        model.outOfBounds();
        model.eatBody();

        // Boost Logic
        if (invisibleTime) {
            invisibleDuration++;
            if (invisibleDuration > 90) {
                invisibleTime = false;
                snake.setVisible(true);
                snake.getBodyPoints().clear();
            }
        }

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
                    fruit.eaten(snake);
                    model.getFoodsList().remove(0);
                    isFruitGenerated = false;

                    invisibleTime = true;
                    snake.setVisible(false);
                    invisibleDuration = 0;
                }
            }
        } else {
            model.EndGame = true;
        }
    }




    // Determine the state of the game.







    @Override
    public String getName() {
        return "Speed Boost - Level 2";
    }

    @Override
    public Image getLevelBackground() {
        return null;
    }




}
