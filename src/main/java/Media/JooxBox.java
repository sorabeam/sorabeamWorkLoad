package Media;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class JooxBox {
    public static HashMap<String,String> playlist;

    public JooxBox(){
        playlist = new HashMap<>();
        playlist.put( "MainMenuMusic" , "/GoodBoy.mp3" );
    }

    public MediaPlayer play(String path, boolean isLoop,float volum) {
        path = playlist.get(path);
        Media media = new Media(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        MediaPlayer mp = new MediaPlayer(media);

        if(isLoop) mp.setCycleCount(MediaPlayer.INDEFINITE);
        mp.setVolume(Math.max(0,volum / 100.0));
        mp.play();

        return mp;
    }

}
