package example;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class ImageUtil
{
	public static Map<String, Image> images = new HashMap<>();
	private static final String IMAGE_PATH = "example/";
	static
	{
		loadSnakeImages();
		loadObstacleImages();
		loadUIImages();
		loadSceneImages();
	}

	private static void loadSnakeImages() {
		// snake
		images.put("snake-head-right", GameUtil.getImage("snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("snake-body.png"));
	}

	private static void loadObstacleImages() {
		// obstacles
		images.put("0", GameUtil.getImage("food-kiwi.png"));
		images.put("1", GameUtil.getImage("food-lemon.png"));
		images.put("2", GameUtil.getImage("food-litchi.png"));
		images.put("3", GameUtil.getImage("food-mango.png"));
		images.put("4", GameUtil.getImage("food-apple.png"));
		images.put("5", GameUtil.getImage("food-banana.png"));
		images.put("6", GameUtil.getImage("food-blueberry.png"));
		images.put("7", GameUtil.getImage("food-cherry.png"));
		images.put("8", GameUtil.getImage("food-durian.png"));
		images.put("9", GameUtil.getImage("food-grape.png"));
		images.put("10", GameUtil.getImage("food-grapefruit.png"));
		images.put("11", GameUtil.getImage("food-peach.png"));
		images.put("12", GameUtil.getImage("food-pear.png"));
		images.put("13", GameUtil.getImage("food-orange.png"));
		images.put("14", GameUtil.getImage("food-pineapple.png"));
		images.put("15", GameUtil.getImage("food-strawberry.png"));
		images.put("16", GameUtil.getImage("food-watermelon.png"));
	}

	private static void loadUIImages() {
		images.put("UI-background", GameUtil.getImage("UI-background.png"));

	}

	private static void loadSceneImages() {
		images.put("game-scene-01", GameUtil.getImage("game-scene-01.jpg"));

	}
}
