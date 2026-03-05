package Beam.Scene;

import Beam.Animation.Animate;
import Beam.CharactorData;
import Beam.UI.MainUI.*;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainMenuScene extends BaseScene {

    public MainMenuScene() {
        super();

        StackPane profile = new MainMenuProfile(CharactorData.getCurrent_Cookie().get_Name(), CharactorData.getCurrent_Cookie().get_Score() + "",root);
        HBox Setting = new SettingZone(root,spacer('H'));
        StackPane MainMenuButtons = new MainMenuButtons();
        StackPane GlassDecoration = new GlassDecoration();

        CharacterShow imgShow = new CharacterShow();
        Animate cookie = imgShow.getCookie();

        AnimationTimer timer = new AnimationTimer() {

            long last = 0;

            @Override
            public void handle(long now) {

                if (last == 0) {
                    last = now;
                    return;
                }

                double dt = (now - last) / 1e9;
                last = now;

                cookie.update(dt);
            }
        };

        timer.start();

        root.getChildren().addAll(
                new MainMenuBG(),
                profile,
                Setting,
                imgShow,
                MainMenuButtons,
                GlassDecoration
        );

        StackPane.setAlignment(GlassDecoration,Pos.CENTER_RIGHT);
        StackPane.setAlignment(MainMenuButtons, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(Setting, Pos.TOP_RIGHT);
        StackPane.setMargin(Setting,new Insets(20,20,0,0));
        StackPane.setAlignment(profile, Pos.TOP_LEFT);
    }
}