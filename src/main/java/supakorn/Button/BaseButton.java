package supakorn.Button;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class BaseButton extends Button implements ClickAble{
    private DropShadow dropShadow;
    private ScaleTransition scaleTransition;
    private boolean isHovering = false;
    private boolean clicked = false;

    public BaseButton(ImageView img, double fitHeight, double fitWidth) {

        super();

        CreateBtn();

        if (fitHeight > 0) {
            img.setFitHeight(fitHeight);
        }

        if (fitWidth > 0) {
            img.setFitWidth(fitWidth);
        }

        img.setPreserveRatio(true);

        setGraphic(img);
        setPadding(Insets.EMPTY);
        setBackground(null);

        // ให้ปุ่มคำนวณขนาดตาม graphic
        setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
    }

    @Override
    public void handleClick() {
        clicked = true;
        scaleTo(1.1,true);
    }

    @Override
    public void onHoverEnter() {
        isHovering = true;
        scaleTo(1.05,false);
    }

    @Override
    public void onHoverExit() {
        isHovering = false;
        scaleTo(1.0,false);
    }

    private void scaleTo(double scale,boolean AR) {
        scaleTransition = new ScaleTransition(Duration.millis(50), this);
        scaleTransition.setToX(scale);
        scaleTransition.setToY(scale);
        scaleTransition.play();

        scaleTransition.setOnFinished(e -> {
            if(clicked){
                clicked = false;
                if(isHovering)scaleTo(1.05,false);
                else scaleTo(1,false);
            }
        });
    }

    public void CreateBtn(){

        dropShadow = new DropShadow();
        dropShadow.setRadius(40);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(Color.rgb(2, 2, 2, 0.4));

        setOnAction(e -> { handleClick(); });
        setOnMouseEntered(e -> { onHoverEnter(); });
        setOnMouseExited(e -> { onHoverExit(); });

    }
}
