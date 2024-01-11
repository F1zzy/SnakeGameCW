package LeaderboardTests;

import example.LeaderBoard.LeaderBoardUtil;
import example.LeaderBoard.LeaderboardDisplay;
import example.LeaderBoard.ScoreEntry;
import example.Main;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TableViewMatchers.*;
import static org.testfx.util.NodeQueryUtils.hasText;



public class LeaderboardDisplayTest extends ApplicationTest {
    private static final String TEST_CSV_FILE_PATH_SAMPLE1 = "src/test/test_resources/Leaderboard/test_leaderboard_sample1.csv";
    private static final String TEST_CSV_FILE_PATH_EMPTY = "src/test/test_resources/Leaderboard/emptyLeaderboard.csv";
    @Override
    public void start(Stage stage) {
        Main.setPrimary(stage);
        LeaderboardDisplay.display(stage);
    }


    public void setUp(){
        LeaderBoardUtil.setLeaderboardFile(TEST_CSV_FILE_PATH_SAMPLE1);
    }

    @Test
    public void testLeaderboardDisplay() {

        setUp();
        // Assuming there is sample data in your leaderboard display
        TableView<ScoreEntry> tableView = lookup("#tableView").query();

        // Verify that the table has rows
        verifyThat(tableView, hasNumRows(5));


        Assertions.assertEquals("Eva", tableView.getItems().getFirst().getUsername());
        Assertions.assertEquals(200, tableView.getItems().getFirst().getScore());
        Assertions.assertEquals("2023-01-05 14:20:00", tableView.getItems().getFirst().getTimestamp());

        Assertions.assertEquals("Charlie", tableView.getItems().get(1).getUsername());
        Assertions.assertEquals(180, tableView.getItems().get(1).getScore());
        Assertions.assertEquals("2023-01-03 15:00:00", tableView.getItems().get(1).getTimestamp());

        Assertions.assertEquals("Alice", tableView.getItems().get(2).getUsername());
        Assertions.assertEquals(150, tableView.getItems().get(2).getScore());
        Assertions.assertEquals("2023-01-01 10:30:00", tableView.getItems().get(2).getTimestamp());

        Assertions.assertEquals("Bob", tableView.getItems().get(3).getUsername());
        Assertions.assertEquals(120, tableView.getItems().get(3).getScore());
        Assertions.assertEquals("2023-01-02 12:45:00", tableView.getItems().get(3).getTimestamp());

        Assertions.assertEquals("David", tableView.getItems().get(4).getUsername());
        Assertions.assertEquals(90, tableView.getItems().get(4).getScore());
        Assertions.assertEquals("2023-01-04 08:15:00", tableView.getItems().get(4).getTimestamp());


    }
    @Test
    public void testEmptyLeaderboardDisplay() throws InterruptedException {

        LeaderBoardUtil.setLeaderboardFile(TEST_CSV_FILE_PATH_EMPTY);
        // Assuming the leaderboard display is initially empty
        TableView<ScoreEntry> tableView = lookup("#tableView").query();
        Thread.sleep(6000);
        // Check if no items exist in the ObservableList
        Assertions.assertTrue(tableView.getItems().isEmpty());

    }

    @Test
    public void testGoBackButton() {
        clickOn("#goBackButton");
        verifyThat("#mainMenuTitle", hasText("Snake Game"));
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{}); // release all held keys
        release(new MouseButton[]{}); // release all held mouse buttons
    }
}

