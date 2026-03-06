package Main.Cookies;

import Main.Animation.AnimationType;
import Main.ObjectInGame.Items.Pearl;
import Main.Animation.Animate;
import Main.Asset;

/**
 * Concrete cookie class representing Boba Cookie.
 *
 * Boba Cookie periodically launches Pearl Beads forward as a skill.
 * The pearl travels through the stage, breaking obstacles and
 * granting additional score bonuses.
 */
public class BobaCookie extends Cookie{

    /**
     * Creates a Boba Cookie with predefined stats, profile image,
     * and skill cooldown configuration.
     */
    public BobaCookie(){

        super(1, "Boba" , 150,
                "Every 10 seconds Boba Cookie\nlaunches the Pearl Beads forward,\nsmashing through obstacles and\nearning bonus points.");

        setImgURL("Boba_Milk_Tea_Cookie");
        setProfileImg(Asset.getImage("Profile_Boba"));
        setScore(23500);
        setCooldownTimer(0);
        setSkillCooldown(10);
        setHasCooldown(true);
    }

    /**
     * Creates and returns the animation controller for the cookie.
     *
     * @return Animate object used to control the cookie animation
     */
    @Override
    public Animate createCookie(){
        return super.createCookie();
    }

    /**
     * Updates the cookie state every frame.
     *
     * This method updates cooldown timing and automatically
     * activates the skill when the cooldown reaches its limit.
     *
     * @param deltaTime time passed since the last update
     */
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if (isDead()) return;

        setCooldownTimer(getCooldownTimer() + deltaTime);

        if (getCooldownTimer() >= getSkillCooldown()) {
            useSkill();
            setCooldownTimer(0);
        }
    }

    /**
     * Activates the Boba Cookie skill.
     *
     * The cookie becomes temporarily invincible and launches
     * a Pearl projectile forward to break obstacles.
     */
    @Override
    public void useSkill() {
        setInvincible(2.0);

        Pearl pearl = new Pearl(
                cookie.getLayoutX() + cookie.getFitWidth(),
                cookie.getLayoutY() + cookie.getFitHeight() * 0.6
        );

        getParentLayer().getChildren().add(pearl);

        playSkill(0.6);
        cookie.changeAnimationState(AnimationType.SKILL);
    }

    /**
     * Returns the cooldown progress of the skill.
     *
     * @return value between 0 and 1 representing cooldown progress
     */
    @Override
    public double getCooldownProgress(){
        return Math.min(getCooldownTimer() / getSkillCooldown(), 1.0);
    }
}