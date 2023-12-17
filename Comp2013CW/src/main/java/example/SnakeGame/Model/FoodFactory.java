package example.SnakeGame.Model;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.NegativeFood;
import example.SnakeGame.Model.GameObjects.FoodObjects.RainbowDrop;
import example.SnakeGame.Model.GameObjects.FoodObjects.AiMoveableFood;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.FoodObjects.StaticFood;

import java.util.ArrayList;
import java.util.List;

public class FoodFactory {
    private static int numOfFood = 0;
    private List<Food> foodList;
    private List<Food> negativeFoodList;

    private List<RainbowDrop> rainbowDropList;

    //1 = Static 2 = Negative 3 = AIMoveable
    public FoodFactory(){
        this.foodList = new ArrayList<Food>();
        this.negativeFoodList = new ArrayList<Food>();
        this.rainbowDropList = new ArrayList<RainbowDrop>();
    }

    public static Food createNewFood(int foodtype  , int x ,int y) {

        // Logic to create a random type of food
        int randomType = (int) (Math.random() * 10) + 5; // Assume there are three types of food

        int scoreValue;
        switch (foodtype){
            case 1:
                return new StaticFood(ImageUtil.images.get(( "" + randomType)));
            case 2:
                return new NegativeFood(x , y);
            case 3:
                return new AiMoveableFood(ImageUtil.images.get("" + randomType));
            case 4:
                return new RainbowDrop(x , y);
        }
        return null;

    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void AddList(Food food) {
        foodList.add(food);
    }

    public void RemoveList(Food food) {
        foodList.remove(food);
    }

    public void clearList() {
        foodList.clear();
    }

    public List<Food> getNegativeFoodList() {
        return negativeFoodList;
    }

    public void AddNegativeList(Food food) {
        negativeFoodList.add(food);
    }

    public void RemoveNeagtiveList(Food food) {
        negativeFoodList.remove(food);
    }

    public List<RainbowDrop> getRainbowDropList() {
        return rainbowDropList;
    }

    public void AddRainbowDropList(RainbowDrop food) {
        rainbowDropList.add(food);
    }
}
