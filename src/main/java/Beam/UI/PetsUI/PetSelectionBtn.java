package Beam.UI.PetsUI;

import GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import Beam.Asset;
import Beam.Button.NavigationButton;

public class PetSelectionBtn extends HBox {

    public PetSelectionBtn(Region spacer){

        NavigationButton backBtn = new NavigationButton(Asset.createImageView("BackBtn",100,0), GameState.INTRO);
        NavigationButton cookieBtn = new NavigationButton(Asset.createImageView("SEcookie",100,0),GameState.SELECTCHAR);

        getChildren().addAll(spacer, cookieBtn, backBtn);
        setSpacing(20);
        setMaxHeight(1);
        setPadding(new Insets(0,20,10,0));

    }
}
