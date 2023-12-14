package example;

import javafx.scene.image.Image;

public class SpeedBoostLevelState implements LevelState {
    private LevelManager levelManager;

    private int speedBoostDuration;
    private boolean boostTime;
    private boolean isFruitGenerated;
    public SpeedBoostLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;

        this.speedBoostDuration = 121;
        this.isFruitGenerated = false;
        System.out.println("SPEED LEVEL");

        this.boostTime = false;

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
                snake.setSpeed(snake.getOriginalSpeed());
                System.out.println("STOP BOOSTing");

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
                    boostTime = true;
                    snake.setSpeed(snake.getOriginalSpeed() + 2);
                    System.out.println("BOOOST");
                    speedBoostDuration = 0;
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
