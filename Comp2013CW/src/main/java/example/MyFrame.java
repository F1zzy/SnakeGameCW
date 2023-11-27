package example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @Project Snakee
 * @Description Load the game and refresh it constantly
 * @Author Abdullah Tukur
 * @version 0.1
 */


public abstract class MyFrame extends JPanel implements KeyListener
{
	private static final long serialVersionUID = -3149926831770554380L;

	private static final int FRAME_WIDTH = 870;
	private static final int FRAME_HEIGHT = 560;
	private static final int SNAKE_SPEED = 5;

	public JFrame jFrame = new JFrame();

	public MyFrame()
	{
		//jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("snake-logo.png")));
	}

	public void loadFrame()
	{
		/**
		 * Configures and loads the game frame.
		 */
		this.setDoubleBuffered(true);
		jFrame.add(this);
		jFrame.addKeyListener(this);

		jFrame.setTitle("Snakee Yipee");
		jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jFrame.setLocationRelativeTo(null);
		//Closing Window safely
		jFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				System.exit(0);
			}
		});
		jFrame.setVisible(true);

		new gameThread().start();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * Thread for continuously updating and repainting the Game Frame.
	 */
	class gameThread extends Thread
	{
		@Override
		public void run()
		{
			super.run();
			while (true)
			{
				repaint();
				try
				{
					sleep(30);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * The Snake Object.
	 */
	public static class MySnake extends SnakeObject implements movable
	{
		private int speed_XY;
		private int length;
		private int numOfBodies; // ?
		public int score = 0;

		private static final BufferedImage IMG_SNAKE_HEAD = (BufferedImage) ImageUtil.images.get("snake-head-right");

		public static List<Point> bodyPoints = new LinkedList<>();

		private static BufferedImage newImgSnakeHead;
		boolean up, down, left, right = true;

		public MySnake(int x, int y)
		{
			this.isAlive = true;
			this.x = x;
			this.y = y;
			this.image = ImageUtil.images.get("snake-body");
			this.width = image.getWidth(null);
			this.height = image.getHeight(null);

			this.speed_XY = SNAKE_SPEED;
			this.length = 1;

			/*
			 * Attention : ?
			 */
			this.numOfBodies = width / speed_XY;
			newImgSnakeHead = IMG_SNAKE_HEAD;

		}

		public int getLength()
		{
			return length;
		}

		public void changeLength(int length)
		{
			this.length = length;
		}

		public void keyPressed(KeyEvent e)
		{
			// Check The Key pressed. if not arrow keys ignore
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				if (!down)
				{
					up = true;
					down = false;
					left = false;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
				}
				break;

			case KeyEvent.VK_DOWN:
				if (!up)
				{
					up = false;
					down = true;
					left = false;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);
				}
				break;

			case KeyEvent.VK_LEFT:
				if (!right)
				{
					up = false;
					down = false;
					left = true;
					right = false;

					newImgSnakeHead = (BufferedImage) GameUtil.rotateImage(IMG_SNAKE_HEAD, -180);

				}
				break;

			case KeyEvent.VK_RIGHT:
				if (!left)
				{
					up = false;
					down = false;
					left = false;
					right = true;

					newImgSnakeHead = IMG_SNAKE_HEAD;
				}

			default:
				break;
			}
		}


		public void move()
		{
			//let the swarm move
			if (up)
			{
				y -= speed_XY;
			} else if (down)
			{
				y += speed_XY;
			} else if (left)
			{
				x -= speed_XY;
			} else if (right)
			{
				x += speed_XY;
			}

		}

		@Override
		public void draw(Graphics g)
		{
			outofBounds();
			eatBody();

			bodyPoints.add(new Point(x, y));

			if (bodyPoints.size() == (this.length + 1) * numOfBodies)
			{
				bodyPoints.remove(0);
			}
			g.drawImage(newImgSnakeHead, x, y, null);
			drawBody(g);

			move();
		}

		/**
		 * Checks for collisions with the snake's own body.
		 */
		public void eatBody()
		{
			for (Point point : bodyPoints)
			{
				for (Point point2 : bodyPoints)
				{
					if (point.equals(point2) && point != point2)
					{
						this.isAlive = false;
					}
				}
			}
		}
		/**
		 * Draws the snake's body on the screen.
		 */
		public void drawBody(Graphics g)
		{
			int length = bodyPoints.size() - 1 - numOfBodies;

			for (int i = length; i >= numOfBodies; i -= numOfBodies)
			{
				Point point = bodyPoints.get(i);
				g.drawImage(this.image, point.x, point.y, null);
			}
		}
		/**
		 * Checks if the snake is out of bounds and sets isAlive fasle.
		 */
		private void outofBounds()
		{
			boolean xOut = (x <= 0 || x >= (870 - width));
			boolean yOut = (y <= 0 || y >= (560 - height));
			if (xOut || yOut)
			{
				isAlive = false;
			}
		}
	}
	/**
	 * Abstract class representing a generic Snake object.
	 */
	public abstract static class SnakeObject
	{
		int x;
		int y;
		Image image;
		int width;
		int height;

		public boolean isAlive;

		/**
		 * Abstract method to draw the SnakeObject on the screen.
		 */
		public abstract void draw(Graphics g);

		public Rectangle getRectangle()
		{
			return new Rectangle(x, y, width, height);
		}
	}
}
