package example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 * 
 * @Project Snakee
 * @Description
 * Play the game
 * @Author Sigurður Sigurðardóttir
 * @version
 */ 

public class Play extends MyFrame {

	public MySnake mySnake = new MySnake(100, 100);// x , y
	public Food food = new Food();

	public Image background = ImageUtil.images.get("UI-background");
	public Image fail = ImageUtil.images.get("game-scene-01");

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		mySnake.keyPressed(e);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);

		// Determine the state of the game.
		if (mySnake.isAlive) {
			mySnake.draw(g);
			if (food.isAlive) {
				food.draw(g);
				food.eaten(mySnake);
			} else {
				food = new Food();
			}
		} else {
			g.drawImage(fail, 0, 0, null);
		}
		drawScore(g);
	}

	public void drawScore(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + mySnake.score, 20, 40);
	}

	public static void main(String[] args) {


		new Play().loadFrame();
//		MusicPlayer.getMusicPlay("src/example/frogger.mp3");

	}


/*
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		// frame.setSize(400,600);
		frame.setBounds(450, 200, 920, 600);
		// frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel panel = new SnakePanel();
		frame.add(panel);

		frame.setVisible(true);

		// Play the background music.
		MusicPlayer.getMusicPlay("resource\\music\\background.mp3");
	} 
*/







}