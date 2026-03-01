package Beam.UI.GameOverUI;

import Beam.Asset;
import Beam.Button.BaseButton;
import Beam.Button.NavBtn;
import Got.GameLogic.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Buttons extends HBox {

    public Buttons(){
        Button playMoreView = new BaseButton(Asset.createImageView("playMoreTest", 250,0));
        Button replayBtn = new NavBtn(Asset.createImageView("ReplayBtn", 90, 0), GameState.INGAME);
        Button backBtn = new NavBtn(Asset.createImageView("backBtn", 90, 0),GameState.INTRO);

        setSpacing(10);
        setMaxHeight(200);
        setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(playMoreView,replayBtn,backBtn);
    }

}
