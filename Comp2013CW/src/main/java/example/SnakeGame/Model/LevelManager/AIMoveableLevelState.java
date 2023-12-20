package example.SnakeGame.Model.LevelManager;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.AiMoveableFood;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;
import javafx.scene.image.Image;

import java.awt.*;
/**
 * The AIMoveableLevelState class represents a game level where an AI Moveable food is introduced.
 * The snake can eat this food, and the food moves in response to the snake's movements.
 */
public class AIMoveableLevelState implements LevelState {
    private LevelManager levelManager;
    private boolean isFoodMoving;
    private Point foodPosition;
    private LevelStageType levelStageType = LevelStageType.AIMOVEFOOD;

    private SoundManager  soundManager = SoundManager.getInstance();


    public AIMoveableLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.isFoodMoving = false;
        this.foodPosition = new Point(0, 0); // Initial food position

        //Start State:
        Model model = levelManager.getModel();
        model.getFoodsList().clear();
        model.getNegativeFoodsList().clear();

        Snake snake = model.getSnake();
        snake.setSpeed(snake.getOriginalSpeed());
        snake.setVisible(true);
    }
    /**
     * Updates the state of the AIMoveableLevelState.
     * Checks for collisions with the AI Moveable food and updates its position.
     */
    @Override
    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();

        //Check normal snake behavior
        model.outOfBounds();
        model.eatBody();

        AiMoveableFood aiMoveableFood = null;
        // Determine the state of the game.
        if (snake.isAlive) {
            if (!isFoodMoving) {
                // If food is not moving, generate a new AI Movable food
                model.addFood(model.newAIMoveableFood());
                aiMoveableFood = (AiMoveableFood) model.getFoodsList().get(0);
                if(aiMoveableFood != null){
                    aiMoveableFood.move(snake);
                }
                isFoodMoving = true;
            } else {
                // Check if the AI Movable food is eaten by the snake
                aiMoveableFood = (AiMoveableFood) model.getFoodsList().get(0);
                if (snake.getRectangle().intersects(aiMoveableFood.getRectangle())) {
                    soundManager.PlayEatFood();
                    aiMoveableFood.eaten(snake);
                    model.getFoodsList().remove(0);
                    isFoodMoving = false; // Allow generating a new AI Moveable food
                }
            }
        } else {
            model.setEndGame(true);
        }
        if(aiMoveableFood != null){
            aiMoveableFood.move(snake);
        }


    }

    @Override
    public LevelStageType getType() {
        return levelStageType;
    }

    @Override
    public String getName() {
        return "AI Moveable";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("AIMoveableFoodLevelState-background");
    }

}
