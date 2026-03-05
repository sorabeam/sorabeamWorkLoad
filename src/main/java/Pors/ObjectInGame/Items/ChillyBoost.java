package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import GameLogic.GameLogic;
import Pors.ObjectInGame.Spawner;

public class ChillyBoost extends BaseItem {
    private double speedMul = 3;
    private int boostTime = 5000;

    public ChillyBoost() {
        super("ChillyBoost");
    }

    public void interact(Cookie player) {
        GameLogic.addScore(1000);
        player.setInvincible(6.0);

        Thread speedBoostThread = new Thread(() -> {
            Spawner.setSpeed(Spawner.getDefaultSpeed()*speedMul);
            try {
                player.setSpeeding(true);
                Thread.sleep(boostTime);
                player.setSpeeding(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Spawner.resetSpeed();

        });
        speedBoostThread.start();
    }
}
