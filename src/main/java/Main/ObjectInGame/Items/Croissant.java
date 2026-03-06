package Main.ObjectInGame.Items;

import Main.CharacterData;
import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;

/**
 * Represents a Croissant item that can move with physics and provide
 * different effects depending on its type when interacted with.
 *
 * The croissant moves using simple gravity physics and can bounce once
 * when hitting the ground.
 */
public class Croissant extends BaseItem {

    /**
     * Horizontal velocity of the croissant.
     */
    private double vx;

    /**
     * Vertical velocity of the croissant.
     */
    public double vy = 0;

    /**
     * Gravity applied to the croissant.
     */
    private double gravity = 1500;

    /**
     * Indicates whether the croissant has already bounced.
     */
    public boolean hasBounced = false;

    /**
     * The type of croissant which determines its effect.
     */
    private CroissantType type;

    /**
     * Creates a croissant with a specified type and horizontal velocity.
     *
     * The image name is determined based on the croissant type.
     *
     * @param type the type of croissant
     * @param vx the initial horizontal velocity
     */
    public Croissant(CroissantType type, double vx) {
        super(getImageName(type));
        this.type = type;
        this.vx = vx;
    }

    /**
     * Returns the image name corresponding to the croissant type.
     *
     * @param type the croissant type
     * @return the image name used for rendering
     */
    private static String getImageName(CroissantType type) {

//        switch (type) {
//
//            case BUTTER:
//                return "CroissantButter";
//
//            case STRAWBERRY:
//                return "CroissantStrawberry";
//
//            default:
//                return "CroissantOriginal";
//        }
        switch (type) {

            case BUTTER: {
                return "CroissantButter";
            }

            case STRAWBERRY: {
                return "CroissantStrawberry";
            }

            default: {
                return "CroissantOriginal";
            }
        }
    }

    /**
     * Updates the croissant's physics including gravity, movement,
     * and ground collision handling.
     *
     * The croissant falls with gravity, moves horizontally while the
     * player is alive, and bounces once when touching the ground.
     *
     * @param dt time delta used for physics calculations
     * @param view the visual representation of the croissant
     * @param groundY the Y position of the ground
     */
    public void updatePhysics(double dt, ItemView view, double groundY) {

        vy += gravity * dt;

        if (!CharacterData.getCurrent_Cookie().isDead()) {
            view.setTranslateX(view.getTranslateX() + vx * dt);
        }
        view.setTranslateY(view.getTranslateY() + vy * dt);

        double bottom = view.getTranslateY() + view.getBoundsInLocal().getHeight();

        if (bottom >= groundY) {

            view.setTranslateY(groundY - view.getBoundsInLocal().getHeight());

            if (!hasBounced) {
                vy = -300;
                hasBounced = true;
            } else {
                vy = 0;
            }
        }
    }

    /**
     * Applies the croissant's effect when the player interacts with it.
     *
     * The effect depends on the croissant type:
     * - ORIGINAL: adds a large amount of score
     * - BUTTER: adds score and activates a ChillyBoost
     * - STRAWBERRY: heals the player and adds score
     *
     * @param player the Cookie that interacts with the croissant
     */
    @Override
    public void interact(Cookie player) {

        switch (type) {

            case ORIGINAL:
                GameLogic.addScore(3000);
                break;

            case BUTTER:
                GameLogic.addScore(1000);
                new ChillyBoost().interact(player);
                break;

            case STRAWBERRY:
                player.heal(20);
                GameLogic.addScore(1000);
                break;
        }
    }

    /**
     * Sets the velocity of the croissant.
     *
     * @param vx the horizontal velocity
     * @param vy the vertical velocity
     */
    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}