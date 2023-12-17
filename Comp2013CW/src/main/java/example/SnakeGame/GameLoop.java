package example.SnakeGame;


import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public  class  GameLoop extends AnimationTimer {

    private Model model;
    private View view;

    private SoundManager soundManager;

    public GameLoop(Model model, View view) {
        this.model = model;
        this.view = view;
        soundManager = SoundManager.getInstance();
    }
    public void CountdownStart(){

        final int[] num = {3};

        Timeline countdownTimeline = new Timeline();
        countdownTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (num[0] > 0) {
                        System.out.println("" + num[0]);
                        view.drawCountdown(num[0]);
                        num[0]--;
                    } else {
                        System.out.println("GO");

                        view.drawCountdown(4);
                        // Stop the countdown timeline
                        countdownTimeline.stop();

                        // Start the game loop
                        soundManager.playBackgroundMusic();
                        this.start();
                    }
                })
        );

        // Set the cycle count to 3 seconds (Need to include GO)
        countdownTimeline.setCycleCount(4);
        countdownTimeline.play();
    }


    @Override
    public void handle(long now) {
        if (model.EndGame) {
            view.gameOverScene();
            soundManager.stopBackgroundMusic();
            soundManager.playGameOver();
            this.stop();
        } else {
            model.updateGame();
        }
    }
}
