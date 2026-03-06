package Main.ObjectInGame.Items;

/**
 * Represents the different types of Croissant items available in the game.
 *
 * Each type determines the effect applied when the croissant is collected
 * or interacted with by the player.
 */
public enum CroissantType {

    /**
     * The standard croissant type that grants a large score bonus.
     */
    ORIGINAL,

    /**
     * A butter croissant that grants score and activates a speed boost effect.
     */
    BUTTER,

    /**
     * A strawberry croissant that heals the player and grants score.
     */
    STRAWBERRY
}