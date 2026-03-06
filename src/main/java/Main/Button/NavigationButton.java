package Main.Button;

import Main.GameLogic.GameLogic;
import Main.GameLogic.GameState;
import javafx.scene.image.ImageView;

import static Main.GameLogic.GameLogic.getStage;

/**
 * Button used for navigating between different game states.
 *
 * This class extends BaseButton and changes the current
 * GameState when the button is clicked.
 */
public class NavigationButton extends BaseButton{

    /**
     * Stores the target GameState that this button will switch to when clicked.
     */
    private final GameState switchState;

    /**
     * Initializes the button with an image and the target GameState to switch to.
     *
     * @param Img image used as the button graphic
     * @param switchState target GameState that will be activated when clicked
     */
    public NavigationButton(ImageView Img, GameState switchState) {
        super(Img);
        this.switchState = switchState;
    }

    /**
     * Overrides BaseButton handleClick(), sets the Stage to resizable,
     * and changes the current GameState in GameLogic.
     */
    @Override
    public void handleClick() {
        super.handleClick();

        if(switchState==null) return;

        getStage().setResizable(true);
        GameLogic.setGameState(switchState);
        System.out.println(switchState);
    }

    /**
     * Returns the target GameState associated with this button.
     *
     * @return target GameState
     */
    public GameState getSwitchState() {
        return switchState;
    }


}
