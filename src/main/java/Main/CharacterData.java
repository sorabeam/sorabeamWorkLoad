package Main;

import Main.Cookies.*;
import Main.Pets.*;

/**
 * Stores all playable characters (Cookies) and pets available in the game.
 * <p>
 * This class acts as a central data holder for character selection.
 * It provides predefined cookie and pet instances and keeps track
 * of the currently selected cookie and pet used during gameplay.
 */
public class CharacterData {

    /**
     * Stores the BobaCookie character instance available for selection in the game.
     */
    public static final Cookie BOBA_COOKIE = new BobaCookie();

    /**
     * Stores the CrossiantCookie character instance available for selection.
     */
    public static final Cookie CROSSIANT_COOKIE = new CrossiantCookie();

    /**
     * Stores the TomYumCookie character instance available for selection.
     */
    public static final Cookie TOMYUM_COOKIE = new TomYumCookie();

    /**
     * Represents a locked cookie character that cannot be selected
     * until it is unlocked.
     */
    public static final Cookie LOCKING_COOKIE =
            new SampleCookie(4, "Holly Berry", 500, "MeowMeow");

    /**
     * Stores the Salad pet instance available for selection.
     */
    public static final Pet SALAD = new Salad();

    /**
     * Stores the Chilly pet instance available for selection.
     */
    public static final Pet CHILLY = new Chilly();

    /**
     * Stores the Moji pet instance available for selection.
     */
    public static final Pet MOJI = new Moji();

    /**
     * Stores another reference to a Chilly pet instance.
     */
    public static final Pet Chilly = new Chilly();

    /**
     * Represents a locked pet that cannot be selected until it is unlocked.
     */
    public static final Pet LOCKING =
            new SamplePet(4, "Lock","","UnSelect_Lock");

    /**
     * Stores the currently selected cookie character in the game.
     */
    private static Cookie Current_Cookie = BOBA_COOKIE;

    /**
     * Stores the currently selected pet companion in the game.
     */
    private static Pet Current_Pet = SALAD;

    /**
     * Returns the currently selected cookie character.
     *
     * @return the current cookie used in the game
     */
    public static Cookie getCurrent_Cookie() {
        return Current_Cookie;
    }

    /**
     * Updates the currently selected cookie character.
     *
     * @param current_Cookie the cookie to set as the current character
     */
    public static void setCurrent_Cookie(Cookie current_Cookie) {
        Current_Cookie = current_Cookie;
    }

    /**
     * Returns the currently selected pet.
     *
     * @return the current pet companion
     */
    public static Pet getCurrent_Pet() {
        return Current_Pet;
    }

    /**
     * Updates the currently selected pet.
     *
     * @param current_Pet the pet to set as the current companion
     */
    public static void setCurrent_Pet(Pet current_Pet) {
        Current_Pet = current_Pet;
    }
}
