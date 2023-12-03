package example;

public class FoodFactory {

    public static Food createNewFood() {
        // Logic to create a random type of food
        int randomType = (int) (Math.random() * 3); // Assume there are three types of food

        return new Food();
    }
}
