package example.LeaderBoard;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

/**
 * Represents a single entry in the leaderboard, including username, score, and timestamp.
 */
public final class ScoreEntry {

    private final SimpleStringProperty username;
    private final SimpleIntegerProperty score;
    private final SimpleStringProperty timestamp;
    /**
     * Constructs a new ScoreEntry object with the specified username, score, and timestamp.
     *
     * @param username The username associated with the score entry.
     * @param score    The score achieved by the user.
     * @param time     The timestamp when the score was recorded.
     */
    public ScoreEntry(String username, int score , String time ) {
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
        this.timestamp = new SimpleStringProperty(time);
    }
    /**
     * Gets the username associated with this score entry.
     *
     * @return The username as a String.
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Gets the JavaFX property representing the username.
     *
     * @return The SimpleStringProperty representing the username.
     */
    public SimpleStringProperty usernameProperty() {
        return username;
    }

    /**
     * Gets the score achieved by the user.
     *
     * @return The score as an integer.
     */
    public int getScore() {
        return score.get();
    }

    /**
     * Gets the JavaFX property representing the score.
     *
     * @return The SimpleIntegerProperty representing the score.
     */
    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Gets the timestamp when the score was recorded.
     *
     * @return The timestamp as a String.
     */
    public String getTimestamp() {
        return timestamp.get();
    }

    /**
     * Gets the JavaFX property representing the timestamp.
     *
     * @return The SimpleStringProperty representing the timestamp.
     */
    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }
}
