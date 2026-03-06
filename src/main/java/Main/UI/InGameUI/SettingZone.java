package Main.UI.InGameUI;

import Main.Button.SettingsPopUpButton;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import Main.Asset;

/**
 * A UI container that represents the settings area in the in-game interface.
 *
 * This zone is typically placed at the top-right of the screen and contains
 * a WiFi icon and a settings button. A spacer is used to push the elements
 * to the right side of the layout.
 */
public class SettingZone extends HBox {

    /**
     * Constructs the settings area containing a Wi-Fi indicator and a settings button.
     *
     * The settings button opens a settings popup when clicked. A spacer region
     * is used to control alignment so that the icons appear on the right side
     * of the container.
     *
     * @param root the root StackPane used for displaying the settings popup
     * @param spacer a flexible region used to push the icons to the right
     */
    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        SettingsPopUpButton NavSettingBtn = new SettingsPopUpButton(SettingImg,root);

        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);
        setMaxWidth(500);

        setSpacing(10);
        getChildren().addAll(spacer,WifiImg, NavSettingBtn);

    }
}
