package Main.ObjectInGame.Obstacle;

import Main.Cookies.Cookie;
import Main.ObjectInGame.Interactable;

/**
 * Represents a basic obstacle in the game that can damage the player
 * when interacted with.
 *
 * This class serves as a base obstacle type containing common
 * properties such as name and damage value.
 */
public class BaseObstacle implements Interactable {

    /**
     * The name of the obstacle.
     */
    private String name;

    /**
     * The damage inflicted on the player when hitting the obstacle.
     */
    private int damage;

    /**
     * Creates a BaseObstacle with the specified name.
     * The default damage value is set to 40.
     *
     * @param name the name of the obstacle
     */
    public BaseObstacle(String name)
    {
        setName(name);
        setDamage(40);
    }

    /**
     * Handles the interaction between the player and the obstacle.
     *
     * When the player collides with the obstacle, damage is applied
     * to the player's health.
     *
     * @param cookie the Cookie that interacts with the obstacle
     */
    @Override
    public void interact(Cookie cookie) {
        cookie.takeDamage(getDamage());
    }

    /**
     * Returns the name of the obstacle.
     *
     * @return the obstacle name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the obstacle.
     *
     * @param name the new obstacle name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the damage value of this obstacle.
     *
     * @return the damage inflicted on the player
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage value of this obstacle.
     *
     * @param damage the new damage value
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
}