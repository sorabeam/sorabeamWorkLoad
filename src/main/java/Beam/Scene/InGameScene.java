package Beam.Scene;

import Beam.UI.InGameUI.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

import static Got.GameLogic.GameLogic.getStage;

public class InGameScene extends BaseRoot{
    SettingZone settingZone = new SettingZone(this,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();
    LastRecord lastRecord = new LastRecord();

    public InGameScene(){
        super();

        root.getChildren().addAll(
                new InGameBG(scene),
                settingZone,
                lastRecord,
                hpzone,
                sc
        );

        StackPane.setMargin(lastRecord,new Insets(120,60,0,0));
        StackPane.setAlignment(lastRecord,Pos.TOP_RIGHT);
        StackPane.setAlignment(sc,Pos.CENTER_RIGHT);
        StackPane.setMargin(sc,new Insets(200,0,0,0));
        StackPane.setAlignment(hpzone,Pos.TOP_CENTER);
        settingZone.setMaxWidth(50);
        StackPane.setAlignment(settingZone,Pos.TOP_RIGHT);
        StackPane.setMargin(settingZone,new Insets(20,20,0,0));

        getStage().setResizable(false);

    }


}
