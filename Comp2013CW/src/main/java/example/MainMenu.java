package example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
        Image mainMenuBackground = ImageUtil.images.get("MainMenu-background");
        BackgroundSize backgroundSize = new BackgroundSize(FRAME_WIDTH, FRAME_HEIGHT, false, false, false, true);
        BackgroundImage mainMenuBackgroundImage = new BackgroundImage(mainMenuBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(mainMenuBackgroundImage);



        String CSSFormat = "-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 18px;";
        // Create buttons with inline styles
        Button playButton = createStyledButton("Play", CSSFormat);
        Button leaderboardsButton = createStyledButton("Leaderboards", CSSFormat);
        Button tutorialButton = createStyledButton("Tutorial", CSSFormat);
        Button optionsButton = createStyledButton("Options", CSSFormat);
        Button exitButton = createStyledButton("Exit", CSSFormat);

        // Create title
        Label titleLabel = new Label("Snake Game");
        titleLabel.setStyle("-fx-font-size: 90px; -fx-text-fill: white;");

        playButton.setOnAction(e -> {
            startGame();
        });

        leaderboardsButton.setOnAction(e -> {
            openLeaderboard();

        });

        tutorialButton.setOnAction(e -> {

        });

        optionsButton.setOnAction(e -> {
            openOptions();
        });

        exitButton.setOnAction(e -> mainStage.close());

        // Create layout
        layout = new VBox(10);
        layout.setId("main-menu");
        layout.getChildren().addAll(titleLabel,playButton, leaderboardsButton, tutorialButton, optionsButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        // Set up the scene
        Scene scene = new Scene(layout, FRAME_WIDTH, FRAME_HEIGHT);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private static void openLeaderboard() {
        LeaderBoard.display(getMainStage());
    }


    private static Button createStyledButton(String text, String inlineStyle) {
        Button button = new Button(text);
        button.setStyle(inlineStyle);
        return button;
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
                    view.drawFailScene();
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
