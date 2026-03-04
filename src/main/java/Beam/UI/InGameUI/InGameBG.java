package Beam.UI.InGameUI;

import Beam.Asset;
import Beam.Image.FloorFade;
import Got.GameLogic.GameLogic;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class InGameBG extends StackPane {

    private ImageView bg1;
    private ImageView bg2;

    private double speed = 200; // pixel per second
    private double width;

    private AnimationTimer timer;
    private long lastTime = 0;

    public InGameBG(StackPane root){

        Pane bgLayer = new Pane();

        bg1 = Asset.createBackgroundView("Bglevel" + GameLogic.getMap(),1,1);
        bg2 = Asset.createBackgroundView("Bglevel" + GameLogic.getMap(),1,1);

        bg1.fitWidthProperty().bind(root.widthProperty());
        bg1.fitHeightProperty().bind(root.heightProperty());

        bg2.fitWidthProperty().bind(root.widthProperty());
        bg2.fitHeightProperty().bind(root.heightProperty());

        Platform.runLater(() -> {
            width = root.getWidth();
            recalculatePositions();
        });

        bgLayer.getChildren().addAll(bg1, bg2);

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(bgLayer, fade);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            width = newVal.doubleValue();
            recalculatePositions();
        });

        createLoop();
        start();
    }

    // ================= LOOP =================

    private void createLoop(){

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) / 1e9;
                lastTime = now;

                double move = speed * dt;

                bg1.setTranslateX(bg1.getTranslateX() - move);
                bg2.setTranslateX(bg2.getTranslateX() - move);

                if (bg1.getTranslateX() + width <= 0) {
                    bg1.setTranslateX(bg2.getTranslateX() + width);
                }

                if (bg2.getTranslateX() + width <= 0) {
                    bg2.setTranslateX(bg1.getTranslateX() + width);
                }
            }
        };
    }

    // ================= POSITION FIX =================

    private void recalculatePositions(){

        double x1 = bg1.getTranslateX();
        double x2 = bg2.getTranslateX();

        if (x1 == 0 && x2 == 0){
            bg1.setTranslateX(0);
            bg2.setTranslateX(width);
            return;
        }

        if (x1 <= x2) {
            bg2.setTranslateX(x1 + width);
        } else {
            bg1.setTranslateX(x2 + width);
        }
    }

    // ================= CONTROL =================

    public void start(){
        if (timer != null) {
            lastTime = 0;   // reset delta
            timer.start();
        }
    }

    public void stop(){
        if (timer != null) {
            timer.stop();
        }
    }

    public void resume(){
        if (timer != null) {
            lastTime = 0;   // ป้องกัน dt กระโดด
            timer.start();
        }
    }
}
