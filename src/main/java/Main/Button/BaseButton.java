package Main.Button;

import Main.MediaPlayer;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Base button component used in the user interface.
 *
 * This class extends Button and implements the Clickable interface.
 * It provides basic button behavior including click handling,
 * hover effects, and scale animation feedback.
 */
public class BaseButton extends Button implements Clickable {

    /**
     * Indicates whether the mouse is currently hovering over the button.
     */
    private boolean isHovering = false;

    /**
     * Indicates whether the button has been clicked.
     */
    private boolean clicked = false;

    /**
     * Creates a button using the provided ImageView as its graphic
     * and configures default layout and styling properties.
     *
     * @param img ImageView used as the button graphic
     */
    public BaseButton(ImageView img) {

        super();

        initializesButton();

        img.setPreserveRatio(true);

        setGraphic(img);
        setPadding(Insets.EMPTY);
        setBackground(null);

        setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
    }

    /**
     * Plays click sound effect, sets clicked state to true,
     * and triggers scale animation for click feedback.
     */
    @Override
    public void handleClick() {
        MediaPlayer.getInstance().playSFX("Click");
        clicked = true;
        scaleTo(1.1);
    }

    /**
     * Sets hovering state to true and slightly enlarges the button.
     */
    @Override
    public void onHoverEnter() {
        isHovering = true;
        scaleTo(1.05);
    }

    /**
     * Resets hovering state to false and restores the button
     * to normal size.
     */
    @Override
    public void onHoverExit() {
        isHovering = false;
        scaleTo(1.0);
    }

    /**
     * Performs scale animation to the specified size
     * and handles post-animation state adjustments.
     *
     * @param scale target scale value
     */
    private void scaleTo(double scale) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(50), this);
        scaleTransition.setToX(scale);
        scaleTransition.setToY(scale);
        scaleTransition.play();

        scaleTransition.setOnFinished(e -> {
            if(clicked){
                clicked = false;
                if(isHovering)scaleTo(1.05);
                else scaleTo(1);
            }
        });
    }

    /**
     * Initializes button with DropShadow effects
     * and adds mouse and action event handlers.
     */
    public void initializesButton(){

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(40);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(Color.rgb(2, 2, 2, 0.4));

        setOnAction(e -> {handleClick();});
        setOnMouseEntered(e -> { onHoverEnter(); });
        setOnMouseExited(e -> { onHoverExit(); });

    }
}
