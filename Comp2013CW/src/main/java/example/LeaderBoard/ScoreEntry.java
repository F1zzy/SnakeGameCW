package example.LeaderBoard;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreEntry {

    private final SimpleStringProperty username;
    private final SimpleIntegerProperty score;
    private final SimpleStringProperty timestamp;

    public ScoreEntry(String username, int score , String time ) {
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

        this.timestamp = new SimpleStringProperty(time);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public String getTimestamp() {
        return timestamp.get();
    }

    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }
}
