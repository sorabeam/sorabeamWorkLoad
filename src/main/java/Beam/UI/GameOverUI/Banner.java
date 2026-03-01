package Beam.UI.GameOverUI;

import Beam.Asset;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Banner extends StackPane{

    public Banner(){
        ImageView bannerView = Asset.createImageView("banner", 200, 230);
        StackPane.setAlignment(bannerView, Pos.TOP_RIGHT);
        StackPane.setMargin(bannerView, new Insets(-37, 40, 0, 0));

        setMouseTransparent(true);
        getChildren().addAll(bannerView);
    }
}
