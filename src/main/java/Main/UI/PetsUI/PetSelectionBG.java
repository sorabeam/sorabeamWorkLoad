package Main.UI.PetsUI;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Main.Asset;
import Main.Image.FloorFade;

/**
 * Background layout for the pet selection scene.
 *
 * <p>This class creates the visual background of the pet selection screen,
 * including the main stage background, a decorative bottom image,
 * and a floor fade effect.</p>
 */
public class PetSelectionBG extends StackPane {

    /**
     * Constructs the pet selection background.
     *
     * <p>The constructor loads the main background image and binds it
     * to the size of the pane so it always fills the screen. It also
     * adds a decorative bottom image and a floor fade effect aligned
     * at the bottom of the layout.</p>
     */
    public PetSelectionBG(){

        ImageView MBg = Asset.createBackgroundView("RedStage",1,1);

        MBg.setPreserveRatio(false);
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        ImageView ryd = Asset.createImageView("Royaldec",250 ,100);
        ryd.fitWidthProperty().bind(widthProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(ryd, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg , ryd , fade);
    }

}
