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
			System.out.println("Working Directory: " + System.getProperty("user.dir"));
			//printContents(directoryPath);

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


	
}
