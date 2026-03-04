package Beam.Pets;

import Beam.Asset;
import Pors.ObjectInGame.Items.ChillyBoost;
import Pors.ObjectInGame.Items.ItemView;

import java.util.ArrayList;
import java.util.Arrays;

public class Chilly extends Pet {
    public Chilly() {
        super(1, "Chilly", " au ai ah ");

        setView(Asset.createImageView("Chilly",0,480));
        setBg(Asset.createImageView("Selecting_Boba",0,350));
        setBtnView(Asset.createImageView("UnSelect_Boba",0,230));
//        setCooldowntime(30000);
        setCooldowntime(5000);
        setSkillReady(true);
        setUsingSkill(false);
        setSpeed(500);

        setSpawnItemList(
                new ArrayList<>(Arrays.asList(
                        () -> new ItemView(new ChillyBoost(), 0, 0)
                ))
        );
    }

    @Override
    public void useSkill() {
        if(!isSkillReady() || isUsingSkill()) return;
        Thread skillCooldownThread = new Thread(() -> {
            try {
                setUsingSkill(true);
                setSkillReady(false);
                Thread.sleep(getCooldowntime());
                setSkillReady(true);
                setUsingSkill(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        skillCooldownThread.start();
    }
}
