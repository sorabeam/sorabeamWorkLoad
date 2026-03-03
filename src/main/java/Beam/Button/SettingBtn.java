package Beam.Button;

import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import Beam.Asset;
import Beam.Image.OutlineText;

public class SettingBtn extends BaseButton {

    private final Pane root;
    private StackPane overlay;

    public SettingBtn(ImageView img, Pane root) {
        super(img);
        this.root = root;
    }

    @Override
    public void handleClick() {
        super.handleClick();
        showSetting();
    }

    private void showSetting() {

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);          // ความฟุ้ง
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(255,255,255,0.3));  // สีเงา

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

        BtnPane.setHgap(30); // ช่องห่างแนวนอน 20px
        BtnPane.setVgap(30); // ช่องห่างแนวตั้ง 15px
//      BtnPane.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));

        NavBtn play = new NavBtn(Asset.createImageView("SBtnBg",0,400), GameState.INGAME);
        StackPane stpp = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl = new OutlineText("Play",'C',25);
        outl.setDropShadow(shadow);
        outl.setMaxWidth(1);
        stpp.getChildren().add(outl);
        StackPane.setAlignment(outl,Pos.TOP_LEFT);
        StackPane.setMargin(outl,new Insets(24,0,0,86));
        play.setGraphic(stpp);
        deleteThis(play);

        NavBtn selectPets = new NavBtn(Asset.createImageView("SBtnBg",0,400), GameState.SELECTPET);
        deleteThis(selectPets);
        StackPane stpsp = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl2 = new OutlineText("Pets",'C',25);
        outl2.setDropShadow(shadow);
        outl2.setMaxWidth(1);
        stpsp.getChildren().add(outl2);
        StackPane.setAlignment(outl2,Pos.TOP_LEFT);
        StackPane.setMargin(outl2,new Insets(24,0,0,86));
        selectPets.setGraphic(stpsp);

        NavBtn selectChar = new NavBtn(Asset.createImageView("SBtnBg",0,400), GameState.SELECTCHAR);
        deleteThis(selectChar);
        StackPane sselectChar = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl3 = new OutlineText("Cookies",'C',25);
        outl3.setDropShadow(shadow);
        outl3.setMaxWidth(1);
        sselectChar.getChildren().add(outl3);
        StackPane.setAlignment(outl3,Pos.TOP_LEFT);
        StackPane.setMargin(outl3,new Insets(24,0,0,62));
        selectChar.setGraphic(sselectChar);

        NavBtn menu = new NavBtn(Asset.createImageView("SBtnBg",0,400), GameState.INTRO);
        deleteThis(menu);
        StackPane smenu = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl4 = new OutlineText("Menu",'C',25);
        outl4.setDropShadow(shadow);
        outl4.setMaxWidth(1);
        smenu.getChildren().add(outl4);
        StackPane.setAlignment(outl4,Pos.TOP_LEFT);
        StackPane.setMargin(outl4,new Insets(24,0,0,79));
        menu.setGraphic(smenu);

        BaseButton leave= new BaseButton(Asset.createImageView("SBtnBg",0,400));
        deleteThis(leave);
        StackPane sleave = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl5 = new OutlineText("Leave",'C',25);
        outl5.setDropShadow(shadow);
        outl5.setMaxWidth(1);
        sleave.getChildren().add(outl5);
        StackPane.setAlignment(outl5,Pos.TOP_LEFT);
        StackPane.setMargin(outl5,new Insets(24,0,0,80));
        leave.setGraphic(sleave);

        BaseButton resumeBtn = new BaseButton(Asset.createImageView("SBtnBg",0,400));
        deleteThis(resumeBtn);
        StackPane sresumeBtn = new StackPane(Asset.createImageView("SBtnBg",0,400));
        OutlineText outl6 = new OutlineText("Resume",'C',25);
        outl6.setDropShadow(shadow);
        outl6.setMaxWidth(1);
        sresumeBtn.getChildren().add(outl6);
        StackPane.setAlignment(outl6,Pos.TOP_LEFT);
        StackPane.setMargin(outl6,new Insets(24,0,0,62));
        resumeBtn.setGraphic(sresumeBtn);

        BtnPane.add(play, 0, 0);   BtnPane.add(menu, 1, 0);
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

            if(button instanceof NavBtn && ((NavBtn)button).getSwitchState() == GameLogic.getGameState() ) {
                root.getChildren().remove(overlay);
                return;
            }

            if (oldAction != null) {
                oldAction.handle(e);
            }
            root.getChildren().remove(overlay);
        });
    }
}