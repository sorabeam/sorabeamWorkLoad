package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import GameLogic.GameLogic;

public class Tomato extends BaseItem {
    public Tomato() {
        super("Tomato");
    }

    public void interact(Cookie player) {
        player.heal(20);
        GameLogic.addScore(100);
    }
}
