package CleanCode.Scene;

import CleanCode.UI.InGameUI.*;
import CleanCode.UI.MainUI.MainMenuBG;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class InGameScene extends BaseScene{
    SettingZone settingZone = new SettingZone(root,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();
    LastRecord lastRecord = new LastRecord();

    public InGameScene(){

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

    }


}
