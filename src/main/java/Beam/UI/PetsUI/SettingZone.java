package Beam.UI.PetsUI;

import Beam.Button.SettingPopUpBtn;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Beam.Asset;
import Beam.Button.NavSettingBtn;
import Beam.Image.OutlineText;

public class SettingZone extends HBox {

    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        OutlineText Pname = new OutlineText("sorabeam",'C',18);

        SettingPopUpBtn NavSettingBtn =new SettingPopUpBtn(SettingImg,root);
        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);

        setPadding(new Insets(0,22,0,0));
        setSpacing(10);
        getChildren().addAll(spacer,Pname,WifiImg, NavSettingBtn);

    }
}
