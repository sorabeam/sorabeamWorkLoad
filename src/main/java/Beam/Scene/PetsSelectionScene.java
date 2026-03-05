
package Beam.Scene;

import Beam.Button.PetsButton;
import Beam.UI.PetsUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Beam.CharactorData;
import Beam.Image.OutlineTextImage;

public class PetsSelectionScene extends BaseScene {

    ImageView Showi,Bgi;
    OutlineTextImage name,Description;

    PetsButton ins1, ins2, ins3, ins4;

    private String txt = CharactorData.getCurrent_Pet().getDesc();

    public PetsSelectionScene(){
        super();

        ins1 = new PetsButton(CharactorData.MOJINIGA,28,50);
        ins2 = new PetsButton(CharactorData.Chilly,28,50);
        ins3 = new PetsButton(CharactorData.SALAD,28,50);
        ins4 = new PetsButton(CharactorData.LOCKING,28,50);

        HBox box = new HBox(spacer('H'),ins1, ins2, ins3, ins4);
        box.setSpacing(-15);
        box.setPadding(new Insets(0,0,0,0));

        PetSelectionBtn petSelectionBtn = new PetSelectionBtn(spacer('H'));
        SettingZone settingZone = new SettingZone(root,spacer('H'));
        SelectingView selectingView = new SelectingView(root,this);

        VBox leftVBox = new VBox(selectingView);
        VBox rightVBox = new VBox(settingZone,box,petSelectionBtn);
        VBox.setMargin(settingZone,new Insets(20,20,0,0));
        HBox mainHBox = new HBox(leftVBox,spacer('H'),rightVBox);

        HBox.setMargin(rightVBox,new Insets(8,-12,0,0));

        root.getChildren().addAll(
                new PetSelectionBG(),
                mainHBox
        );

        //root.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));

        enableSwap(ins1);
        enableSwap(ins2);
        enableSwap(ins3);

        leftVBox.setPadding(new Insets(0,0,0,50));
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(50);

    }

    public ImageView getShowi() { return Showi; }
    public void setShowi(ImageView showi) { Showi = showi; }
    public ImageView getBgi() { return Bgi; }
    public void setBgi(ImageView bgi) { Bgi = bgi; }
    public OutlineTextImage getName() { return name; }
    public void setName(OutlineTextImage name) { this.name = name; }
    public OutlineTextImage getDescription() { return Description; }
    public void setDescription(OutlineTextImage description) { Description = description; }
    public PetsButton getIns1() { return ins1; }
    public void setIns1(PetsButton ins1) { this.ins1 = ins1; }
    public PetsButton getIns2() { return ins2; }
    public void setIns2(PetsButton ins2) { this.ins2 = ins2; }
    public PetsButton getIns3() { return ins3; }
    public void setIns3(PetsButton ins3) { this.ins3 = ins3; }
    public PetsButton getIns4() { return ins4; }
    public void setIns4(PetsButton ins4) { this.ins4 = ins4; }
    public String getTxt() { return txt; }
    public void setTxt(String txt) { this.txt = txt; }

    private void enableSwap(PetsButton button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            CharactorData.setCurrent_Pet(button.getPet());

            Showi.setImage(button.getI());
            Bgi.setImage(button.getBg());
            name.setText(button.getName());
            Description.setText(button.getD());

        });

    }
}
