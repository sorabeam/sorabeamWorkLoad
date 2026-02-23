package CleanCode.UI.PetsUI;

import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import supakorn.Asset;
import supakorn.Button.NavBtn;

public class PetSelectionBtn extends HBox {

    public PetSelectionBtn(Region spacer){

        NavBtn backBtn = new NavBtn(Asset.createImageView("BackBtn",100,0), GameState.INTRO);
        NavBtn cookieBtn = new NavBtn(Asset.createImageView("SEcookie",100,0),GameState.SELECTCHAR);

        getChildren().addAll(spacer, cookieBtn, backBtn);
        setSpacing(20);
        setMaxHeight(1);
        setPadding(new Insets(0,20,10,0));

    }
}
