package supakorn.ObsticleAndItem.Items;

import supakorn.CD;

public class HealingPotion extends BaseItem{
    private int healingStat;

    public HealingPotion(String name,int healingStat)
    {
        super(name);
        setHealingStat(healingStat);
    }

    public int getHealingStat() {
        return healingStat;
    }

    public void setHealingStat(int healingStat) {
        this.healingStat = healingStat;
    }

    @Override
    public void interact(CD player) {
        //player.heal(healingStat);
    }
}
