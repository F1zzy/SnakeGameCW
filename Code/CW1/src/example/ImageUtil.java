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
		images.put("snake-head-right", GameUtil.getImage("example/snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage("example/snake-body.png"));
	}

	private static void loadObstacleImages() {
		// obstacles
		images.put("0", GameUtil.getImage("example/food-kiwi.png"));
		images.put("1", GameUtil.getImage("example/food-lemon.png"));
		images.put("2", GameUtil.getImage("example/food-litchi.png"));
		images.put("3", GameUtil.getImage("example/food-mango.png"));
		images.put("4", GameUtil.getImage("example/food-apple.png"));
		images.put("5", GameUtil.getImage("example/food-banana.png"));
		images.put("6", GameUtil.getImage("example/food-blueberry.png"));
		images.put("7", GameUtil.getImage("example/food-cherry.png"));
		images.put("8", GameUtil.getImage("example/food-durian.png"));
		images.put("9", GameUtil.getImage("example/food-grape.png"));
		images.put("10", GameUtil.getImage("example/food-grapefruit.png"));
		images.put("11", GameUtil.getImage("example/food-peach.png"));
		images.put("12", GameUtil.getImage("example/food-pear.png"));
		images.put("13", GameUtil.getImage("example/food-orange.png"));
		images.put("14", GameUtil.getImage("example/food-pineapple.png"));
		images.put("15", GameUtil.getImage("example/food-strawberry.png"));
		images.put("16", GameUtil.getImage("example/food-watermelon.png"));
	}

	private static void loadUIImages() {
		File directory = new File("src/example/Backgrounds");
		if (directory.exists() && directory.isDirectory()) {
			String[] fileNames = directory.list();
			if (fileNames != null) {
				for (String fileName : fileNames) {
					System.out.println(fileName);
				}
			}
		} else {
			System.out.println("The specified path is not a valid directory.");
		}
		images.put("UI-background", GameUtil.getImage("/example/UI-background2.png"));

	}

	private static void loadSceneImages() {
		images.put("game-scene-01", GameUtil.getImage("example/game-scene-01.jpg"));

	}
}
