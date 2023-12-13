package example;

import javafx.scene.image.Image;

public class SpeedBoostLevelState implements LevelState {
    private LevelManager levelManager;
    private int speedBoostStartTime;

    private int speedBoostDuration;
    private boolean boostTime;

    public SpeedBoostLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;

        this.speedBoostDuration = 0;
    }

    @Override
    public void update() {
        //Default Level Settings
        Model model = levelManager.getModel();
        Snake snake = levelManager.getModel().getSnake();
        Food food = levelManager.getModel().getFood();
        // Default Game Logic
        model.outofBounds();
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
            if (food.isAlive) {
                food.eaten(snake);
            } else {
                model.NewFood();
                boostTime = true;
                snake.setSpeed(2);
                speedBoostDuration = 0;

            }
        } else {

            model.EndGame = true;
        }


    }



    @Override
    public String getName() {
        return "Speed Boost - Level 2";
    }

    @Override
    public Image getLevelBackground() {
        return null;
    }




}
