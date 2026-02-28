
package Beam.Scene;

import Beam.UI.CookiesUI.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import Beam.Asset;
import Beam.Button.CDBtn;
import Beam.CharactorData;
import Beam.Image.OutlineText;

public class CookieSelectionScene extends BaseRoot{

    ImageView SkillVideo;
    OutlineText name,Description,Record;
    private String txt = CharactorData.getCurrent_Cookie().get_Desc();

    CDBtn B1 = Asset.createGridButton(CharactorData.BOBACOOKIE,220,0);
    CDBtn B2 = Asset.createGridButton(CharactorData.SHADOW_MILK,220,0);
    CDBtn B3 = Asset.createGridButton(CharactorData.PURE_VANILLA,220,0);
    CDBtn B4 = Asset.createGridButton(CharactorData.HOLLY_BERRY,220,0);
    CDBtn B5 = Asset.createGridButton(CharactorData.WHITE_LILY,220,0);

    CDBtn selectButton = B5;
    GridPane characterBoard = new GridDisplay(B1,B2,B3,B4,B5);

    public  CookieSelectionScene(){
        super();

        HBox Setting = new SettingZone(root,spacer('H'));

        initCDBtn();

        HBox MainHBox = new HBox(new CharacterDisplay(this),new DataDisplay(txt,this),characterBoard);
        CharacterSelectButtons Btns = new CharacterSelectButtons();
        VBox MainLayer = new VBox(Setting,MainHBox,Btns);

        root.getChildren().addAll(
                new CookieSelectBG(),
                MainLayer
        );

        VBox.setMargin(Btns,new Insets(0,0,30,30));
        MainHBox.setPrefHeight(540);
        MainHBox.setSpacing(40);
        MainHBox.setAlignment(Pos.CENTER);
        MainLayer.setSpacing(60);
        MainLayer.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(Setting,new Insets(20,20,0,0));

    }

    private void initCDBtn() {
        enableSwap(B1);
        enableSwap(B2);
        enableSwap(B3);
        enableSwap(B4);
        enableSwap(B5);
    }

    private void enableSwap(CDBtn button) {

        button.setOnAction(e -> {

            Integer col = GridPane.getColumnIndex(button);
            Integer row = GridPane.getRowIndex(button);

            SkillVideo.setImage(button.getImg());
            name.setText(button.getN());
            Description.setText(button.getD());
            Record.setText(button.getR());

            characterBoard.getChildren().remove(button);
            characterBoard.add(selectButton, col, row);
            selectButton = button;

        });
    }

    public void setSkillVideo(ImageView skillVideo) {
        SkillVideo = skillVideo;
    }

    public void setName(OutlineText name) {
        this.name = name;
    }

    public void setDescription(OutlineText description) {
        Description = description;
    }

    public void setRecord(OutlineText record) {
        Record = record;
    }

    public ImageView getSkillVideo() {
        return SkillVideo;
    }

    public OutlineText getName() {
        return name;
    }

    public OutlineText getDescription() {
        return Description;
    }

    public OutlineText getRecord() {
        return Record;
    }
}
