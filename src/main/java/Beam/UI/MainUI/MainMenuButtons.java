package Beam.UI.MainUI;

import GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import Beam.Asset;
import Beam.Button.NavigationButton;

public class MainMenuButtons extends StackPane {

    public MainMenuButtons() {

        NavigationButton playBtn = new NavigationButton(Asset.createImageView("play",100,0), GameState.INGAME);
        NavigationButton cookieBtn = new NavigationButton(Asset.createImageView("MainCookie",0,300), GameState.SELECTCHAR );
        NavigationButton petsBtn = new NavigationButton(Asset.createImageView("PetsBtn",0,300), GameState.SELECTPET );

        VBox ButtonBoy = new VBox(petsBtn,cookieBtn);

        ButtonBoy.setSpacing(15);
        ButtonBoy.setMaxHeight(cookieBtn.getHeight() + petsBtn.getHeight() + 15);
        ButtonBoy.setMaxWidth(200);

        getChildren().addAll(playBtn,ButtonBoy);

        StackPane.setAlignment(playBtn, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(ButtonBoy, Pos.BOTTOM_LEFT);
        StackPane.setMargin(ButtonBoy,new Insets(0,0,100,30));
        StackPane.setMargin(playBtn,new Insets(0,0,30,0));
        setMaxHeight(500);
    }
}