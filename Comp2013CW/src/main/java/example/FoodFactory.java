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
        int randomType = (int) (Math.random() * 10) + 5; // Assume there are three types of food
        Image retImage = ImageUtil.images.get(( "" + randomType));
        int scoreValue;
//        switch (randomType) {
//            case 0:
//                retImage = ImageUtil.images.get("0");
//                scoreValue = 5;
//                break;
//            case 1:
//                retImage = ImageUtil.images.get("1");
//                scoreValue = 4;
//                break;
//            case 2:
//                retImage = ImageUtil.images.get("2");
//                scoreValue = 3;
//                break;
//            case 3:
//                retImage = ImageUtil.images.get("3");
//                scoreValue = 2;
//                break;
//            default:
//                retImage = ImageUtil.images.get("4");
//                scoreValue = 1;
//                break;
//
//        }

        return new Food(retImage , 1);
    }

    public static void reset() {
        numOfFood = 0;
    }
}
