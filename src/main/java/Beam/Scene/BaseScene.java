package Beam.Scene;

import Got.GameLogic.GameLogic;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class BaseScene extends StackPane{

    protected Scene scene;
    protected StackPane root = this;

    private static final double BASE_WIDTH = 1440;
    private static final double BASE_HEIGHT = 900;

    public BaseScene() {

        scene = GameLogic.getCurScene();

        setMinSize(BASE_WIDTH, BASE_HEIGHT);
        setPrefSize(BASE_WIDTH, BASE_HEIGHT);
        setMaxSize(BASE_WIDTH, BASE_HEIGHT);

        Rectangle clip = new Rectangle(BASE_WIDTH, BASE_HEIGHT);
        setClip(clip);

        setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
    }

    public Region spacer(char c){

        //มันเป็นเหมือน spacer ใน Swift ที่สร้างมาเพราะจะเอาใว้จัดหน้า UI ง่ายๆ

        Region space = new Region();
        if(c == 'V'){
            VBox.setVgrow(space, Priority.ALWAYS);
        } else if (c == 'H') {
            HBox.setHgrow(space, Priority.ALWAYS);
        }
        return space;
    }
}