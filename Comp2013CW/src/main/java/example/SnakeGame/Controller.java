package example.SnakeGame;

import example.LeaderBoard.LeaderBoardUtil;
import example.MainMenu;
import example.SnakeGame.Model.Model;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * The Controller class is responsible for handling user input,
 * such as button presses and keyboard events.
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * Constructs a new Controller with the specified model.
     *
     * @param model The model to associate with the controller.
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Sets the view associated with the controller.
     *
     * @param setView The view to associate with the controller.
     */
    public void setView(View setView) {
        this.view = setView;
    }

    /**
     * Gets the EventHandler for handling key pressed events.
     *
     * @return The key pressed event handler.
     */
    public EventHandler<? super KeyEvent> keyPressed() {
        return event -> {
            model.getSnake().keyPressed(event);
        };
    }

    /**
     * Returns to the main menu.
     */
    public void goBack() {
        MainMenu.display();
    }

    /**
     * Restarts the game.
     */
    public void retry() {
        MainMenu.startGame();
    }

    /**
     * Submits the player's score to the leaderboard.
     *
     * @param username The username of the player (as inputted in the input box).
     * @param score    The score achieved by the player.
     */
    public void submitScore(String username, int score) {
        LeaderBoardUtil.addScoreRecord(username, score);
        System.out.println("ADDING SCORE");
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        model.pauseGame();
        view.showPauseMenu();
    }

    /**
     * Resumes the game.
     */
    public void resumeGame() {
        model.resumeGame();
        view.hidePauseMenu();
    }
}
