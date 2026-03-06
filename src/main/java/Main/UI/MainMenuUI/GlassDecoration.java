package Main.UI.MainMenuUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import Main.Asset;
import Main.Button.BaseButton;

/**
 * A decorative UI component used in the main menu to display
 * layered glass elements on the right side of the screen.
 *
 * <p>This class creates three decorative glass images using
 * {@link BaseButton} and positions them with different margins
 * to form a layered visual effect.</p>
 */
public class GlassDecoration extends StackPane {

    /**
     * Creates the glass decoration layout for the main menu.
     *
     * <p>This constructor initializes three glass decorative elements
     * using images from {@link Asset}. Each element is wrapped in a
     * {@link BaseButton} and positioned on the right side with different
     * margins to create a layered visual arrangement.</p>
     */
    public GlassDecoration() {

        BaseButton glass1 = new BaseButton(Asset.createImageView("Glass1", 0, 500));
        BaseButton glass2 = new BaseButton(Asset.createImageView("Glass2", 0, 500));
        BaseButton glass3 = new BaseButton(Asset.createImageView("Glass3", 0, 500));

        setAlignment(glass1, Pos.CENTER_RIGHT);
        setAlignment(glass2, Pos.CENTER_RIGHT);
        setAlignment(glass3, Pos.CENTER_RIGHT);

        setMargin(glass1, new Insets(0, 20, 300, 0));
        setMargin(glass2, new Insets(0, -50, 20, 20));
        setMargin(glass3, new Insets(390, 50, 0, 0));

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        getChildren().addAll(glass3, glass2, glass1);
    }
}
