package GameScenes;

import GUI_beam.Animate;
import components.ScoreBoard;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
import java.util.Set;

public class InGameScene {
    private Scene scene;

    //physics setting
    private final double gravity = 10;
    private final double jumpSpeed = 30;

    private void handlePlayerJump() {

    }

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

        //ground
        Rectangle ground = new Rectangle();
        ground.setHeight(80);
        ground.setLayoutX(0);
        ground.widthProperty().bind(root.widthProperty());
        ground.layoutYProperty().bind(root.heightProperty().subtract(80));
        ground.setFill(Color.BLACK);
        root.getChildren().add(ground);

        Animate player = new Animate(new Image("imgSC.png"), 0,5,120,139);
        root.getChildren().add(player);

        scene.setOnKeyPressed(e -> {
            if(e.getCode()==KeyCode.SPACE) {
                handlePlayerJump();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
