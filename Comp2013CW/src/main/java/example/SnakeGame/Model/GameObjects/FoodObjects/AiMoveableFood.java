package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.SnakeGame.Model.GameObjects.Snake;
import javafx.scene.image.Image;

public class AiMoveableFood extends Food {
    private static final int SPEED = 2;
    private static final int CORNER_THRESHOLD = 10;

    private static final int CHANGE_DIRECTION_INTERVAL = 6; // Adjust the interval for changing direction ( In Frames)
    private static final double AVOIDANCE_DISTANCE = 30;

    private int directionChangeCounter = 0;
    private int targetDirectionX;
    private int targetDirectionY;
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

        // Ensure the food stays within the game boundaries
        if (this.x < 0) {
            this.x = 0;
            chooseRandomDirection();
        } else if (this.x > 900 - width) {
            this.x = 900 - width;
            chooseRandomDirection();
        }

        if (this.y < 0) {
            this.y = 0;
            chooseRandomDirection();
        } else if (this.y > 600 - height) {
            this.y = 600 - height;
            chooseRandomDirection();
        }

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

    }




