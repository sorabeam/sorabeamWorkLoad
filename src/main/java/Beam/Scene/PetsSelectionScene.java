
package Beam.Scene;

import Beam.Button.SelectPBtn;
import Beam.UI.PetsUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Beam.CharactorData;
import Beam.Image.OutlineText;

public class PetsSelectionScene extends BaseRoot{

    ImageView Showi,Bgi;
    OutlineText name,Description;

    SelectPBtn ins1, ins2, ins3, ins4;

    private String txt = CharactorData.getCurrent_Pet().getDesc();

    public PetsSelectionScene(){
        super();

        ins1 = new SelectPBtn(CharactorData.MOJINIGA,28,50);
        ins2 = new SelectPBtn(CharactorData.MOJINIGA,28,50);
        ins3 = new SelectPBtn(CharactorData.SALAD,28,50);
        ins4 = new SelectPBtn(CharactorData.LOCKING,28,50);

        HBox box = new HBox(spacer('H'),ins1, ins2, ins3, ins4);
        box.setSpacing(-15);
        box.setPadding(new Insets(0,0,0,0));

        PetSelectionBtn petSelectionBtn = new PetSelectionBtn(spacer('H'));
        SettingZone settingZone = new SettingZone(root,spacer('H'));
        SelectingView selectingView = new SelectingView(root,this);

        VBox leftVBox = new VBox(selectingView);
        VBox rightVBox = new VBox(settingZone,box,petSelectionBtn);
        HBox mainHBox = new HBox(leftVBox,spacer('H'),rightVBox);

        HBox.setMargin(rightVBox,new Insets(20,20,30,0));

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
    public OutlineText getName() { return name; }
    public void setName(OutlineText name) { this.name = name; }
    public OutlineText getDescription() { return Description; }
    public void setDescription(OutlineText description) { Description = description; }
    public SelectPBtn getIns1() { return ins1; }
    public void setIns1(SelectPBtn ins1) { this.ins1 = ins1; }
    public SelectPBtn getIns2() { return ins2; }
    public void setIns2(SelectPBtn ins2) { this.ins2 = ins2; }
    public SelectPBtn getIns3() { return ins3; }
    public void setIns3(SelectPBtn ins3) { this.ins3 = ins3; }
    public SelectPBtn getIns4() { return ins4; }
    public void setIns4(SelectPBtn ins4) { this.ins4 = ins4; }
    public String getTxt() { return txt; }
    public void setTxt(String txt) { this.txt = txt; }

    private void enableSwap(SelectPBtn button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            System.out.println("jutyhng");

            Showi.setImage(button.getI());
            Bgi.setImage(button.getBg());
            name.setText(button.getName());
            Description.setText(button.getD());

        });

    }
}
