package supakorn.Scene;

import Got.GameLogic.GameState;
import supakorn.Media.JooxBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import supakorn.Asset;
import supakorn.Button.*;
import supakorn.CookieData;
import supakorn.Image.FloorFade;
import supakorn.Image.OutlineText;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class CookiesSelectionScene extends BaseScene{

    private String txt = "Write Millennial Tree Cookie, Glinda \n Cookie, Elphaba Cookie, Chess Choco \n Cookie, Salt Cellar Cookie, Charcoal \n Cookie, Menthol Cookie, Seltzer \n Cookie, Grapefruit Cookie, Lime \n Cookie, Orange Cookie, Lemon \n Cookie, Cream Soda Cookie, Sugarfly \nuuuy Cookie, Pavlova Cookie, and";

    CDBtn B1 = Asset.createGridButton(CookieData.SILENCE_SALT,220,0);
    CDBtn B2 = Asset.createGridButton(CookieData.SHADOW_MILK,220,0);
    CDBtn B3 = Asset.createGridButton(CookieData.PURE_VANILLA,220,0);
    CDBtn B4 = Asset.createGridButton(CookieData.HOLLY_BERRY,220,0);
    CDBtn B5 = Asset.createGridButton(CookieData.WHITE_LILY,220,0);

    ImageView SkillVideo = Asset.createImageView("S5",0,440);

    private BaseButton selectButton;   // ปุ่มที่อยู่นอก grid (ตอนนี้คือ B5)
    private GridPane characterBoard;

    OutlineText Description;
    OutlineText Record;
    OutlineText name;

    public CookiesSelectionScene() {

        SetUpScene();
        drawGridBoard();
    }

    public void drawGridBoard(){

        // floorFade //--------------------------------------------------------------------------//

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, BOTTOM_CENTER);

        // Setting //----------------------------------------------------------------------------//

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);

        SettingBtn settingBtn = new SettingBtn(SettingImg,this.root);
        ImageView Wifi = Asset.createImageView("WiFi",0,25);
        OutlineText Pname = new OutlineText("sorabeam",'C',18);

        // Setting Box //------------------------------------------------------------------------//

        HBox TopHBox = new HBox(spacer('H'),Pname,Wifi,settingBtn);
        TopHBox.setMaxHeight(1);
        TopHBox.setSpacing(10);
        VBox.setMargin(TopHBox, new Insets(30,30,0,0));
        HBox.setMargin(Pname,new Insets(10,0,0,0));
        HBox.setMargin(Wifi,new Insets(10,0,0,0));

        // drawGridBoard //----------------------------------------------------------------------//

        characterBoard = new GridPane();

        characterBoard.setMaxHeight(455);
        characterBoard.setMaxWidth(455);

        characterBoard.setHgap(15); // ระยะห่างแนวนอน
        characterBoard.setVgap(15); // ระยะห่างแนวตั้ง

        selectButton = B5;

        enableSwap(B1);
        enableSwap(B2);
        enableSwap(B3);
        enableSwap(B4);
        enableSwap(B5);

        characterBoard.add(B1, 0, 0);    characterBoard.add(B2, 1, 0);
        characterBoard.add(B3, 0, 1);    characterBoard.add(B4, 1, 1);

        // StackPane //---------------------------------------------------------------------------//

        // Record //

        Record = new OutlineText("Best Record : 123,569,574",'C',20);
        for(var auto : Record.getChildren()){
            StackPane.setAlignment(auto, Pos.TOP_CENTER);}
        Record.setPadding(new Insets(0,0,20,0));

        // Hp &  FavIco //

        ImageView HpBar = Asset.createImageView("CookieHpBar",0,300);
        FavBtn Fav = new FavBtn();
        Fav.setHeight(40);

        HBox SubHBox = new HBox(HpBar,Fav);
        SubHBox.setSpacing(10);
        SubHBox.setMaxHeight(100);
        SubHBox.setAlignment(Pos.CENTER);

        // Description //

        Description = new OutlineText(txt,'M',18);

        // sum //

        VBox InfoBox = new VBox(Record,SubHBox,SkillVideo,Description);
        InfoBox.setSpacing(0);

        // Nav Button //-------------------------------------------------------------------------//

        NavBtn BackBtn = new NavBtn(Asset.createImageView("BackBtn",0,200),GameState.INTRO);
        NavBtn PetsBtn = new NavBtn(Asset.createImageView("CSPetsBtn",0,200),GameState.SELECTPET);

        HBox DownHBox = new HBox(BackBtn,PetsBtn);
        DownHBox.setSpacing(20);
        DownHBox.setAlignment(Pos.BOTTOM_LEFT);

        VBox.setMargin(DownHBox, new Insets(0,0,30,30));

        // drawVBox //--------------------------------------------------------------------------//

        StackPane prefab = new StackPane();

        name = new OutlineText("White Lilly",'C',30);
        name.setPadding(new Insets(0,0,40,0));
        StackPane.setAlignment(name, BOTTOM_CENTER);

        prefab.getChildren().add(name);
        prefab.setPrefWidth(400);
        prefab.setPrefHeight(500);
        prefab.setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0),new Insets(0,0,0,0))));

        HBox MainHBox = new HBox(prefab,InfoBox,characterBoard);
        MainHBox.setPrefHeight(540);
        MainHBox.setSpacing(40);
        MainHBox.setAlignment(Pos.CENTER);

        // Outter Box //------------------------------------------------------------------------//

        VBox MainVBox = new VBox(TopHBox,spacer('V'),MainHBox,spacer('V'),DownHBox);
        root.getChildren().addAll(fade,MainVBox);

    }

    @Override
    public void SetUpScene() {

        root = new StackPane();
        scene = new Scene(root);
        sportify = new JooxBox();

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
}
