package Main.UI.PetsUI;

import Main.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import Main.Asset;
import Main.Button.NavigationButton;

/**
 * Navigation button layout for the pet selection screen.
 *
 * <p>This class arranges buttons that allow the user to navigate
 * between different scenes from the pet selection interface.</p>
 */
public class PetSelectionBtn extends HBox {

    /**
     * Constructs the navigation button section for the pet selection screen.
     *
     * <p>The layout includes a back button that returns to the intro screen
     * and a button that navigates to the character selection screen.
     * A spacer region is used to push the buttons to the desired alignment.</p>
     *
     * @param spacer a flexible region used to control spacing and alignment
     *               of the navigation buttons inside the layout
     */
    public PetSelectionBtn(Region spacer){

        NavigationButton backBtn = new NavigationButton(Asset.createImageView("BackBtn",100,0), GameState.INTRO);
        NavigationButton cookieBtn = new NavigationButton(Asset.createImageView("SEcookie",100,0),GameState.SELECT_CHAR);

        getChildren().addAll(spacer, cookieBtn, backBtn);
        setSpacing(20);
        setMaxHeight(1);
        setPadding(new Insets(0,20,10,0));

    }
}
