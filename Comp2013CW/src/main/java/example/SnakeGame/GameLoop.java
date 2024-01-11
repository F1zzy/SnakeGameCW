package example.SnakeGame;


import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
/**
 * The GameLoop class represents the main game loop responsible for updating the game state and rendering.
 */
public class GameLoop extends AnimationTimer {

    private Model model;
    private View view;
    private SoundManager soundManager;

    private boolean isRunning;

    /**
     * Constructs a new GameLoop with the specified model and view.
     *
     * @param model The model of the game.
     * @param view  The view of the game.
     */
    public GameLoop(Model model, View view) {
        this.model = model;
        this.view = view;
        this.soundManager = SoundManager.getInstance();
        this.isRunning = false;
    }

    /**
     * Starts a countdown before the game begins.
     */
    public void countdownStart() {
        final int[] countdownValue = {3};

        // Create a timeline for the countdown
        Timeline countdownTimeline = new Timeline();
        countdownTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (countdownValue[0] > 0) {
                        System.out.println("" + countdownValue[0]);
                        view.drawCountdown(countdownValue[0]);
                        countdownValue[0]--;
                    } else {
                        System.out.println("GO");

                        view.drawCountdown(4); // Magic number, consider using a constant or explaining why 4
                        // Stop the countdown timeline
                        countdownTimeline.stop();

                        // Start the game loop
                        soundManager.playBackgroundMusic();
                        this.start();
                        isRunning = true;
                    }
                })
        );

        // Set the cycle count to 3 seconds (Need to include GO)
        countdownTimeline.setCycleCount(4);
        countdownTimeline.play();
    }

    /**
     * Handles the game loop update logic.
     *
     * @param now The timestamp of the current frame.
     */
    @Override
    public void handle(long now) {
        if (model.endGame()) {
            view.gameOverScene();
            soundManager.stopBackgroundMusic();
            soundManager.playGameOver();
            this.stop(); // Stop the game loop
        } else {
            // Update the game state
            model.updateGame();
        }
    }

    /**
     * Stops the game loop.
     */
    public void stopGameLoop() {
        this.stop();
        isRunning = false;
    }

    /**
     * Checks if the game loop is currently running.
     *
     * @return True if the game loop is running; false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }
}
