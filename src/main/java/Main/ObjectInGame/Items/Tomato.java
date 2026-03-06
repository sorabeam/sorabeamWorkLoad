package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;

/**
 * Represents a Tomato item that restores health and gives a small score bonus
 * when collected by the player.
 */
public class Tomato extends BaseItem {

    /**
     * Creates a Tomato item with the default name "Tomato".
     */
    public Tomato() {
        super("Tomato");
    }

    /**
     * Handles the interaction between the player and the tomato item.
     *
     * When collected, the player recovers health and gains a small amount
     * of score.
     *
     * @param player the Cookie that interacts with this item
     */
    public void interact(Cookie player) {
        player.heal(20);
        GameLogic.addScore(100);
    }
}