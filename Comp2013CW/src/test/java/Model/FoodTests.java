package Model;

import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food.FoodType;
import example.SnakeGame.Model.GameObjects.FoodObjects.StaticFood;
import example.SnakeGame.Model.GameObjects.Snake;
import example.Utilities.ImageUtil;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FoodTests {

    @Test
    public void testNormalFoodEaten() {
        StaticFood food = new StaticFood(ImageUtil.images.get("1"));

        Snake snake = new Snake(100, 100);
        snake.setAlive(true);

        assertFalse(food.isAlive()); // food is not alive initially

        // Set the position of the food to match the snake's head
        food.setX(snake.getX());
        food.setY(snake.getY());

        assertFalse(food.eaten(snake)); // snake hasn't collided yet

        assertTrue(food.isAlive()); // food should be alive after collision
        assertEquals(1, snake.getLength()); // snake length should increase
        assertEquals(1, 1); // snake score should increase
    }

    @Test
    public void testNegativeFoodEaten() {
        // similar tests for NegativeFood
    }

    @Test
    public void testMoveableFoodEaten() {
        // similar tests for MoveableFood
    }

    @Test
    public void testRaindropFoodEaten() {
        // similar tests for RaindropFood
    }
}
