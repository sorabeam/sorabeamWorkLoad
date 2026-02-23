package CleanCode.Scene;

import CleanCode.UI.PetsUI.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PetsSelectionScene extends BaseScene{

    public PetsSelectionScene(){

        PetSelectionBtn petSelectionBtn = new PetSelectionBtn(spacer('H'));
        SettingZone settingZone = new SettingZone(root,spacer('H'));
        HBox petsShow = new PetsShow(spacer('H'));
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

//        petsShow.setBackground(new Background(
//                new BackgroundFill(Color.BLUE,null,null)
//        ));
//
//        rightVBox.setBackground(new Background(
//                new BackgroundFill(Color.VIOLET,null,null)
//        ));
//
//        selectingView.setBackground(new Background(
//                new BackgroundFill(Color.YELLOW,null,null)
//        ));
//
//        petSelectionBtn.setBackground(new Background(
//                new BackgroundFill(Color.GREEN,null,null)
//        ));

    }
}
