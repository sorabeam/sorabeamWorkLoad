package Beam.Scene;

import Beam.Asset;
import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Beam.CharactorData;
import javafx.util.Duration;

import java.util.Objects;

public class GameOverRoot extends BaseRoot {

    private void addHoverEffect(Button btn) {

        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), btn);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), btn);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        btn.setOnMouseEntered(e -> scaleUp.playFromStart());
        btn.setOnMouseExited(e -> scaleDown.playFromStart());
    }

    //change to game data later, after game really done
    public GameOverRoot() {
        super();
//        Cookie cookie = CharactorData.getCurrent_Cookie();
//        ImageView cookieView = Asset.createImageView(cookie.getImgURL(), 400, 400);
        StackPane charWrapper = new StackPane();
        ImageView cookieView = Asset.createImageView("TestChar", 600, 600);
        BackgroundImage bkImg = new BackgroundImage(
                Asset.getImage("BackGround/RedStage"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        100, 100,
                        true, true, true, true
                )
        );

        Font headerFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/Fonts/Chango-Regular.ttf")).toExternalForm(), 24);
        Font contentFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/Fonts/Chango-Regular.ttf")).toExternalForm(), 16);

        ImageView petView = Asset.createImageView("Moji", 150, 150);
        StackPane.setAlignment(petView, Pos.TOP_RIGHT);

        Text username = new Text("Boba");
        username.setFont(headerFont);
        username.setFill(Color.WHITE);
        username.setStroke(Color.BLACK);
        username.setStrokeWidth(3);
        StackPane.setAlignment(username, Pos.TOP_CENTER);

        charWrapper.getChildren().addAll(petView, cookieView, username);
        charWrapper.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        StackPane.setAlignment(charWrapper, Pos.BOTTOM_LEFT);


        HBox statsPane = new HBox();
        StackPane wrapper = new StackPane();
        ImageView statsImg = Asset.createImageView("statsBox", 400, 400);
        VBox statVBox = new VBox();
        Text score = new Text(GameLogic.getScore()+"score!");
        Text bestScore = new Text("Highest " + GameLogic.getBestScore() + "score!");
        score.setFont(headerFont);
        bestScore.setFont(contentFont);
        statVBox.setAlignment(Pos.CENTER);

        score.setFill(Color.WHITE);
        score.setStroke(Color.BLACK);
        score.setStrokeWidth(1);

        bestScore.setFill(Color.WHITE);
        bestScore.setStroke(Color.BLACK);
        bestScore.setStrokeWidth(1);

        statVBox.getChildren().addAll(score, bestScore);

        statsPane.setAlignment(Pos.CENTER_RIGHT);
        wrapper.setPadding(new Insets(16));
        wrapper.getChildren().addAll(statsImg, statVBox);

        StackPane wrapper2 = new StackPane();
        ImageView curCharBox = Asset.createImageView("charBox", 300, 300);
        ImageView curCharView = Asset.createImageView("B1", 200, 200);
        StackPane.setMargin(curCharView, new Insets(0, -20, 0, 20));
        wrapper2.getChildren().addAll(curCharBox, curCharView);

        statsPane.setSpacing(-48);
        statsPane.getChildren().addAll(wrapper2, wrapper);
        setBackground(new Background(bkImg));

        HBox btnPane = new HBox();
//        HBox wrapper3 = new HBox();
        ImageView replayView = Asset.createImageView("replay", 100, 300);
        ImageView backView = Asset.createImageView("backBtn", 100, 200);
        ImageView playMoreView = Asset.createImageView("playMoreTest", 200, 400);
        Button replayBtn = new Button();
        Button backBtn = new Button();
        Button playMoreBtn = new Button();
        replayBtn.setGraphic(replayView);
        backBtn.setGraphic(backView);
        playMoreBtn.setGraphic(playMoreView);
        replayBtn.setStyle("-fx-background-color: transparent;");
        backBtn.setStyle("-fx-background-color: transparent;");
        playMoreBtn.setStyle("-fx-background-color: transparent;");

        backBtn.setOnAction(e -> {
            GameLogic.setGameState(GameState.INTRO);
//            System.out.println("Clicked INTRO");
        });

        replayBtn.setOnAction(e -> {
            GameLogic.setGameState(GameState.INGAME);
//            System.out.println("Clicked INGAME");
        });

        addHoverEffect(replayBtn);
        addHoverEffect(backBtn);

//        btnPane.setAlignment(Pos.BOTTOM_RIGHT);
        btnPane.setPadding(new Insets(32));
        btnPane.setSpacing(16);
        btnPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        btnPane.getChildren().addAll(playMoreBtn, backBtn, replayBtn);
        StackPane.setAlignment(btnPane, Pos.BOTTOM_RIGHT);
        btnPane.setAlignment(Pos.CENTER);

        //setting part
//        StackPane settingPane = new StackPane();
//        ImageView settingView = Asset.createImageView("SettingBtn", 80, 80);
//        Button settingBtn = new Button();
//        settingBtn.setGraphic(settingView);
//        settingBtn.setStyle("-fx-background-color: transparent;");
//        StackPane.setAlignment(settingBtn, Pos.TOP_LEFT);
//        settingPane.getChildren().add(settingBtn);
//        settingPane.setPadding(new Insets(24));
//        StackPane.setAlignment(btnPane, Pos.BOTTOM_RIGHT);

        ImageView bannerView = Asset.createImageView("banner", 200, 230);
        StackPane.setAlignment(bannerView, Pos.TOP_RIGHT);
        StackPane.setMargin(bannerView, new Insets(0, 40, 0, 0));
        getChildren().addAll(charWrapper, statsPane, bannerView, btnPane);
    }
}
