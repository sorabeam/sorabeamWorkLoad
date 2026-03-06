package Main.Cookies;

import Main.Asset;

/**
 * Cookie class representing TomYum Cookie.
 *
 * Every 15 seconds Tom Yum Cookie summons Ingredient Rain.
 * Ingredient jellies fall from the sky and grant bonus points.
 */
public class TomYumCookie extends Cookie {

    /**
     * Indicates whether the ingredient rain skill is ready.
     */
    private boolean rainReady = false;

    /**
     * Initializes TomYum Cookie with predefined stats and skill settings.
     */
    public TomYumCookie() {
        super(2, "TomYum", 170,
                "Every 15 seconds, Tom Yum Cookie\nsummons Ingredient Rain.\nShrimp, Galangal, Lemongrass,\nand Kaffir Lime Leaf\nJellies fall from the sky,\ngranting bonus points.");
        setImgURL("TomYum_Cookie_sheet");
        setProfileImg(Asset.getImage("Profile_Tomyum"));
        setScore(133000);
        setHasCooldown(true);
        setCooldownTimer(0);
        setSkillCooldown(15);
    }

    /**
     * Activates the ingredient rain skill.
     *
     * The cookie becomes temporarily invincible and prepares
     * the ingredient rain event.
     */
    @Override
    public void useSkill() {
        if (isDead()) return;

        playSkill(0.5);
        setCooldownTimer(0);
        setInvincible(2.0);
        rainReady = true;
    }

    /**
     * Updates the skill cooldown timer.
     *
     * @param dt time passed since the last update
     */
    public void updateSkill(double dt){

        setCooldownTimer(getCooldownTimer() + dt);

        if(getCooldownTimer() >= getSkillCooldown()){
            useSkill();
        }
    }

    /**
     * Returns the cooldown progress of the skill.
     *
     * @return cooldown progress value between 0 and 1
     */
    @Override
    public double getCooldownProgress(){
        return Math.min(cooldownTimer / skillCooldown, 1.0);
    }

    /**
     * Returns whether the ingredient rain skill is ready.
     *
     * @return true if the rain skill is ready
     */
    public boolean isSkillReady(){
        return rainReady;
    }

    /**
     * Consumes the ingredient rain trigger.
     */
    public void consumeRain(){
        rainReady = false;
    }
}