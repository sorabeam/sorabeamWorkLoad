package Main.Button;

import Main.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import Main.Asset;
import Main.Image.OutlineTextImage;

/**
 * Navigation button used inside the settings popup.
 *
 * This class extends NavigationButton and displays
 * outlined text on top of the button background.
 */
public class NavSettingButton extends NavigationButton {

    /**
     * Custom outlined text image displayed on the button.
     */
    private final OutlineTextImage outlineTextImage;

    /**
     * Stores the button label text.
     */
    private final String txt;

    /**
     * Reference to the popup button that will be closed after click.
     */
    private final SettingsPopUpButton settingsPopupButton;

    /**
     * Initializes the navigation setting button with target GameState,
     * display text, and reference to the SettingsPopupButton.
     *
     * @param switchState target GameState to switch to
     * @param txt button label text
     * @param settingsPopupButton reference to the SettingsPopupButton
     */
    public NavSettingButton(GameState switchState , String txt, SettingsPopUpButton settingsPopupButton) {

        super(Asset.createImageView("SBtnBg",0,400),switchState);
        this.txt = txt;
        DropShadow shadow = setShadow();

        this.settingsPopupButton = settingsPopupButton;
        outlineTextImage = new OutlineTextImage(txt,'C',25);

        outlineTextImage.setDropShadow(shadow);
        outlineTextImage.setMaxWidth(1);
        StackPane buttonPane = new StackPane(Asset.createImageView("SBtnBg", 0, 400), outlineTextImage);

        StackPane.setAlignment(outlineTextImage,Pos.TOP_LEFT);
        setGraphic(buttonPane);

    }

    /**
     * Creates and configures a DropShadow effect for the text.
     *
     * @return configured DropShadow effect
     */
    private DropShadow setShadow(){
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(255,255,255,0.3));
        return shadow;
    }

    /**
     * Overrides NavigationButton handleClick(), performs state switch,
     * and closes the settings popup.
     */
    @Override
    public void handleClick() {

        super.handleClick();

        settingsPopupButton.setOpen(false);
    }

    /**
     * Sets margin for the outlined text inside the StackPane.
     *
     * @param i margin value applied to the text
     */
    public void setInset(Insets i){
        StackPane.setMargin(outlineTextImage,i);
    }

    /**
     * Returns the button label text.
     *
     * @return button text label
     */
    public String getTxt() {
        return txt;
    }
}