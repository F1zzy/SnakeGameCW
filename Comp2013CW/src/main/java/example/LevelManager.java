package example;

public class LevelManager {
    private LevelState currentLevelState;
    private Model model;

    public LevelManager(Model model) {
        setLevelState(new AIMoveableLevelState(this));
        this.model = model;
    }

    public void setLevelState(LevelState levelState) {
        this.currentLevelState = levelState;
    }

    public void update() {
        currentLevelState.update();
        // Additional level manager logic, if any
    }

    public Model getModel() {
        return model;
    }
}
