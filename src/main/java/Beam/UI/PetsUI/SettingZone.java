package Beam.UI.PetsUI;

import Beam.Button.SettingsPopupButton;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Beam.Asset;
import Beam.Image.OutlineTextImage;

public class SettingZone extends HBox {

    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        OutlineTextImage Pname = new OutlineTextImage("sorabeam",'C',18);

        SettingsPopupButton NavSettingBtn =new SettingsPopupButton(SettingImg,root);
        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);

        setPadding(new Insets(0,22,0,0));
        setSpacing(10);
        getChildren().addAll(spacer,Pname,WifiImg, NavSettingBtn);

    }
}
