package Got.GameLogic;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameLogic {
    private static int score;
    private static int bestScore;
    private static final ObjectProperty<GameState> gameState = new SimpleObjectProperty<>(GameState.INTRO);
    private static Scene curScene;
    private static Stage stage;
    //score

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        bestScore = Math.max(score, bestScore);
        GameLogic.score = score;
    }

    public static int getBestScore() {
        return GameLogic.bestScore;
    }

    public static void setBestScore(int bestScore) {
        GameLogic.bestScore = bestScore;
    }

    public static void addScore(int val) {
        if(gameState.get()!=GameState.INGAME) return;
        score += val;
        bestScore = Math.max(score, bestScore);
    }

    //game state

    public static GameState getGameState() {
        return gameState.get();
    }

    public static void setGameState(GameState state) {
        gameState.set(state);
    }

    public static ObjectProperty<GameState> gameStateProperty() {
        return gameState;
    }

    public static Scene getCurScene() {
        return curScene;
    }

    public static void setCurScene(Scene curScene) {
        GameLogic.curScene = curScene;
        stage.setScene(curScene);
    }

    public static void setStage(Stage stage) {
        GameLogic.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }
}
