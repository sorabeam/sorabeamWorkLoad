package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Spawner;

public class SpeedBoost extends BaseItem{
    private int speedStat;

    public SpeedBoost() {
        super("ChillyBoost");
        setSpeedStat(3);
    }

    public int getSpeedStat() {
        return speedStat;
    }

    public void setSpeedStat(int speedStat) {
        this.speedStat = speedStat;
    }

    @Override
    public void interact(Cookie player) {
        player.setInvincible(6.0);

        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*getSpeedStat());
            try {
                player.setSpeeding(true);
                Thread.sleep(4000);
                player.setSpeeding(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();

        });
        speedBoostThread.start();
    }
}
