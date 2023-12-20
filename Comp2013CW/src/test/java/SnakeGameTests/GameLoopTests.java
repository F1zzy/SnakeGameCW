package SnakeGameTests;
import example.Main;
import example.SnakeGame.Controller;
import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.Model;
import example.SnakeGame.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;

public class GameLoopTests extends ApplicationTest {

    private Model model;
    private View view;
    private Controller controller;
    private GameLoop gameLoop;
    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
    }

    @BeforeEach
    public void setUp() {
        Main.setPrimary(stage);
        model = new Model();
        controller = new Controller(model);
        view = new View(model, controller);
        gameLoop = new GameLoop(model, view);
        model.setGameLoop(gameLoop);
        controller.setView(view);

        // Start Countdown Start
        gameLoop.countdownStart();
    }

    @Test
    public void testIsRunningFlag() throws InterruptedException {
        // Wait for the countdown to complete
        Thread.sleep(4000);

        // Check if the isRunning flag is true after countdown
        boolean isRunning = gameLoop.isRunning();
        assertTrue(isRunning);

        // Assuming stopGameLoop() method stops the game loop
        gameLoop.stopGameLoop();

        // Check if the isRunning flag is false after stopping the game loop
        isRunning = gameLoop.isRunning();
        assertFalse(isRunning);
    }
}