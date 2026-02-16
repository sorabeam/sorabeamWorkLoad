package GUI_beam;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import javax.print.attribute.standard.Media;

import Media.PlayList;
import Media.MediaController;

import java.util.HashSet;
import java.util.Set;

public class GameSceneExample extends Application {


    private String BackGround = "BGTest.png";
    private String PlayerStrI = "Player.png";
    private String light = "Blue_Light.png";
    private double playerSpeed = 3;
    private Set<String> keysPressed = new HashSet<>();

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        root.setPrefSize(800, 600);
        Scene scene = new Scene(root);

        PlayList p = new PlayList();
        MediaController m = new MediaController();

//        DropShadow dropShadow = new DropShadow();
//        dropShadow.setRadius(50.0);
//        dropShadow.setColor(Color.rgb(254, 244, 153, 0.41));

//        ImageView backGround = new ImageView(new Image(BackGround));
//
//        ImageView PlayerImage = new ImageView(new Image(PlayerStrI));
//        PlayerImage.setFitHeight(100);
//        PlayerImage.setFitWidth(100);
//        PlayerImage.setX(400);
//        PlayerImage.setY(300);
//        PlayerImage.setEffect(dropShadow);


//        root.getChildren().add(backGround);
//        root.getChildren().add(PlayerImage);
        Animate player = new Animate( new Image("imgSC.png"),
                0,5,120,139);
        root.getChildren().add(player);

//        backGround.fitWidthProperty().bind(root.widthProperty());
//        backGround.fitHeightProperty().bind(root.heightProperty());

        // press detection

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode().toString()));

        scene.setFill(Color.BLACK);

        // Game Loop Test Simutlator

        stage.setTitle("Simple JavaFX Game Scene");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}