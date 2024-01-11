package LeaderboardTests;

import example.LeaderBoard.LeaderBoardUtil;
import example.LeaderBoard.ScoreEntry;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
public class LeaderboardTest {
    private static final String TEST_CSV_FILE_PATH_SAMPLE1 = "src/test/test_resources/Leaderboard/test_leaderboard_sample1.csv";
    private  static final String TEST_CSV_FILE_INVALID = "src/test/test_resources/Leaderboard/test_leaderboard_invalid.csv";
    private static  int Highscore;

    @Before
    public void setUp() {
        //Set Up leaderboard to use Test File


    }

    @After
    public void shutDown() {
        // Close JavaFX Platform
        Platform.exit();
    }

    @Test
    public void testGreaterThanHighScore() {
        LeaderBoardUtil.setHighscoreForTesting(300);

        assertTrue(LeaderBoardUtil.greaterThanHighScore(900));
        assertFalse(LeaderBoardUtil.greaterThanHighScore(1));
        assertTrue(LeaderBoardUtil.greaterThanHighScore(300));
    }

    @Test
    public void testReadDataFromCSVWithValidInput() {
        LeaderBoardUtil.setLeaderboardFile(TEST_CSV_FILE_PATH_SAMPLE1);

        // Call the readDataFromCSV method
        ObservableList<ScoreEntry> testData = LeaderBoardUtil.readDataFromCSV();

        // Check if the data is not null and has the expected size
        assertNotNull(testData);
        assertEquals(5, testData.size());

        Assertions.assertEquals("Eva", testData.getFirst().getUsername());
        Assertions.assertEquals(200, testData.getFirst().getScore());
        Assertions.assertEquals("2023-01-05 14:20:00", testData.getFirst().getTimestamp());

        Assertions.assertEquals("Charlie", testData.get(1).getUsername());
        Assertions.assertEquals(180, testData.get(1).getScore());
        Assertions.assertEquals("2023-01-03 15:00:00", testData.get(1).getTimestamp());

        Assertions.assertEquals("Alice", testData.get(2).getUsername());
        Assertions.assertEquals(150, testData.get(2).getScore());
        Assertions.assertEquals("2023-01-01 10:30:00", testData.get(2).getTimestamp());

        Assertions.assertEquals("Bob", testData.get(3).getUsername());
        Assertions.assertEquals(120, testData.get(3).getScore());
        Assertions.assertEquals("2023-01-02 12:45:00", testData.get(3).getTimestamp());

        Assertions.assertEquals("David", testData.get(4).getUsername());
        Assertions.assertEquals(90, testData.get(4).getScore());
        Assertions.assertEquals("2023-01-04 08:15:00", testData.get(4).getTimestamp());
    }

    @Test
    public void testReadDataFromCSVWithInvalidInput() {
        // Set the LEADERBOARD_FILE to the path Invalid File
        LeaderBoardUtil.setLeaderboardFile(TEST_CSV_FILE_INVALID);

        // Call the readDataFromCSV method
        ObservableList<ScoreEntry> testData = LeaderBoardUtil.readDataFromCSV();

        // Check if the data is not null and empty (invalid input should not add entries)
        assertNotNull(testData);
        assertTrue(testData.isEmpty());

    }

    @Test
    public void testReadDataFromCSVWithIOException() {
        // Set the LEADERBOARD_FILE to a non-existing path to trigger IOException
        LeaderBoardUtil.setLeaderboardFile("non-existing-path/emptyLeaderboard.csv");

        // Call the readDataFromCSV method
        ObservableList<ScoreEntry> testData = LeaderBoardUtil.readDataFromCSV();

        // Check if the data is not null and empty (IOException should not add entries)
        assertNotNull(testData);
        assertTrue(testData.isEmpty());
    }


}
