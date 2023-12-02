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
}
