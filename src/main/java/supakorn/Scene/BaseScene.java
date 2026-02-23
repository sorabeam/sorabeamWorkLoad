package supakorn.Scene;

import supakorn.Media.JooxBox;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class BaseScene {

    protected StackPane root;
    protected Scene scene;
    protected JooxBox sportify;

    public Scene getScene(){
        return scene;
    }

    public JooxBox getMp3(){
        return sportify;
    }

    public void setMp3(JooxBox mp3){
        sportify = mp3;
    }

    public Region spacer(char c){
        Region space = new Region();
        if(c == 'V'){
            VBox.setVgrow(space, Priority.ALWAYS);
        } else if (c == 'H') {
            HBox.setHgrow(space, Priority.ALWAYS);
        }
        return space;
    }

    public abstract void SetUpScene();
}
