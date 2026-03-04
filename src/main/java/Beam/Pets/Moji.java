package Beam.Pets;

import Beam.Asset;
import Pors.ObjectInGame.Items.ItemView;
import Pors.ObjectInGame.Items.StickyMochi;

import java.util.ArrayList;
import java.util.Arrays;

public class Moji extends Pet {

    public Moji() {
        super(3, "Moji Niga", " au ai ah ");

        setView(Asset.createImageView("Moji",0,480));
        setViewImage(Asset.getImage("Moji"));
        setBgImage(Asset.getImage("Selecting_Boba"));
        setBtnImage(Asset.getImage("UnSelect_Boba"));
        setCooldowntime(20000);
//        setCooldowntime(5000);
        setSkillReady(true);
        setUsingSkill(false);
        setSpeed(500);

        setSpawnItemList(
            new ArrayList<>(Arrays.asList(
                () -> new ItemView(new StickyMochi(), 0, 0)
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
