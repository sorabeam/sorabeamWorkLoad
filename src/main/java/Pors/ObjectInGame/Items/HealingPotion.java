package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;

public class HealingPotion extends BaseItem{
    private int healingStat;

    public HealingPotion(String name)
    {
        super(name);
        setHealingStat(20);
    }

    public int getHealingStat() {
        return healingStat;
    }

    public void setHealingStat(int healingStat) {
        this.healingStat = healingStat;
    }

    @Override
    public void interact(Cookie cookie) {
        cookie.heal(healingStat);
    }
}
