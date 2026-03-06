package Main.UI.GameOverUI;

import Main.Asset;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Represents the banner component displayed in the Game Over interface.
 * <p>
 * This class shows a decorative banner image positioned in the
 * top-right corner of the layout. The banner is set to be moused
 * transparent so it does not block interaction with other UI elements.
 */
public class Banner extends StackPane{

    /**
     * Initializes the banner component for the Game Over interface.
     * <p>
     * The constructor loads the banner image using {@link Asset},
     * positions it at the top-right of the container, applies a margin
     * offset for visual placement, and ensures the banner does not
     * intercept mouse events.
     */
    public Banner(){
        ImageView bannerView = Asset.createImageView("banner", 200, 230);
        StackPane.setAlignment(bannerView, Pos.TOP_RIGHT);
        StackPane.setMargin(bannerView, new Insets(-37, 40, 0, 0));

        setMouseTransparent(true);
        getChildren().addAll(bannerView);
    }
}
