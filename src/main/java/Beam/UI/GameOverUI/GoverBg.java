package Beam.UI.GameOverUI;

import Beam.Asset;
import Beam.Image.FloorFade;
import Beam.Image.TopFade;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GoverBg extends StackPane{

    public GoverBg(){

        ImageView MBg = Asset.createBackgroundView("RedStage",1,1);
        ImageView BB = Asset.createImageView("BlackBar",300,10);

        BB.fitWidthProperty().bind(widthProperty());

        MBg.setPreserveRatio(false);
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        TopFade fade = new TopFade(200);
        StackPane.setAlignment(fade, Pos.TOP_CENTER);
        StackPane.setAlignment(BB, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,BB,fade);
    }

}
