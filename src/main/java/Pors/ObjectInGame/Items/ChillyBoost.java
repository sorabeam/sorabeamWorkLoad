package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Spawner;

public class ChillyBoost extends BaseItem {
    private double speedMul = 3;
//    private int boostTime = 3000;
private int boostTime = 50000;

    public ChillyBoost() {
        super("ChillyBoost");
    }


    public void interact(Cookie player) {
        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getSpeed()*speedMul);
            try {
                Thread.sleep(boostTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();
        });
        speedBoostThread.start();
    }

    public double getSpeedMul() {
        return speedMul;
    }

    public void setSpeedMul(double speedMul) {
        this.speedMul = speedMul;
    }

    public int getBoostTime() {
        return boostTime;
    }

    public void setBoostTime(int boostTime) {
        this.boostTime = boostTime;
    }
}
