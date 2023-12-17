package example.Utilities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static SoundManager instance;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer AudioPlayer;

    private SoundManager() {

    }

    public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void playBackgroundMusic() {
        Media backgroundMusic = SoundUtil.getBackgroundMusic();
        backgroundMusicPlayer= new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repeat indefinitely
        backgroundMusicPlayer.play();
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public void playGameOver(){
        SoundUtil.getAudioClip("gameOver").play();
    }
    public void PlayEatFood(){
        SoundUtil.getAudioClip("eatFood").play();
    }


    // Other methods for managing game sounds...

    private static class SoundUtil {
        private static final String SOUND_DIRECTORY = "/sounds/";

        // Map to store loaded sounds
        private static Map<String, AudioClip> sounds = new HashMap<>();
        private static Media backgroundMusic = new Media(SoundUtil.class.getResource(SOUND_DIRECTORY + "background-music.mp3").toExternalForm());

        static {
            loadSounds();
        }

        private static void loadSounds() {

            //loadAndStoreSound("eatBody", "eat_body.mp3");
            loadAndStoreSound("gameOver", "game-over.mp3");
            loadAndStoreSound("eatFood", "eat-food.mp3");

        }

        private static void loadAndStoreSound(String soundName, String soundFileName) {
            String soundPath = SOUND_DIRECTORY + soundFileName;
            AudioClip audioClip = new AudioClip(SoundUtil.class.getResource(soundPath).toExternalForm());
            sounds.put(soundName, audioClip);
        }

        public static AudioClip getAudioClip(String soundName) {
            return sounds.get(soundName);
        }
        public static Media getBackgroundMusic(){
            return backgroundMusic;
        }

    }
}
