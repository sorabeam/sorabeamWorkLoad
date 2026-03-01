package components;

import GameLogic.GameLogic;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class ScoreBoard extends VBox {
    private Text scoreText;
    private Text bestScoreText;
    public ScoreBoard() {
        scoreText = new Text("Score: " + GameLogic.getScore());
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Arial", 24));
        getChildren().add(scoreText);

        bestScoreText = new Text("Score: " + GameLogic.getBestScore());
        bestScoreText.setFill(Color.WHITE);
        bestScoreText.setFont(Font.font("Arial", 16));
        getChildren().add(bestScoreText);
        this.setAlignment(Pos.BASELINE_RIGHT);

        updateScore();
    }

    public void addScore(int val) {
        GameLogic.addScore(val);
        scoreText.setText("Score: " + GameLogic.getScore());
    }

    public void updateScore() {
        scoreText.setText("Score: " + GameLogic.getScore());
        bestScoreText.setText("Best: " + GameLogic.getBestScore());
    }
}
