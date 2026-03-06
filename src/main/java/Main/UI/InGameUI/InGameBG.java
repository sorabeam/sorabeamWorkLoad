package Main.UI.InGameUI;

import Main.Asset;
import Main.Image.FloorFade;
import Main.GameLogic.GameLogic;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * A scrolling background container used during gameplay.
 * <p>
 * This class creates two background images that move horizontally to produce
 * an infinite scrolling effect. When one background moves out of the screen,
 * it is repositioned to the right of the other background to maintain a
 * seamless loop. A floor fade effect is also added at the bottom of the screen.
 */
public class InGameBG extends StackPane {

    /**
     * First background image used for the scrolling effect.
     */
    private ImageView bg1;

    /**
     * Second background image used for seamless background looping.
     */
    private ImageView bg2;

    /**
     * Scrolling speed of the background in pixels per second.
     */
    private double speed = 200;

    /**
     * Current width of the scene used to reposition backgrounds.
     */
    private double width;

    /**
     * AnimationTimer responsible for updating background movement each frame.
     */
    private AnimationTimer timer;

    /**
     * Stores the timestamp of the previous frame for delta time calculation.
     */
    private long lastTime = 0;

    /**
     * Constructs the in-game scrolling background.
     * <p>
     * The constructor loads two background images based on the current map,
     * binds their sizes to the root container, and places them inside a
     * background layer. A floor fade effect is added for visual styling.
     * The scrolling animation loop is then created and started.
     *
     * @param root the root StackPane whose size determines the background size
     */
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

    /**
     * Creates the AnimationTimer loop that moves the background images.
     * <p>
     * Each frame calculates delta time and shifts both background images
     * horizontally. When one image completely leaves the screen, it is
     * repositioned to the right side of the other image to maintain a
     * continuous scrolling effect.
     */
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

    /**
     * Recalculates the background positions when the screen size changes.
     * <p>
     * Ensures that both background images remain placed side-by-side so
     * the scrolling loop continues smoothly after resizing or resetting.
     */
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

    /**
     * Starts the background scrolling animation.
     * <p>
     * Resets the time reference before starting the AnimationTimer.
     */
    public void start(){
        if (timer != null) {
            lastTime = 0;
            timer.start();
        }
    }

    /**
     * Stops the background scrolling animation.
     */
    public void stop(){
        if (timer != null) {
            timer.stop();
        }
    }
}
