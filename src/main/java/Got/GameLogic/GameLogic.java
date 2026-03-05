package Got.GameLogic;

import Beam.Scene.*;
import Beam.UI.InGameUI.HpDisplayZone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameLogic {

    // ===== SCORE =====
    private static final IntegerProperty score = new SimpleIntegerProperty(0);
    private static int bestScore;
    private static double multiplier = 1;
    private static MainGameScene app;
    private static int cookieCountMod = 0;
    private static int Map = 1;
    private static HpDisplayZone hpBar;

    private static int MusicVolume = 50;
    private static int SFXVolume = 50;

    private static StackPane gameroot;

    public static int getScore() {
        return score.get();
    }

    public static void setScore(int value) {
        bestScore = Math.max(value, bestScore);
        score.set(value);
    }

    public static void addScore(int amount) {
        if (amount <= 0) return;

        int finalAmount = (int) (amount * multiplier);
        score.set(score.get() + finalAmount);
        bestScore = Math.max(score.get(), bestScore);
    }

    public static IntegerProperty scoreProperty() {
        return score;
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static void resetScore() {
        score.set(0);
        multiplier = 1;
    }

    // ===== GAME STATE =====
    private static GameplayScene currentGameScene;

    private static final ObjectProperty<GameState> gameState =
            new SimpleObjectProperty<>(null);

    public static GameState getGameState() {
        return gameState.get();
    }

    public static void setGameState(GameState state) {
        if (gameState.get() == state) return;
        if (currentGameScene != null) {
            currentGameScene.stopGame();
            currentGameScene = null;
        }

        gameState.set(state);
    }

    public static void setCurrentGameScene(GameplayScene scene) {
        currentGameScene = scene;
    }

    public static ObjectProperty<GameState> gameStateProperty() {
        return gameState;
    }

    // ===== SCENE / STAGE =====
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

    // ===== MULTIPLIER =====
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

    public static int getCookieCountMod() {
        return cookieCountMod;
    }

    public static void setCookieCountMod(int cookieCountMod) {
        GameLogic.cookieCountMod = cookieCountMod;
    }

    public static int getMap() {
        return Map;
    }

    public static void setMap(int map) {
        Map = Math.max(1,map);
    }


    public static StackPane getGameroot() {
        return gameroot;
    }

    public static void setGameroot(StackPane gameroot) {
        GameLogic.gameroot = gameroot;
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
        return MusicVolume;
    }

    public static void setMusicVolume(int musicVolume) {
        MusicVolume = musicVolume;
    }

    public static int getSFXVolume() {
        return SFXVolume;
    }

    public static void setSFXVolume(int SFXVolume) {
        GameLogic.SFXVolume = SFXVolume;
    }
}
