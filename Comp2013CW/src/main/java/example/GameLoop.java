package example;


import javafx.animation.AnimationTimer;

public  class  GameLoop extends AnimationTimer {

    private Model model;
    private View view;

    public GameLoop(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void handle(long now) {
        if (model.EndGame) {
            view.gameOverScene();
            this.stop();
        } else {
            model.updateGame();
        }
    }
}
