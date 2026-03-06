package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;

/**
 * Represents a Vegetable item that grants score when collected
 * by the player.
 */
public class Vegetable extends BaseItem {

    /**
     * Creates a Vegetable item with the default name "Vegetable".
     */
    public Vegetable() {
        super("Vegetable");
    }

    /**
     * Handles the interaction between the player and the vegetable item.
     *
     * When collected, the player gains a score bonus.
     *
     * @param player the Cookie that interacts with this item
     */
    public void interact(Cookie player) {
        GameLogic.addScore(300);
    }
}