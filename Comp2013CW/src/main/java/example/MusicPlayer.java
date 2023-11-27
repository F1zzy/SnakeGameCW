package example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * The MusicPlayer class is responsible for playing music in a separate thread.
 */
public class MusicPlayer extends Thread
{
	private String musicFilePath;
	public Player musicPlayer;
	/**
	 * Constructs a MusicPlayer with the given music file path.
	 *
	 * @param musicFilePath The path of the music file to be played.
	 */
	public MusicPlayer(String filename)
	{
		this.musicFilePath = filename;
	}

	/**
	 * Plays the music in a separate thread.
	 */
	public void play()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				while (true) {
					try {
						//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
						musicPlayer = new Player(new BufferedInputStream(new FileInputStream(musicFilePath)));
						musicPlayer.play();

					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}.start();
	}


	/**
	 * Simplifies the process of creating a MusicPlayer instance and starting music playback.
	 *
	 * @param musicFilePath The path of the music file to be played.
	 */
	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
