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
	}

	private static void loadSnakeImages() {
		images.put("snake-head-right", new Image("snake-head-right.png"));
		images.put("snake-body", new Image("snake-body.png"));
	}

	private static void loadObstacleImages() {
		// obstacles
		images.put("0", new Image("food-kiwi.png"));
		images.put("1", new Image("food-lemon.png"));
		images.put("2", new Image("food-litchi.png"));
		images.put("3", new Image("food-mango.png"));
		images.put("4", new Image("food-apple.png"));
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
	}

	private static void loadUIImages() {
		images.put("UI-background", new Image("UI-background.png"));
		//images.put("MainMenu-Background", new Image("MainMenu-Background.png"));
	}


	private static void loadSceneImages() {
		images.put("Fail-Scene", new Image("Fail-Scene.jpg"));
	}
}
