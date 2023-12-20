package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.MainMenu;
import example.SnakeGame.Model.GameObjects.Snake;
import javafx.scene.image.Image;

import javafx.scene.image.Image;

public class AiMoveableFood extends Food {
    private static final int SPEED = 1;

    private static final int CHANGE_DIRECTION_INTERVAL = 120; // Adjust the interval for changing direction
    private static final double AVOIDANCE_DISTANCE = 10;

    private int directionChangeCounter = 0;
    private int targetDirectionX;
    private int targetDirectionY;
    public AiMoveableFood(Image image) {
        super(image);

        this.isAlive = true;
        this.image = image;

        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.scoreValue = 5;

        // Set random coordinates for the food within the game panel
        this.x = (int) (Math.random() * (MainMenu.FRAME_WIDTH - width));
        this.y = (int) (Math.random() * (MainMenu.FRAME_HEIGHT - height));
    }
    public void move(Snake snake) {
        directionChangeCounter++;

        // Change direction at regular intervals
        if (directionChangeCounter >= CHANGE_DIRECTION_INTERVAL) {
            adjustDirectionAwayFromSnake(snake);
            directionChangeCounter = 0;
        }

        // Move towards the target direction
        this.x += targetDirectionX * SPEED;
        this.y += targetDirectionY * SPEED;

        // Wrap around the edges
        wrapAroundEdges();
    }

    private void chooseRandomDirection() {
        // Choose a random direction
        targetDirectionX = (Math.random() > 0.5) ? 1 : -1;
        targetDirectionY = (Math.random() > 0.5) ? 1 : -1;
    }

    private void adjustDirectionAwayFromSnake(Snake snake) {

        int vectorX = this.x - snake.getX();
        int vectorY = this.y - snake.getY();


        double distance = Math.sqrt(vectorX * vectorX + vectorY * vectorY);


        if (distance < AVOIDANCE_DISTANCE) {
            // Normalize the vector
            double normalizedVectorX = vectorX / distance;
            double normalizedVectorY = vectorY / distance;


            targetDirectionX = (int) -normalizedVectorX;
            targetDirectionY = (int) -normalizedVectorY;
        } else {
            chooseRandomDirection();
        }
    }
    private void wrapAroundEdges() {
        if (this.x < 0) {
            this.x = MainMenu.FRAME_WIDTH - width; // Wrap to the right side
        } else if (this.x > MainMenu.FRAME_WIDTH - width) {
            this.x = 0; // Wrap to the left side
        }

        if (this.y < 0) {
            this.y = MainMenu.FRAME_HEIGHT - height; // Wrap to the bottom
        } else if (this.y > MainMenu.FRAME_HEIGHT - height) {
            this.y = 0; // Wrap to the top
        }
    }
}

