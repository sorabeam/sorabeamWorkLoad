package CleanCode.Scene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class BaseRoot extends StackPane{

    protected Scene scene;
    protected StackPane root = this;

    public BaseRoot(){
        scene = getScene();
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