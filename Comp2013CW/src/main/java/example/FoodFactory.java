package example;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.Random;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.Random;

public class FoodFactory {
    private static int numOfFood = 0;

    public static Food createNewFood(boolean isNegative) {
        numOfFood++;
        // Logic to create a random type of food
        int randomType = (int) (Math.random() * 10) + 5; // Assume there are three types of food
        Image retImage = ImageUtil.images.get(( "" + randomType));
        int scoreValue;
        return new Food(retImage , 1);
    }

}
