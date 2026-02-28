
package Beam.Scene;

import Beam.Button.CDBtn;
import Beam.Button.SelectPBtn;
import Beam.UI.PetsUI.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Beam.CharactorData;
import Beam.Image.OutlineText;

public class PetsSelectionScene extends BaseRoot{

    ImageView Picture;
    ImageView BGpicture;
    OutlineText name,Description;

    SelectPBtn ins1, ins2, ins3, ins4;

    private String txt = CharactorData.getCurrent_Pet().getDesc();

    public PetsSelectionScene(){
        super();

        PetSelectionBtn petSelectionBtn = new PetSelectionBtn(spacer('H'));
        SettingZone settingZone = new SettingZone(root,spacer('H'));
        HBox petsShow = new PetsShow(spacer('H'),ins1, ins2, ins3, ins4);
        SelectingView selectingView = new SelectingView();

        VBox leftVBox = new VBox(selectingView);
        VBox rightVBox = new VBox(settingZone,petsShow,petSelectionBtn);
        HBox mainHBox = new HBox(leftVBox,spacer('H'),rightVBox);


        HBox.setMargin(rightVBox,new Insets(20,20,30,0));

        root.getChildren().addAll(
                new PetSelectionBG(scene),
                mainHBox
        );

        leftVBox.setPadding(new Insets(0,0,0,50));
        leftVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(50);

    }

    private void enableSwap(SelectPBtn button) {

        button.setOnAction(e -> {

            Integer col = GridPane.getColumnIndex(button);
            Integer row = GridPane.getRowIndex(button);

            Picture.setImage(button.getI());
            name.setText(button.getName());
            Description.setText(button.getD());
            BGpicture.setImage(button.getBg());

        });
    }
}
