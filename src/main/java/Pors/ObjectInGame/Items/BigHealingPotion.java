package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;

public class BigHealingPotion extends BaseItem{
    private int healingStat;

    public BigHealingPotion()
    {
        super("BigHealingPotion");
        setHealingStat(50);
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
