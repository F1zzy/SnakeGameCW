package example;

import javafx.scene.image.Image;

import java.awt.*;

public class AIMoveableLevelState implements LevelState {
    private LevelManager levelManager;
    private boolean isFoodMoving;
    private Point foodPosition;

    public AIMoveableLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.isFoodMoving = false;
        this.foodPosition = new Point(0, 0); // Initial food position
        Model model = levelManager.getModel();
        model.getFoodsList().clear();
    }

    @Override
    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        model.outOfBounds();
        AiMoveableFood aiMoveableFood = null;
        model.eatBody();

        // Determine the state of the game.
        if (snake.isAlive) {
            if (!isFoodMoving) {
                // If food is not moving, generate a new AI Moveable food
                model.addFood(model.newAIMoveableFood());
                aiMoveableFood = (AiMoveableFood) model.getFoodsList().get(0);
                if(aiMoveableFood != null){
                    aiMoveableFood.move(snake);
                }
                isFoodMoving = true;
            } else {
                // Check if the AI Moveable food is eaten by the snake
                aiMoveableFood = (AiMoveableFood) model.getFoodsList().get(0);
                if (snake.getRectangle().intersects(aiMoveableFood.getRectangle())) {
                    aiMoveableFood.eaten(snake);
                    model.getFoodsList().remove(0);
                    isFoodMoving = false; // Allow generating a new AI Moveable food
                }
            }
        } else {
            model.EndGame = true;
        }
        if(aiMoveableFood != null){
            aiMoveableFood.move(snake);
        }


    }

    @Override
    public String getName() {
        return "AI Moveable";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("AIMoveableFoodLevelState-background");
    }

    @Override
    public void setStartState() {

    }
}
