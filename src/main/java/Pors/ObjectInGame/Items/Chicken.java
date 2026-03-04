package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;

public class Chicken extends BaseItem {
    public Chicken() {
        super("Chicken");
    }

    public void interact(Cookie player) {
        GameLogic.addScore(100);
        player.heal(20);
    }
}
