package GuiAndScene.Scene;

import GuiAndScene.UI.InGameUI.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import supakorn.ObsticleAndItem.Spawner;

public class InGameScene extends BaseRoot{
    SettingZone settingZone = new SettingZone(this,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();
    LastRecord lastRecord = new LastRecord();
    private Pane gameLayer = new Pane();

    public InGameScene(){
        super();

        root.getChildren().addAll(
                new InGameBG(scene),
                gameLayer,
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

        Spawner spawner =
                new Spawner(
                        gameLayer,
                        scene.getWidth(),
                        scene.getHeight()
                );

        spawner.start();
    }


}
