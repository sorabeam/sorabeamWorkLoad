package Main.ObjectInGame.Jelly;

import Main.Cookies.Cookie;
import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Interactable;

import java.util.Objects;

/**
 * Represents a basic jelly object in the game that the player can collect.
 *
 * Each jelly has a name and a score value that will be added to the game
 * when the player interacts with it.
 */
public class BaseJelly implements Interactable {

    /**
     * The name of the jelly.
     */
    private String name;

    /**
     * The score awarded when the jelly is collected.
     */
    private int score;

    /**
     * Creates a BaseJelly with the specified name.
     * The score value is determined based on the jelly name.
     *
     * @param name the name of the jelly
     */
    public BaseJelly(String name)
    {
        setName(name);

        if(Objects.equals(name, "Jelly2"))
        {
            setScore(200);
        }
        else
        {
            setScore(100);
        }
    }

    /**
     * Handles the interaction between the player and the jelly.
     *
     * When collected, the jelly adds its score value to the game score.
     *
     * @param cookie the Cookie that interacts with the jelly
     */
    @Override
    public void interact(Cookie cookie) {
        GameLogic.addScore(getScore());
    }

    /**
     * Returns the name of the jelly.
     *
     * @return the jelly name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the jelly.
     *
     * @param name the new jelly name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the score awarded by this jelly.
     *
     * @return the score value
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score awarded by this jelly.
     *
     * @param score the new score value
     */
    public void setScore(int score) {
        this.score = score;
    }
}