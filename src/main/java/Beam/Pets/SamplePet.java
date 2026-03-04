package Beam.Pets;

import Beam.Asset;

public class SamplePet extends Pet{

    public SamplePet(int id, String name, String desc,String imgkey) {
        super(id, name, desc);

        setView(Asset.createImageView("Selecting_Boba",0,480));
        setViewImage(Asset.getImage(imgkey));
        setBgImage(Asset.getImage("Selecting_Boba"));
        setBtnImage(Asset.getImage(imgkey));
    }

    @Override
    public void useSkill() {

    }
}
