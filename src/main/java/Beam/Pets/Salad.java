package Beam.Pets;

import Beam.Asset;
import Pors.ObjectInGame.Items.Chicken;
import Pors.ObjectInGame.Items.ItemView;
import Pors.ObjectInGame.Items.Tomato;
import Pors.ObjectInGame.Items.Vegetable;

import java.util.ArrayList;
import java.util.Arrays;

public class Salad extends Pet {

    public Salad() {
        super(1, "Salad", " I want to break free ");

        setView(Asset.createImageView("Salad",0,480));
        setBg(Asset.createImageView("Selecting_Boba",0,350));
        setBtnView(Asset.createImageView("UnSelect_Boba",0,230));
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
