package example.SnakeGame.Model.GameObjects.FoodObjects;

import example.Utilities.ImageUtil;
import javafx.scene.paint.Color;

public class RainbowDrop extends Food {
    private  Color col;
    public RainbowDrop(int x, int y) {
        super(x,y);

        this.type = FoodType.RAINDROP;
        this.scoreValue = 1;
        this.image = ImageUtil.images.get("raindrop");
        this.x = x;
        this.y = y;
        this.col = Color.RED;
    }


    public void setX(){}
    public void setY(int num){this.y = num;}

    public int getY() {
        return this.y;
    }


    public Color getColor() {
        return this.col;
    }
}
