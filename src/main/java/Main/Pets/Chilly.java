package Main.Pets;

import Main.Asset;
import Main.ObjectInGame.Items.ChillyBoost;
import Main.ObjectInGame.Items.ItemView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the Chilly pet.
 *
 * This pet can produce a {@link ChillyBoost} item as its skill and uses
 * a cooldown system to control how often the skill can be activated.
 */
public class Chilly extends Pet {

    /**
     * Creates a Chilly pet with its default configuration, images,
     * movement speed, cooldown, and spawned skill item.
     */
    public Chilly() {
        super(2, "Chilly", "Produce Speed Boost" + "\n" +
                "Every 30 Seconds");

        setView(Asset.createImageView("Chilly",0,480));
        setViewImage(Asset.getImage("Chilly"));
        setBackGroundImage(Asset.getImage("Selecting_Chilly"));
        setButtonImage(Asset.getImage("UnSelect_Chilly"));
        setCooldownTime(20000);
        setSkillReady(true);
        setUsingSkill(false);
        setSpeed(500);

        setSpawnItemList(
                new ArrayList<>(Arrays.asList(
                        () -> new ItemView(new ChillyBoost(), 0, 0)
                ))
        );
    }

    /**
     * Activates the pet's skill if it is available.
     *
     * The skill starts a cooldown thread that temporarily marks the skill
     * as unavailable until the cooldown period ends.
     */
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