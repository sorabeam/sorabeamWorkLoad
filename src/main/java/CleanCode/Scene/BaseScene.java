package CleanCode.Scene;

import javafx.scene.Scene;
import javafx.scene.layout.*;

public abstract class BaseScene {

    protected StackPane root;
    protected Scene scene;

    public BaseScene() {
        root = new StackPane();
        scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
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