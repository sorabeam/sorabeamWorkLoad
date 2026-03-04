package Beam.Cookies;

import Beam.Asset;

public class TomYumCookie extends Cookie {
    private double rainCooldown = 5;
    private double timer = 0;

    private boolean rainReady = false;

    public TomYumCookie() {
        super(2,"TomYum",170,"Ingredient Rain");
        setImgURL("TomYum_Cookie_sheet");
        setProfileImg(Asset.getImage("Profile_Tomyum"));
        setScore(133000);
    }

    @Override
    public void useSkill() {

        //playSkill(0.3); // animation

        rainReady = true;
    }

    public void updateSkill(double dt){

        timer += dt;

        if(timer >= rainCooldown){
            playSkill(0.5);
            timer = 0;
            rainReady = true;
        }
    }

    public boolean isRainReady(){
        return rainReady;
    }

    public void consumeRain(){
        rainReady = false;
    }
}
