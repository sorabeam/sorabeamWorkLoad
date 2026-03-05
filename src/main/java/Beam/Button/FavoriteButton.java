package Beam.Button;

import javafx.scene.image.ImageView;
import Beam.Asset;

public class FavoriteButton extends BaseButton{

    private boolean isFav = true;

    public FavoriteButton() {
        super(Asset.createImageView("FavIco",30,0));
    }

    public void setHeight(double H){
        ImageView img = (ImageView) getGraphic();
        img.setFitHeight(H);
    }
}
