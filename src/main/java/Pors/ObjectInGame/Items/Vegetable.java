package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;

public class Vegetable extends BaseItem{
    public Vegetable() {
        super("Vegetable");
    }

    public void interact(Cookie player) {
        GameLogic.addScore(1000);
    }
}
