package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import javafx.scene.image.Image;

public class MoveableFood extends Food {

    private int speed_XY;
    boolean up, down, left, right = true;

    /**
     * Constructor to make a new Food object with a random image and position.
     *
     * @param image
     */
    public MoveableFood(Image image) {
        super(image);
    }

    public void move() {
        //let the swarm move
        if (up) {
            y -= speed_XY;
        } else if (down) {
            y += speed_XY;
        } else if (left) {
            x -= speed_XY;
        } else if (right) {
            x += speed_XY;
        }
    }
}
