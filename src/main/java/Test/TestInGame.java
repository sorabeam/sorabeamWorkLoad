package Test;

import GUI_beam.Animate;
import GameLogic.*;
import GameScenes.GameOverScene;
import GameScenes.InGameScene;
import GameScenes.IntroScene;
import GameScenes.SelectCharScene;
import Media.JooxBox;
import components.ScoreBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestInGame extends Application {
    private Scene introScene;
    private Scene inGameScene;
    private Scene selectCharScene;
    private Scene gameOverScene;
    private void initScenes() {
        this.introScene = new IntroScene().getScene();
        this.selectCharScene = new SelectCharScene().getScene();
        this.inGameScene = new InGameScene().getScene();
//        this.gameOverScene = new GameOverScene().getScene();
    }
    @Override
    public void start(Stage stage) {
        initScenes();
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case INTRO -> {
                    stage.setScene(introScene);
                }
                case SELECTCHAR -> {
                    stage.setScene(selectCharScene);
                }
                case INGAME -> {
                    stage.setScene(inGameScene);
                }
                case GAMEOVER -> {
                    stage.setScene(gameOverScene);
                }
            }
        });
        GameLogic.setGameState(GameState.INTRO);
        stage.setScene(introScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
