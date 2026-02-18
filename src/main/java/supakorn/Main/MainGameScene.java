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

import java.util.Objects;

public class MainGameScene extends Application {

    private Scene scene;
    private Pane root;
    private JooxBox sportify;

    private void initializeScene(){

        root = new StackPane();
        root.setPrefSize(800, 600);
        scene = new Scene(root);

        // BG Setting
        Asset.BgTest.fitWidthProperty().bind(scene.widthProperty());
        Asset.BgTest.fitHeightProperty().bind(scene.heightProperty());
        Asset.BgTest.setPreserveRatio(false);

        sportify = new JooxBox();
        sportify.play("MainMenuMusic",true,100);
        //root.setStyle("-fx-background-color: #010101;");
    }

    private void drawUI(){
        StackPane scorePane = Asset.text("Record 123,589,678",27);
        StackPane CookieNamePane = Asset.text("White Lilly",27);

        ModifyImage Wifi = new ModifyImage(Asset.Wifi,25,0);
        ModifyImage CookieHpBar = new ModifyImage(Asset.CookieHpBar,30,0);
        ModifyImage CookiePropBar = new ModifyImage(Asset.CookiePropBar,30,0);
        ModifyImage PlayerBar = new ModifyImage(Asset.PlayerBar,130,0);

        Region floorFade = Asset.floorFade();
        floorFade.setMinHeight(0);
        floorFade.setMaxHeight(250);
        StackPane.setAlignment(floorFade,Pos.BOTTOM_CENTER);

        Button playBtn = new NavButton(Asset.PlayBtn,100,0);
        Button cookieBtn = new NavButton(Asset.CookieBtn,100,0);
        Button petsBtn = new NavButton(Asset.PetsBtn,100,0);
        Button settingBtn = new PopUpButton(Asset.SettingBtn,80,0);
        Button favico = new FavoriteButton(Asset.FavIcon,30,0);


        HBox hBox = new HBox(scorePane,spacer('H'),petsBtn,cookieBtn,playBtn);
        hBox.setSpacing(10);
        hBox.setMaxHeight(100);

        HBox SetHBox = new HBox(Wifi,settingBtn);
        SetHBox.setSpacing(10);
        HBox.setMargin(Wifi,new Insets(5,0,0,0));
        HBox.setMargin(settingBtn,new Insets(-5,0,0,0));
        SetHBox.setAlignment(Pos.TOP_RIGHT);

        HBox AginHbox = new HBox(spacer('H'),favico);

        VBox topVBox = new VBox(SetHBox,CookieNamePane,CookieHpBar,CookiePropBar,AginHbox);
        topVBox.setSpacing(10);
        topVBox.setMaxWidth(200);

        HBox topHBox = new HBox(PlayerBar,spacer('H'),topVBox);
        topHBox.setSpacing(10);
        topHBox.setMaxHeight(120);

        VBox baseVBox = new VBox(topHBox,spacer('V'),hBox);
        baseVBox.setPadding(new Insets(30,40,20,40));

        root.getChildren().addAll(Asset.BgTest,floorFade,baseVBox);
    }

    @Override
    public void start(Stage stage) {

        initializeScene();
        drawUI();

        stage.setTitle("CEDT Cookie Run -main");
        stage.setScene(scene);
        stage.show();

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