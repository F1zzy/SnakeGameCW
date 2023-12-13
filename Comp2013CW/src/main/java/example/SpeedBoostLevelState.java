package example;

import javafx.scene.image.Image;

public class SpeedBoostLevelState implements LevelState {
    private LevelManager levelManager;
    private int speedBoostStartTime;

    private int speedBoostDuration;
    private boolean boostTime;
    private boolean isFruitGenerated;
    public SpeedBoostLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;

        this.speedBoostDuration = 0;
        this.isFruitGenerated = false;
    }

    @Override
    public void update() {

        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        Food food;
        model.outOfBounds();
        model.eatBody();
        //Boost Logic
        if(boostTime){
            speedBoostDuration++;
            if(speedBoostDuration > 120){
                boostTime = false;
                snake.setSpeed(1);

            }
        }

        // Determine the state of the game.
        if (snake.isAlive) {
            if (!isFruitGenerated) {
                // If no fruit, generate a new fruit
                model.addFood(model.newFood());
                isFruitGenerated = true;
                boostTime = true;
                snake.setSpeed(2);
                speedBoostDuration = 0;
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
