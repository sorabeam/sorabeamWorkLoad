package Beam.Cookies;

import Beam.Animation.AnimationType;
import Filmmy.Pearl;
import Beam.Animation.Animate;
import Beam.Asset;

public class BobaCookie extends Cookie{

    protected double cooldownTimer = 0;
    protected double skillCooldown = 10;

    public BobaCookie(){

        super(1, "Boba" , 150,
                "Boba Milk Tea Cookie is a laid-back" +"\n"+
                        "spirit  and the true queen of" +"\n"+
                        "the boba world. Every 10 seconds,"+ "\n" +
                        "she launches the Pearl Beads forward," + "\n" +
                        "smashing through obstacles and" + "\n" +
                        "earning bonus points." );

        setImgURL("Boba_Milk_Tea_Cookie");
        setProfileImg(Asset.getImage("Profile_Boba"));
        setScore(23500);
    }

    @Override
    public Animate createCookie(){
        return super.createCookie();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        cooldownTimer += deltaTime;

        if (cooldownTimer >= skillCooldown) {
            useSkill();
            cooldownTimer = 0;
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

        playSkill(0.3);
        cookie.changeAnimationState(AnimationType.SKILL);
    }

    @Override
    public double getCooldownProgress(){
        return Math.min(cooldownTimer / skillCooldown, 1.0);
    }

}
