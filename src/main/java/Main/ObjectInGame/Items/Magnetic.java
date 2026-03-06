package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;

/**
 * Represents a magnetic item that temporarily enables a magnet effect
 * for the player.
 *
 * When activated, nearby items can be attracted to the player for a
 * limited duration.
 */
public class Magnetic extends BaseItem {

    /**
     * Duration of the magnetic effect in milliseconds.
     */
    private int activeTime = 8000;

    /**
     * Creates a Magnetic item with the default name "Magnetic".
     */
    public Magnetic() {
        super("Magnetic");
    }

    /**
     * Activates the magnetic effect when the player interacts with this item.
     *
     * A separate thread is used to enable the magnetic state for the player
     * for a limited amount of time, then automatically disable it.
     *
     * @param player the Cookie that activates the magnetic effect
     */
    @Override
    public void interact(Cookie player) {
        Thread activeTimeThread = new Thread(() -> {
            try {
                player.setMagnetic(true);
                Thread.sleep(activeTime);
                player.setMagnetic(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        activeTimeThread.start();
    }

    /**
     * Returns the duration of the magnetic effect.
     *
     * @return the active time in milliseconds
     */
    public int getActiveTime() {
        return activeTime;
    }

    /**
     * Sets the duration of the magnetic effect.
     *
     * @param activeTime the new active time in milliseconds
     */
    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }
}