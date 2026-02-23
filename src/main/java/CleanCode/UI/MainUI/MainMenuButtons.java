package CleanCode.UI.MainUI;

import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import supakorn.Asset;
import supakorn.Button.NavBtn;

public class MainMenuButtons extends StackPane {

    public MainMenuButtons() {

        NavBtn playBtn = new NavBtn(Asset.createImageView("play",100,0), GameState.INGAME);
        NavBtn cookieBtn = new NavBtn(Asset.createImageView("MainCookie",0,270), GameState.SELECTCHAR );
        NavBtn petsBtn = new NavBtn(Asset.createImageView("PetsBtn",0,270), GameState.SELECTPET );

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