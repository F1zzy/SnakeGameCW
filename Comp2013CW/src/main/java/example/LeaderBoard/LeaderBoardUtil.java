package example.LeaderBoard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility class for managing a leaderboard.
 */
public class LeaderBoardUtil {
    private static String LEADERBOARD_FILE = "src/main/resources/SaveFiles/leaderboard.csv";

    private static ObservableList<ScoreEntry> data;

    private static  int Highscore;

    public static final String TIMESTAMP_PATTERN = "HH:mm dd-MM-yyyy";

    // Static block to initialize data and therefore Highscore on class loading
    static {
        data = readDataFromCSV();
        Highscore = data.isEmpty() ? 0 : data.get(0).getScore();
    }

    /**
     * Reads data from the CSV file and populates an ObservableList of ScoreEntry objects.
     *
     * @return The ObservableList of ScoreEntry objects read from the CSV file.
     */
    public static ObservableList<ScoreEntry> readDataFromCSV() {
        ObservableList<ScoreEntry> data = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // Check if the line has 3 Parts ( username , Score , Time )
                if (parts.length == 3) {
                    //Add All parts to Data
                    String username = parts[0].trim();
                    try {
                        int score = Integer.parseInt(parts[1].trim());
                        String timestamp = parts[2].trim();
                        //Add Score to List
                        data.add(new ScoreEntry(username, score, timestamp));
                    } catch (NumberFormatException e) {
                        // Handle the case where the score is not a valid integer
                        System.err.println("Invalid score format: " + parts[1].trim());
                    }
                } else {
                    // Handle the case where file format is incorrect
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        return data;
    }
    /**
     * Adds a new score record to the leaderboard and updates the CSV file.
     *
     * @param username The username of the player. (Inputted from Input Control box)
     * @param score    The score gotten by the player.
     */
    public static void addScoreRecord(String username, int score ) {
        data = readDataFromCSV();
        //Get Current Time In the correct format
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN);

        ScoreEntry scoreRecord = new ScoreEntry(username , score , currentDateTime.format(formatter));

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
            e.printStackTrace();
        }
    }

    /**
     * Checks if the given score is greater than the current highest score.
     *
     * @param givenScore The score to compare with the highest score.
     * @return Boolean of  if the given score is greater than the highest score, false otherwise.
     */
    public static boolean greaterThanHighScore(int givenScore) {
        return Highscore <= givenScore;
    }


    /**
     * Returns the leaderboard data.
     *
     * @return The ObservableList of ScoreEntry objects representing the leaderboard data.
     */
    protected static ObservableList<ScoreEntry> getData(){
        data = readDataFromCSV();
        return  data;}


    //Testing Purposes
    // Package-private method to set Highscore for testing
    public static void setHighscoreForTesting(int newHighscore) {
        Highscore = newHighscore;
    }

    public static void setLeaderboardFile(String path){
        LeaderBoardUtil.LEADERBOARD_FILE = path;
    }



}
