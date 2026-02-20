package GameScenes;

import GameLogic.GameLogic;
import GameLogic.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class GameMenuRoot extends VBox {


    public GameMenuRoot() {
//        VBox root = new VBox();
        BackgroundSize size = new BackgroundSize(
                100, 100,
                true, true,
                false, true
        );
        Image backgroundImg = new Image(Objects.requireNonNull(getClass().getResource("/Maps/stage_background1.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
                );
        this.setBackground(new Background(backgroundImage));
        Button startBtn = new Button("Play");
        Button tutorialBtn = new Button("Tutorial");

        startBtn.setOnAction(e -> {
            GameLogic.setGameState(GameState.INGAME);
        });
        this.getChildren().addAll(startBtn, tutorialBtn);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(8);
    }
}
