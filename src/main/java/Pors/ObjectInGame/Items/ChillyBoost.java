package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Spawner;

public class ChillyBoost extends BaseItem {
    private double speedMul = 2;
//    private int boostTime = 3000;
    private int boostTime = 30000;

    public ChillyBoost() {
        super("ChillyBoost");
    }


    public void interact(Cookie player) {
        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*speedMul);
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
