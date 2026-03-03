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

        if(switchState.equals(GameState.INGAME)){

            //test
            GameLogic.setScore(0);
            JooxBox.getInstance().ChangeMusic("MS1",true,100);
        }
        getStage().setResizable(true);
        GameLogic.setGameState(switchState);
        System.out.println(switchState);
    }

    public GameState getSwitchState() {
        return switchState;
    }
}
