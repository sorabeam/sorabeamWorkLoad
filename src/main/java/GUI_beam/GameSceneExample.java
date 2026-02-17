package GUI_beam;

import Media.JooxBox;
import components.ScoreBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;

public class GameSceneExample extends Application {


    private String BackGround = "BGTest.png";
    private String PlayerStrI = "Characters/Player.png";
    private String light = "Blue_Light.png";
    private double playerSpeed = 3;
    private Set<String> keysPressed = new HashSet<>();

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        root.setPrefSize(800, 600);
        Scene scene = new Scene(root);

        JooxBox sportify = new JooxBox();
        sportify.play("MainMenuMusic",true,100);


        Animate player = new Animate( new Image("imgSC.png"),
                0,5,120,139);
        root.getChildren().add(player);

        // press detection

        scene.setOnKeyPressed(e -> keysPressed.add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> keysPressed.remove(e.getCode().toString()));

        scene.setFill(Color.BLACK);

        // Game Loop Test Simutlator

//        stage.setTitle("Simple JavaFX Game Scene");
//        stage.setScene(scene);
//        stage.show();

        //

        ScoreBoard scoreBoard = new ScoreBoard();
        root.getChildren().add(scoreBoard);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}