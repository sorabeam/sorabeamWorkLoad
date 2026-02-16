package Media;

import javafx.scene.media.*;

import java.util.Objects;

import static Media.PlayList.playlist;

public class MediaController {
    private MediaPlayer mp3;

    public MediaController(){
        // s == sound
        PlayMusic(mp3,"MainMenuMusic");
    }

    public void PlayMusic(MediaPlayer mp,String path){
        path = playlist.get("MainMenuMusic");
        Media media = new Media(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        mp = new MediaPlayer(media);
        mp.play();
    }

}
