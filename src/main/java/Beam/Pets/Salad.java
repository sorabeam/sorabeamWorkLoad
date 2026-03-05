package Beam.Pets;

import Beam.Asset;
import Pors.ObjectInGame.Items.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Salad extends Pet {

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
        setBgImage(Asset.getImage("Selecting_Salad"));
        setBtnImage(Asset.getImage("UnSelect_Salad"));
        setCooldowntime(5000);
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
