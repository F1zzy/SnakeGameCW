package example;

//import sun.awt.windows.WPathGraphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static java.lang.Thread.sleep;

/**
 * 
 * @Project Snakee
 * @Description
 * Play the game
 * @Author Abdullah Tukur
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

//		super.paint(g);
//		g.drawImage(background, 0, 0, null);
//
//		// Determine the state of the game.
//		if (mySnake.isAlive) {
//			mySnake.draw(g);
//			if (food.isAlive) {
//				food.draw(g);
//				//food.eaten(mySnake);
//			} else {
//				food = new Food();
//			}
//		} else {
//			//g.drawImage(fail, 0, 0, getWidth(), getHeight(), null);
//		}
//		drawScore(g);
	}

	public void drawScore(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + mySnake.score, 20, 40);
	}
/*
	public static void main(String[] args) {

		//Model model = new Model();
		//View view = new View(model);
		//Controller controller = new Controller(model);


		new Play().loadFrame();
		MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");

	}

*/

    public static void main(String[] args) throws InterruptedException {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model);

        // Set up the frame
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Set up the panel

        view.panel.setFocusable(true);
        view.panel.addKeyListener(controller);

        // Add the panel to the frame
        frame.add(view.panel);

        // Add a window listener to handle closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
		//MusicPlayer.getMusicPlay("src/main/resources/frogger.mp3");

        // Game loop
        while (true) {
            model.updateGame();
			if(model.EndGame) {
				sleep(20);
				break;
			}
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