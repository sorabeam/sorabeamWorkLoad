package Main.UI.CookiesUI;

import Main.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import Main.Asset;
import Main.Button.NavigationButton;

/**
 * Displays navigation buttons used in the cookie selection scene.
 * <p>
 * This component creates two buttons that allow the player to navigate
 * to different game states. The buttons are arranged horizontally
 * inside an {@link HBox}.
 */
public class CharacterSelectButtons extends HBox {

    /**
     * Creates and arranges two navigation buttons (Back and Pets).
     * <p>
     * The constructor loads button images using {@link Asset}, assigns
     * target {@link GameState} values to each button, and adds them to
     * the layout. Spacing, alignment, and padding are also configured
     * to position the buttons properly in the UI.
     */
    public CharacterSelectButtons(){

        NavigationButton BackBtn =
                new NavigationButton(Asset.createImageView("BackBtn",0,200), GameState.INTRO);

        NavigationButton PetsBtn =
                new NavigationButton(Asset.createImageView("CSPetsBtn",0,200), GameState.SELECT_PET);

        getChildren().addAll(BackBtn, PetsBtn);
        setSpacing(20);
        setAlignment(Pos.BOTTOM_LEFT);

        setPadding(new Insets(10, 0, 0, 30));
    }
}
