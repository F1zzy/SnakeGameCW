package example;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

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

        MainMenu.startGame();  // Assuming you have a reset method in your Model

    }
}
