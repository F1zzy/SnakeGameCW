package example;

import javafx.scene.image.Image;

public interface LevelState {

    void update();
    
    

    String getName();

    Image getLevelBackground();


    default void setStartState(){

    }

}
