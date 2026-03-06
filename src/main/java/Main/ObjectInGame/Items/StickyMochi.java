package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;

/**
 * Represents a StickyMochi item that provides both healing
 * and score when collected by the player.
 */
public class StickyMochi extends BaseItem {

    /**
     * Creates a StickyMochi item with the default name "StickyMochi".
     */
    public StickyMochi() {
        super("StickyMochi");
    }

    /**
     * Handles the interaction between the player and the StickyMochi item.
     *
     * When used, the player gains score and recovers a small amount of health.
     *
     * @param player the Cookie that interacts with this item
     */
    public void interact(Cookie player) {
        GameLogic.addScore(1000);
        player.heal(20);
    }
}