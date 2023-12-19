package example;

import example.LeaderBoard.LeaderBoard;
import example.Settings.Settings;
import example.Settings.SettingsManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static example.Settings.Settings.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws URISyntaxException, IOException {
        //Gather Data for High Score Comparisons
        SettingsManager.loadSettings();
        MainMenu.display();
        //updateCSSFile();
        System.out.println("DONE");

//Files.writeString(Path.of(Settings.class.getResource("/SaveFiles/SettingsSaveFile.txt").toURI()), "CHANGED", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }


}

