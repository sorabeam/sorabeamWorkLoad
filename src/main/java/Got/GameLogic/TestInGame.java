//package Got.GameLogic;
//
//import CleanCode.Scene.CookieSelectionScene;
//import CleanCode.Scene.InGameScene;
//import CleanCode.Scene.PetsSelectionScene;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import supakorn.Scene.CookiesSelectionScene;
//import CleanCode.Scene.MainMenuScene;
//
//public class TestInGame extends Application {
//
//    private Scene gameMenuScene;
//    private Scene inGameScene;
//    private Scene selectCharScene;
//    private Scene selectPetScene;
//    private Scene gameOverScene;
//
//    private void initScenes() {
//        this.gameMenuScene = new MainMenuScene().getScene();
//        this.selectPetScene = new PetsSelectionScene().getScene();
//        this.selectCharScene = new CookieSelectionScene().getScene();
//        this.inGameScene = new InGameScene().getScene();
//    }
//
//    @Override
//    public void start(Stage stage) {
//
//
//        initScenes();
//        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
//            switch (newState) {
//                case INTRO -> {
//                    System.out.println(newState);
//                    stage.setScene(gameMenuScene);
//                }
//                case SELECTCHAR -> {
//                    System.out.println(newState);
//                    stage.setScene(selectCharScene);
//                }
//                case SELECTPET -> {
//                    System.out.println(newState);
//                    stage.setScene(selectPetScene);
//                }
//                case INGAME -> {
//                    System.out.println(newState);
//                    stage.setScene(inGameScene);
//                }
//                case GAMEOVER -> {
//                    System.out.println(newState);
//                    stage.setScene(gameOverScene);
//                }
//
//            }
//
//            stage.setFullScreen(true);
//        });
//        GameLogic.setGameState(GameState.INTRO);
//        stage.setScene(gameMenuScene);
//        stage.setFullScreen(true);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}