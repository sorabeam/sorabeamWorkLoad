/*package CleanCode.UI.InGameUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Image.FloorFade;

public class InGameBG extends StackPane {

    public InGameBG(Scene scene){
        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        ImageView MBg = Asset.createBackgroundView("Bglevel1",1,1);

        MBg.fitWidthProperty().bind(scene.widthProperty());
        MBg.fitHeightProperty().bind(scene.heightProperty());

        ImageView ExpBar = Asset.createImageView("EXPBar",0,10);
        ExpBar.fitWidthProperty().bind(scene.widthProperty());
        StackPane.setAlignment(ExpBar,Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,ExpBar,fade);
    }
}*/

package GuiAndScene.UI.InGameUI;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Image.FloorFade;

public class InGameBG extends StackPane {

    private ImageView bg1;
    private ImageView bg2;
    private double speed = 0.5;

    public InGameBG(Scene scene){

        Pane bgLayer = new Pane();

        bg1 = Asset.createBackgroundView("Bglevel1",1,1);
        bg2 = Asset.createBackgroundView("Bglevel1",1,1);

        bg1.fitWidthProperty().bind(scene.widthProperty());
        bg1.fitHeightProperty().bind(scene.heightProperty());

        bg2.fitWidthProperty().bind(scene.widthProperty());
        bg2.fitHeightProperty().bind(scene.heightProperty());

        bg2.setTranslateX(scene.getWidth());

        bgLayer.getChildren().addAll(bg1, bg2);

        startLoop(scene);

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        ImageView ExpBar = Asset.createImageView("EXPBar",0,10);
        ExpBar.fitWidthProperty().bind(scene.widthProperty());
        StackPane.setAlignment(ExpBar,Pos.BOTTOM_CENTER);

        getChildren().addAll(bgLayer, ExpBar, fade);
    }

    private void startLoop(Scene scene){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bg1.setTranslateX(bg1.getTranslateX() - speed);
                bg2.setTranslateX(bg2.getTranslateX() - speed);

                if (bg1.getTranslateX() + scene.getWidth() <= 0) {
                    bg1.setTranslateX(bg2.getTranslateX() + scene.getWidth());
                }

                if (bg2.getTranslateX() + scene.getWidth() <= 0) {
                    bg2.setTranslateX(bg1.getTranslateX() + scene.getWidth());
                }
            }
        };
        timer.start();
    }
}