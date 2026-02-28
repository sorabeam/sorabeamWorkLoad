package Beam.UI.InGameUI;

import Beam.Asset;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ExpBar extends StackPane {

    public ExpBar(Scene scene) {
        ImageView ExpBar = Asset.createImageView("EXPBar", 0, 10);
        ExpBar.fitWidthProperty().bind(scene.widthProperty());
        StackPane.setAlignment(ExpBar, Pos.BOTTOM_CENTER);

        getChildren().add(ExpBar);
    }
}
