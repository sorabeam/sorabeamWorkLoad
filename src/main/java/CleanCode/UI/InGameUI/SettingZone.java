package CleanCode.UI.InGameUI;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Button.SettingBtn;
import supakorn.Image.OutlineText;

public class SettingZone extends HBox {

    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        SettingBtn SettingBtn =new SettingBtn(SettingImg,root);
        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);
        setMaxWidth(500);

        setSpacing(10);
        getChildren().addAll(spacer,WifiImg,SettingBtn);

    }
}