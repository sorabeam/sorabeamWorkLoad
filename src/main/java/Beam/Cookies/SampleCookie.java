package Beam.Cookies;

import Beam.Animation.Animate;

public class SampleCookie extends Cookie{

    public SampleCookie(int id, String name, int hp, String desc) {
        super(id, name, hp, desc);
    }

    @Override
    public void useSkill() {

    }

    @Override
    public Animate createCookie() {
        return null;
    }
}
