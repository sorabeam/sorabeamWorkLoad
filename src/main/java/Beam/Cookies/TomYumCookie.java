package Beam.Cookies;

public class TomYumCookie extends Cookie {
    protected double cooldownTimer = 0;
    protected double skillCooldown = 20;

    private boolean rainReady = false;

    public TomYumCookie() {
        super(2, "TomYumCookie", 170,
                "Every 20 seconds, Tom Yum Cookie" + "\n" +
                        "summons Ingredient Rain." + "\n" +
                        "Shrimp, Galangal, Lemongrass," + "\n" +
                        "and Kaffir Lime Leaf" + "\n" +
                        "Jellies fall from the sky," + "\n" +
                        "granting bonus points.");
        setImgURL("TomYum_Cookie_sheet");
        setScore(133000);
    }

    @Override
    public void useSkill() {

        //playSkill(0.3); // animation

        //rainReady = true;
    }

    public void updateSkill(double dt){

        cooldownTimer += dt;

        if(cooldownTimer >= skillCooldown){
            playSkill(0.5);
            cooldownTimer = 0;
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
