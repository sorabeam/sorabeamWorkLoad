package Beam.Scene;

import Beam.UI.MainUI.*;
import Beam.Media.JooxBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MainMenuScene extends BaseRoot {

    public MainMenuScene() {
        super();
        playMusic();

        StackPane profile = new MainMenuProfile("White Lilly", "102,455,353");
        HBox Setting = new SettingZone(root,spacer('H'));
        StackPane MainMenuButtons = new MainMenuButtons();
        StackPane GlassDecoration = new GlassDecoration();


        root.getChildren().addAll(
                new MainMenuBG(scene),
                profile,
                Setting,
                MainMenuButtons,
                GlassDecoration
        );

        StackPane.setAlignment(GlassDecoration,Pos.CENTER_RIGHT);
        StackPane.setAlignment(MainMenuButtons, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(Setting, Pos.TOP_RIGHT);
        StackPane.setMargin(Setting,new Insets(20,20,0,0));
        StackPane.setAlignment(profile, Pos.TOP_LEFT);
    }

    private void playMusic() {
        JooxBox sportify = new JooxBox();
        sportify.play("MainMenuMusic", true, 100);
    }
}