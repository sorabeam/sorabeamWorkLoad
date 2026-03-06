package Main.UI.GameOverUI;

import Main.Asset;
import Main.Image.CellingFade;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * A background container for the Game Over screen.
 * <p>
 * This class creates the visual background layout including the stage
 * background image, a bottom black bar, and a fade effect at the top.
 * The background image automatically resizes to match the container size.
 */
public class GameOverBg extends StackPane{

    /**
     * Constructs the background layout for the Game Over screen.
     * <p>
     * The constructor loads the stage background image and a bottom bar
     * using {@code Asset}. The background image is bound to the container's
     * width and height so it scales with the window size. A top fade effect
     * is added to improve visual transition.
     */
    public GameOverBg(){

        ImageView MBg = Asset.createBackgroundView("RedStage",1,1);
        ImageView BB = Asset.createImageView("BlackBar",300,10);

        BB.fitWidthProperty().bind(widthProperty());

        MBg.setPreserveRatio(false);
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        CellingFade fade = new CellingFade(200);
        StackPane.setAlignment(fade, Pos.TOP_CENTER);
        StackPane.setAlignment(BB, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,BB,fade);
    }

}
