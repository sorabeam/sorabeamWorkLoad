package Main.ObjectInGame.Items;

import Main.Cookies.Cookie;

/**
 * Represents a healing potion item that restores a small amount of health
 * to a Cookie when used.
 */
public class HealingPotion extends BaseItem {

    /**
     * The amount of health restored by this potion.
     */
    private int healingStat;

    /**
     * Creates a HealingPotion with a predefined healing value.
     * The item name is set through the BaseItem constructor.
     */
    public HealingPotion()
    {
        super("HealingPotion");
        setHealingStat(20);
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