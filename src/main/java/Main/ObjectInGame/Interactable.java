package Main.ObjectInGame;

import Main.Cookies.Cookie;

/**
 * Represents an object that can interact with the player in the game.
 *
 * Classes implementing this interface define what happens when the
 * player interacts with the object, such as collecting items,
 * gaining score, healing, or taking damage.
 */
public interface Interactable {

    /**
     * Defines the interaction behavior between the object and the player.
     *
     * @param player the Cookie that interacts with the object
     */
    void interact(Cookie player);
}