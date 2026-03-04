package Beam.Button;

import Beam.Media.JooxBox;
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

        if(switchState==null) return;

        if(switchState.equals(GameState.INGAME)){
        }
        getStage().setResizable(true);
        GameLogic.setGameState(switchState);
        System.out.println(switchState);
    }

    public GameState getSwitchState() {
        return switchState;
    }


}
