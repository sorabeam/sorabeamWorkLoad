package Beam.Button;

import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.scene.image.ImageView;

import static Got.GameLogic.GameLogic.getStage;

public class NavBtn extends BaseButton{


    private GameState switchState;

    public NavBtn(ImageView Img, GameState switchState) {
        super(Img);
        this.switchState = switchState;
    }

    @Override
    public void handleClick() {
        super.handleClick();

        getStage().setResizable(false);
        GameLogic.setGameState(switchState);
        System.out.println(switchState);
    }

    public GameState getSwitchState() {
        return switchState;
    }
}
