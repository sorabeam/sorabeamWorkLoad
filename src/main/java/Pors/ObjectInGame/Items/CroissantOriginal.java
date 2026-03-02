package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;

public class CroissantOriginal extends BaseItem {
    private double multiplier;

//    public CroissantOriginal(String name, int multiplier){
//        super(name);
//        setMultiplier(multiplier);
//    }

    public CroissantOriginal(int multiplier){
        super("CroissantOriginal");
        setMultiplier(multiplier);
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void interact(Cookie player) {
        GameLogic.setMultiplier(GameLogic.getMultiplier()+multiplier);
    }
}
