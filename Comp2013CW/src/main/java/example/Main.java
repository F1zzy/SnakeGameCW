package example;

import example.Settings.SettingsManager;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @Project Snakee Yippee
 * @Description A simple Snakee game implementation using JavaFX.
 *              This project demonstrates game development concepts,
 *              user interface design, and event handling in Java.
 * @Author Abdullah Tukur
 * @Version 1.0
 */
public class Main extends Application {


    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The primary stage of the JavaFX application.
     * Static to make it accessible at the class level.
     */
    public static Stage primaryStage ;


    /**
     * Start Method to launch Game and Load Settings
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the static primaryStage variable to be accessed from anywhere
        this.primaryStage = primaryStage;

        // Load Settings
        SettingsManager.loadSettings();

        // Display the main menu
        MainMenu.display();


    }

    //Functions for Testing Purposes:
    public static void setPrimary(Stage stage) {
        primaryStage = stage;
    }
    public static Stage getPrimary() {
        return primaryStage;
    }


}

