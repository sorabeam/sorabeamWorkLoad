package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Spawner;

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
    public void interact(Cookie player) {
        //fill code//
        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*speedStat);
            try {
                Thread.sleep(speedStat);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();
        });
        speedBoostThread.start();
    }
}
