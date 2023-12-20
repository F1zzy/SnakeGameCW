package LeaderboardTests;

import example.LeaderBoard.ScoreEntry;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreEntryTests {

    @Test
    public void testScoreEntryConstructorAndGetters() {
        // Arrange
        String username = "testUser";
        int score = 100;
        String timestamp = "10:30 01-01-2023";

        // Act
        ScoreEntry scoreEntry = new ScoreEntry(username, score, timestamp);

        // Assert
        assertEquals(username, scoreEntry.getUsername());
        assertEquals(score, scoreEntry.getScore());
        assertEquals(timestamp, scoreEntry.getTimestamp());
    }






    @Test
    public void testScoreEntryTimestampParsing() {
        // Arrange
        String username = "testUser";
        int score = 100;
        String timestamp = "10:30 01-01-2023";

        // Act
        ScoreEntry scoreEntry = new ScoreEntry(username, score, timestamp);

        // Assert
        assertEquals(username, scoreEntry.getUsername());
        assertEquals(score, scoreEntry.getScore());
        assertEquals(timestamp, scoreEntry.getTimestamp());
    }

    // Add more tests based on your specific requirements

}
