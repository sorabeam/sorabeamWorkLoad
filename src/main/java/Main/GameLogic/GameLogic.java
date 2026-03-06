package Main.GameLogic;

import Main.MainGameScene;
import Main.Scene.GameplayScene;
import Main.UI.InGameUI.HpDisplayZone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Core game logic manager.
 *
 * This class controls score management, game state transitions,
 * scene switching, map selection, audio settings, and shared
 * game components.
 */
public class GameLogic {

    /**
     * Property storing the current score.
     */
    private static final IntegerProperty SCORE = new SimpleIntegerProperty(0);

    /**
     * Best score achieved during gameplay.
     */
    private static int bestScore;

    /**
     * Score multiplier applied to gained score.
     */
    private static double multiplier = 1;

    /**
     * Reference to the main game application.
     */
    private static MainGameScene app;

    /**
     * Current map number.
     */
    private static int map = 1;

    /**
     * HP display UI element.
     */
    private static HpDisplayZone hpBar;

    /**
     * Background music volume.
     */
    private static int musicVolume = 50;

    /**
     * Sound effect volume.
     */
    private static int sfxVolume = 50;

    /**
     * Root container of the gameplay scene.
     */
    private static StackPane gameRoot;

    /**
     * Returns the current score.
     *
     * @return current score
     */
    public static int getScore() {
        return SCORE.get();
    }

    /**
     * Sets the score value.
     *
     * @param value new score value
     */
    public static void setScore(int value) {
        bestScore = Math.max(value, bestScore);
        SCORE.set(value);
    }

    /**
     * Adds score with multiplier applied.
     *
     * @param amount score amount to add
     */
    public static void addScore(int amount) {
        if (amount <= 0) return;

        int finalAmount = (int) (amount * multiplier);
        SCORE.set(SCORE.get() + finalAmount);
        bestScore = Math.max(SCORE.get(), bestScore);
    }

    /**
     * Returns the score property.
     *
     * @return IntegerProperty of score
     */
    public static IntegerProperty scoreProperty() {
        return SCORE;
    }

    /**
     * Current gameplay scene.
     */
    private static GameplayScene currentGameScene;

    /**
     * Property storing the current game state.
     */
    private static final ObjectProperty<GameState> GAME_STATE =
            new SimpleObjectProperty<>(null);

    /**
     * Returns the current game state.
     *
     * @return current GameState
     */
    public static GameState getGameState() {
        return GAME_STATE.get();
    }

    /**
     * Sets the current game state.
     *
     * Stops the previous gameplay scene before switching.
     *
     * @param state new game state
     */
    public static void setGameState(GameState state) {
        if (GAME_STATE.get() == state) return;
        if (currentGameScene != null) {
            currentGameScene.stopGame();
            currentGameScene = null;
        }

        GAME_STATE.set(state);
    }

    /**
     * Sets the current gameplay scene.
     *
     * @param scene gameplay scene
     */
    public static void setCurrentGameScene(GameplayScene scene) {
        currentGameScene = scene;
    }

    /**
     * Returns the game state property.
     *
     * @return ObjectProperty of GameState
     */
    public static ObjectProperty<GameState> gameStateProperty() {
        return GAME_STATE;
    }

    /**
     * Current scene displayed on the stage.
     */
    private static Scene curScene;

    /**
     * Main application stage.
     */
    private static Stage stage;

    /**
     * Sets the main application stage.
     *
     * @param primaryStage stage instance
     */
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Sets the current scene.
     *
     * @param scene scene to display
     */
    public static void setCurScene(Scene scene) {
        curScene = scene;
        if (stage != null) {
            stage.setScene(scene);
        }
    }

    /**
     * Returns the current scene.
     *
     * @return current Scene
     */
    public static Scene getCurScene() {
        return curScene;
    }

    /**
     * Returns the main stage.
     *
     * @return application Stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Returns the score multiplier.
     *
     * @return multiplier value
     */
    public static double getMultiplier() {
        return multiplier;
    }

    /**
     * Sets the score multiplier.
     *
     * @param value multiplier value
     */
    public static void setMultiplier(double value) {
        multiplier = value;
    }

    /**
     * Sets the main game application reference.
     *
     * @param application main game application
     */
    public static void setApp(MainGameScene application){
        app = application;
    }

    /**
     * Returns the main game application.
     *
     * @return MainGameScene instance
     */
    public static MainGameScene getApp(){
        return app;
    }

    /**
     * Returns the current map number.
     *
     * @return map number
     */
    public static int getMap() {
        return map;
    }

    /**
     * Sets the current map.
     *
     * @param mapNo map number
     */
    public static void setMap(int mapNo) {
        map = Math.max(1,mapNo);
    }

    /**
     * Returns the gameplay root container.
     *
     * @return StackPane game root
     */
    public static StackPane getGameroot() {
        return gameRoot;
    }

    /**
     * Sets the gameplay root container.
     *
     * @param gameRoot root pane
     */
    public static void setGameRoot(StackPane gameRoot) {
        GameLogic.gameRoot = gameRoot;
    }

    /**
     * Sets the best score.
     *
     * @param bestScore best score value
     */
    public static void setBestScore(int bestScore) {
        GameLogic.bestScore = bestScore;
    }

    /**
     * Returns the HP display UI.
     *
     * @return HpDisplayZone instance
     */
    public static HpDisplayZone getHpBar() {
        return hpBar;
    }

    /**
     * Sets the HP display UI.
     *
     * @param hpBar HP display component
     */
    public static void setHpBar(HpDisplayZone hpBar) {
        GameLogic.hpBar = hpBar;
    }

    /**
     * Returns the current gameplay scene.
     *
     * @return GameplayScene instance
     */
    public static GameplayScene getCurrentGameScene() {
        return currentGameScene;
    }

    /**
     * Returns the background music volume.
     *
     * @return music volume value
     */
    public static int getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the background music volume.
     *
     * @param musicVolumeValue music volume value
     */
    public static void setMusicVolume(int musicVolumeValue) {
        musicVolume = musicVolumeValue;
    }

    /**
     * Returns the sound effect volume.
     *
     * @return sfx volume value
     */
    public static int getSFXVolume() {
        return sfxVolume;
    }

    /**
     * Sets the sound effect volume.
     *
     * @param sfxVolume sound effect volume
     */
    public static void setSFXVolume(int sfxVolume) {
        GameLogic.sfxVolume = sfxVolume;
    }
}