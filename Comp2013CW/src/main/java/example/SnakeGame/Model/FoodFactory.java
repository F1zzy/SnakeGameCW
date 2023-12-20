package example.SnakeGame.Model;

import example.Utilities.ImageUtil;
import example.SnakeGame.Model.GameObjects.FoodObjects.NegativeFood;
import example.SnakeGame.Model.GameObjects.FoodObjects.AiMoveableFood;
import example.SnakeGame.Model.GameObjects.FoodObjects.Food;
import example.SnakeGame.Model.GameObjects.FoodObjects.StaticFood;

import java.util.ArrayList;
import java.util.List;
/**
 * The FoodFactory class is responsible for creating and managing different types of food objects in the Snake game.
 */
public class FoodFactory {
    private static int numOfFood = 0;
    private List<Food> foodList;
    private List<Food> negativeFoodList;



    /**
     * Constructor for the FoodFactory class.
     * Initializes the lists for regular and negative food objects.
     */
    public FoodFactory(){
        this.foodList = new ArrayList<Food>();
        this.negativeFoodList = new ArrayList<Food>();
    }
    /**
     * Creates a new food object based on the specified type.
     *
     * @param foodType The type of food to create (1 = Static, 2 = Negative, 3 = AIMoveable).
     * @param x        The x-coordinate for the new food.
     * @param y        The y-coordinate for the new food.
     * @return A new Food object of the specified type.
     */
    public static Food createNewFood(int foodType  , int x ,int y) {
        numOfFood++;
        // Logic to create a random type of food
        int randomType = (int) (Math.random() * 10) + 5; // Assume there are three types of food

        int scoreValue;
        switch (foodType){
            case 1:
                return new StaticFood(ImageUtil.images.get(( "" + randomType)));
            case 2:
                return new NegativeFood(x , y);
            case 3:
                return new AiMoveableFood(ImageUtil.images.get("" + randomType));

        }
        return null;

    }
    /**
     * Gets the list of regular food objects.
     *
     * @return The list of regular food objects.
     */
    public List<Food> getFoodList() {
        return foodList;
    }
    /**
     * Adds a regular food object to the list.
     *
     * @param food The regular food object to add.
     */
    public void addList(Food food) {
        foodList.add(food);
    }
    /**
     * Removes a regular food object from the list.
     *
     * @param food The regular food object to remove.
     */
    public void removeList(Food food) {
        foodList.remove(food);
    }
    /**
     * Clears the list of regular food objects.
     */
    public void clearList() {
        foodList.clear();
    }
    /**
     * Gets the list of negative food objects.
     *
     * @return The list of negative food objects.
     */
    public List<Food> getNegativeFoodList() {
        return negativeFoodList;
    }
    /**
     * Adds a negative food object to the list.
     *
     * @param food The negative food object to add.
     */
    public void addNegativeList(Food food) {
        negativeFoodList.add(food);
    }

}
