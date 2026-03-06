package Main.Cookies;

import Main.Animation.Animate;
import Main.Asset;
import Main.Image.OutlineTextImage;
import Main.ObjectInGame.Items.CroissantType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Concrete cookie class representing Croissant Cookie.
 *
 * Croissant Cookie gains a special croissant ability after collecting
 * a certain number of jellies. Every 30 jellies collected will trigger
 * a Croissant Jelly drop that grants different effects.
 */
public class CrossiantCookie extends Cookie {

    /**
     * Tracks the current croissant type cycle.
     */
    private int croissantCycle = 0;

    /**
     * Indicates whether a croissant reward is ready to be consumed.
     */
    private boolean croissantReady = false;

    /**
     * UI text used to display the jelly collection counter.
     */
    private OutlineTextImage counterText;

    /**
     * Creates a Croissant Cookie with predefined stats and ability settings.
     */
    public CrossiantCookie() {
        super(3, "Croissant", 140,
                "Every 50 Jellies collected,\na Croissant Jelly falls from the sky.\nOriginal grants bonus points,\nButter gives a Speed Boost,\nand Strawberry restores 20 HP.");
        setImgURL("Croissant_Cookie_sheet");
        setProfileImg(Asset.getImage("Profile_Cross"));
        setScore(256200);
        setSkillCounter(0);
        setHasCooldown(false);
    }

    /**
     * Creates the cookie animation and initializes the jelly counter UI.
     *
     * @return Animate object used to control the cookie animation
     */
    @Override
    public Animate createCookie() {

        Animate anim = super.createCookie();

        counterText = new OutlineTextImage("0/50", 'C', 28);
        counterText.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        counterText.setColor(Color.YELLOW);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetY(2);
        shadow.setColor(Color.BLACK);

        counterText.setEffect(shadow);

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

    /**
     * Called when a jelly is collected.
     *
     * Updates the counter and prepares the croissant reward
     * when the required amount is reached.
     */
    public void onJellyCollected() {

        setSkillCounter(getSkillCounter() + 1);

        if (counterText != null) {
            counterText.setText(getSkillCounter() + "/50");
        }

        if (getSkillCounter() >= 50) {

            setSkillCounter(0);
            croissantReady = true;

            if (counterText != null) {
                counterText.setText("0/50");
            }
        }
    }

    /**
     * Returns whether the croissant reward is ready.
     *
     * @return true if the croissant reward can be used
     */
    public boolean isCroissantReady() {
        return croissantReady;
    }

    /**
     * Consumes the croissant reward and returns its type.
     *
     * The type cycles between Original, Butter, and Strawberry.
     *
     * @return CroissantType representing the next croissant effect
     */
    public CroissantType consumeCroissant() {

        croissantReady = false;

        CroissantType type;

//        switch (croissantCycle) {
//            case 0 -> type = CroissantType.ORIGINAL;
//            case 1 -> type = CroissantType.BUTTER;
//            case 2 -> type = CroissantType.STRAWBERRY;
//            default -> type = CroissantType.ORIGINAL;
//        }

        switch (croissantCycle) {
            case 0: {
                type = CroissantType.ORIGINAL;
                break;
            }
            case 1: {
                type = CroissantType.BUTTER;
                break;
            }
            case 2: {
                type = CroissantType.STRAWBERRY;
                break;
            }
            default: {
                type = CroissantType.ORIGINAL;
                break;
            }
        }

        croissantCycle = (croissantCycle + 1) % 3;

        return type;
    }

    /**
     * Croissant Cookie does not use a direct skill activation.
     *
     * The ability is triggered automatically through jelly collection.
     */
    @Override
    public void useSkill() {
    }
}