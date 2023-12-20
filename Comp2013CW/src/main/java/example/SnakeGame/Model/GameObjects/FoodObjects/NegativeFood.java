package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.Utilities.ImageUtil;
/**
 * Represents a negative food object in the Snake game.
 * Negative Points
 */
public class NegativeFood extends Food {
    /**
     * Constructor to create a new NegativeFood object with a specific position.
     *
     * @param x The x-coordinate of the NegativeFood object.
     * @param y The y-coordinate of the NegativeFood object.
     */
    public NegativeFood(int x , int y){
        super(x, y);
        this.image = ImageUtil.images.get("Poo");

        this.isAlive = true;
        this.type = FoodType.NEGATIVE;

        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.scoreValue = -1;

        this.x = x;
        this.y = y;

    }
}
