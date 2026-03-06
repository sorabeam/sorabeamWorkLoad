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

public class GameLogic {

    //SCORE
    private static final IntegerProperty SCORE = new SimpleIntegerProperty(0);
    private static int bestScore;
    private static double multiplier = 1;
    private static MainGameScene app;
    private static int map = 1;
    private static HpDisplayZone hpBar;

    private static int musicVolume = 50;
    private static int sfxVolume = 50;

    private static StackPane gameRoot;

    public static int getScore() {
        return SCORE.get();
    }

    public static void setScore(int value) {
        bestScore = Math.max(value, bestScore);
        SCORE.set(value);
    }

    public static void addScore(int amount) {
        if (amount <= 0) return;

        int finalAmount = (int) (amount * multiplier);
        SCORE.set(SCORE.get() + finalAmount);
        bestScore = Math.max(SCORE.get(), bestScore);
    }

    public static IntegerProperty scoreProperty() {
        return SCORE;
    }

    //GameState
    private static GameplayScene currentGameScene;

    private static final ObjectProperty<GameState> GAME_STATE =
            new SimpleObjectProperty<>(null);

    public static GameState getGameState() {
        return GAME_STATE.get();
    }

    public static void setGameState(GameState state) {
        if (GAME_STATE.get() == state) return;
        if (currentGameScene != null) {
            currentGameScene.stopGame();
            currentGameScene = null;
        }

        GAME_STATE.set(state);
    }

    public static void setCurrentGameScene(GameplayScene scene) {
        currentGameScene = scene;
    }

    public static ObjectProperty<GameState> gameStateProperty() {
        return GAME_STATE;
    }

    //Scene/Stage
    private static Scene curScene;
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void setCurScene(Scene scene) {
        curScene = scene;
        if (stage != null) {
            stage.setScene(scene);
        }
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static Stage getStage() {
        return stage;
    }

    // MULTIPLIER
    public static double getMultiplier() {
        return multiplier;
    }

    public static void setMultiplier(double value) {
        multiplier = value;
    }

    public static void setApp(MainGameScene application){
        app = application;
    }

    public static MainGameScene getApp(){
        return app;
    }

    public static int getMap() {
        return map;
    }

    public static void setMap(int mapNo) {
        map = Math.max(1,mapNo);
    }


    public static StackPane getGameroot() {
        return gameRoot;
    }

    public static void setGameRoot(StackPane gameRoot) {
        GameLogic.gameRoot = gameRoot;
    }

    public static void setBestScore(int bestScore) {
        GameLogic.bestScore = bestScore;
    }

    public static HpDisplayZone getHpBar() {
        return hpBar;
    }

    public static void setHpBar(HpDisplayZone hpBar) {
        GameLogic.hpBar = hpBar;
    }

    public static GameplayScene getCurrentGameScene() {
        return currentGameScene;
    }

    public static int getMusicVolume() {
        return musicVolume;
    }

    public static void setMusicVolume(int musicVolumeValue) {
        musicVolume = musicVolumeValue;
    }

    public static int getSFXVolume() {
        return sfxVolume;
    }

    public static void setSFXVolume(int sfxVolume) {
        GameLogic.sfxVolume = sfxVolume;
    }
}
