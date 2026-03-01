package Beam.Scene;

import Got.GameLogic.GameLogic;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseRoot extends StackPane{

    protected Scene scene;
    protected StackPane root = this;

    public BaseRoot() {

        setMaxSize(1440, 900);
        scene = GameLogic.getCurScene();
        Rectangle clip = new Rectangle(1440, 900);
        clip.setArcWidth(0);
        clip.setArcHeight(0);

        setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));

        setClip(clip);
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
}