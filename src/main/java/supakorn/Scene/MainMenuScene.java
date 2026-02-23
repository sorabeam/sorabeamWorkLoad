package supakorn.Scene;

import Got.GameLogic.GameState;
import supakorn.Media.JooxBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import supakorn.Asset;
import supakorn.Button.BaseButton;
import supakorn.Button.FavBtn;
import supakorn.Button.NavBtn;
import supakorn.Button.SettingBtn;
import supakorn.Image.FloorFade;
import supakorn.Image.OutlineText;

import static javafx.geometry.Pos.CENTER_RIGHT;
import static javafx.geometry.Pos.TOP_RIGHT;

public class MainMenuScene extends BaseScene{
    private String stringScore = "102,455,353";

    public MainMenuScene(){

        SetUpScene();

        drawBackGround();
        drawFloorFade();
        writeName();

        drawSettingZone();

        //SettingZone มันน่าจะมี HBox ขนาดใหญ่จนมันบังตัวอิ่น ถ้าอยากให้ตัวไหนกดได้ให้้ วาด หลัง setting / แก้ setting

        drawProfile();
        drawGlass();
        drawButton();

    }

    public void drawBackGround(){

        ImageView MBg = Asset.createImageView("BgLobby",1,1);

        MBg.fitWidthProperty().bind(scene.widthProperty());
        MBg.fitHeightProperty().bind(scene.heightProperty());

        root.getChildren().add(MBg);

    }

    public void SetUpScene(){

        root = new StackPane();
        scene = new Scene(root);

        sportify = new JooxBox();
        sportify.play("MainMenuMusic",true,20);

    }

    public void drawFloorFade(){

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade,Pos.BOTTOM_CENTER);
        root.getChildren().add(fade);

    }

    public void drawProfile(){

        ImageView LillyBar = Asset.createImageView("LillyBar",0,400);
        ImageView LillyProfile = Asset.createImageView("LillyProfile",0,400);

        FavBtn CfavIco = new FavBtn();

        OutlineText CharactorName = new OutlineText("White Lilly",'C',30);
        StackPane.setAlignment(CharactorName,Pos.TOP_LEFT);
        CharactorName.setMaxHeight(1);
        CharactorName.setMaxWidth(1);

        OutlineText BestScore = new OutlineText("Best Score : " + stringScore ,'M',20);
        StackPane.setAlignment(BestScore,Pos.TOP_LEFT);
        BestScore.setMaxHeight(1);
        BestScore.setMaxWidth(1);

        StackPane.setAlignment(LillyBar,Pos.TOP_LEFT);
        StackPane.setAlignment(LillyProfile,Pos.TOP_LEFT);
        StackPane.setAlignment(CharactorName,Pos.TOP_LEFT);
        StackPane.setAlignment(CfavIco,Pos.TOP_LEFT);
        StackPane.setMargin(LillyBar,new Insets(20,0,0,20));
        StackPane.setMargin(LillyProfile,new Insets(20,0,0,20));
        StackPane.setMargin(CharactorName,new Insets(20 +  LillyProfile.getLayoutBounds().getHeight()/3.1,0,0,50));
        StackPane.setMargin(BestScore,new Insets(30 + LillyProfile.getLayoutBounds().getHeight(),0,0,30));
        StackPane.setMargin(CfavIco,new Insets(30 + LillyProfile.getLayoutBounds().getHeight(),0,0,20 + LillyBar.getLayoutBounds().getWidth() - 50));

        root.getChildren().addAll(LillyBar,LillyProfile,BestScore,CfavIco,CharactorName);
    }

    public void writeName(){

        OutlineText name = new OutlineText("sorabeam"  ,'M',18);
        StackPane.setAlignment(name,Pos.BOTTOM_LEFT);
        name.setMaxHeight(1);
        name.setMaxWidth(1);
        StackPane.setMargin(name,new Insets(0,0,20,40));

        root.getChildren().add(name);
    }

    public void drawButton(){

        ImageView MPlayBtn = Asset.createImageView("play",100,0);
        ImageView MCookieBtn = Asset.createImageView("MainCookie",80,0);
        ImageView MPetsBtn = Asset.createImageView("PetsBtn",80,0);

        NavBtn playBtn = new NavBtn(MPlayBtn, GameState.INGAME);
        NavBtn cookieBtn = new NavBtn(MCookieBtn, GameState.SELECTCHAR);
        NavBtn petsBtn = new NavBtn(MPetsBtn, GameState.SELECTPET );

        StackPane.setAlignment(playBtn,Pos.BOTTOM_CENTER);
        StackPane.setMargin(playBtn,new Insets(0,0,30,0));

        VBox SelectorVBox = new VBox(cookieBtn,petsBtn);
        SelectorVBox.setMaxHeight(100);
        SelectorVBox.setSpacing(12);

        StackPane.setAlignment(SelectorVBox,Pos.BOTTOM_LEFT);
        StackPane.setAlignment(petsBtn,Pos.BOTTOM_LEFT);
        StackPane.setMargin(SelectorVBox,new Insets(0,0,80,30));

        root.getChildren().addAll(playBtn,SelectorVBox);

    }

    public void drawSettingZone(){

        ImageView SettingImg = Asset.createImageView("SettingBtn",80,0);
        ImageView WifiImg = Asset.createImageView("WiFi",0,25);

        SettingBtn SettingBtn =new SettingBtn(SettingImg,this.root);

        HBox SettingHBox = new HBox(spacer('H'),WifiImg,SettingBtn);
        SettingHBox.setSpacing(10);
        HBox.setMargin(WifiImg,new Insets(10,0,0,0));

        StackPane.setAlignment(SettingHBox, TOP_RIGHT);
        StackPane.setMargin(SettingHBox,new Insets(20,20,0,0));

        root.getChildren().add(SettingHBox);
    }

    public void drawGlass(){

        ImageView Glass1img = Asset.createImageView("Glass1",0,500);
        ImageView Glass2img = Asset.createImageView("Glass2",0,500);
        ImageView Glass3img = Asset.createImageView("Glass3",0,500);

        BaseButton Glass1 = new BaseButton(Glass1img);
        BaseButton Glass2 = new BaseButton(Glass2img);
        BaseButton Glass3 = new BaseButton(Glass3img);

        StackPane.setAlignment(Glass1,CENTER_RIGHT);
        StackPane.setAlignment(Glass2,CENTER_RIGHT);
        StackPane.setAlignment(Glass3,CENTER_RIGHT);
        StackPane.setMargin(Glass1,new Insets(0,20,300,0));
        StackPane.setMargin(Glass2,new Insets(0,-50,20,20));
        StackPane.setMargin(Glass3,new Insets(390,50,0,0));

        root.getChildren().addAll(Glass3,Glass2,Glass1);
    }
}
