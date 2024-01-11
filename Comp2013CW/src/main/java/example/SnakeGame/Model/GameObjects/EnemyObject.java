package example.SnakeGame.Model.GameObjects;

import example.SnakeGame.Model.Model;
import example.Utilities.ImageUtil;

public class EnemyObject extends GameObject{
    private static final int Enemy_SPEED = 1;


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
        double interpolationFactor = 0.1;
        // Calculate the direction from the enemy to the snake
        double directionX = snakeX - this.getX();
        double directionY = snakeY - this.getY();

        // Calculate the distance between the enemy and the snake
        double distance = Math.sqrt(directionX * directionX + directionY * directionY);

        // Normalize the direction vector
        directionX /= distance;
        directionY /= distance;

        // Calculate the target position based on the normalized direction and movement speed
        double targetX = this.getX() + directionX * Enemy_SPEED;
        double targetY = this.getY() + directionY * Enemy_SPEED;

        // Linear interpolation for smoother movement
        this.x = (int) lerp(this.getX(), targetX, interpolationFactor);
        this.y = (int) lerp(this.getY(), targetY, interpolationFactor);
    }

    private double lerp(double start, double end, double interpolationFactor) {
        return start + interpolationFactor * (end - start);
    }

}
