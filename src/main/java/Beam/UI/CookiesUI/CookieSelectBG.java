package Beam.UI.CookiesUI;

import Beam.Asset;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import Beam.Image.FloorFade;
import javafx.scene.paint.Color;

public class CookieSelectBG extends StackPane {

    public CookieSelectBG(){

        ImageView MBg = Asset.createBackgroundView("CBG",1,1);

        // bind กับตัวเอง
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,fade);

        setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
    }


}
