package supakorn.Main;

import Media.JooxBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import supakorn.Asset;
import supakorn.Button.FavoriteButton;
import supakorn.Button.NavButton;
import supakorn.Button.PopUpButton;
import supakorn.Image.ModifyImage;

import static javafx.geometry.Pos.CENTER_RIGHT;
import static javafx.geometry.Pos.TOP_RIGHT;

public class MainMenu2 extends Application{

    private Scene scene;
    private Pane root;
    private JooxBox sportify;
    private String stringScore = "102,455,353";

    private void initializeScene(){

        root = new StackPane();
        root.setPrefSize(800, 600);
        scene = new Scene(root);

        // BG Setting
        //Asset.BgTest.fitWidthProperty().bind(scene.widthProperty());
        //Asset.BgTest.fitHeightProperty().bind(scene.heightProperty());
        //Asset.BgTest.setPreserveRatio(false);

        sportify = new JooxBox();
        sportify.play("MainMenuMusic",true,20);
        //root.setStyle("-fx-background-color: #010101;");

    }

    @Override
    public void start(Stage stage) {

        initializeScene();
        drawUI();

        stage.setTitle("CEDT Cookie Run -main");
        stage.setScene(scene);
        stage.show();

    }

    private void drawUI(){

        Region floorFade = Asset.floorFade();
        floorFade.setMinHeight(0);
        floorFade.setMaxHeight(200);
        StackPane.setAlignment(floorFade,Pos.BOTTOM_CENTER);

        ModifyImage LillyBar = new ModifyImage(Asset.LillyBar,0,400);
        ModifyImage LillyProfile = new ModifyImage(Asset.LillyProfile,0,400);

        FavoriteButton CharactorFavoriteStatus = new FavoriteButton(Asset.FavIcon,30,0);

        StackPane CharactorName = Asset.text("White Lilly",20);
        StackPane.setAlignment(CharactorName,Pos.TOP_LEFT);
        CharactorName.setMaxHeight(1);
        CharactorName.setMaxWidth(1);

        StackPane BestScore = Asset.text("Best Score : " + stringScore ,14);
        StackPane.setAlignment(BestScore,Pos.TOP_LEFT);
        BestScore.setMaxHeight(1);
        BestScore.setMaxWidth(1);

        StackPane name = Asset.text("sorabeam",16);
        StackPane.setAlignment(name,Pos.BOTTOM_LEFT);
        name.setMaxHeight(1);
        name.setMaxWidth(1);
        StackPane.setMargin(name,new Insets(0,0,20,40));

        StackPane.setAlignment(LillyBar,Pos.TOP_LEFT);
        StackPane.setAlignment(LillyProfile,Pos.TOP_LEFT);
        StackPane.setAlignment(CharactorName,Pos.TOP_LEFT);
        StackPane.setAlignment(CharactorFavoriteStatus,Pos.TOP_LEFT);
        StackPane.setMargin(LillyBar,new Insets(20,0,0,20));
        StackPane.setMargin(LillyProfile,new Insets(20,0,0,20));
        StackPane.setMargin(CharactorName,new Insets(20 +  LillyProfile.getLayoutBounds().getHeight()/3.1,0,0,30));
        StackPane.setMargin(BestScore,new Insets(30 + LillyProfile.getLayoutBounds().getHeight(),0,0,30));
        StackPane.setMargin(CharactorFavoriteStatus,new Insets(18 + LillyProfile.getLayoutBounds().getHeight(),0,0,20 + LillyBar.getLayoutBounds().getWidth() - 50));

        NavButton playBtn = new NavButton(Asset.PlayBtn,90,0);
        NavButton cookieBtn = new NavButton(Asset.CookieBtn,80,0);
        NavButton petsBtn = new NavButton(Asset.PetsBtn,80,0);

        StackPane.setAlignment(playBtn,Pos.BOTTOM_CENTER);
        StackPane.setMargin(playBtn,new Insets(0,0,30,0));

        VBox SelectorVBox = new VBox(cookieBtn,petsBtn);
        SelectorVBox.setMaxHeight(100);
        SelectorVBox.setSpacing(12);

        StackPane.setAlignment(SelectorVBox,Pos.BOTTOM_LEFT);
        StackPane.setAlignment(petsBtn,Pos.BOTTOM_LEFT);
        StackPane.setMargin(SelectorVBox,new Insets(0,0,80,30));

        PopUpButton SettingBtn =new PopUpButton(Asset.SettingBtn,80,0);
        ModifyImage Wifi =new ModifyImage(Asset.Wifi,25,0);

        HBox SettingHBox = new HBox(spacer('H'),Wifi,SettingBtn);
        SettingHBox.setSpacing(10);
        HBox.setMargin(Wifi,new Insets(10,0,0,0));

        StackPane.setAlignment(SettingHBox, TOP_RIGHT);
        StackPane.setMargin(SettingHBox,new Insets(20,20,0,0));

        NavButton Glass1 = new NavButton(Asset.Glass1,0,500);
        NavButton Glass2 = new NavButton(Asset.Glass2,0,500);
        NavButton Glass3 = new NavButton(Asset.Glass3,0,500);

        StackPane.setAlignment(Glass1,CENTER_RIGHT);
        StackPane.setAlignment(Glass2,CENTER_RIGHT);
        StackPane.setAlignment(Glass3,CENTER_RIGHT);
        StackPane.setMargin(Glass1,new Insets(0,20,300,0));
        StackPane.setMargin(Glass2,new Insets(0,-50,20,20));
        StackPane.setMargin(Glass3,new Insets(390,50,0,0));

        StackPane BiggestPane = new StackPane(LillyBar,LillyProfile,BestScore,SettingHBox,CharactorFavoriteStatus,Glass3,Glass2,Glass1,CharactorName,name,playBtn,SelectorVBox);
        root.getChildren().addAll(floorFade,BiggestPane);
    }

    public Region spacer(char c){
        Region space = new Region();
        if(c == 'V'){
            VBox.setVgrow(space, Priority.ALWAYS);
        } else if (c == 'H') {
            HBox.setHgrow(space, Priority.ALWAYS);
        }
        return space;
    }

    public static void main(String[] args) {
        launch();
    }
}
