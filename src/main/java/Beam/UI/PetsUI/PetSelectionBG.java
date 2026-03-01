package Beam.UI.PetsUI;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Asset;
import Beam.Image.FloorFade;

public class PetSelectionBG extends StackPane {

    public PetSelectionBG(){

        ImageView MBg = Asset.createBackgroundView("RedStage",1,1);

        MBg.setPreserveRatio(false);
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg, fade);
    }

}
