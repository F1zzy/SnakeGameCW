package example;

import javafx.scene.image.Image;

public class StaticFood extends  Food{


    /**
     * Constructor to make a new Food object with a random image and position.
     *
     * @param image
     */
    public StaticFood(Image image) {

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

}
