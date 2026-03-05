package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;

public class Magnetic extends BaseItem{
    private int activeTime = 8000;

    public Magnetic() {
        super("Magnet");
    }

    @Override
    public void interact(Cookie player) {
        //fill code//
        Thread activeTimeThread = new Thread(() -> {
            try {
                player.setMagnetic(true);
                Thread.sleep(activeTime);
                player.setMagnetic(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        activeTimeThread.start();
    }

    public int getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(int activeTime) {
        this.activeTime = activeTime;
    }
}
