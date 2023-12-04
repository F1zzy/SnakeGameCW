package example;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenu {

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static Stage primaryStage;
    private static VBox layout;

    public static void display() {
        primaryStage = new Stage();
        primaryStage.setTitle("Main Menu");
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

        // Set button actions
        playButton.setOnAction(e -> {
            // Start the game when the Play button is pressed
            startGame();
        });

        leaderboardsButton.setOnAction(e -> {
            // Add logic for leaderboards button
        });

        tutorialButton.setOnAction(e -> {
            // Add logic for tutorial button
        });

        optionsButton.setOnAction(e -> {
            openOptions();
        });

        exitButton.setOnAction(e -> primaryStage.close());

        // Create layout
        layout = new VBox(10);
        layout.setId("main-menu");
        layout.getChildren().addAll(titleLabel,playButton, leaderboardsButton, tutorialButton, optionsButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(background);

        // Set up the scene
        Scene scene = new Scene(layout, FRAME_WIDTH, FRAME_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static Button createStyledButton(String text, String inlineStyle) {
        Button button = new Button(text);
        button.setStyle(inlineStyle);
        return button;
    }

    private static void startGame() {
        // Initialize your game components (Model, Controller, View)
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller , primaryStage);

        // Start the game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.updateGame();  // Update game logic
            }
        };

        MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");
        gameLoop.start();
    }

    private static void openOptions() {
        Options.display(primaryStage);
    }

}
