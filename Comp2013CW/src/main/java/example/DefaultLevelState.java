    package example;

    import javafx.scene.image.Image;

    public class DefaultLevelState  implements LevelState
    {

        private LevelManager levelManager;

        public DefaultLevelState(LevelManager levelManager) {
                this.levelManager = levelManager;
        }


        @Override
        public void update() {
            //Default Level Settings
            Model model = levelManager.getModel();
            Snake snake = levelManager.getModel().getSnake();
            Food food = levelManager.getModel().getFood();
            // Default Game Logic
            model.outofBounds();
            model.eatBody();

            // Determine the state of the game.
            if (snake.isAlive) {
                if (food.isAlive) {
                    food.eaten(snake);
                } else {
                    model.NewFood();
                }
            } else {

                model.EndGame = true;
            }
        }

        @Override
        public String getName() {
            return "Level 1 - Default";
        }

        @Override
        public Image getLevelBackground() {
            return null;
        }
    }
