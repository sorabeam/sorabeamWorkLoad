package Beam.UI.CookiesUI;

import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import Beam.Asset;
import Beam.Button.NavBtn;

public class CharacterSelectButtons extends HBox {
    public  CharacterSelectButtons(){
        NavBtn BackBtn = new NavBtn(Asset.createImageView("BackBtn",0,200), GameState.INTRO);
        NavBtn PetsBtn = new NavBtn(Asset.createImageView("CSPetsBtn",0,200),GameState.SELECTPET);

        getChildren().addAll(BackBtn, PetsBtn);
        setSpacing(20);
        setAlignment(Pos.BOTTOM_LEFT);

        setPadding(new Insets(10, 0, 0, 30));
    }
}
