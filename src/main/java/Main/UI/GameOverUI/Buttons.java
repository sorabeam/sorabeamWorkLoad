package Main.UI.GameOverUI;

import Main.Asset;
import Main.Button.BaseButton;
import Main.Button.NavigationButton;
import Main.GameLogic.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Represents the button container used in the Game Over screen.
 * <p>
 * This class arranges several action buttons horizontally, including
 * Play More, Replay, and Back. The Replay and Back buttons use
 * {@link NavigationButton} to change the game state.
 */
public class Buttons extends HBox {

    /**
     * Initializes the button container for the Game Over interface.
     * <p>
     * The constructor creates three buttons: a Play More button,
     * a Replay button that navigates back to the game state, and
     * a Back button that returns to the intro screen. All buttons
     * are aligned horizontally and positioned to the right side
     * of the layout.
     */
    public Buttons(){
        Button playMoreView = new BaseButton(Asset.createImageView("playMoreTest", 250,0));
        Button replayBtn = new NavigationButton(Asset.createImageView("ReplayBtn", 90, 0), GameState.IN_GAME);
        Button backBtn = new NavigationButton(Asset.createImageView("backBtn", 90, 0),GameState.INTRO);

        setSpacing(10);
        setMaxHeight(200);
        setAlignment(Pos.CENTER_RIGHT);

        getChildren().addAll(playMoreView,replayBtn,backBtn);
    }

}
