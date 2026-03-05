package Beam.Cookies;

import Beam.Animation.AnimationType;
import Filmmy.Pearl;
import Beam.Animation.Animate;
import Beam.Asset;

public class BobaCookie extends Cookie{

    public BobaCookie(){

        super(1, "Boba" , 150,
                    "Every 10 seconds Boba Cookie"+ "\n" +
                        "launches the Pearl Beads forward," + "\n" +
                        "smashing through obstacles and" + "\n" +
                        "earning bonus points." );

        setImgURL("Boba_Milk_Tea_Cookie");
        setProfileImg(Asset.getImage("Profile_Boba"));
        setScore(23500);
        setCooldownTimer(0);
        setSkillCooldown(10);
        setCooldownable(true);
    }

    @Override
    public Animate createCookie(){
        return super.createCookie();
    }

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

    @Override
    public double getCooldownProgress(){
        return Math.min(getCooldownTimer() / getSkillCooldown(), 1.0);
    }

    public double getCooldownTimer() {
        return cooldownTimer;
    }

    public void setCooldownTimer(double cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }

    public double getSkillCooldown() {
        return skillCooldown;
    }

    public void setSkillCooldown(double skillCooldown) {
        this.skillCooldown = skillCooldown;
    }
}
