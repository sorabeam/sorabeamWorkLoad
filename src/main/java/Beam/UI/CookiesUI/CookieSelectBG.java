package Beam.UI.CookiesUI;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import Beam.Image.FloorFade;
import javafx.scene.paint.Color;

public class CookieSelectBG extends StackPane {

    public CookieSelectBG(){

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(fade);

        setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
    }


}
