package Got.GameLogic;

import Beam.Scene.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestInGame2 extends Application {
    public Scene scene;
    @Override
    public void start(Stage stage) {
        GameLogic.setStage(stage);
        scene = new Scene(new StackPane(), 800, 800);
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case INTRO -> {
                    scene.setRoot(new MainMenuScene());
                }
                case SELECTCHAR -> {
                    scene.setRoot(new CookieSelectionScene());
                }
                case INGAME -> {
                    scene.setRoot(new InGameScene());
                }
                case SELECTPET -> {
                    scene.setRoot(new PetsSelectionScene());
                }
                case GAMEOVER -> {
                   scene.setRoot(new GameOverRoot());
                }
            }
        });

//        GameLogic.setGameState(GameState.INTRO);
        GameLogic.setGameState(GameState.GAMEOVER);
        GameLogic.setCurScene(scene);
        stage.setScene(scene);
        stage.show();

        System.out.println(GameLogic.getGameState());
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene getScene() {
        return scene;
    }
}

