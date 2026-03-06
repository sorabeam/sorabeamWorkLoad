package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Spawner;

/**
 * Represents a special boost item that temporarily increases game speed
 * and grants the player temporary invincibility.
 *
 * When used, it increases the score, makes the player invincible for a short
 * duration, and speeds up the spawning system for a limited time.
 */
public class ChillyBoost extends BaseItem {

    /**
     * Multiplier applied to the default spawner speed.
     */
    private double speedMul = 3;

    /**
     * Duration of the speed boost in milliseconds.
     */
    private int boostTime = 5000;

    /**
     * Creates a ChillyBoost item with the default name "ChillyBoost".
     */
    public ChillyBoost() {
        super("ChillyBoost");
    }

    /**
     * Activates the boost effect when the player interacts with this item.
     *
     * The interaction performs the following actions:
     * - Adds score to the game
     * - Grants temporary invincibility to the player
     * - Temporarily increases the spawning speed using a separate thread
     *
     * @param player the Cookie that interacts with this item
     */
    public void interact(Cookie player) {
        GameLogic.addScore(1000);
        player.setInvincible(6.0);

        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*speedMul);
            try {
                player.setSpeeding(true);
                Thread.sleep(boostTime);
                player.setSpeeding(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();

        });
        speedBoostThread.start();
    }
}