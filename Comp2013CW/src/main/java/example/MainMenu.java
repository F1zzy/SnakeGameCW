package example;

import example.LeaderBoard.LeaderboardDisplay;
import example.Settings.SettingsDisplay;
import example.Settings.Settings;
import example.SnakeGame.Controller;
import example.SnakeGame.GameLoop;
import example.SnakeGame.Model.Model;
import example.SnakeGame.View;
import example.Utilities.ImageUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
/**
 * The MainMenu class for the Main Menu of Game
 */
public class MainMenu {

    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;


    private static Stage mainStage;

    /**
     * Gets the singleton instance of the Stage for the main menu.
     *
     * @return The main stage for the main menu.
     */
    public static Stage getMainStage() {
        if (mainStage == null) {
            mainStage = Main.primaryStage;
            mainStage.getIcons().add(ImageUtil.images.get("logo"));
        }
        return mainStage;
    }
    /**
     * Displays the main menu.
     */
    public static void display() {
        mainStage = getMainStage();
        mainStage.setTitle("Main Menu");

        // Create buttons with inline styles
        Button playButton = Settings.createStyledButton("Play");
        playButton.setId("playButton");
        Button leaderboardsButton = Settings.createStyledButton("LeaderBoard");
        leaderboardsButton.setId("leaderboardsButton");
        Button optionsButton = Settings.createStyledButton("Options");
        optionsButton.setId("optionsButton");
        Button exitButton = Settings.createStyledButton("Exit");
        exitButton.setId("exitButton");

        // Create title
        Label titleLabel = Settings.createLabel("Snake Game" , 90);
        titleLabel.setId("mainMenuTitle");


        ImageView mainMenuSnakeImage = new ImageView(ImageUtil.images.get("MainMenu-background"));

        //Apply EventListeners to Buttons
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

        // Apply background fill from Settings
        buttonLayout.setBackground(Settings.ReturnBackgroundFill());

        //Apply main menu Snake Image to sit at the bottom right
        StackPane.setAlignment(mainMenuSnakeImage, Pos.BOTTOM_RIGHT);
        rootLayout.getChildren().addAll(buttonLayout, mainMenuSnakeImage);

        // Set up the scene with the root layout to include buttons and snake
        Scene sceneWithSnake = new Scene(rootLayout, FRAME_WIDTH, FRAME_HEIGHT);

        mainStage.setScene(sceneWithSnake);
        mainStage.show();

    }
    /**
     * Opens the Leaderboard display.
     */
    private static void openLeaderboard() {
        LeaderboardDisplay.display(getMainStage());
    }



    /**
     * Starts the Snake Game .
     */
    public static void startGame() {
        // Initialize your game components (Model, Controller, View)
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller );

        //Generate GameLoop which gets model and view
        GameLoop gameLoop = new GameLoop(model,view);
        model.setGameLoop(gameLoop);
        controller.setView(view);

        //Start Countdown Start
        gameLoop.CountdownStart();

    }

    /**
     * Opens the options display.
     */
    private static void openOptions() {
        SettingsDisplay.display(getMainStage());
    }

    //Function for Testing Purposes


}
