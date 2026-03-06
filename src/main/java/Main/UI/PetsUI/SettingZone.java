package Main.UI.PetsUI;

import Main.Button.SettingsPopUpButton;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Main.Asset;
import Main.Image.OutlineTextImage;

/**
 * A UI component that represents the settings area in the pet selection screen.
 *
 * <p>This layout contains the player name, Wi-Fi status icon, and a settings
 * button. A spacer is used to push the elements to the right side of the
 * container.</p>
 */
public class SettingZone extends HBox {

    /**
     * Constructs the settings zone for the pet selection UI.
     *
     * <p>The component creates a settings button, Wi-Fi icon, and player name
     * display. The elements are arranged horizontally and aligned using a
     * spacer so that they appear on the top-right side of the interface.</p>
     *
     * @param root the root pane used by the settings popup button
     * @param spacer a flexible region used to push the components to the right
     */
    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        OutlineTextImage Pname = new OutlineTextImage("sorabeam",'C',18);

        SettingsPopUpButton NavSettingBtn = new SettingsPopUpButton(SettingImg,root);
        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);

        setPadding(new Insets(0,22,0,0));
        setSpacing(10);
        getChildren().addAll(spacer,Pname,WifiImg, NavSettingBtn);
    }
}
