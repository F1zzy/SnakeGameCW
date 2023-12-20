package example.SnakeGame.Model.LevelManager;

import example.SnakeGame.Model.Model;
import example.Utilities.SoundManager;

import java.util.Random;
/**
 * The LevelManager class manages the game levels and their transitions.
 * It is responsible for updating the current level state and changing the level when specific conditions are met.
 */
public class LevelManager {
    private boolean levelChanged;
    private LevelState currentLevelState;
    private final Model model;

    public SoundManager soundManager;
    public LevelManager(Model model) {
        this.model = model;
        this.levelChanged = false;
        this.soundManager = SoundManager.getInstance();
        LevelState state = new AIMoveableLevelState(this);
        setLevelState(state);
    }
    /**
     * Sets the current level state.
     *
     * @param levelState The level state to be set as the current state.
     */
    public void setLevelState(LevelState levelState) {
        this.currentLevelState = levelState;
    }


    /**
     * Updates the current level state and checks for conditions to change the level.
     */
    public void update() {
        currentLevelState.update();

        if (!levelChanged) {

            // Check conditions to change the level
            if (model.getSnake().getLength() % 5 == 0) {
                // Change the level state when the body length is divisible by 5
                //changeLevelState();
                this.model.getFoodsList().clear();
                this.model.getNegativeFoodsList().clear();

                levelChanged = true;
            }

        }

        if(levelChanged && !(model.getSnake().getLength() % 5 == 0)){
            levelChanged = false;
        }



    }
    /**
     * Gets the associated Model.
     *
     * @return The Model associated with the LevelManager.
     */
    public Model getModel() {
        return model;
    }
    /**
     * Changes the current level state to a randomly selected state.
     *
     * @return The new level state.
     */
    public LevelState changeLevelState() {
        Random random = new Random();
        int randomNumber = random.nextInt(7) + 1;
        LevelState state;
        switch (randomNumber){
            case 1:
                state = new DefaultLevelState(this);
                setLevelState(state);
                return state;
            case 2:
                state = new SpeedBoostLevelState(this);
                setLevelState(state);
                return state;
            case 3:
                state = new NegativeFoodLevelState(this);
                setLevelState(state);
                return state;
            case 4:
                state = new AIMoveableLevelState(this);
                setLevelState(state);
                return state;
            case 5:
                state = new MultipleFoodLevelState(this);
                setLevelState(state);
                return state;
            case 6:
                state = new InvisibleSnakeLevelState(this);
                setLevelState(state);
                return state;
            case 7:
                state = new EvilAIEnemyLevelState(this);
                setLevelState(state);
                return state;
            default:
                return null;
        }
    }

    /**
     * Return  the current level state of Game.
     *
     * @return The current level state.
     */
    public  LevelState getCurrentLevelState(){
        return currentLevelState;
    }

}
