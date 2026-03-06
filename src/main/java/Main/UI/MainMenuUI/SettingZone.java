package Main.UI.MainMenuUI;

import Main.Button.SettingsPopUpButton;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import Main.Asset;

/**
 * UI component representing the settings area in the main menu.
 *
 * <p>This layout contains a WiFi status icon and a settings button.
 * A spacer region is used to push the components to the right side
 * of the layout.</p>
 */
public class SettingZone extends HBox {

    /**
     * Creates and initializes the settings zone in the main menu.
     *
     * <p>The constructor creates a WiFi icon and a settings button.
     * The settings button opens a settings popup using the provided
     * root StackPane. A spacer is used to align the elements to the
     * right side of the HBox.</p>
     *
     * @param root the root pane used to display the settings popup
     * @param spacer a flexible region used to push the icons to the right side
     */
    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        SettingsPopUpButton NavSettingBtn = new SettingsPopUpButton(SettingImg, root);
        setMargin(WifiImg,new Insets(10,0,0,0));

        setMaxHeight(80);
        setMaxWidth(500);

        setSpacing(10);
        getChildren().addAll(spacer, WifiImg, NavSettingBtn);

    }
}

