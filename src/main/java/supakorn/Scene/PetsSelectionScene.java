package supakorn.Scene;

import Got.GameLogic.GameState;
import supakorn.Media.JooxBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import supakorn.Asset;
import supakorn.Button.*;
import supakorn.Image.FloorFade;
import supakorn.Image.OutlineText;

import static javafx.geometry.Pos.*;

public class PetsSelectionScene extends BaseScene {

    public PetsSelectionScene(){

        SetUpScene();
        drawBackGround();
        drawUI();

    }

    private void drawBackGround() {

        ImageView bg = Asset.createImageView("RedStage",1000,1000);
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(root.widthProperty());
        bg.fitHeightProperty().bind(root.heightProperty());

        root.getChildren().add(bg);
    }

    @Override
    public void SetUpScene() {
        root = new StackPane();
        scene = new Scene(root);

        sportify = new JooxBox();
    }

    private void drawUI(){

        // Button //------------------------------------------------------------------//

        SettingBtn settingBtn = new SettingBtn((Asset.createImageView("SettingBtn",80,0)),this.root);
        ImageView Wifi = Asset.createImageView("WiFi",25,0);
        OutlineText name =new OutlineText("sorabeam",'C',20);

        for (var auto : name.getChildren()){
            StackPane.setAlignment(auto,Pos.TOP_CENTER);
        }

        HBox SettingHBox = new HBox(spacer('H'),name,Wifi,settingBtn);
        SettingHBox.setMaxHeight(1);
        SettingHBox.setSpacing(10);
        HBox.setMargin(name,new Insets(10,0,0,0));

        HBox.setMargin(Wifi,new Insets(10,0,0,0));

        StackPane.setAlignment(SettingHBox, TOP_RIGHT);
        StackPane.setMargin(SettingHBox,new Insets(20,20,0,0));

        //----------------------------------------------------------------------------//

        NavBtn backBtn = new NavBtn(Asset.createImageView("BackBtn",100,0), GameState.INTRO);
        NavBtn cookieBtn = new NavBtn(Asset.createImageView("SEcookie",100,0),GameState.SELECTCHAR);

        HBox ButtonHBox = new HBox(spacer('H'),cookieBtn,backBtn);
        ButtonHBox.setSpacing(20);
        ButtonHBox.setMaxHeight(1);
        StackPane.setAlignment(ButtonHBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(ButtonHBox,new Insets(0,70,50,0));

        SelectPBtn ins1 =new SelectPBtn(Asset.createImageView("DrogonFruit",0,230) ,"DragonFruit",28,50);
        StackPane.setAlignment(ins1,Pos.TOP_CENTER);

        SelectPBtn ins2 =new SelectPBtn(Asset.createImageView("Pitachio",0,230),"Pitachio",28,50);
        StackPane.setAlignment(ins2,Pos.TOP_CENTER);

        SelectPBtn ins3 =new SelectPBtn(Asset.createImageView("Lemon",0,230),"Lemon",28,50);
        StackPane.setAlignment(ins3,Pos.TOP_CENTER);

        SelectPBtn ins4 =new SelectPBtn(Asset.createImageView("Pancake",0,230),"Pancake",28,50);
        StackPane.setAlignment(ins4,Pos.TOP_CENTER);

        HBox petHBox = new HBox(spacer('H'),ins1,ins2,ins3,ins4);
        petHBox.setSpacing(-15);
        petHBox.setMaxHeight(1);
        StackPane.setAlignment(petHBox,CENTER);
        StackPane.setMargin(petHBox,new Insets(0,60,0,0));

        // Music Disc //----------------------------------------------------------//

//        PopUpButton Disc = new PopUpButton(Asset.Disc,0,380);
//        StackPane.setAlignment(Disc,BOTTOM_CENTER);
//        StackPane.setMargin(Disc,new Insets(0,0,-220,0));
//
//        RotateTransition rotate = new RotateTransition(Duration.seconds(3), Disc);
//        rotate.setByAngle(360);      // หมุน 360 องศา
//        rotate.setCycleCount(RotateTransition.INDEFINITE); // หมุนตลอด
//        rotate.setAutoReverse(false);
//
//        rotate.play();

        // Selecting Zone //------------------------------------------------------//

        ImageView Light = Asset.createImageView("Light",0,1800);
        Light.setMouseTransparent(true);
        StackPane.setMargin(Light,new Insets(0,1200,0,0));

        ImageView Light2 = Asset.createImageView("Light",0,1800);
        Light2.setMouseTransparent(true);
        StackPane.setMargin(Light2,new Insets(0,700,0,0));

        ImageView Moji = Asset.createImageView("Moji",0,480);
        ImageView SelectingBg = Asset.createImageView("Selecting_Boba",0,450);
        BaseButton DeployBtn = new BaseButton( Asset.createImageView("DeplayBtn",0,330));

        FavBtn fav = new FavBtn();
        fav.setHeight(40);
        StackPane.setAlignment(fav,TOP_CENTER);
        StackPane.setMargin(fav, new Insets(200,230,0,0));

        OutlineText PName = new OutlineText("MojiNiga",'C',40);
        StackPane.setMargin(PName,new Insets(45,0,0,40));

        StackPane SelectingPane = new StackPane(SelectingBg,PName,Moji,fav);
        SelectingPane.setMaxWidth(1);
        SelectingPane.setMaxHeight(1);

        OutlineText description = new OutlineText(" is one of the five Ancient \n Heroes in Cookie Run: Kingdom. \n She was the final Ancient Cookie \n to become playable,  being \n released  in the Secrets of \n the Silver  Kingdom update",'M',20);

        description.setMaxHeight(1);
        description.setMaxWidth(1);
        for (var auto : description.getChildren()){
            StackPane.setAlignment(auto,Pos.TOP_CENTER);
        }

        StackPane.setMargin(SelectingBg,new Insets(100,0,0,100));
        StackPane.setMargin(DeployBtn,new Insets(220,0,0,140));
        StackPane.setAlignment(DeployBtn,CENTER_LEFT);
        StackPane.setMargin(Moji,new Insets(0,0,340,55));
        StackPane.setAlignment(SelectingPane,CENTER_LEFT);
        StackPane.setMargin(SelectingPane,new Insets(0,0,0,0));

        StackPane.setAlignment(description,BOTTOM_LEFT);
        StackPane.setMargin(description,new Insets(0,0,100,140));

        FloorFade fade = new FloorFade(400);
        StackPane.setAlignment(fade, BOTTOM_CENTER);

        ImageView RoyalDesc = Asset.createImageView("Royaldec",0,1700);
        StackPane.setAlignment(RoyalDesc,BOTTOM_CENTER);
        StackPane.setMargin(RoyalDesc,new Insets(0,-60,-32,0));

        // Add //------------------------------------------------------------------//

        root.getChildren().addAll(RoyalDesc,SelectingPane,fade,description,petHBox,DeployBtn,ButtonHBox,SettingHBox,Light,Light2);

        //-------------------------------------------------------------------------//

    }

}
