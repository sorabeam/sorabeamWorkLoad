package Main.UI.InGameUI;

import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Spawner;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MoveGround extends Pane {

    private ImageView ground1;
    private ImageView ground2;

    private final double groundH = 150;
    private double width;

    private AnimationTimer timer;
    private long lastTime = 0;

    private final Pane gameLayer;

    public MoveGround(Pane gameLayer){

        Image groundImg =
                new Image("/Image/Background/GroundLevel" + GameLogic.getMap() + ".png");
        this.gameLayer = gameLayer;

        ground1 = new ImageView(groundImg);
        ground2 = new ImageView(groundImg);

        ground1.setFitHeight(groundH + 100);
        ground2.setFitHeight(groundH + 100);

//        ground1.setFitWidth(gameLayer.getLayoutBounds().getWidth() * 2);
//        ground2.setFitWidth(gameLayer.getLayoutBounds().getWidth() * 2);

        ground1.setPreserveRatio(false);
        ground2.setPreserveRatio(false);

        ground1.setScaleY(2);
        ground2.setScaleY(2);

        ground1.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));
        ground2.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));

        getChildren().addAll(ground1, ground2);

//        Platform.runLater(() -> {
//            width = gameLayer.getLayoutBounds().getWidth() * 2;
//
//            ground1.setTranslateX(0);
//            ground2.setTranslateX(width);
//        });
        Platform.runLater(() -> updateGroundWidth(gameLayer.getLayoutBounds()));

        gameLayer.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            updateGroundWidth(newBounds);
        });

        createLoop();
        start();
    }

    private void updateGroundWidth(Bounds bounds) {
        width = bounds.getWidth() * 2;

        ground1.setFitWidth(width);
        ground2.setFitWidth(width);

        if (ground2.getTranslateX() == 0 && ground1.getTranslateX() == 0) {
            ground1.setTranslateX(0);
            ground2.setTranslateX(width);
        }
    }

    private void createLoop(){

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (lastTime == 0){
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) / 1e9;
                lastTime = now;

                double speed = Spawner.getSpeed();

                ground1.setTranslateX(ground1.getTranslateX() + speed * dt);
                ground2.setTranslateX(ground2.getTranslateX() + speed * dt);

                if (ground1.getTranslateX() <= -width) {
                    ground1.setTranslateX(ground2.getTranslateX() + width);
                }

                if (ground2.getTranslateX() <= -width) {
                    ground2.setTranslateX(ground1.getTranslateX() + width);
                }
            }
        };
    }

    public double getGroundY() {
        return ground1.getLayoutY();
    }

    public void start(){
        lastTime = 0;
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}