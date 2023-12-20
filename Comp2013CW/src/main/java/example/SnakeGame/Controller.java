package example.SnakeGame;

import example.LeaderBoard.LeaderBoardUtil;
import example.MainMenu;
import example.SnakeGame.Model.Model;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class Controller {
    private Model model;
    private View view;
    public Controller(Model model ) {
        this.model = model;
    }
    public void setView(View setView){
        this.view = setView;
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
        LeaderBoardUtil.addScoreRecord(username , score );
        System.out.println("ADDING SCORE");
    }

    public void pauseGame() {
        model.pauseGame();
        view.showPauseMenu();
    }

    public void resumeGame() {
        model.resumeGame();
        view.hidePauseMenu();
    }
}
