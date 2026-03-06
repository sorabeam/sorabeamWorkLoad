package Main.Button;

import javafx.scene.image.ImageView;
import Main.Asset;

/**
 * Button used to represent a favorite action in the user interface.
 *
 * This class extends BaseButton and displays a predefined
 * favorite icon image.
 */
public class FavoriteButton extends BaseButton{

    /**
     * Creates a favorite button with a predefined favorite icon image.
     */
    public FavoriteButton() {
        super(Asset.createImageView("FavIco",30,0));
    }

    /**
     * Adjusts the height of the button's image graphic.
     *
     * @param H the new height of the image graphic
     */
    public void setHeight(double H){
        ImageView img = (ImageView) getGraphic();
        img.setFitHeight(H);
    }
}
