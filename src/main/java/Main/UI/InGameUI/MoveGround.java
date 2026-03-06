package Main.UI.InGameUI;

import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Spawner;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * A UI component that creates a continuously scrolling ground effect in the game.
 *
 * The class uses two ground images placed side by side and moves them horizontally
 * using an AnimationTimer. When one image moves completely out of the screen,
 * it is repositioned to the right side of the other image to create a seamless loop.
 *
 * The ground size automatically adjusts when the game layer size changes.
 */
public class MoveGround extends Pane {

    private ImageView ground1;
    private ImageView ground2;

    private final double groundH = 150;
    private double width;

    private AnimationTimer timer;
    private long lastTime = 0;

    private final Pane gameLayer;

    /**
     * Constructs the moving ground system and attaches it to the provided game layer.
     *
     * Two ground images are created and positioned at the bottom of the game layer.
     * Their width is dynamically updated based on the layer bounds, and a scrolling
     * animation is started to simulate ground movement.
     *
     * @param gameLayer the main game layer used for positioning and resizing the ground
     */
    public MoveGround(Pane gameLayer){

        Image groundImg =
                new Image("/Image/Background/GroundLevel" + GameLogic.getMap() + ".png");
        this.gameLayer = gameLayer;

        ground1 = new ImageView(groundImg);
        ground2 = new ImageView(groundImg);

        ground1.setFitHeight(groundH + 100);
        ground2.setFitHeight(groundH + 100);

        ground1.setPreserveRatio(false);
        ground2.setPreserveRatio(false);

        ground1.setScaleY(2);
        ground2.setScaleY(2);

        ground1.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));
        ground2.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));

        getChildren().addAll(ground1, ground2);

        Platform.runLater(() -> updateGroundWidth(gameLayer.getLayoutBounds()));

        gameLayer.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            updateGroundWidth(newBounds);
        });

        createLoop();
        start();
    }

    /**
     * Updates the width of the ground images based on the current bounds of the game layer.
     * This ensures the ground always covers the visible area and maintains seamless looping.
     *
     * @param bounds the current layout bounds of the game layer
     */
    private void updateGroundWidth(Bounds bounds) {
        width = bounds.getWidth() * 2;

        ground1.setFitWidth(width);
        ground2.setFitWidth(width);

        if (ground2.getTranslateX() == 0 && ground1.getTranslateX() == 0) {
            ground1.setTranslateX(0);
            ground2.setTranslateX(width);
        }
    }

    /**
     * Creates the animation loop that moves the ground images every frame.
     *
     * The movement speed is based on the value provided by the Spawner class.
     * When one ground image moves completely off-screen, it is repositioned
     * to the right side of the other image to keep the scrolling continuous.
     */
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

    /**
     * Returns the vertical layout position of the ground.
     * This value can be used for positioning characters or objects relative to the ground.
     *
     * @return the Y position of the ground
     */
    public double getGroundY() {
        return ground1.getLayoutY();
    }

    /**
     * Starts the ground scrolling animation.
     */
    public void start(){
        lastTime = 0;
        timer.start();
    }

    /**
     * Stops the ground scrolling animation.
     */
    public void stop(){
        timer.stop();
    }
}
