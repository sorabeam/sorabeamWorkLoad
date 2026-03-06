package Main.GameLogic;

/**
 * Enum representing the different states of the game.
 *
 * This enum is used by GameLogic to control scene transitions
 * and game flow.
 */
public enum GameState {

    /**
     * Intro screen of the game.
     */
    INTRO,

    /**
     * Character selection screen.
     */
    SELECT_CHAR,

    /**
     * Gameplay state where the player is actively playing.
     */
    IN_GAME,

    /**
     * Game over screen displayed when the player loses.
     */
    GAME_OVER,

    /**
     * Pet selection screen.
     */
    SELECT_PET
}