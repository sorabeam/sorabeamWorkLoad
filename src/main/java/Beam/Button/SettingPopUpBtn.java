package Beam.Button;

import Beam.Asset;
import Beam.Image.OutlineText;
import Beam.Media.JooxBox;
import Beam.Scene.InGameScene;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class SettingPopUpBtn extends BaseButton{
    private final Pane root;
    private StackPane overlay;

    public SettingPopUpBtn(ImageView img, Pane root) {
        super(img);
        this.root = root;
    }

    @Override
    public void handleClick() {
        super.handleClick();
        if(GameLogic.getGameState().equals(GameState.INGAME)){
            Node child = GameLogic.getGameroot().getChildren().getFirst();

            if (child instanceof InGameScene inGameScene) {
                inGameScene.stopGameByBool();
            }
        }
        showSetting();
    }

    private void showSetting() {

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(255,255,255,0.3));

        overlay = new StackPane();

        Slider MainvolumeSlider = new Slider(0, 100, GameLogic.getMusicVolume());

        MainvolumeSlider.setMinSize(500,30);
        MainvolumeSlider.setMaxSize(500,30);
        MainvolumeSlider.setPrefSize(500,30);

        MainvolumeSlider.setMajorTickUnit(0.25);

        Slider SFXvolumeSlider = new Slider(0, 100, GameLogic.getSFXVolume());

        SFXvolumeSlider.setMinSize(500,30);
        SFXvolumeSlider.setMaxSize(500,30);
        SFXvolumeSlider.setPrefSize(500,30);
        SFXvolumeSlider.setMajorTickUnit(0.25);


        MainvolumeSlider.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            if (newSkin != null) {
                Node track = MainvolumeSlider.lookup(".track");
                Node thumb = MainvolumeSlider.lookup(".thumb");
                if (track != null) {
                    track.setStyle(
                            "-fx-background-color: black, white;" +
                                    "-fx-background-radius: 5;" +
                                    "-fx-padding: 3px;"
                    );
                }
                thumb.setStyle(
                        "-fx-background-color: white, rgb(57, 44, 62);" +
                                "-fx-background-insets: 0, 4;" +
                                "-fx-background-radius: 50;" +
                                "-fx-pref-width: 30px;" +
                                "-fx-pref-height: 30px;"
                );
            }
        });

        SFXvolumeSlider.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            if (newSkin != null) {
                Node track = SFXvolumeSlider.lookup(".track");
                Node thumb = SFXvolumeSlider.lookup(".thumb");
                if (track != null) {
                    track.setStyle(
                            "-fx-background-color: black, white;" +
                                    "-fx-background-radius: 5;" +
                                    "-fx-padding: 3px;"
                    );
                }
                thumb.setStyle(
                        "-fx-background-color: white, rgb(57, 44, 62);" +
                                "-fx-background-insets: 0, 4;" +
                                "-fx-background-radius: 50;" +
                                "-fx-pref-width: 30px;" +
                                "-fx-pref-height: 30px;"
                );
            }
        });



        OutlineText setting = new OutlineText("setting",'C',30);
        setting.setDropShadow(shadow);
        VBox.setMargin(setting,new Insets(100,0,0,0));

        OutlineText mvolume = new OutlineText("Main Volume",'C',25);
        mvolume.setDropShadow(shadow);
        OutlineText sfxvolume = new OutlineText("SFX Volume",'C',25);
        sfxvolume.setDropShadow(shadow);

        OutlineText intmv = new OutlineText(GameLogic.getMusicVolume()+"",'M',25);
        intmv.setDropShadow(shadow);
        intmv.setPadding(new Insets(0,0,0,-35));
        OutlineText intsfxv = new OutlineText(GameLogic.getSFXVolume()+"",'M',25);
        intsfxv.setDropShadow(shadow);
        intsfxv.setPadding(new Insets(0,0,0,-60));

        HBox volumBGSetting = new HBox(mvolume,MainvolumeSlider,intmv);
        volumBGSetting.setMaxHeight(200);
        volumBGSetting.setSpacing(40);
        volumBGSetting.setPadding(new Insets(0,50,0,100));

        HBox volumSFXSetting = new HBox(sfxvolume,SFXvolumeSlider,intsfxv);
        volumSFXSetting.setMaxHeight(200);
        volumSFXSetting.setSpacing(55);
        volumSFXSetting.setPadding(new Insets(0,50,0,100));

        GridPane BtnPane = new GridPane();
        BtnPane.setAlignment(Pos.CENTER);

        BtnPane.setHgap(30);
        BtnPane.setVgap(30);

        MainvolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {

            GameLogic.setMusicVolume(newVal.intValue());
            intmv.setText(GameLogic.getMusicVolume() + "");
            JooxBox.getInstance().updateBGMVolume();
        });

        SFXvolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            GameLogic.setSFXVolume(newVal.intValue());
            intsfxv.setText(GameLogic.getSFXVolume() + "");
            JooxBox.getInstance().updateSFXVolume();
        });


        NavSettingBtn play = new NavSettingBtn(GameState.INGAME,"PLAY");
        play.setInset(new Insets(24,0,0,82));

        NavSettingBtn selectPets = new NavSettingBtn(GameState.SELECTPET,"Pets");
        selectPets.setInset(new Insets(24,0,0,86));

        NavSettingBtn selectChar = new NavSettingBtn(GameState.SELECTCHAR,"Cookies");
        selectChar.setInset(new Insets(24,0,0,62));

        NavSettingBtn menu = new NavSettingBtn(GameState.INTRO,"Menu");
        menu.setInset(new Insets(24,0,0,79));

        NavSettingBtn leave = new NavSettingBtn(null,"Leave");
        leave.setInset(new Insets(24,0,0,80));

        NavSettingBtn resumeBtn = new NavSettingBtn(null,"Resume");
        resumeBtn.setInset(new Insets(24,0,0,62));



        deleteThis(play);
        deleteThis(selectPets);
        deleteThis(selectChar);
        deleteThis(menu);
        deleteThis(leave);
        runItBack(resumeBtn);



        BtnPane.add(play, 0, 0);         BtnPane.add(menu, 1, 0);
        BtnPane.add(selectPets, 0, 1);   BtnPane.add(leave, 1, 1);
        BtnPane.add(selectChar, 0, 2);   BtnPane.add(resumeBtn, 1, 2);




        VBox popupBox = new VBox(setting,volumBGSetting,volumSFXSetting,BtnPane);
        popupBox.setSpacing(50);
        StackPane.setAlignment(popupBox, Pos.CENTER_LEFT);
        popupBox.setMaxWidth(960);

        ImageView bg = Asset.createImageView("SettingBG",1,0);
        bg.fitHeightProperty().bind(root.heightProperty());
        StackPane.setAlignment(bg,Pos.CENTER_LEFT);

        overlay.getChildren().addAll(bg,popupBox);
        root.getChildren().addAll(overlay);
    }

    private void deleteThis(BaseButton button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
            oldAction.handle(e);
            }

            if (((NavSettingBtn)button).getSwitchState() == null ){

                if (Objects.equals(((NavSettingBtn) button).getTxt(), "Leave")) {
                    System.exit(0);
                }

                root.getChildren().remove(overlay);
                return;
            }

            if(((NavSettingBtn) button).getSwitchState() == GameLogic.getGameState()) {
                root.getChildren().remove(overlay);
                return;
            }


            root.getChildren().remove(overlay);
        });
    }

    private void runItBack(BaseButton button){

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

        if(GameLogic.getGameState().equals(GameState.INGAME)){
            Node child = GameLogic.getGameroot().getChildren().getFirst();

            if (child instanceof InGameScene inGameScene) {
                inGameScene.resumeGameByBool();
            }
        }
            root.getChildren().remove(overlay);

        });
    }
}
