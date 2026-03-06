package Main.Cookies;

import Main.Animation.Animate;

/**
 * Sample cookie implementation used as a placeholder or template.
 *
 * This class extends Cookie and provides a basic structure
 * for creating new cookie characters.
 */
public class SampleCookie extends Cookie{

    /**
     * Creates a sample cookie with basic character information.
     *
     * @param id unique identifier of the cookie
     * @param name name of the cookie
     * @param hp maximum health points of the cookie
     * @param description description of the cookie
     */
    public SampleCookie(int id, String name, int hp, String description) {
        super(id, name, hp, description);
    }

    /**
     * Activates the cookie skill.
     *
     * This method should be overridden to implement
     * the specific skill behavior of the cookie.
     */
    @Override
    public void useSkill() {

    }

    /**
     * Creates the animation controller for the cookie.
     *
     * This method should be overridden to provide
     * the cookie animation implementation.
     *
     * @return Animate object used to control cookie animation
     */
    @Override
    public Animate createCookie() {
        return null;
    }
}