package GameLogic;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GameLogic {
    private static int score;
    private static int bestScore;
    private static final ObjectProperty<GameState> gameState = new SimpleObjectProperty<>(GameState.INTRO);
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
}
