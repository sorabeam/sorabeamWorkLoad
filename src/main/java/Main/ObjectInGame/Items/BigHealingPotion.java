package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;

/**
 * Represents a healing item that restores a large amount of health to a Cookie.
 *
 * When interacted with, this potion heals the Cookie using its healing stat value.
 */
public class BigHealingPotion extends BaseItem {

    /**
     * The amount of health restored when the potion is used.
     */
    private int healingStat;

    /**
     * Creates a BigHealingPotion with a predefined healing value.
     * The item name is set through the BaseItem constructor.
     */
    public BigHealingPotion()
    {
        super("BigHealingPotion");
        setHealingStat(50);
    }

    /**
     * Returns the healing value of this potion.
     *
     * @return the amount of health restored
     */
    public int getHealingStat() {
        return healingStat;
    }

    /**
     * Sets the healing value of this potion.
     *
     * @param healingStat the new healing amount
     */
    public void setHealingStat(int healingStat) {
        this.healingStat = healingStat;
    }

    /**
     * Applies the healing effect to the specified Cookie.
     *
     * @param cookie the Cookie that receives the healing effect
     */
    @Override
    public void interact(Cookie cookie) {
        cookie.heal(getHealingStat());
    }
}