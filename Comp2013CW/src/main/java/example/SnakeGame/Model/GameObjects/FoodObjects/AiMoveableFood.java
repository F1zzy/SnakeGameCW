package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.SnakeGame.Model.GameObjects.Snake;
import javafx.scene.image.Image;
/**
 * Represents a food object in the Snake game with AI-like movement.
 * The food moves randomly but changes direction at regular intervals,
 * avoids the snake when it comes too close, and stays within the game boundaries.
 */
public class AiMoveableFood extends Food {
    // Constants for movement and behavior
    private static final int SPEED = 2;
    private static final int CORNER_THRESHOLD = 10;
    private static final int CHANGE_DIRECTION_INTERVAL = 6; // Adjust the interval for changing direction ( In Frames)
    private static final double AVOIDANCE_DISTANCE = 30;

    private int directionChangeCounter = 0;
    private int targetDirectionX;
    private int targetDirectionY;

    /**
     * Constructs an AiMoveableFood object with the specified image.
     *
     * @param image The image representing the AiMoveableFood.
     */
    public AiMoveableFood(Image image) {
        super(image);

        this.isAlive = true;
        //ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));
        this.image = image;

        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.scoreValue = 1;

        // Set random coordinates for the food within the game panel
        this.x = (int) (Math.random() * (870 - width + 10));
        this.y = (int) (Math.random() * (560 - height - 40));
    }
    /**
     * Moves the AiMoveableFood, considering snake avoidance and boundary handling.
     *
     * @param snake The snake object that the food avoids.
     */
    public void move(Snake snake) {
        double interpolationFactor = 0.3;
        directionChangeCounter++;

        // Change direction at regular intervals
        if (directionChangeCounter >= CHANGE_DIRECTION_INTERVAL) {
            adjustDirectionAwayFromSnake(snake);
            directionChangeCounter = 0;
        }

        // Calculate the target position based on the direction and movement speed
        double targetX = this.x + targetDirectionX * SPEED;
        double targetY = this.y + targetDirectionY * SPEED;

        // Linear interpolation for smoother movement
        this.x = (int) lerp(this.x, targetX, interpolationFactor);
        this.y = (int) lerp(this.y, targetY, interpolationFactor);

        // Ensure the food stays within the game boundaries
        handleBoundaryCollision();
    }
    /**
     * Linear interpolation between two values.
     *
     * @param start               The starting value.
     * @param end                 The ending value.
     * @param interpolationFactor The interpolation factor.
     * @return The interpolated value.
     */
    private double lerp(double start, double end, double interpolationFactor) {
        return start + interpolationFactor * (end - start);
    }

    /**
     * Handles collisions with the game boundaries and changes the direction accordingly.
     */
    private void handleBoundaryCollision() {
        // Ensure the food stays within the game boundaries
        if (this.x < 0 || this.x > 900 - width || this.y < 0 || this.y > 600 - height) {
            chooseRandomDirection();
        }
    }
    /**
     * Chooses a random direction for the AiMoveableFood.
     * In order to add Randomness
     */
    private void chooseRandomDirection() {
        // Choose a random direction
        targetDirectionX = (Math.random() > 0.5) ? 1 : -1;
        targetDirectionY = (Math.random() > 0.5) ? 1 : -1;
    }

    /**
     * Adjusts the direction of the AiMoveableFood away from the snake if it is too close.
     *
     * @param snake The snake object to avoid.
     */
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

    }




