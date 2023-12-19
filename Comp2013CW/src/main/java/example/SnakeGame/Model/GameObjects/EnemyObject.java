package example.SnakeGame.Model.GameObjects;

import example.SnakeGame.Model.Model;
import example.Utilities.ImageUtil;

public class EnemyObject extends GameObject{
    private static final int Enemy_SPEED = 2;


    public  EnemyObject(int x , int y) {

       this.isAlive = true;
       this.x = x;
       this.y = y;
       this.image = ImageUtil.images.get("Enemy");
       this.width = (int) image.getWidth();
       this.height = (int) image.getHeight();

    }

    public void eaten(Model model){
        Snake mySnake = model.getSnake();

        // Check for collision between snake and food
        if (mySnake.getRectangle().intersects(this.getRectangle()) && isAlive && mySnake.isAlive)	{
            model.setEndGame(true);
        }

    }

    public void move(double snakeX, double snakeY) {
        // Calculate the direction from the enemy to the snake
        double directionX = snakeX - this.getX();
        double directionY = snakeY - this.getY();

        // Calculate the distance between the enemy and the snake
        double distance = Math.sqrt(directionX * directionX + directionY * directionY);

        // Normalize the direction vector
        directionX /= distance;
        directionY /= distance;

        // Calculate the new position based on the normalized direction and movement speed
        this.x += (int) (directionX * Enemy_SPEED);
        this.y += (int) (directionY * Enemy_SPEED);
    }

}
