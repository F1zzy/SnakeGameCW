package example;

import javafx.animation.AnimationTimer;
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

        Style.init();
        MainMenu.display();

    }
}

