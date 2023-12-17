package example.SnakeGame.Model.LevelManager;

import example.SnakeGame.Model.GameObjects.FoodObjects.RainbowDrop;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.Model.LevelManager.LevelManager;
import example.SnakeGame.Model.LevelManager.LevelState;
import example.SnakeGame.Model.Model;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RainbowRainLevelState implements LevelState {
    private static final int RAINBOW_DROP_SPEED = 3;
    private LevelManager levelManager;
    private Random random;

    public RainbowRainLevelState(LevelManager levelManager) {
        this.levelManager = levelManager;
        this.random = new Random();
    }

    @Override

    public void update() {
        Model model = levelManager.getModel();
        Snake snake = model.getSnake();
        //List<RainbowDrop> rainbowDrops = model.getrainbowDropList();

        model.outOfBounds();
        model.eatBody();

        // Determine the state of the game.
        if (snake.isAlive) {
            if (model.getrainbowDropList().isEmpty()) {
                // If no fruits, generate a new set of random fruits
                generateRainbowDrops(model);
            } else {
                // Check if any fruit is eaten by the snake
                checkSnakeCollisions(model , model.getrainbowDropList());
            }
        } else {
            model.EndGame = true;
        }
        updateRainbowDrops(model.getrainbowDropList());

    }

    private void checkSnakeCollisions(Model model , List<RainbowDrop> rainbowDrops) {

        Snake snake = model.getSnake();

        List<RainbowDrop> eatenFruits = new ArrayList<>();

        for (RainbowDrop fruit : model.getrainbowDropList()) {
            if (snake.getRectangle().intersects(fruit.getRectangle())) {
                System.out.println("EATEN");
                eatenFruits.add( fruit);
            }
        }

        // Remove eaten fruits from the model
        model.getrainbowDropList().removeAll(eatenFruits);

         //Add new fruits to maintain a certain number on the screen
        while (model.getrainbowDropList().size() < 3) {
            int startX = (int) (Math.random() * (870 - 40));
            int startY = (int) (Math.random() * (560 - 40));
            RainbowDrop food = model.newRainbowRainFood(startX,-10);
            model.addrainbowRainFood(food);
        }
    }

    private void updateRainbowDrops(List<RainbowDrop> rainbowDrops) {
        Iterator<RainbowDrop> iterator = rainbowDrops.iterator();
        while (iterator.hasNext()) {
            RainbowDrop drop = iterator.next();
            drop.setY(drop.getY() + RAINBOW_DROP_SPEED);
            if (drop.getY() > 600) {
                iterator.remove(); // Remove the drop if it's off-screen
            }
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

    @Override
    public void setStartState() {

    }

    private void generateRainbowDrops(Model model) {
        int numFruits = random.nextInt(3) + 1; // Generate 1 to 3 fruits

        for (int i = 0; i < 4; i++) {
            int startX = (int) (Math.random() * (870 - 40));
            int startY = (int) (Math.random() * (560 - 40));
            RainbowDrop food = model.newRainbowRainFood(startX,-10);
            model.addrainbowRainFood(food);
        }
    }

}
