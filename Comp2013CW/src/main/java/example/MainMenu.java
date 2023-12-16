package example;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final int TARGET_FPS = 60; // Adjust as needed
    private static final long TARGET_NANOSECONDS_PER_FRAME = 1_000_000_000 / TARGET_FPS;
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
        Button playButton = Settings.createStyledButton("Play");
        Button leaderboardsButton = Settings.createStyledButton("LeaderBoard");
        Button optionsButton = Settings.createStyledButton("Options");
        Button exitButton = Settings.createStyledButton("Exit");

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

        buttonLayout.setBackground(Settings.ReturnBackgroundFill());


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

        GameLoop gameLoop = new GameLoop(model,view);
        model.setGameLoop(gameLoop);
        controller.setView(view);
        //MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");

        gameLoop.CountdownStart();

    }


    private static void openOptions() {
        Options.display(getMainStage());
    }

}
