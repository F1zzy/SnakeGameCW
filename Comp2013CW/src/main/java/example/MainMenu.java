package example;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;


    public static void display() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Main Menu");



        // Create buttons
        Button playButton = new Button("Play");
        Button leaderboardsButton = new Button("Leaderboards");
        Button tutorialButton = new Button("Tutorial");
        Button optionsButton = new Button("Options");
        Button exitButton = new Button("Exit");

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
            // Add logic for options button
        });

        exitButton.setOnAction(e -> primaryStage.close());

        // Create layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(playButton, leaderboardsButton, tutorialButton, optionsButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        // Set up the scene
        Scene scene = new Scene(layout, FRAME_WIDTH, FRAME_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void startGame() {
        // You can initialize the game and start it here
        // Initialize your JavaFX game here
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.updateGame();  // Update game logic

            }
        };

        MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");
        gameLoop.start();

        // Close the main menu stage
       // ((Stage) view.createScene().getWindow()).close();
    }
}
