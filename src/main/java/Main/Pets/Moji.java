package Main.Pets;

import Main.Asset;
import Main.ObjectInGame.Items.ItemView;
import Main.ObjectInGame.Items.StickyMochi;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the Moji pet.
 *
 * Moji periodically produces StickyMochi items which give
 * both score and healing when collected by the player.
 */
public class Moji extends Pet {

    /**
     * Creates a Moji pet with its default configuration including
     * images, cooldown settings, movement speed, and the item
     * produced by its skill.
     */
    public Moji() {
        super(3, "Moji", "Produce Mochi jelly,\nevery 20 seconds Contains\nscores and healing");

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

    /**
     * Activates the pet's skill if it is available.
     *
     * The skill temporarily disables itself and enters a cooldown
     * period before it can be used again.
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