package Model;

import example.Main;
import example.SnakeGame.Controller;
import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.Model;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.Snake;
import example.SnakeGame.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTests extends ApplicationTest  {

    Model model;
    Controller controller;
    View view;
    GameLoop gameLoop;

    Stage stage;
    @Start
    public void start(Stage stage) {
        this.stage = stage;
        Main.setPrimary(stage);
        model = new Model();

        controller = new Controller(model);
        view = new View(model, controller );
    }

    @Test
    public void testUpdateGame() {
        model.updateGame();
        assertFalse(model.endGame());
    }

    @Test
    public void testGetSnake() {
        Snake snake = model.getSnake();
        assertNotNull(snake);
        assertEquals(100, snake.getX());
        assertEquals(100, snake.getY());
    }

    @Test
    public void testAddAndRemoveFood() {
        Food food = model.newStaticFood();
        model.addFood(food);
        assertEquals(1, model.getFoodsList().size());

        model.removeFood(food);
        assertTrue(model.getFoodsList().isEmpty());
    }

    @Test
    public void testEatBody() {
        Snake snake = model.getSnake();

        // Adding two body points that are the same
        snake.bodyPoints.add(new java.awt.Point(100, 100));
        snake.changeLength(snake.getLength() + 1);
        assertTrue(snake.isAlive);

        snake.bodyPoints.add(new java.awt.Point(100, 100));
        snake.changeLength(snake.getLength() + 1);

        model.eatBody();

        // Assert that the snake is not alive
        assertFalse(snake.isAlive);

        // Assert that the snake length is correct after eating the body
        assertEquals(3, snake.getLength());

    }


    @Test
    public void testOutOfBounds() {
        Snake snake = model.getSnake();
        snake.setX(0);
        snake.setY(0);
        model.outOfBounds();
        assertFalse(snake.isAlive);

        snake.setX(1000);
        snake.setY(1000);
        model.outOfBounds();
        assertFalse(snake.isAlive);
    }

    @Test
    public void testEndGame() {
        assertFalse(model.endGame());
        model.setEndGame(true);
        assertTrue(model.endGame());
    }
}

