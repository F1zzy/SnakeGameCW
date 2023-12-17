package example;

import example.LeaderBoard.LeaderBoard;
import example.Settings.Settings;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Gather Data for High Score Comparisons
        LeaderBoard.Init();
        Settings.Init();
        MainMenu.display();

    }
}

