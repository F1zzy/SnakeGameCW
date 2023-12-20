package SnakeGameTests;

import example.Main;
import example.SnakeGame.Controller;
import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.Model;
import example.SnakeGame.View;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testng.Assert;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class ControllerTest extends ApplicationTest {
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

        //Generate GameLoop which gets model and view
        gameLoop = new GameLoop(model,view);
        model.setGameLoop(gameLoop);
        controller.setView(view);

        //Start Countdown Start
        gameLoop.start();

        model.getSnake().setSpeed(1);
    }

    @Test
    public void testPauseGame() {
        // Assuming pauseGame() method pauses the game and shows the pause menu
        clickOn("#pauseButton"); // Assuming you have a method to get the pause button
        waitForFxEvents();

        Assert.assertFalse(gameLoop.isRunning());
        // Add assertions or further checks based on your specific requirements
    }

    @Test
    public void testPauseThenResumeGame() throws TimeoutException, InterruptedException {
        clickOn("#pauseButton");
        waitForFxEvents();
        Assert.assertFalse(gameLoop.isRunning());
        clickOn("#resumeButton");
        Thread.sleep(6000);
        Assert.assertTrue(gameLoop.isRunning());
    }

    @Test
    public void testPauseThenRetry() throws TimeoutException, InterruptedException {
        Model initalMod = model;
        clickOn("#pauseButton");
        waitForFxEvents();
        Assert.assertFalse(gameLoop.isRunning());
        clickOn("#retryButton");
        waitForFxEvents();
        Thread.sleep(6000);
        Assert.assertFalse(gameLoop.isRunning());
    }

    @Test
    public void testPauseThenGoBack(){
        clickOn("#pauseButton");
        waitForFxEvents();
        Assert.assertFalse(gameLoop.isRunning());
        clickOn("#goBackButton");
        verifyThat("#mainMenuTitle", hasText("Snake Game"));
    }

    @Test
    public void testMovingSnakeWithKeyPresses() throws InterruptedException {


        press(KeyCode.DOWN);
        Thread.sleep(500);
        Assert.assertNotEquals(100, model.getSnake().getY());


    }



}
