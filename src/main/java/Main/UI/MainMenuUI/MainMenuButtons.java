package Main.UI.MainMenuUI;

import Main.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import Main.Asset;
import Main.Button.NavigationButton;

/**
 * UI container that holds the main navigation buttons for the Main Menu.
 *
 * <p>This class creates the Play button and a vertical group of buttons
 * for navigating to the character selection and pet selection screens.
 * The buttons are arranged and positioned within the layout using
 * {@link StackPane} and {@link VBox}.</p>
 */
public class MainMenuButtons extends StackPane {

    /**
     * Creates and initializes the main menu button layout.
     *
     * <p>The constructor generates three navigation buttons:
     * Play, Cookie (character selection), and Pets (pet selection).
     * The Play button is placed at the bottom center of the screen,
     * while the Cookie and Pets buttons are grouped vertically on
     * the bottom-left side.</p>
     */
    public MainMenuButtons() {

        NavigationButton playBtn = new NavigationButton(
                Asset.createImageView("play",100,0),
                GameState.IN_GAME
        );

        NavigationButton cookieBtn = new NavigationButton(
                Asset.createImageView("MainCookie",0,300),
                GameState.SELECT_CHAR
        );

        NavigationButton petsBtn = new NavigationButton(
                Asset.createImageView("PetsBtn",0,300),
                GameState.SELECT_PET
        );

        VBox ButtonBoy = new VBox(petsBtn,cookieBtn);

        ButtonBoy.setSpacing(15);
        ButtonBoy.setMaxHeight(cookieBtn.getHeight() + petsBtn.getHeight() + 15);
        ButtonBoy.setMaxWidth(200);

        getChildren().addAll(playBtn,ButtonBoy);

        StackPane.setAlignment(playBtn, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(ButtonBoy, Pos.BOTTOM_LEFT);
        StackPane.setMargin(ButtonBoy,new Insets(0,0,100,30));
        StackPane.setMargin(playBtn,new Insets(0,0,30,0));
        setMaxHeight(500);
    }
}
