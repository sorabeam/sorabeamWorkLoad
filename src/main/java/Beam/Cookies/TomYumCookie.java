package Beam.Cookies;

import Beam.Asset;

public class TomYumCookie extends Cookie {

    private boolean rainReady = false;

    public TomYumCookie() {
        super(2, "TomYum", 170,
                "Every 20 seconds, Tom Yum Cookie" + "\n" +
                        "summons Ingredient Rain." + "\n" +
                        "Shrimp, Galangal, Lemongrass," + "\n" +
                        "and Kaffir Lime Leaf" + "\n" +
                        "Jellies fall from the sky," + "\n" +
                        "granting bonus points.");
        setImgURL("TomYum_Cookie_sheet");
        setProfileImg(Asset.getImage("Profile_Tomyum"));
        setScore(133000);
        setCooldownable(true);
        setCooldownTimer(0);
        setSkillCooldown(20);
    }

    @Override
    public void useSkill() {

        //playSkill(0.3); // animation

        //rainReady = true;
    }

    public void updateSkill(double dt){

        setCooldownTimer(getCooldownTimer() + dt);

        if(getCooldownTimer() >= getSkillCooldown()){
            playSkill(0.5);
            setCooldownTimer(0);
            setInvincible(2.0);
            rainReady = true;
        }
    }

    @Override
    public double getCooldownProgress(){
        return Math.min(cooldownTimer / skillCooldown, 1.0);
    }

    public boolean isSkillReady(){
        return rainReady;
    }

    public void consumeRain(){
        rainReady = false;
    }
}
