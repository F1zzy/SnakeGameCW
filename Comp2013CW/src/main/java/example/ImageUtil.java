package example;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
	public static Map<String, Image> images = new HashMap<>();

	static {
		loadSnakeImages();
		loadObstacleImages();
		loadUIImages();
		loadSceneImages();


		images.put("logo", new Image("snake-logo.png"));
	}

	private static void loadSnakeImages() {
		Image SnakeHead = new Image(Settings.ReturnSnakeHeadPath(), 25, 25, true, true);
		Image SnakeBody = new Image(Settings.ReturnSnakeBodyPath(), 25, 25, true, true);

		// Store the resized images in the images map
		images.put("snake-head-right", SnakeHead);
		images.put("snake-body", SnakeBody);

		images.put("temp-snake-head" , SnakeHead);
		images.put("temp-snake-body" , SnakeBody);
	}

	private static void loadObstacleImages() {
		// obstacles

		//Each Fruit has been given its own Number. This number is used
		images.put("0", new Image("food-kiwi.png")); //Size 20px
		images.put("1", new Image("food-lemon.png")); //Size 25px
		images.put("2", new Image("food-litchi.png")); //Size 30px

		images.put("3", new Image("food-mango.png")); //Size 35px
		images.put("4", new Image("food-apple.png")); //Size 40px


		//For GamePlay Purposes The number of differenet fruits has been reduced.
		images.put("5", new Image("food-banana.png"));
		images.put("6", new Image("food-blueberry.png"));
		images.put("7", new Image("food-cherry.png"));
		images.put("8", new Image("food-durian.png"));

		images.put("9", new Image("food-grape.png"));
		images.put("10", new Image("food-grapefruit.png"));
		images.put("11",new Image("food-peach.png"));
		images.put("12", new Image("food-pear.png"));

		images.put("13", new Image("food-orange.png"));
		images.put("14", new Image("food-pineapple.png"));
		images.put("15", new Image("food-strawberry.png"));
		images.put("16", new Image("food-watermelon.png"));


		images.put("Poo", new Image("food-poo.png", 32, 32, true, true));
		images.put("raindrop", new Image("raindrop.png", 32, 32, true, true));
	}

	private static void loadUIImages() {
		images.put("MainMenu-background", new Image("MainMenu-Background-removebg-preview.png"));
		images.put("DefaultLevelState-background", new Image("UI-background.png", 900, 600, true, true));
		images.put("MultipleFoodLevelState-background", new Image("UIBackground1.jpg", 900, 600, true, true));
		images.put("SpeedBoostLevelState-background", new Image("UI-background2.png", 900, 600, true, true));
		images.put("AIMoveableFoodLevelState-background", new Image("UI-Background4.jpg", 900, 600, true, true));
		images.put("NegativeFoodLevelState-background", new Image("UI-Background4.jpg", 900, 600, true, true));
	}


	private static void loadSceneImages() {
		images.put("Fail-Scene", new Image("Fail-Scene.jpg"));
	}
	public static void changeSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-head-right");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(newImagePath, 25, 25, true, true);
		images.put("snake-head-right", newSnakeHead);
	}
	public static void changeSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-body");

		// Load and store the new snake head image
		Image newSnakeBody = new Image(newImagePath, 25, 25, true, true);
		images.put("snake-body", newSnakeBody);
	}

	public static void changeTempSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-head");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(newImagePath, 25, 25, true, true);
		images.put("temp-snake-head", newSnakeHead);
	}
	public static void changeTempSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-body");

		// Load and store the new snake head image
		Image newSnakeBody = new Image(newImagePath, 25, 25, true, true);
		images.put("temp-snake-body", newSnakeBody);
	}
}
