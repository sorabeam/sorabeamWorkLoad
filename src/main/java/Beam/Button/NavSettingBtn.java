package Beam.Button;

import GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import Beam.Asset;
import Beam.Image.OutlineTextImage;

public class NavSettingBtn extends NavigationButton {

    private StackPane Rimg;
    private DropShadow shadow;
    private OutlineTextImage outl;
    private String txt;
    private SettingsPopupButton settingsPopupButton;

    public NavSettingBtn(GameState switchState , String txt,SettingsPopupButton settingsPopupButton) {

        super(Asset.createImageView("SBtnBg",0,400),switchState);
        this.txt = txt;
        shadow = setShadow();

        this.settingsPopupButton = settingsPopupButton;
        outl = new OutlineTextImage(txt,'C',25);

        outl.setDropShadow(shadow);
        outl.setMaxWidth(1);
        Rimg = new StackPane(Asset.createImageView("SBtnBg",0,400),outl);

        StackPane.setAlignment(outl,Pos.TOP_LEFT);
        setGraphic(Rimg);

    }

    private DropShadow setShadow(){
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(255,255,255,0.3));
        return shadow;
    }

    @Override
    public void handleClick() {

        super.handleClick();

        settingsPopupButton.setOpen(false);
    }

    public void setInset(Insets i){
        StackPane.setMargin(outl,i);
    }

    public String getTxt() {
        return txt;
    }
}