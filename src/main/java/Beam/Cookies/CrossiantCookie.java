package Beam.Cookies;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.Image.OutlineText;
import Pors.ObjectInGame.Items.CroissantType;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class CrossiantCookie extends Cookie {

    private int jellyCollected = 0;
    private int croissantCycle = 0;
    private boolean croissantReady = false;
    private OutlineText counterText;

    public CrossiantCookie() {
        super(3, "CroissantCookie", 140, "");
        setImgURL("Croissant_Cookie_sheet");
    }

    @Override
    public Animate createCookie() {

        Animate anim = super.createCookie(); // สร้าง cookie + hitbox จาก class แม่ก่อน

        counterText = new OutlineText("0/30", 'C', 28);
        counterText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        counterText.setColor(Color.YELLOW);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetY(2);
        shadow.setColor(Color.BLACK);

        counterText.setEffect(shadow);

        // ให้อยู่บนหัว cookie
        counterText.layoutXProperty().bind(
                anim.layoutXProperty()
                        .add(anim.fitWidthProperty().divide(2))
                        .subtract(counterText.boundsInLocalProperty().get().getWidth() / 2)
        );

        counterText.layoutYProperty().bind(
                anim.layoutYProperty().subtract(20)
        );

        if (getParentLayer() != null) {
            getParentLayer().getChildren().add(counterText);
        }

        return anim;
    }

    public void onJellyCollected() {

        jellyCollected++;

        if (counterText != null) {
            counterText.setText(jellyCollected + "/30");
        }

        if (jellyCollected >= 30) {

            jellyCollected = 0;
            croissantReady = true;

            playSkill(0.3);
            cookie.changeAnimationState(AnimationType.SKILL);

            if (counterText != null) {
                counterText.setText("0/30");
            }
        }
    }

    public boolean isCroissantReady() {
        return croissantReady;
    }

    public CroissantType consumeCroissant() {

        croissantReady = false;

        CroissantType type;

        switch (croissantCycle) {
            case 0 -> type = CroissantType.ORIGINAL;
            case 1 -> type = CroissantType.BUTTER;
            case 2 -> type = CroissantType.STRAWBERRY;
            default -> type = CroissantType.ORIGINAL;
        }

        croissantCycle = (croissantCycle + 1) % 3;

        return type;
    }

    @Override
    public void useSkill() {
        //
    }
}
