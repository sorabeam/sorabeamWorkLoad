package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;

/**
 * Represents a Chicken item in the game.
 *
 * When the player interacts with this item, it increases the game score.
 */
public class Chicken extends BaseItem {

    /**
     * Creates a Chicken item with the default name "Chicken".
     */
    public Chicken() {
        super("Chicken");
    }

    /**
     * Handles the interaction between the player and this item.
     *
     * When used, the player's action adds 500 points to the game score.
     *
     * @param player the Cookie that interacts with this item
     */
    public void interact(Cookie player) {
        GameLogic.addScore(500);
    }
}