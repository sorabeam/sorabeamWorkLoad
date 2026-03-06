package Main;

import Main.GameLogic.GameLogic;
import Main.GameLogic.GameState;
import Main.Scene.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main entry point of the JavaFX game application.
 * <p>
 * This class initializes the main window, prepares the root containers,
 * manages scene scaling to keep the aspect ratio, and switches game
 * scenes based on the current {@link GameState}.
 */
public class MainGameScene extends Application {

    /**
     * Stores the main root container that holds and switches
     * the current game scene content.
     */
    private StackPane gameRoot;

    /**
     * Stores the main JavaFX scene used to display the game window.
     */
    private Scene scene;

    /**
     * Stores the layer that is scaled to keep the game display
     * proportional when the window size changes.
     */
    private StackPane scalableLayer;

    /**
     * Defines the base width of the game window used
     * as the reference size for scaling.
     */
    private static final double BASE_WIDTH = 1600;

    /**
     * Defines the base height of the game window used
     * as the reference size for scaling.
     */
    private static final double BASE_HEIGHT = 900;

    /**
     * Initializes the main game window and prepares the application layout.
     * <p>
     * This method sets up the root containers, configures automatic scaling
     * when the window size changes, listens for game state updates, and
     * switches scenes based on the current {@link GameState}.
     *
     * @param stage the primary stage provided by the JavaFX application
     */
    @Override
    public void start(Stage stage) {

        GameLogic.setStage(stage);

        gameRoot = new StackPane();
        gameRoot.setPrefSize(BASE_WIDTH, BASE_HEIGHT);

        scalableLayer = new StackPane(gameRoot);

        StackPane root = new StackPane(scalableLayer);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);
        stage.setScene(scene);

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        GameLogic.setGameRoot(gameRoot);

        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {

            switch (newState) {
                case INTRO -> {
                    gameRoot.getChildren().setAll(new MainMenuScene());
                    playMusic("Lobby",50);
                }

                case SELECT_CHAR -> {
                    gameRoot.getChildren().setAll(new CookieSelectionScene());
                    playMusic("Main/Cookies",50);
                }

                case IN_GAME -> {

                    CharacterData.getCurrent_Cookie().setHp(CharacterData.getCurrent_Cookie().getMaxHp());
                    CharacterData.getCurrent_Cookie().setCooldownTimer(0);
                    CharacterData.getCurrent_Cookie().setSkillCounter(0);
                    CharacterData.getCurrent_Cookie().setDead(false);
                    CharacterData.getCurrent_Cookie().reset();
                    GameLogic.setScore(0);
                    playMusic("SoundMAP" + GameLogic.getMap(),50);

                    GameplayScene inGameScene = new GameplayScene();
                    GameLogic.setCurrentGameScene(inGameScene);
                    gameRoot.getChildren().setAll(inGameScene);
                }
                case SELECT_PET -> {
                    gameRoot.getChildren().setAll(new PetsSelectionScene());
                    playMusic("Main/Pets",50);
                }

                case GAME_OVER -> {
                    gameRoot.getChildren().setAll(new GameOverScene());
                    playMusic("GameOver",50);
                }
            }
        });

        GameLogic.setCurScene(scene);
        GameLogic.setGameState(GameState.INTRO);
        stage.show();

        updateScale(scalableLayer);
    }

    /**
     * Updates the scale of the game display so that the content
     * maintains its aspect ratio when the window size changes.
     *
     * @param scalableLayer the layer that will be scaled based on the window size
     */
    private void updateScale(StackPane scalableLayer) {

        double scaleX = scene.getWidth() / BASE_WIDTH;
        double scaleY = scene.getHeight() / BASE_HEIGHT;

        double scale = Math.min(scaleX, scaleY);

        scalableLayer.setScaleX(scale);
        scalableLayer.setScaleY(scale);
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Plays the background music associated with the given key.
     * The music is played using the global MediaPlayer instance.
     *
     * @param key the key used to find the music file in the playlist
     * @param v unused parameter representing volume level
     */
    private void playMusic(String key,int v) {
        MediaPlayer.getInstance().playBGM(key, true);
    }
}
