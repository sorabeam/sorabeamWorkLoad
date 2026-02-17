package GameScenes;

import components.ScoreBoard;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;

public class InGameScene {
    private Scene scene;

    public InGameScene() {
        AnchorPane root = new AnchorPane();
        ScoreBoard scoreBoard = new ScoreBoard();
        root.setPrefSize(800, 600);
        AnchorPane.setTopAnchor(scoreBoard, 10.0);
        AnchorPane.setRightAnchor(scoreBoard, 20.0);
        root.getChildren().add(scoreBoard);
        this.scene = new Scene(root);
        Image backgroundImg = new Image(Objects.requireNonNull(getClass().getResource("/Maps/stage_background2.png")).toExternalForm());
        BackgroundSize size = new BackgroundSize(
                100, 100,
                true, true,
                false, true
        );
        BackgroundImage backgroundImage = new BackgroundImage(
                backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
        );
        root.setBackground(new Background(backgroundImage));
    }

    public Scene getScene() {
        return scene;
    }
}
