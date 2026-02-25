package Beam.Pets;

import Beam.Asset;

public class SamplePet extends Pet{

    public SamplePet(int id, String name, String desc,String imgkey) {
        super(id, name, desc);

        setView(Asset.createImageView(imgkey,0,230));
        setBg(Asset.createImageView("Selecting_Boba",0,350));
        setBtnView(Asset.createImageView(imgkey,0,230));
    }

    @Override
    public void useSkill() {

    }
}
