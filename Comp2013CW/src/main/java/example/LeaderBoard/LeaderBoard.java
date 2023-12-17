package example.LeaderBoard;

import example.MainMenu;
import example.Settings.Settings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;


public class LeaderBoard{
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final String LEADERBOARD_FILE = "src/main/java/example/leaderboard.csv";

    private static ObservableList<ScoreEntry> data;

    private static int Highscore;
    public static void Init(){
        data = readDataFromCSV();
        //Highscore = data.getFirst().getScore();
    }


    public static void display(Stage stage) {
        stage.setTitle("Leaderboard");

        data = readDataFromCSV();

        // Create TableView
        TableView<ScoreEntry> tableView = new TableView<>(data);
        // Create columns
        TableColumn<ScoreEntry, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        usernameCol.setStyle(Settings.CSSFormat);


        TableColumn<ScoreEntry, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());
        scoreCol.setStyle(Settings.CSSFormat);

        TableColumn<ScoreEntry, String> timestampCol = new TableColumn<>("Time");
        timestampCol.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
        timestampCol.setStyle(Settings.CSSFormat);

        tableView.getColumns().addAll(usernameCol, scoreCol, timestampCol);

        tableView.setStyle(Settings.CSSFormat);

        // Customize the row background
        tableView.setRowFactory(tv -> {
            TableRow<ScoreEntry> row = new TableRow<>();
            row.setStyle(Settings.CSSFormat);
            return row;
        });

        for (TableColumn<ScoreEntry, ?> col : tableView.getColumns()) {
            col.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        }

        // Create "Go Back" button
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> {
            MainMenu.display();
        });
        goBackButton.setStyle(Settings.CSSFormat);


        // Create StackPane to overlap "Go Back" button over TableView
        StackPane stackPane = new StackPane(tableView, goBackButton);
        stackPane.setAlignment(Pos.TOP_RIGHT); // Align the stackPane content to the top right

        // Set up the scene
        Scene scene = new Scene(stackPane, FRAME_WIDTH, FRAME_HEIGHT);
        stackPane.setStyle(Settings.CSSFormat);
        stage.setScene(scene);
        stage.show();

    }






    private static ObservableList<ScoreEntry> readDataFromCSV() {
        ObservableList<ScoreEntry> data = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                String timestamp = parts[2].trim();
                data.add(new ScoreEntry(username, score, timestamp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void addScoreRecord(ScoreEntry scoreRecord) {
        List<ScoreEntry> existingData = readDataFromCSV();

        //Insert into correct space
        int insertIndex = 0;
        while (insertIndex < data.size() && scoreRecord.getScore() < data.get(insertIndex).getScore()) {
            insertIndex++;
        }
        // Insert the new score record at the found position
        data.add(insertIndex, scoreRecord);

        try (PrintWriter writer = new PrintWriter(new FileWriter(LEADERBOARD_FILE))) {
            for (ScoreEntry Record : data) {
                writer.println(Record.getUsername() + "," + Record.getScore() + "," + Record.getTimestamp());
            }
        } catch (IOException e) {
            // Handle the exception more gracefully, e.g., log or notify the user
            e.printStackTrace();
        }
    }

    public static boolean GreaterThanHighScore(int givenScore) {
        return Highscore < givenScore;

    }


}
