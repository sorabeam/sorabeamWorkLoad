package Main.UI.CookiesUI;

import Main.Button.SettingsPopUpButton;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Main.Asset;
import Main.Image.OutlineTextImage;

/**
 * Represents the top settings area in the cookie selection UI.
 * <p>
 * This component arranges UI elements such as the player name,
 * Wi-Fi icon, and settings button inside an {@link HBox}. A spacer
 * is used to push the elements to the right side of the layout.
 */
public class SettingZone extends HBox {

    /**
     * Creates the top-right setting area containing the player name,
     * Wi-Fi icon, and a settings button.
     * <p>
     * The layout uses a spacer region to push the components to the
     * right side. The settings button opens a settings popup attached
     * to the provided root container.
     *
     * @param root   the root {@link StackPane} used to display the settings popup
     * @param spacer a flexible {@link Region} used to push UI elements to the right
     */
    public SettingZone(StackPane root, Region spacer) {

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        OutlineTextImage Pname = new OutlineTextImage("sorabeam",'C',18);

        SettingsPopUpButton NavSettingBtn = new SettingsPopUpButton(SettingImg,root);
        setMargin(WifiImg,new Insets(10,0,0,0));
        setMaxHeight(80);
        setMaxWidth(500);

        setPadding(new Insets(0,22,0,0));
        setSpacing(10);
        getChildren().addAll(spacer,Pname,WifiImg, NavSettingBtn);

    }
}
