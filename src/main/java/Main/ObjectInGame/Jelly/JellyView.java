package Main.ObjectInGame.Jelly;

import Main.Cookies.Cookie;
import Main.ObjectInGame.Spawner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static Main.Scene.GameplayScene.groundY;

/**
 * Represents the visual and physical behavior of a jelly in the game.
 *
 * This class extends ImageView and handles rendering, movement,
 * gravity physics, bouncing behavior, and magnet attraction to the player.
 */
public class JellyView extends ImageView {

    /**
     * The logical jelly object associated with this view.
     */
    private BaseJelly jelly;

    /**
     * Horizontal velocity of the jelly.
     */
    private double vx;

    /**
     * Vertical velocity of the jelly.
     */
    private double vy;

    /**
     * Indicates whether the jelly is currently falling.
     */
    private boolean falling = false;

    /**
     * Gravity applied to the jelly while falling.
     */
    private double gravity = 1000;

    /**
     * Indicates whether the jelly has already bounced once.
     */
    private boolean hasBounced = false;

    /**
     * Creates a JellyView for the specified jelly with initial velocity.
     * The image and size are determined based on the jelly name.
     *
     * @param jelly the jelly associated with this view
     * @param vx the horizontal velocity
     * @param vy the vertical velocity
     */
    public JellyView(BaseJelly jelly, double vx, double vy) {
        this.jelly = jelly;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Jelly/" + jelly.getName() + ".png"));

        if(Objects.equals(jelly.getName(), "Jelly1"))
        {
            setFitWidth(50);
            setFitHeight(50);
        }
        else
        {
            setFitWidth(60);
            setFitHeight(60);
        }
    }

    /**
     * Updates the jelly's movement and physics.
     *
     * If the jelly is falling, gravity is applied and the jelly rotates
     * while descending. When the jelly hits the ground, it bounces once
     * before stopping.
     *
     * @param deltaTime the time elapsed since the last update
     */
    public void update(double deltaTime) {

        if(falling){
            vy += gravity * deltaTime;
            setRotate(getRotate() + 360 * deltaTime);
        }

        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);

        if(falling){
            double height = getBoundsInLocal().getHeight();
            double bottom = getTranslateY() + getBoundsInLocal().getHeight();

            if(bottom >= groundY){

                setTranslateY(groundY - height);

                if (!hasBounced) {
                    vy = -400;
                    hasBounced = true;
                } else {
                    vy = 0;
                    falling = false;
                }
            }
        }
    }

    /**
     * Pulls the jelly toward the player when the magnetic effect is active.
     *
     * The jelly moves toward the player's center position based on
     * the current spawner speed and elapsed time.
     *
     * @param player the Cookie that attracts the jelly
     * @param dt the time delta used for movement calculation
     */
    public void pullToPlayer(Cookie player, double dt) {
        double x = player.getCookie().getLayoutX()+player.getCookie().getFitWidth()/2+player.getCookie().getTranslateX();
        double y = player.getCookie().getLayoutY()+player.getCookie().getFitHeight()/2+player.getCookie().getTranslateY();

        double ux = x-getTranslateX();
        double uy = y-getTranslateY();

        double dist = Math.hypot(ux, uy);

        if(dist<=1) return;

        double step = Math.abs(Spawner.getSpeed())*dt*2;
        step = Math.min(step, dist);

        setTranslateX(getTranslateX()+ux/dist*step);
        setTranslateY(getTranslateY()+uy/dist*step);
    }

    /**
     * Sets whether the jelly should start or stop falling.
     *
     * @param falling true if the jelly should fall, false otherwise
     */
    public void setFalling(boolean falling){
        this.falling = falling;
    }

    /**
     * Returns the logical jelly associated with this view.
     *
     * @return the BaseJelly object
     */
    public BaseJelly getJelly() {
        return jelly;
    }

    /**
     * Sets both horizontal and vertical velocity of the jelly.
     *
     * @param vx the new horizontal velocity
     * @param vy the new vertical velocity
     */
    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Sets the horizontal velocity of the jelly.
     *
     * @param vx the new horizontal velocity
     */
    public void setSpeedX(double vx) {
        this.vx = vx;
    }
}