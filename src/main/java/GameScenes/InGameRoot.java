package GameScenes;

import Beam.Animation.Animate;
import Beam.Asset;
import components.ScoreBoard;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class InGameRoot extends StackPane {
    //physics setting
    private final double groundH = 80;
    private final double gravity = 0.4;
    private final double jumpSpeed = -15;
    private double velocity;
    private boolean onGround;

    public void handlePlayerJump() {
        if(!onGround) return;
        velocity = jumpSpeed;
        onGround = false;
    }

    public InGameRoot() {
//        StackPane root = new StackPane();
        Pane gameLayer = new Pane();
        AnchorPane uiLayer = new AnchorPane();
        ScoreBoard scoreBoard = new ScoreBoard();
        this.setPrefSize(800, 600);
        AnchorPane.setTopAnchor(scoreBoard, 10.0);
        AnchorPane.setRightAnchor(scoreBoard, 20.0);
        uiLayer.getChildren().add(scoreBoard);
        this.getChildren().addAll(gameLayer, uiLayer);
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
        this.setBackground(new Background(backgroundImage));

        //ground
        Rectangle ground = new Rectangle();
        ground.setHeight(groundH);
        ground.setLayoutX(0);
        ground.widthProperty().bind(gameLayer.widthProperty());
        ground.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH));
        ground.setFill(Color.LIGHTGRAY);
        gameLayer.getChildren().add(ground);

        Animate player = new Animate(new Image(Objects.requireNonNull(getClass().getResource("/Characters/char3.png")).toExternalForm()), 0,5,0,0,0,0,0,0,0,400,400);
//        player.setLayoutX(root.getWidth()/2-player.getBoundsInParent().getWidth()/2);
        player.setLayoutX(800/2 - 120/2); // make player at the center with fixed position, will fix later
        gameLayer.getChildren().add(player);

        AnimationTimer playerAnimations = new AnimationTimer() {
            long last = 0;
            @Override
            public void handle(long now) {
                if(last==0) {
                    last = now;
                    return;
                }
                double dt = (now-last)/1e9;
                last = now;
                velocity += gravity;
                player.setLayoutY(player.getLayoutY()+velocity);
                double groundY = getHeight()-groundH;
                double playerFeet = player.getLayoutY()+player.getBoundsInParent().getHeight();
                if(playerFeet>groundY) {
                    player.setLayoutY(groundY-player.getBoundsInParent().getHeight());
                    velocity = 0;
                    onGround = true;
                } else {
                    onGround = false;
                }
                player.update(dt);
            }
        };

        playerAnimations.start();

        setOnKeyPressed(e -> {
            if(e.getCode()==KeyCode.SPACE) {
                handlePlayerJump();
            }
        });

        setFocusTraversable(true);
        Platform.runLater(this::requestFocus);
    }
}
