package example.SnakeGame.Model.LevelManager;

import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.LevelManager.LevelManager;
import example.SnakeGame.Model.LevelManager.LevelState;
import example.SnakeGame.Model.Model;
import example.Utilities.ImageUtil;
import example.Utilities.SoundManager;
import javafx.scene.image.Image;

public class InvisibleSnakeLevelState implements LevelState {
    private LevelManager levelManager;
    private int speedBoostStartTime;

    private int invisibleDuration;
    private boolean invisibleTime;
    private boolean isFruitGenerated;
    private final LevelStageType levelStageType = LevelStageType.INVISIBLE ;

    private Model model;
    private final SoundManager soundManager = SoundManager.getInstance();
    public InvisibleSnakeLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.invisibleDuration = 200;
        this.isFruitGenerated = false;

        this.model = levelManager.getModel();
        model.getFoodsList().clear();
        Snake snake = model.getSnake();
        snake.setSpeed(snake.getOriginalSpeed());
        snake.setVisible(true);

    }

    @Override
    public void update() {
        model = levelManager.getModel();
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
                    soundManager.PlayInvisible();
                    fruit.eaten(snake);
                    model.getFoodsList().remove(0);
                    isFruitGenerated = false;

                    invisibleTime = true;
                    snake.setVisible(false);
                    invisibleDuration = 0;
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

    @Override
    public String getName() {
        return "Invisible Level";
    }

    @Override
    public Image getLevelBackground() {
        return ImageUtil.images.get("InvisibleLevelState-background");
    }




}
