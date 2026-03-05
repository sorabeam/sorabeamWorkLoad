package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import GameLogic.GameLogic;

public class StickyMochi extends BaseItem {
    public StickyMochi() {
        super("StickyMochi");
    }

    public void interact(Cookie player) {
        GameLogic.addScore(1000);
        player.heal(20);
    }
}
