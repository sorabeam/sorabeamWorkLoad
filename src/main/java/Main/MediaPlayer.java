package Main;

import Main.GameLogic.GameLogic;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.HashMap;
import java.util.Objects;

/**
 * Manages background music (BGM) and sound effects (SFX) for the game.
 * <p>
 * This class uses the Singleton pattern so that only one audio manager
 * exists during the program execution. It maintains a playlist of sound
 * keys mapped to file paths, preloads frequently used sound effects,
 * and controls playback and volume updates.
 */
public class MediaPlayer {

    /**
     * Stores mapping between sound keys and file paths.
     */
    private final HashMap<String, String> playList;

    /**
     * Stores preloaded sound effects for faster playback.
     */
    private final HashMap<String, AudioClip> sfxCache;

    /**
     * Handles background music playback.
     */
    private javafx.scene.media.MediaPlayer bgmPlayer;

    /**
     * Singleton instance of the MediaPlayer class.
     */
    private static MediaPlayer instance;

    /**
     * Returns the single instance of the MediaPlayer (Singleton Pattern).
     * If the instance does not exist, it will be created.
     *
     * @return the single MediaPlayer instance
     */
    public static MediaPlayer getInstance() {
        if (instance == null)
            instance = new MediaPlayer();
        return instance;
    }

    /**
     * Initializes the MediaPlayer.
     * <p>
     * Creates the playlist and SFX cache, registers all sound paths,
     * and preloads frequently used sound effects to reduce playback delay.
     */
    public MediaPlayer(){
        playList = new HashMap<>();
        sfxCache = new HashMap<>();

        playList.put("GameOver", "/SOUND/GameOver.mp3");
        playList.put("HeatWave", "/SOUND/HeatWaves.mp3");
        playList.put("Hit", "/SOUND/Hit.mp3");
        playList.put("JUMP", "/SOUND/JUMP.mp3");
        playList.put("Main/Cookies", "/SOUND/Cookies.mp3");
        playList.put("SLIDE", "/SOUND/SLIDE.mp3");
        playList.put("Click", "/SOUND/CLICK.mp3");
        playList.put("Lobby", "/SOUND/Lobby.mp3");
        playList.put("Main/Pets", "/SOUND/PETS.mp3");
        playList.put("Jelly", "/SOUND/JellyCollectedSound.mp3");
        playList.put("Item", "/SOUND/ItemCollectedSound.mp3");

        playList.put("SoundMAP1", "/SOUND/SOUNDMAP1.mp3");
        playList.put("SoundMAP2", "/SOUND/SOUNDMAP2.mp3");
        playList.put("SoundMAP3", "/SOUND/SOUNDMAP3.mp3");

        // preload SFX
        preloadSFX("Hit");
        preloadSFX("JUMP");
        preloadSFX("SLIDE");
        preloadSFX("Click");
        preloadSFX("Jelly");
        preloadSFX("Item");
    }

    /**
     * Loads a sound effect from the playlist and stores it in the SFX cache.
     * This allows the sound to be played instantly without loading from disk again.
     *
     * @param key the sound key used to find the file path in the playlist
     */
    private void preloadSFX(String key) {

        String path = playList.get(key);
        if (path == null) return;

        var url = getClass().getResource(path);
        if (url == null) return;

        sfxCache.put(key, new AudioClip(url.toExternalForm()));
    }

    /**
     * Plays background music based on the specified key.
     * If another BGM is currently playing, it will be stopped and replaced.
     *
     * @param key  the key used to locate the music file in the playlist
     * @param loop true if the music should loop indefinitely
     */
    public void playBGM(String key, boolean loop) {

        if (bgmPlayer != null) {
            bgmPlayer.stop();
            bgmPlayer.dispose();
        }

        String path = playList.get(key);
        if (path == null) {
            System.out.println("BGM key not found: " + key);
            return;
        }

        Media media = new Media(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        bgmPlayer = new javafx.scene.media.MediaPlayer(media);

        if (loop)
            bgmPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);

        bgmPlayer.setVolume(GameLogic.getMusicVolume() / 100.0);
        bgmPlayer.play();
    }

    /**
     * Plays a preloaded sound effect from the SFX cache.
     * If the sound has not been preloaded, it will not be played.
     *
     * @param key the key of the sound effect to play
     */
    public void playSFX(String key) {

        AudioClip clip = sfxCache.get(key);
        if (clip == null) {
            System.out.println("SFX not preloaded: " + key);
            return;
        }

        clip.setVolume(GameLogic.getSFXVolume() / 100.0);
        clip.play();
    }

    /**
     * Updates the volume of the currently playing background music
     * based on the latest value from GameLogic.
     */
    public void updateBGMVolume() {
        if (bgmPlayer != null) {
            bgmPlayer.setVolume(GameLogic.getMusicVolume() / 100.0);
        }
    }

    /**
     * Updates the volume of all cached sound effects
     * according to the current SFX volume setting.
     */
    public void updateSFXVolume() {
        for (AudioClip clip : sfxCache.values()) {
            clip.setVolume(GameLogic.getSFXVolume() / 100.0);
        }
    }
}
