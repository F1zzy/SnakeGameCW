package example;

import java.awt.Graphics;
import java.io.Serial;
import java.util.Random;

/**
 * Represents a food object in the Snake game.
 */
public class Food extends MyFrame.SnakeObject  {

	@Serial
	private static final long serialVersionUID = -3641221053272056036L;


	/**
	 * Constructor to make a new Food object with a random image and position.
	 */
	public Food()	{
		this.isAlive = true;

		this.image = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

		this.width = image.getWidth(null);
		this.height = image.getHeight(null);

		// Set random coordinates for the food within the game panel
		this.x = (int) (Math.random() * (870 - width + 10));
		this.y = (int) (Math.random() * (560 - height - 40));
	}
	/**
	 * When the food is eaten by the snake.
	 *
	 * @param snake The snake object.
	 */
	public void eaten(MyFrame.MySnake mySnake)	{

		// Check for collision between snake and food
		if (mySnake.getRectangle().intersects(this.getRectangle()) && isAlive && mySnake.isAlive)		{
			this.isAlive = false;
			// Increase the snake's length and update the score when food is eaten
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 1;
		}
	}
	/**
	 * Draws the food on the game panel.
	 *
	 */
	@Override
	public void draw(Graphics g)
	{	// Draw the food on the game panel
		g.drawImage(image, x, y, null);
	}
}
