package GameScenes;

import GameLogic.GameLogic;
import GameLogic.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class IntroScene {

    private Scene scene;

    public IntroScene() {
        VBox root = new VBox();
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
        root.setBackground(new Background(backgroundImage));
        Button startBtn = new Button("Play");
        Button tutorialBtn = new Button("Tutorial");

        startBtn.setOnAction(e -> {
            GameLogic.setGameState(GameState.INGAME);
        });
        root.getChildren().addAll(startBtn, tutorialBtn);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        this.scene = new Scene(root, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }
}
