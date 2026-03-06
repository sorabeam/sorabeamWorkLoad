package Main.Pets;

import Main.Asset;
import Main.ObjectInGame.Items.Chicken;
import Main.ObjectInGame.Items.ItemView;
import Main.ObjectInGame.Items.Tomato;
import Main.ObjectInGame.Items.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the Salad pet.
 *
 * This pet produces food items at regular intervals using a random
 * probability system. Each spawned item gives a different benefit,
 * such as healing or score.
 */
public class Salad extends Pet {

    /**
     * Creates a Salad pet with its default configuration, including
     * images, cooldown settings, movement speed, spawnable items,
     * and item spawn probabilities.
     */
    public Salad() {
        super(3, "Salad",
                "Produces Salad Jelly" + "\n" +
                        "every 5 seconds:" + "\n" +
                        "5% Tomato heals 20 HP," + "\n" +
                        "65% Veggies give points," + "\n" +
                        "30% Chicken gives bonus"
        );

        setView(Asset.createImageView("Salad",0,480));
        setViewImage(Asset.getImage("Salad"));
        setBackGroundImage(Asset.getImage("Selecting_Salad"));
        setButtonImage(Asset.getImage("UnSelect_Salad"));
        setCooldownTime(5000);
        setSkillReady(true);
        setUsingSkill(false);
        setSpeed(500);
        setUseRandomSpin(true);

        setSpawnItemList(
                new ArrayList<>(Arrays.asList(
                        () -> new ItemView(new Tomato(), 0, 0),
                        () -> new ItemView(new Vegetable(), 0, 0),
                        () -> new ItemView(new Chicken(), 0, 0)
                ))
        );

        setProbability(new ArrayList<>(Arrays.asList(
                0.05,
                0.65,
                0.30
        )));
    }

    /**
     * Activates the pet's skill if it is available.
     *
     * The skill enters a cooldown period during which it cannot
     * be used again until the cooldown time has finished.
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