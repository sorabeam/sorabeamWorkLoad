package Beam.Media;

import Got.GameLogic.GameLogic;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;

public class JooxBox {

    private HashMap<String, String> playlist;
    private HashMap<String, AudioClip> sfxCache;
    private MediaPlayer bgmPlayer;
    private static JooxBox instance;

    public static JooxBox getInstance() {
        if (instance == null)
            instance = new JooxBox();
        return instance;
    }

    public JooxBox(){
        playlist = new HashMap<>();
        sfxCache = new HashMap<>();

        playlist.put("GameOver", "/SOUND/GameOver.mp3");
        playlist.put("HeatWave", "/SOUND/HeatWaves.mp3");
        playlist.put("Hit", "/SOUND/Hit.mp3");
        playlist.put("JUMP", "/SOUND/JUMP.mp3");
        playlist.put("Cookies", "/SOUND/Cookies.mp3");
        playlist.put("SLIDE", "/SOUND/SLIDE.mp3");
        playlist.put("Click", "/SOUND/CLICK.mp3");
        playlist.put("Lobby", "/SOUND/Lobby.mp3");
        playlist.put("Pets", "/SOUND/PETS.mp3");
        playlist.put("Jelly", "/SOUND/JellyCollectedSound.mp3");
        playlist.put("Item", "/SOUND/ItemCollectedSound.mp3");

        playlist.put("SoundMAP1", "/SOUND/SOUNDMAP1.mp3");
        playlist.put("SoundMAP2", "/SOUND/SOUNDMAP2.mp3");
        playlist.put("SoundMAP3", "/SOUND/SOUNDMAP3.mp3");

        // preload SFX
        preloadSFX("Hit");
        preloadSFX("JUMP");
        preloadSFX("SLIDE");
        preloadSFX("Click");
        preloadSFX("Jelly");
        preloadSFX("Item");
    }

    private void preloadSFX(String key) {

        String path = playlist.get(key);
        if (path == null) return;

        var url = getClass().getResource(path);
        if (url == null) return;

        sfxCache.put(key, new AudioClip(url.toExternalForm()));
    }

    public void playBGM(String key, boolean loop) {

        if (bgmPlayer != null) {
            bgmPlayer.stop();
            bgmPlayer.dispose();
        }

        String path = playlist.get(key);
        if (path == null) {
            System.out.println("BGM key not found: " + key);
            return;
        }

        Media media = new Media(getClass().getResource(path).toExternalForm());
        bgmPlayer = new MediaPlayer(media);

        if (loop)
            bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        bgmPlayer.setVolume(GameLogic.getMusicVolume() / 100.0);
        bgmPlayer.play();
    }

    public void playSFX(String key) {

        AudioClip clip = sfxCache.get(key);
        if (clip == null) {
            System.out.println("SFX not preloaded: " + key);
            return;
        }

        clip.setVolume(GameLogic.getSFXVolume() / 100.0);
        clip.play();
    }

    public void updateBGMVolume() {
        if (bgmPlayer != null) {
            bgmPlayer.setVolume(GameLogic.getMusicVolume() / 100.0);
        }
    }

    public void updateSFXVolume() {
        for (AudioClip clip : sfxCache.values()) {
            clip.setVolume(GameLogic.getSFXVolume() / 100.0);
        }
    }
}
