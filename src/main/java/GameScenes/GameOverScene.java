package GameScenes;

import GameLogic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class GameOverScene {
    private Scene scene;

    public GameOverScene() {
        AnchorPane root = new AnchorPane();
        Font textFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/Fonts/Chango-Regular.ttf")).toExternalForm(), 36);
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        StackPane placeholder = new StackPane();
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/UI/placeholder.png")).toExternalForm());
        ImageView img = new ImageView(image);
        img.setFitWidth(500);
        img.setFitHeight(100);
        placeholder.getChildren().add(img);
        placeholder.setAlignment(Pos.CENTER);
        Text finalScore = new Text(GameLogic.getScore() + " score!");
        finalScore.setFill(Color.WHITE);
        finalScore.setFont(textFont);
        finalScore.setStroke(Color.BLACK);
        finalScore.setStrokeWidth(3);

        Image pCharImg = new Image(Objects.requireNonNull(getClass().getResource("/Characters/Player.png")).toExternalForm());
        ImageView charView = new ImageView(pCharImg);
        charView.setFitWidth(300);
        charView.setPreserveRatio(true);
        placeholder.getChildren().add(finalScore);
        content.getChildren().addAll(placeholder, charView);

        Text highestScore = new Text("Highest Score");
        highestScore.setFont(textFont);
//        highestScore.setFill(Color.WHITE);
        highestScore.setStroke(Color.BLACK);
        highestScore.setStrokeWidth(3);
        LinearGradient gradient1 = new LinearGradient(
                0, 0,
                1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.YELLOW),
                new Stop(1, Color.WHITE)
        );
        highestScore.setFill(gradient1);

        Text score = new Text(GameLogic.getBestScore()+" score!");
//        score.setFill(Color.WHITE);
        score.setFont(textFont);
        score.setStroke(Color.BLACK);
        score.setStrokeWidth(3);
        LinearGradient gradient2 = new LinearGradient(
                0, 0,
                1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(255, 39, 56, 0.6)),
                new Stop(1, Color.WHITE)
        );
        score.setFill(gradient2);

        Region bottomShadow = new Region();
        bottomShadow.setPrefHeight(80);
        bottomShadow.setStyle("""
            -fx-background-color: linear-gradient(to top,
                rgba(255, 249, 78, 0.5),
                rgba(255, 255, 255,0.0)
            );
        """);
        StackPane.setAlignment(bottomShadow, Pos.BOTTOM_CENTER);

        content.setPadding(new Insets(0, 0, 60, 0));

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);

        AnchorPane.setLeftAnchor(bottomShadow, 0.0);
        AnchorPane.setRightAnchor(bottomShadow, 0.0);
        AnchorPane.setBottomAnchor(bottomShadow, 0.0);

        bottomShadow.setMinHeight(60);
        bottomShadow.setMaxHeight(80);

        content.getChildren().addAll(highestScore, score);

        root.getChildren().addAll(content, bottomShadow);
        this.scene = new Scene(root);
    }

    public Scene getScene() {
        return scene;
    }
}
