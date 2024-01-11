package example.Utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;
/**
 * The Singleton SoundManager class manages the playing and stopping of game sounds, including background music and various audio clips.
 */
public class SoundManager {
    private static SoundManager instance;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer AudioPlayer;

    private SoundManager() {}
    /**
     * Gets the singleton instance of the SoundManager.
     *
     * @return The singleton instance of the SoundManager.
     */
    public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }
    /**
     * Plays the background music.
     */
    public void playBackgroundMusic() {
        Media backgroundMusic = SoundUtil.getBackgroundMusic();
        backgroundMusicPlayer= new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repeat indefinitely
        backgroundMusicPlayer.play();
    }
    /**
     * Stops the background music.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }
    /**
     * Plays the game over sound.
     */
    public void playGameOver(){
        SoundUtil.getAudioClip("gameOver").play();
    }

    /**
     * Plays the munch sound.
     */
    public void PlayEatFood(){
        SoundUtil.getAudioClip("eatFood").play();
    }

    /**
     * Plays the invisible  sound.
     */
    public  void PlayInvisible(){SoundUtil.getAudioClip("invisible").play();}

    /**
     * Plays the negative food  sound.
     */
    public  void PlayFart(){SoundUtil.getAudioClip("fart").play();}
    /**
     * Plays the boost level sound.
     */
    public void PlayBoost(){SoundUtil.getAudioClip("boost").play();}
    /**
     * The SoundUtil class provides utility methods for loading and managing audio clips.
     */
    private static class SoundUtil {
        private static final String SOUND_DIRECTORY = "/sounds/";

        // Map to store loaded sounds
        private static Map<String, AudioClip> sounds = new HashMap<>();
        private static Media backgroundMusic = new Media(SoundUtil.class.getResource(SOUND_DIRECTORY + "background-music.mp3").toExternalForm());

        static {
            loadSounds();
        }
        // Load sound files during class initialization
        private static void loadSounds() {


            loadAndStoreSound("gameOver", "game-over.mp3");
            loadAndStoreSound("eatFood", "eat-food.mp3");
            loadAndStoreSound("invisible", "invisible.mp3");
            loadAndStoreSound("fart", "fart.mp3");
            loadAndStoreSound("boost", "boost.mp3");

        }
        // Load and store a sound in the sounds map
        private static void loadAndStoreSound(String soundName, String soundFileName) {
            String soundPath = SOUND_DIRECTORY + soundFileName;
            AudioClip audioClip = new AudioClip(SoundUtil.class.getResource(soundPath).toExternalForm());
            sounds.put(soundName, audioClip);
        }
        /**
         * Gets the audio clip associated with the specified sound name.
         *
         * @param soundName The name of the sound.
         * @return The audio clip associated with the specified sound name.
         */
        public static AudioClip getAudioClip(String soundName) {
            return sounds.get(soundName);
        }
        /**
         * Gets the background music media.
         *
         * @return The background music media.
         */
        public static Media getBackgroundMusic(){
            return backgroundMusic;
        }

    }
}
