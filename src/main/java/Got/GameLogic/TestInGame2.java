package Got.GameLogic;

import CleanCode.Scene.CookieSelectionScene;
import CleanCode.Scene.InGameScene;
import CleanCode.Scene.MainMenuScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestInGame2 extends Application {
    private Scene scene;
    @Override
    public void start(Stage stage) {
        System.out.println("Opened");
        scene = new Scene(new StackPane(), 800, 800);
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case INTRO -> {
                    System.out.println("Setted1");
                    scene.setRoot(new MainMenuScene());
                }
                case SELECTCHAR -> {
                    System.out.println("Setted1");
                    scene.setRoot(new CookieSelectionScene());
                }
                case INGAME -> {
                    System.out.println("Setted1");
                    scene.setRoot(new InGameScene());
                }
                case GAMEOVER -> {
                   scene.setRoot(new MainMenuScene());
                }
            }
        });

        GameLogic.setGameState(GameState.INTRO);
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

