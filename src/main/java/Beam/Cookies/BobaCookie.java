package Beam.Cookies;

import Beam.Animation.AnimationType;
import Filmmy.Pearl;
import Beam.Animation.Animate;
import Beam.Asset;

public class BobaCookie extends Cookie{

    public BobaCookie(){

        super(1, "BobaCookie" , 500,
                "Boba Milk Tea Cookie is a laid-back spirit " +"\n"+
                      "and the true queen of the boba world." +"\n"+
                      "Every 10 seconds, she launches the Pearl "+ " \n " +
                      "Beads forward, smashing through obstacles "+ " \n " +
                      "and earning bonus points.?");

        setImgURL("Boba_Milk_Tea_Cookie");
        skillcoodown = 10;
    }

    @Override
    public Animate createCookie(){
        return super.createCookie();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        accumulator += deltaTime;

        if (accumulator >= 1) {

            accumulator = 0;
            cdvalues -= 1;

            if (cdvalues <= 0) {
                useSkill();
                cdvalues = skillcoodown;
                System.out.println(cdvalues);
            }
        }
    }

    @Override
    public void useSkill() {
        Pearl pearl = new Pearl(
                cookie.getLayoutX() + cookie.getFitWidth(),
                cookie.getLayoutY() + cookie.getFitHeight() * 0.6
        );

        getParentLayer().getChildren().add(pearl);

        playSkill(0.3);
        cookie.changeAnimationState(AnimationType.SKILL);
    }

}
