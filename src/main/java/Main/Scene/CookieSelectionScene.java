package Main.Scene;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.Button.BaseButton;
import Main.UI.CookiesUI.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Main.Asset;
import Main.Button.CharacterButton;
import Main.CharacterData;
import Main.Image.OutlineTextImage;

import static javafx.geometry.Pos.BOTTOM_CENTER;

/**
 * Scene responsible for allowing the player to select a playable cookie.
 *
 * This scene displays available characters, their information,
 * and a preview animation of the selected cookie.
 */
public class CookieSelectionScene extends BaseScene {

    CharacterButton block1 = Asset.createGridButton(CharacterData.BOBA_COOKIE,220,0);
    CharacterButton block2 = Asset.createGridButton(CharacterData.TOMYUM_COOKIE,220,0);
    CharacterButton block3 = Asset.createGridButton(CharacterData.CROSSIANT_COOKIE,220,0);

    BaseButton block4 = new BaseButton(Asset.createImageView("B4",220,0));

    ImageView skillVideo;
    OutlineTextImage name,description,record;
    Animate cookie;
    CharacterButton selectButton = block1;

    GridPane characterBoard = new GridDisplay(block1, block2, block3,block4);
    CharacterDisplay characterDisplay =  new CharacterDisplay(this);

    /**
     * Creates the cookie selection scene.
     *
     * This constructor initializes the layout, character buttons,
     * character preview display, and animation update loop.
     */
    public CookieSelectionScene(){
        super();

        HBox Setting = new SettingZone(root,spacer('H'));
        String txt = CharacterData.getCurrent_Cookie().get_Desc();

        initCookieBtn();

        HBox MainHBox = new HBox(characterDisplay,new DataDisplay(txt,this),characterBoard);
        MainHBox.setPadding(new Insets(0,30,0,30));

        HBox.setMargin(characterDisplay,new Insets(0,0,-30,0));

        CharacterSelectButtons Btns = new CharacterSelectButtons();
        VBox MainLayer = new VBox(Setting,MainHBox,Btns);

        root.getChildren().addAll(
                new CookieSelectBG(),
                MainLayer
        );

        cookie.changeAnimationState(AnimationType.IDLE);

        /**
         * Animation loop used to update the preview cookie animation.
         */
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

    /**
     * Initializes all cookie selection buttons.
     */
    private void initCookieBtn() {
        enableShow(block1);
        enableShow(block2);
        enableShow(block3);
    }

    /**
     * Attaches behavior to a character button so that selecting it
     * updates the displayed character preview and information.
     *
     * @param button the character button to enable
     */
    private void enableShow (CharacterButton button) {

        var oldAction = button.getOnAction();

        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            skillVideo.setImage(button.getImage());
            name.setText(button.getName());
            description.setText(button.getDescription());
            record.setText(button.getRecord());
            selectButton = button;

            Animate newCookie = button.getCookie().createCookie();

            if (newCookie != null) {

                if (cookie != null) {
                    characterDisplay.getChildren().remove(cookie);
                }

                cookie = newCookie;
                cookie.changeAnimationState(AnimationType.IDLE);

                characterDisplay.getChildren().add(cookie);
                StackPane.setAlignment(cookie, BOTTOM_CENTER);

                CharacterData.setCurrent_Cookie(button.getCookie());
            }

            System.out.println("Current_Cookie change to " + CharacterData.getCurrent_Cookie().get_Name());
        });
    }

    /**
     * Sets the skill preview image.
     */
    public void setSkillVideo(ImageView skillVideo) { this.skillVideo = skillVideo; }

    /**
     * Sets the name text element.
     */
    public void setName(OutlineTextImage name) { this.name = name; }

    /**
     * Sets the description text element.
     */
    public void setDescription(OutlineTextImage description) { this.description = description; }

    /**
     * Sets the record text element.
     */
    public void setRecord(OutlineTextImage record) { this.record = record; }

    /**
     * Sets the animated cookie preview.
     */
    public void setCookie(Animate cookie) { this.cookie = cookie; }

    /**
     * Returns the skill preview image.
     */
    public ImageView getSkillVideo() { return skillVideo; }

    /**
     * Returns the name text element.
     */
    public OutlineTextImage getName() { return name; }

    /**
     * Returns the description text element.
     */
    public OutlineTextImage getDescription() { return description; }

    /**
     * Returns the record text element.
     */
    public OutlineTextImage getRecord() { return record; }

    /**
     * Returns the currently displayed cookie animation.
     */
    public Animate getCookie() { return cookie; }
}