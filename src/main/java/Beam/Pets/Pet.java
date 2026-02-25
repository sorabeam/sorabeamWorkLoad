package Beam.Pets;

import javafx.scene.image.ImageView;


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

    public Pet(int id, String name, String desc) {
        this.id = id;
        Pid = "P" + id;
        BGid = "BG" + id;
        this.name = name;
        this.desc = desc;
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
}
