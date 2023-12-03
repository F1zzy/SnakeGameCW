package example;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.Random;

import javafx.scene.image.Image;

import java.io.Serial;
import java.util.Random;

public class FoodFactory {
    private static int numOfFood = 0;

    public static Food createNewFood() {
        numOfFood++;
        // Logic to create a random type of food
        int randomType = (int) (Math.random() * 3); // Assume there are three types of food
        Image retImage = null;

        switch (randomType) {
            case 0:

            case 1:

            case 2:
                
            default:

        }
        return new Food(retImage);
    }

}
