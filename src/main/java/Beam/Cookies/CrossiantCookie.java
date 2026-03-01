package Beam.Cookies;

import Beam.Animation.Animate;

public class CrossiantCookie extends Cookie {
    public CrossiantCookie() {
        super(2, "CrossiantCookie", 140, "");

        setImgURL("crossiant_sheet");
        skillcoodown = 10;
    }

    @Override
    public Animate createCookie() {
        return super.createCookie();
    }

    @Override
    public void useSkill() {

    }
}
