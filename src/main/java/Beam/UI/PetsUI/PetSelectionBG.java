package Beam.UI.PetsUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Asset;
import Beam.Image.FloorFade;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class PetSelectionBG extends StackPane {

    public PetSelectionBG(Scene scene){

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        ImageView bg = Asset.createBackgroundView("RedStage",1000,1000);
        bg.setPreserveRatio(false);
        bg.fitWidthProperty().bind(scene.widthProperty());
        bg.fitHeightProperty().bind(scene.heightProperty());

        ImageView RoyalDesc = Asset.createImageView("Royaldec",0,1700);
        StackPane.setAlignment(RoyalDesc,BOTTOM_CENTER);
        StackPane.setMargin(RoyalDesc,new Insets(0,-60,-32,0));

        getChildren().addAll(bg,RoyalDesc,fade);
    }

}
