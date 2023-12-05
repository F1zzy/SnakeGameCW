package example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;



public class LeaderBoard{
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final String LEADERBOARD_FILE = "src/main/java/example/leaderboard.csv";

    public static void display(Stage stage) {
        stage.setTitle("Leaderboard");

        ObservableList<ScoreEntry> data = readDataFromCSV();

        // Create TableView
        TableView<ScoreEntry> tableView = new TableView<>(data);
        // Create columns
        TableColumn<ScoreEntry, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        TableColumn<ScoreEntry, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        TableColumn<ScoreEntry, String> timestampCol = new TableColumn<>("Time");
        timestampCol.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());

        tableView.getColumns().addAll(usernameCol, scoreCol, timestampCol);

        tableView.setStyle("-fx-background-color: transparent;");

        // Customize the row background
        tableView.setRowFactory(tv -> {
            TableRow<ScoreEntry> row = new TableRow<>();
            row.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"); //Set as Transparent
            return row;
        });

        for (TableColumn<ScoreEntry, ?> col : tableView.getColumns()) {
            col.prefWidthProperty().bind(tableView.widthProperty().divide(3.0)); //Strecth to fit Window
        }

        // Create "Go Back" button
        Button goBackButton = new Button("Go Back");
        goBackButton.setOnAction(e -> {
            MainMenu.display();
        });

        // Create HBox for the "Go Back" button and spacer
        HBox topBar = new HBox(goBackButton, new Region());
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);

        //TableView
        VBox root = new VBox(topBar, tableView);
        root.setAlignment(Pos.CENTER);
        VBox.setVgrow(tableView, Priority.ALWAYS);


        Scene scene = new Scene(root, FRAME_WIDTH, FRAME_HEIGHT);
        root.setStyle("-fx-background-color: #45A049;"); //Background Color
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
}
