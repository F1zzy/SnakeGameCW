package example.LeaderBoard;

import example.MainMenu;
import example.Settings.Settings;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * The LeaderboardDisplay class is responsible for displaying the leaderboard Called by MainMenu
 */
public class LeaderboardDisplay {
    /**
     * Function to Display the LeaderBoard
     */
    public static void display(Stage stage) {

        //Get the Stage from MainMenu
        stage = stage;

        // Set the title of the stage
        stage.setTitle("Leaderboard");

        // Retrieve data for the emptyLeaderboard.csv
        ObservableList<ScoreEntry> data = LeaderBoardUtil.getData();

        // Create TableView with data Then Create Columns
        TableView<ScoreEntry> tableView = Settings.createTableView(data);

        //Create Position Column
        TableColumn<ScoreEntry, Integer> positionCol = Settings.createTableColumn("Position");
        positionCol.setCellValueFactory(cellData ->
                Bindings.createIntegerBinding(() -> tableView.getItems().indexOf(cellData.getValue()) + 1, tableView.getItems()).asObject());
        //Create Username Column
        TableColumn<ScoreEntry, String> usernameCol = Settings.createTableColumn("Username");
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        //Create Score Column
        TableColumn<ScoreEntry, Integer> scoreCol = Settings.createTableColumn("Score");
        scoreCol.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

        //Create Time Column
        TableColumn<ScoreEntry, String> timestampCol = Settings.createTableColumn("Time");
        timestampCol.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
        //Add All Columns to Tableview
        tableView.getColumns().addAll(positionCol, usernameCol, scoreCol, timestampCol);
        tableView.setId("tableView");
        // Set preferred column widths based on the TableView width
        for (TableColumn<ScoreEntry, ?> col : tableView.getColumns()) {
            col.prefWidthProperty().bind(tableView.widthProperty().divide(4)); // Adjust this as needed
        }

        // Create "Go Back" button And position it at Top Right of Scene
        Button goBackButton = Settings.createStyledButton("Go Back");
        goBackButton.setId("goBackButton");
        goBackButton.setOnAction(e -> MainMenu.display());
        StackPane stackPane = new StackPane(tableView, goBackButton);
        stackPane.setAlignment(Pos.BOTTOM_RIGHT);

        // Set up the scene with the StackPane And Show it.
        Scene scene = new Scene(stackPane, MainMenu.FRAME_WIDTH, MainMenu.FRAME_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
