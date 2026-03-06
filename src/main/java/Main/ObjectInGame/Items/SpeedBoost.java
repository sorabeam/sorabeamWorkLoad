package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.ObjectInGame.Spawner;

/**
 * Represents a speed boost item that temporarily increases the game speed
 * and grants the player temporary invincibility.
 */
public class SpeedBoost extends BaseItem {

    /**
     * Multiplier applied to the default spawning speed.
     */
    private int speedStat;

    /**
     * Creates a SpeedBoost item with a predefined speed multiplier.
     * The item name is set through the BaseItem constructor.
     */
    public SpeedBoost() {
        super("ChillyBoost");
        setSpeedStat(3);
    }

    /**
     * Returns the speed multiplier of this boost.
     *
     * @return the speed multiplier
     */
    public int getSpeedStat() {
        return speedStat;
    }

    /**
     * Sets the speed multiplier for this boost.
     *
     * @param speedStat the new speed multiplier
     */
    public void setSpeedStat(int speedStat) {
        this.speedStat = speedStat;
    }

    /**
     * Activates the speed boost effect when the player interacts with this item.
     *
     * The interaction performs the following actions:
     * - Grants temporary invincibility to the player
     * - Temporarily increases the spawning speed
     * - Enables the player's speeding state for a short duration
     *
     * @param player the Cookie that activates the speed boost
     */
    @Override
    public void interact(Cookie player) {
        player.setInvincible(6.0);

        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*getSpeedStat());
            try {
                player.setSpeeding(true);
                Thread.sleep(4000);
                player.setSpeeding(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();

        });
        speedBoostThread.start();
    }
}