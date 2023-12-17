package example.SnakeGame.Model.LevelManager;

import javafx.scene.image.Image;

public interface LevelState {
    enum LevelStageType {
        DEFAULT, SPEED_BOOST, INVISIBLE , ENENMY , AIMOVEFOOD , MULTIPLEFOOD , NEGATIVEFOOD
    }



    void update();

    LevelStageType getType();
    String getName();

    Image getLevelBackground();


    default void setStartState(){

    }

}
