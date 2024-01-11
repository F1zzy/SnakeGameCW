package example.SnakeGame.Model.LevelManager;

import javafx.scene.image.Image;
/**
 * The LevelState interface defines the behavior and properties of different game levels in the Snake game.
 */
public interface LevelState {
    /**
     * Enum defining the types of level stages.
     */
    enum LevelStageType {
        DEFAULT, SPEED_BOOST, INVISIBLE , ENENMY , AIMOVEFOOD , MULTIPLEFOOD , NEGATIVEFOOD
    }


    /**
     * Updates the state of the level.
     */
    void update();
    /**
     * Gets the type of the level stage.
     *
     * @return The type of the level stage.
     */
    LevelStageType getType();

    /**
     * Gets the name of the level.
     *
     * @return The name of the level.
     */
    String getName();
    /**
     * Gets the background image for the level.
     *
     * @return The background image for the level.
     */
    Image getLevelBackground();




}
