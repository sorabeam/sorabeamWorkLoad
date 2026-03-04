package Beam.Pets;

import Pors.ObjectInGame.Items.ItemView;
import Pors.ObjectInGame.Items.StickyMochi;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;


public abstract class Pet {
    int id;
    int cooldowntime;
    String Pid;   //paneID
    String BGid;

    String name;
    String desc;
    ImageView view;
    ImageView Bg;
    ImageView BtnView;
    private boolean isSkillReady;
    private boolean isUsingSkill;

    private double targetPosX;
    private double targetPoxY;
    private double speed;

    private int indexRoll = -1; //updateIndex before using
    private ArrayList<Supplier<ItemView>> spawnItemList;
    private ArrayList<Double> probability;
    private boolean useRandomSpin;

    public Pet(int id, String name, String desc) {
        this.id = id;
        Pid = "P" + id;
        BGid = "BG" + id;
        this.name = name;
        this.desc = desc;
        useRandomSpin = false;
    }

    public boolean hasArrived() {
        double dx = getTargetPosX()-getView().getLayoutX();
        double dy = getTargetPoxY()-getView().getLayoutY();
        return Math.hypot(dx, dy)<=3;
    }

    public void update(double dt) {
        double x = getView().getLayoutX();
        double y = getView().getLayoutY();
        double ux = getTargetPosX()-x;
        double uy = getTargetPoxY()-y;
        double dist = Math.hypot(ux, uy);
        if(dist<1e-6) return;
        double step = Math.min(speed*dt, dist);
        view.setLayoutX(x+ux/dist*step);
        view.setLayoutY(y+uy/dist*step);
    }

    public void updateIndex() {
        if(useRandomSpin) {
            double r = Math.random();
            double cumulative = 0;
            for (int i = 0; i < probability.size(); i++) {
                cumulative += probability.get(i);
                if (r <= cumulative) {
                    indexRoll = i;
                    return;
                }
            }
            indexRoll = probability.size() - 1;
        } else {
            indexRoll+=1;
            int sz = spawnItemList.size();
            if(indexRoll>=sz) indexRoll = 0;
        }
    }

    public int getId() { return id; }
    public String getPid() { return Pid; }
    public String getName() { return name; }
    public String getBGid() { return BGid; }

    public abstract void useSkill();

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public ImageView getBg() {
        return Bg;
    }

    public void setBg(ImageView bg) {
        Bg = bg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ImageView getBtnView() {
        return BtnView;
    }

    public void setBtnView(ImageView btnView) {
        BtnView = btnView;
    }

    public int getCooldowntime() {
        return cooldowntime;
    }

    public void setCooldowntime(int cooldowntime) {
        this.cooldowntime = cooldowntime;
    }

    public void ActiveCooldowm(){
        // threds.sleep(cooldowntime)
    }

    public void setUsingSkill(boolean usingSkill) {
        isUsingSkill = usingSkill;
    }

    public void setSkillReady(boolean skillReady) {
        isSkillReady = skillReady;
    }

    public boolean isUsingSkill() {
        return isUsingSkill;
    }

    public boolean isSkillReady() {
        return isSkillReady;
    }

    public void setTargetPos(double targetPosX, double targetPosY) {
        this.targetPosX = targetPosX;
        this.targetPoxY = targetPosY;
    }

    public double getTargetPosX() {
        return targetPosX;
    }

    public double getTargetPoxY() {
        return targetPoxY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public ItemView getCurrentSpawnItem() {
        return spawnItemList.get(indexRoll).get();
    }

    public void setSpawnItemList(ArrayList<Supplier<ItemView>> spawnItemList) {
        this.spawnItemList = spawnItemList;
    }

    public ArrayList<Supplier<ItemView>> getSpawnItemList() {
        return spawnItemList;
    }

    public void setUseRandomSpin(boolean useRandomSpin) {
        this.useRandomSpin = useRandomSpin;
    }

    public ArrayList<Double> getProbability() {
        return probability;
    }

    public void setProbability(ArrayList<Double> probability) {
        this.probability = probability;
    }
}
