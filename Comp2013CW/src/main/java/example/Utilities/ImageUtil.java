package example.Utilities;

import example.Settings.Settings;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
	public static Map<String, Image> images = new HashMap<>();
	private static final int SNAKE_BODY_SIZE = 25;

	static {

		images.put("logo", new Image(ImageUtil.class.getResource("/Images/logo/snake-logo.png").toString()));

		loadSnakeImages();
		loadObstacleImages();
		loadUIImages();
		loadSceneImages();



	}

	private static void loadSnakeImages() {
		System.out.println(Settings.ReturnSnakeHeadPath());
		Image SnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(Settings.ReturnSnakeHeadPath())), SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		Image SnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(Settings.ReturnSnakeBodyPath())) , SNAKE_BODY_SIZE , SNAKE_BODY_SIZE , true , true );

		// Store the resized images in the images map
		images.put("snake-head-right", SnakeHead);
		images.put("snake-body", SnakeBody);

		images.put("temp-snake-head" , SnakeHead);
		images.put("temp-snake-body" , SnakeBody);
	}

	private static void loadObstacleImages() {
		// obstacles
		String filePath = "/Images/foodImages/";
		//Each Fruit has been given its own Number. This number is used
		images.put("0", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-kiwi.png"))));
		images.put("1", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-lemon.png"))));
		images.put("2", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-litchi.png"))));
		images.put("3", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-mango.png"))));
		images.put("4", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-apple.png"))));
		images.put("5", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-banana.png"))));
		images.put("6", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-blueberry.png"))));
		images.put("7", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-cherry.png"))));
		images.put("8", new Image(String.valueOf(ImageUtil.class.getResource(filePath +"food-durian.png"))));
		images.put("9", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-grape.png"))));
		images.put("10", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-grapefruit.png"))));
		images.put("11", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-peach.png"))));
		images.put("12", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-pear.png"))));
		images.put("13", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-orange.png"))));
		images.put("14", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-pineapple.png"))));
		images.put("15", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-strawberry.png"))));
		images.put("16", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-watermelon.png"))));

		images.put("Poo", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-poo.png"))));
		images.put("raindrop", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "raindrop.png"))));
	}

	private static void loadUIImages() {
		images.put("MainMenu-background",
				new Image(String.valueOf(ImageUtil.class.getResource("/Images/Miscellaneous/MainMenu-Background-removebg-preview.png")) ,
						900 , 600 , true , true));
		String filePath = "/Images/Level_Backgrounds/";

		images.put("DefaultLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-background-1.png")),
						900, 600, true, true));

		images.put("MultipleFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-2.png")),
						900, 600, true, true));

		images.put("SpeedBoostLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-3.jpg")),
						900, 600, true, true));

		images.put("AIMoveableFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-4.jpg")),
						900, 600, true, true));

		images.put("NegativeFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-5.png")),
						900, 600, true, true));
	}


	private static void loadSceneImages() {
		images.put("Fail-Scene", new Image(String.valueOf(ImageUtil.class.getResource("/Images/Miscellaneous/Fail-Scene.jpg"))));
	}
	public static void changeSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-head-right");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, 25, 25, true, true);
		images.put("snake-head-right", newSnakeHead);
	}
	public static void changeSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-body");

		// Load and store the new snake head image
		Image newSnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, 25, 25, true, true);
		images.put("snake-body", newSnakeBody);
	}

	public static void changeTempSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-head");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, 25, 25, true, true);
		images.put("temp-snake-head", newSnakeHead);
	}
	public static void changeTempSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-body");

		// Load and store the new snake head image
		System.out.println(newImagePath);
		Image newSnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, 25, 25, true, true);
		images.put("temp-snake-body", newSnakeBody);
	}
}
