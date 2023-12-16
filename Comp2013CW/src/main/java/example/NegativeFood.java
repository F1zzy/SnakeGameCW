package example;

import javafx.scene.image.Image;

public class NegativeFood extends Food{
    /**
     * Constructor to make a new Food object with a random image and position.
     *
     *
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
