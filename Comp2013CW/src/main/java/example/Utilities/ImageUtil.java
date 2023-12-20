package example.Utilities;

import example.MainMenu;
import example.Settings.Settings;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
/**
 * The ImageUtil class provides utility methods for loading and managing images used in the game application.
 */
public class ImageUtil {

	// Map to store images with corresponding names
	public static Map<String, Image> images = new HashMap<>();
	private static final int SNAKE_BODY_SIZE = 25;
	private  static  final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
	private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

	// Static block to load images during class initialization
	static {
		//Load Logo
		images.put("logo", new Image(ImageUtil.class.getResource("/Images/logo/snake-logo.png").toString()));

		loadSnakeImages();
		loadObstacleImages();
		loadUIImages();
		loadSceneImages();

	}

	// Load snake-related images
	private static void loadSnakeImages() {
		System.out.println(Settings.ReturnSnakeHeadPath());
		Image SnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(Settings.ReturnSnakeHeadPath())), SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		Image SnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(Settings.ReturnSnakeBodyPath())) , SNAKE_BODY_SIZE , SNAKE_BODY_SIZE , true , true );

		// Store the resized images in the images map
		images.put("snake-head-right", SnakeHead);
		images.put("snake-body", SnakeBody);

		images.put("temp-snake-head" , SnakeHead);
		images.put("temp-snake-body" , SnakeBody);

		//Make Enemy Size. Make size: 50
		images.put("Enemy" , new Image(String.valueOf(ImageUtil.class.getResource("/Images/Snake_Images/Snake_Heads/Enemy.png"))
				, 50, 50, true, true));

	}

	// Load obstacle-related images
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

		images.put("Poo", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "food-poo.png")) ,
				30 , 30 , true , true));
		images.put("raindrop", new Image(String.valueOf(ImageUtil.class.getResource(filePath + "raindrop.png"))));
	}

	// Load UI-related images
	private static void loadUIImages() {
		images.put("MainMenu-background",
				new Image(String.valueOf(ImageUtil.class.getResource("/Images/Miscellaneous/MainMenu-Background-removebg-preview.png"))));
		String filePath = "/Images/Level_Backgrounds/";

		images.put("DefaultLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-background-1.png")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));

		images.put("MultipleFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-2.png")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));

		images.put("SpeedBoostLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-3.jpg")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));

		images.put("AIMoveableFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-4.jpg")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));

		images.put("NegativeFoodLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-5.png")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));


		images.put("InvisibleLevelState-background",
				new Image(String.valueOf(ImageUtil.class.getResource(filePath + "Game-Background-6.png")),
						FRAME_WIDTH, FRAME_HEIGHT, true, true));
	}

	// Load scene-related images
	private static void loadSceneImages() {
		images.put("Fail-Scene", new Image(String.valueOf(ImageUtil.class.getResource("/Images/Miscellaneous/Fail-Scene.jpg"))));
	}
	//Image utitliy Functions

	/**
	 * Changes the snake head image in the images map to the specified image.
	 *
	 * @param newImagePath The path of the new snake head image.
	 */
	public static void changeSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-head-right");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		images.put("snake-head-right", newSnakeHead);
	}

	/**
	 * Changes the snake body image in the images map to the specified image.
	 *
	 * @param newImagePath The path of the new snake body image.
	 */
	public static void changeSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("snake-body");

		// Load and store the new snake head image
		Image newSnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		images.put("snake-body", newSnakeBody);
	}

	/**
	 * Changes the temporary snake head image in the images map to the specified image.
	 *
	 * @param newImagePath The path of the new temporary snake head image.
	 */
	public static void changeTempSnakeHeadImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-head");

		// Load and store the new snake head image
		Image newSnakeHead = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		images.put("temp-snake-head", newSnakeHead);
	}

	/**
	 * Changes the temporary snake body image in the images map to the specified image.
	 *
	 * @param newImagePath The path of the new temporary snake body image.
	 */
	public static void changeTempSnakeBodyImage(String newImagePath ) {
		// Remove the old snake head image from the map
		images.remove("temp-snake-body");

		// Load and store the new snake head image
		System.out.println(newImagePath);
		Image newSnakeBody = new Image(String.valueOf(ImageUtil.class.getResource(newImagePath))
				, SNAKE_BODY_SIZE, SNAKE_BODY_SIZE, true, true);
		images.put("temp-snake-body", newSnakeBody);
	}
}
