package supakorn;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Asset{

    public static final Font textFont =
            Font.loadFont(Asset.class.getResourceAsStream("/Fonts/Chango-Regular.ttf"), 30);

    public static final ImageView Wifi =
            new ImageView (new Image("/Image/WIFI.png"));

    public static final ImageView SettingBtn =
            new ImageView (new Image("/Image/SettingBtn.png"));

    public static final ImageView CookieHpBar =
            new ImageView (new Image("/Image/CookieHpBar.png"));

    public static final ImageView CookiePropBar =
            new ImageView (new Image("/Image/CookiePropBar.png"));

    public static final ImageView FavIcon =
            new ImageView (new Image("/Image/FavIco.png"));

    public static final ImageView PlayBtn =
            new ImageView (new Image("/Image/PlayBtn.png"));

    public static final ImageView CookieBtn =
            new ImageView (new Image("/Image/CookieBtn.png"));

    public static final ImageView PetsBtn =
            new ImageView (new Image("/Image/PetsBtn.png"));

    public static final ImageView PlayerBar =
            new ImageView (new Image("/Image/PlayerBar.png"));

    public static final ImageView BgTest =
            new ImageView (new Image("/Image/BgTest.jpg"));

    public static final ImageView BobaCookie =
            new ImageView( new Image("/Image/Boba_Milk_Tea_Cookie.png"));

    public static final ImageView LillyProfile =
            new ImageView( new Image("/Image/LillyProfile.png"));

    public static final ImageView LillyBar =
            new ImageView( new Image("/Image/LillyBar.png"));

    public static final ImageView Glass1 =
            new ImageView( new Image("/Image/Glass1.png"));

    public static final ImageView Glass2 =
            new ImageView( new Image("/Image/Glass2.png"));

    public static final ImageView Glass3 =
            new ImageView( new Image("/Image/Glass3.png"));

    public static StackPane text(String txt,int size){

        Text tS = new Text(txt);
        tS.setFont(Font.font(textFont.getFamily(), size));
        tS.setFill(Color.TRANSPARENT);
        tS.setStroke(Color.BLACK);
        tS.setStrokeWidth(6);

        Text tF = new Text(txt);
        tF.setFont(Font.font(textFont.getFamily(), size));
        tF.setFill(Color.WHITE);

        return new StackPane(tS, tF);
    }

    public static Region floorFade(){
        Region floorFadePng = new Region();
        floorFadePng.setPrefHeight(100);

        floorFadePng.setStyle("""
            -fx-background-color: linear-gradient(to top,
                rgba(2, 2, 2, 0.7),
                rgba(255, 255, 255,0.0)
            );
        """);

        return floorFadePng;
    }
    
}
