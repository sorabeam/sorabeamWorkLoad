package Beam.Button;

import Beam.Asset;
import Beam.Image.OutlineText;
import Beam.Scene.InGameScene;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

        OutlineText setting = new OutlineText("setting",'C',30);
        setting.setDropShadow(shadow);
        VBox.setMargin(setting,new Insets(100,0,0,0));

        OutlineText mvolume = new OutlineText("Music Volume",'C',25);
        mvolume.setDropShadow(shadow);

        OutlineText thankyou = new OutlineText("Thank you for\nplaying",'C',25);
        thankyou.setDropShadow(shadow);
        thankyou.setTextAlignment(TextAlignment.CENTER);
        thankyou.setPadding(new Insets(20,0,20,0));

        HBox volumSetting = new HBox(mvolume);
        volumSetting.setMaxHeight(200);
        volumSetting.setPadding(new Insets(0,50,0,100));

        GridPane BtnPane = new GridPane();
        BtnPane.setAlignment(Pos.CENTER);

        BtnPane.setHgap(30);
        BtnPane.setVgap(30);




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




        VBox popupBox = new VBox(setting,volumSetting,thankyou,BtnPane);
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
