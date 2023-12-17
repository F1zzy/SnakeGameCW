package example.SnakeGame.Model.LevelManager;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;
import javafx.scene.image.Image;

public class SpeedBoostLevelState implements LevelState {
    private LevelManager levelManager;

    private int speedBoostDuration;
    private boolean boostTime;
    private boolean isFruitGenerated;
    private  Model model;
    LevelStageType levelStageType = LevelStageType.SPEED_BOOST;

    private SoundManager soundManager = SoundManager.getInstance();
    public SpeedBoostLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.model = levelManager.getModel();
        this.speedBoostDuration = 121;
        this.isFruitGenerated = false;
        System.out.println("SPEED LEVEL");
        this.boostTime = false;

        model.getFoodsList().clear();
        Snake snake = model.getSnake();
        snake.setVisible(true);
    }

    @Override
    public void update() {

        model = levelManager.getModel();
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
                    soundManager.PlayBoost();
                    speedBoostDuration = 0;
                    isFruitGenerated = false; // Allow generating a new fruit
                }
            }
        } else {

            model.setEndGame(true);
        }


    }

    @Override
    public LevelStageType getType() {
        return levelStageType;
    }


    // Determine the state of the game.







    @Override
    public String getName() {
        return "Speed Boost - Level 2";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("SpeedBoostLevelState-background");
    }

    @Override
    public void setStartState() {

    }


}
