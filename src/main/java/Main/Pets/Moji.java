package Main.Pets;

import Main.Asset;
import Main.ObjectInGame.Items.ItemView;
import Main.ObjectInGame.Items.StickyMochi;

import java.util.ArrayList;
import java.util.Arrays;

public class Moji extends Pet {

    public Moji() {
        super(3, "Moji", """
                Produce Mochi jelly,
                every 20 seconds Contains
                scores and healing""");

        setView(Asset.createImageView("Moji",0,480));
        setViewImage(Asset.getImage("Moji"));
        setBackGroundImage(Asset.getImage("Selecting_Boba"));
        setButtonImage(Asset.getImage("UnSelect_Boba"));
        setCooldownTime(20000);
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
                Thread.sleep(getCooldownTime());
                setSkillReady(true);
                setUsingSkill(false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        skillCooldownThread.start();
    }
}
