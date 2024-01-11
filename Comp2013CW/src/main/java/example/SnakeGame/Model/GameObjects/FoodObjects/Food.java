package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.SnakeGame.Model.GameObjects.GameObject;
import example.SnakeGame.Model.GameObjects.Snake;
import javafx.scene.image.Image;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a food object in the Snake game.
 */
public abstract class Food extends GameObject implements Serializable {
	int scoreValue;
	FoodType type;

	public Food() {

	}

	public boolean isAlive() {
		return isAlive;
	}

	public enum FoodType {
		NORMAL, NEGATIVE , MOVEABLE , RAINDROP
	}

	@Serial
	private static final long serialVersionUID = -3641221053272056036L;


    /**
     * Constructor to make a new Food object with a random image and position.
     */
    public Food(Image image) {
		this.isAlive = true;
		this.image = image;
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
    }
	public Food(int x , int y){}

    public Image getFoodImage(){return this.image;}
	public FoodType getType() {
		return this.type;
	}


	/**
	 * When the food is eaten by the snake.
	 *
	 * @param snake The snake object.
	 * @return
	 */
	public boolean eaten(Snake mySnake){

		// Check for collision between snake and food
		if (mySnake.getRectangle().intersects(this.getRectangle()) && isAlive && mySnake.isAlive)	{
			this.isAlive = false;
			// Increase the snake's length and update the score when food is eaten
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += scoreValue;
			System.out.println("Length " + mySnake.getLength() + "\t Score:" + mySnake.score);
			return  true;
		}
		return  false;
	}



}
