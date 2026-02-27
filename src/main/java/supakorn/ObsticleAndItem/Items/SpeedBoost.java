package supakorn.ObsticleAndItem.Items;

import supakorn.CD;

public class SpeedBoost extends BaseItem{
    private int speedStat;

    public SpeedBoost(String name,int speedStat) {
        super(name);
        setSpeedStat(speedStat);
    }

    public int getSpeedStat() {
        return speedStat;
    }

    public void setSpeedStat(int speedStat) {
        this.speedStat = speedStat;
    }

    @Override
    public void interact(CD player) {
        //fill code//
    }
}
