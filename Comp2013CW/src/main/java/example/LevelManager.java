package example;

import java.util.Random;

public class LevelManager {
    private boolean levelChanged;
    private LevelState currentLevelState;
    private Model model;

    public SoundManager soundManager;
    public LevelManager(Model model) {
        LevelState state = new DefaultLevelState(this);
        setLevelState(state);
        this.model = model;
        this.levelChanged = false;
        this.soundManager = SoundManager.getInstance();
    }

    public void setLevelState(LevelState levelState) {
        this.currentLevelState = levelState;
    }

    public void update() {
        currentLevelState.update();

        if (levelChanged == false) {

            // Check conditions to change the level
            if (model.getSnake().getLength() % 3 == 0) {
                // Change the level state when the body length is divisible by 5
                //For Testing Purpose Dont change State
                //changeLevelState();
                this.model.getFoodsList().clear();
                this.model.getNegativeFoodsList().clear();

                levelChanged = true;
            }

        }

        if(levelChanged == true  && !(model.getSnake().getLength() % 3 == 0)){
            levelChanged = false;
        }



    }

    public Model getModel() {
        return model;
    }

    public LevelState changeLevelState() {
        Random random = new Random();
        int randomNumber = random.nextInt(3 - 1 + 1) + 1;
        LevelState state;
        switch (randomNumber){
            case 1:
                state = new DefaultLevelState(this);
                setLevelState(state);
                state.setStartState();
                return state;
            case 2:
                state = new SpeedBoostLevelState(this);
                setLevelState(state);
                state.setStartState();
                return state;
            case 3:
                state = new NegativeFoodLevelState(this);
                setLevelState(state);
                state.setStartState();
                return state;
            case 4:
                state = new AIMoveableLevelState(this);
                setLevelState(state);
                state.setStartState();
                return state;
            case 5:
                state = new MultipleFoodLevelState(this);
                setLevelState(state);
                state.setStartState();
                return state;
            case 6:
                state = new InvisibleSnakeLevelState(this);
                setLevelState(state);
                state.setStartState();
            default:
                return null;
        }
    }
    public  LevelState getCurrentLevelState(){
        return currentLevelState;
    }

}
