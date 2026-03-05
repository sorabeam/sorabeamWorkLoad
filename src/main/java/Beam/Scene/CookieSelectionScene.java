
package Beam.Scene;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.Button.BaseButton;
import Beam.UI.CookiesUI.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Beam.Asset;
import Beam.Button.CharacterButton;
import Beam.CharactorData;
import Beam.Image.OutlineTextImage;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class CookieSelectionScene extends BaseScene {

    CharacterButton block1 = Asset.createGridButton(CharactorData.BOBACOOKIE,220,0);
    CharacterButton block2 = Asset.createGridButton(CharactorData.TOMYUM_COOKIE,220,0);
    CharacterButton block3 = Asset.createGridButton(CharactorData.CROSSIANT_COOKIE,220,0);

    //ล็อคไม่ให้มันกดได้นะ ไม่งั้นมันบัค
    BaseButton block4 = new BaseButton(Asset.createImageView("B4",220,0));

    ImageView SkillVideo;
    OutlineTextImage name,Description,Record;
    Animate cookie;
    CharacterButton selectButton = block1;

    GridPane characterBoard = new GridDisplay(block1, block2, block3,block4);
    CharacterDisplay cd =  new CharacterDisplay(this);

    public CookieSelectionScene(){
        super();
        HBox Setting = new SettingZone(root,spacer('H'));
        String txt = CharactorData.getCurrent_Cookie().get_Desc();
        initCookieBtn();

        HBox MainHBox = new HBox(cd,new DataDisplay(txt,this),characterBoard);
        MainHBox.setPadding(new Insets(0,30,0,30));

        HBox.setMargin(cd,new Insets(0,0,-30,0));
        //MainHBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
        CharacterSelectButtons Btns = new CharacterSelectButtons();
        VBox MainLayer = new VBox(Setting,MainHBox,Btns);

        root.getChildren().addAll(
                new CookieSelectBG(),
                MainLayer
        );

        cookie.changeAnimationState(AnimationType.IDLE);
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

        VBox.setMargin(Btns,new Insets(0,0,30,30));
        MainHBox.setPrefHeight(540);
        MainHBox.setSpacing(20);
        MainHBox.setAlignment(Pos.CENTER);
        MainLayer.setSpacing(50);
        MainLayer.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(Setting,new Insets(20,20,0,0));

    }

    private void initCookieBtn() {
        enableShow(block1);
        enableShow(block2);
        enableShow(block3);
    }

    private void enableShow (CharacterButton button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            SkillVideo.setImage(button.getImg());
            name.setText(button.getN());
            Description.setText(button.getD());
            Record.setText(button.getR());
            selectButton = button;

            Animate newCookie = button.getCookie().createCookie();

            if (newCookie != null) {

                if (cookie != null) {
                    cd.getChildren().remove(cookie);
                }

                cookie = newCookie;
                cookie.changeAnimationState(AnimationType.IDLE);

                cd.getChildren().add(cookie);
                StackPane.setAlignment(cookie, BOTTOM_CENTER);

                CharactorData.setCurrent_Cookie(button.getCookie());
            }System.out.println("Current_Cookie change to " + CharactorData.getCurrent_Cookie().get_Name());
        });


    }

    public void setSkillVideo(ImageView skillVideo) { SkillVideo = skillVideo; }
    public void setName(OutlineTextImage name) { this.name = name; }
    public void setDescription(OutlineTextImage description) { Description = description; }
    public void setRecord(OutlineTextImage record) { Record = record; }
    public void setCookie(Animate cookie) { this.cookie = cookie; }

    public ImageView getSkillVideo() { return SkillVideo; }
    public OutlineTextImage getName() { return name; }
    public OutlineTextImage getDescription() { return Description; }
    public OutlineTextImage getRecord() { return Record; }
    public Animate getCookie() { return cookie; }
}
