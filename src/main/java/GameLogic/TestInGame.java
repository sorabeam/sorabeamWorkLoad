package GameLogic;

import GameScenes.GameOverRoot;
import GameScenes.InGameRoot;
import GameScenes.GameMenuRoot;
import GameScenes.SelectCharRoot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestInGame extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new StackPane(), 800, 800);
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case INTRO -> {
                    scene.setRoot(new GameMenuRoot());
                }
                case SELECTCHAR -> {
                    scene.setRoot(new SelectCharRoot());
                }
                case INGAME -> {
                    scene.setRoot(new InGameRoot());
                }
                case GAMEOVER -> {
                    scene.setRoot(new GameOverRoot());
                }
            }
        });
        stage.setScene(scene);
        stage.show();
        GameLogic.setGameState(GameState.GAMEOVER);
    }

    public static void main(String[] args) {
        launch();
    }
}
