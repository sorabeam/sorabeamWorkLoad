package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;

public class StickyMochi extends BaseItem {
    public StickyMochi() {
        super("StickyMochi");
    }

    public void interact(Cookie player) {
        GameLogic.addScore(100);
        player.heal(20);
    }
}
