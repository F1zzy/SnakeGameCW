package example;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public EventHandler<? super KeyEvent> keyPressed() {

        return event -> {
            model.getSnake().keyPressed(event);
        };
    }

    public void goBack() {

        MainMenu.display();
    }

    public void retry() {

        MainMenu.startGame();

    }



    public void submitScore(String username, int score) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        System.out.println("ADDING SCORE");
        LeaderBoard.addScoreRecord(new ScoreEntry(username , score , currentDateTime.format(formatter)));
    }
}
