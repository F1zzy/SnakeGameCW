package example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static Stage mainStage;
    private static VBox layout;

    // Method to get the singleton instance of the Stage
    public static Stage getMainStage() {
        if (mainStage == null) {
            mainStage = new Stage();
            mainStage.getIcons().add(ImageUtil.images.get("logo"));
        }
        return mainStage;
    }

    public static void display() {
        mainStage = getMainStage();
        mainStage.setTitle("Main Menu");

        // Create buttons with inline styles
        Button playButton = Style.createStyledButton("Play");
        Button leaderboardsButton = Style.createStyledButton("LeaderBoard");
        Button optionsButton = Style.createStyledButton("Options");
        Button exitButton = Style.createStyledButton("Exit");

        // Create title
        Label titleLabel = new Label("Snake Game");
        titleLabel.setStyle("-fx-font-size: 90px; -fx-text-fill: white;");

        ImageView MMSnake = new ImageView(ImageUtil.images.get("MainMenu-background"));

        playButton.setOnAction(e -> {
            startGame();
        });

        leaderboardsButton.setOnAction(e -> {
            openLeaderboard();

        });

        optionsButton.setOnAction(e -> {
            openOptions();
        });

        exitButton.setOnAction(e -> mainStage.close());

        // Create layout for buttons
        VBox buttonLayout = new VBox(10);
        buttonLayout.setId("main-menu");
        buttonLayout.getChildren().addAll(titleLabel,playButton, leaderboardsButton, optionsButton, exitButton);
        buttonLayout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(buttonLayout, FRAME_WIDTH, FRAME_HEIGHT);

        StackPane rootLayout = new StackPane();

        buttonLayout.setBackground(Style.ReturnBackgroundFill());


        StackPane.setAlignment(MMSnake, Pos.BOTTOM_RIGHT);
        rootLayout.getChildren().addAll(buttonLayout, MMSnake);

       // rootLayout.setBackground(new Background(backgroundFill));
        System.out.println(rootLayout.getBackground());
        // Set up the scene with the root layout
        Scene sceneWithSnake = new Scene(rootLayout, FRAME_WIDTH, FRAME_HEIGHT);

        mainStage.setScene(sceneWithSnake);
        mainStage.show();

    }

    private static void openLeaderboard() {
        LeaderBoard.display(getMainStage());
    }




    public static void startGame() {
        // Initialize your game components (Model, Controller, View)
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller , mainStage);


        // Start the game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(model.EndGame) {
                    view.gameOverScene();
                    this.stop();
                }
                else{
                    model.updateGame();  // Update game logic
                }

            }
        };

        //MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");

        final int[] num = {3};

        Timeline countdownTimeline = new Timeline();
        countdownTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (num[0] > 0) {
                        System.out.println("" + num[0]);
                        view.drawCountdown(num[0]);
                        num[0]--;
                    } else {
                        System.out.println("" + "GO");

                        view.drawCountdown(4);
                        // Stop the countdown timeline
                        countdownTimeline.stop();

                        // Start the game loop
                        gameLoop.start();
                    }
                })
        );

        // Set the cycle count to 3 seconds (Need to include GO)
        countdownTimeline.setCycleCount(4);
        countdownTimeline.play();

    }


    private static void openOptions() {
        Options.display(getMainStage());
    }

}
