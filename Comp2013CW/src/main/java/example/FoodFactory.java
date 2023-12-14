package example;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.Random;

public class FoodFactory {
    private static int numOfFood = 0;
    private List<Food> foodList;
    private List<Food> negativeFoodList;
    //1 = Static 2 = Negative 3 = AIMoveable
    public FoodFactory(){
        this.foodList = new ArrayList<Food>();
        this.negativeFoodList = new ArrayList<Food>();
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

}
