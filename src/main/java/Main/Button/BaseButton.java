package Main.Button;

import Main.Media.MediaPlayer;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BaseButton extends Button implements Clickable {
    private boolean isHovering = false;
    private boolean clicked = false;

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

    @Override
    public void handleClick() {
        MediaPlayer.getInstance().playSFX("Click");
        clicked = true;
        scaleTo(1.1);
    }

    @Override
    public void onHoverEnter() {
        isHovering = true;
        scaleTo(1.05);
    }

    @Override
    public void onHoverExit() {
        isHovering = false;
        scaleTo(1.0);
    }

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
