package example;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;
import javax.imageio.ImageIO;

public class GameUtil
{
	public static Image getImage(String imagePath)
	{
		URL imageURL = GameUtil.class.getClassLoader().getResource(imagePath);


		BufferedImage image = null;
		try
		{
			image = ImageIO.read(imageURL);
		} catch (Exception e)
		{
			System.err.println("ERROR : SPECIFIC IMAGE NOT FOUND !\n");
			//System.out.println(System.getProperty("java.class.path"));
			//System.out.println("Working Directory: " + System.getProperty("user.dir"));
			//printMainDirectoryContents();

			e.printStackTrace();
		}

		return image;
	}

	public static Image rotateImage(final BufferedImage bufferedImage, final int degree)
	{
	int width = bufferedImage.getWidth();
	int height = bufferedImage.getHeight();
	int transparency = bufferedImage.getColorModel().getTransparency();

	BufferedImage rotatedImage;
	Graphics2D graphics2d;

	(graphics2d = (rotatedImage = new BufferedImage(width, height, transparency)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

	graphics2d.rotate(Math.toRadians(degree), width / 2, height / 2);
	graphics2d.drawImage(bufferedImage, 0, 0, null);
	graphics2d.dispose();

	return rotatedImage;

	}

	public static void printMainDirectoryContents() {
		String currentDirectoryPath = "src/main/resources";
		File currentDirectory = new File(currentDirectoryPath);

		if (currentDirectory.exists() && currentDirectory.isDirectory()) {
			File[] files = currentDirectory.listFiles();

			if (files != null) {
				for (File file : files) {
					System.out.println(file.getName());
				}
			} else {
				System.out.println("The main directory is empty.");
			}
		} else {
			System.out.println("Unable to access the main directory.");
		}
	}

	
}
