package Got.GameLogic;

import Beam.CharactorData;
import Beam.Media.MediaPlayer;
import Beam.Scene.*;
import Pors.ObjectInGame.Spawner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGameScene extends Application {

    private StackPane gameRoot;
    private Scene scene;
    private StackPane scalableLayer;

    private final double BASE_WIDTH = 1600;
    private final double BASE_HEIGHT = 900;

    private boolean scaleLocked = false;

    @Override
    public void start(Stage stage) {

        GameLogic.setStage(stage);

        // 🔥 ชั้นที่เอาไว้ใส่ scene เกมจริง
        gameRoot = new StackPane();
        gameRoot.setPrefSize(BASE_WIDTH, BASE_HEIGHT);

        // 🔥 ชั้นที่เอาไว้ scale ทั้งเกม
        scalableLayer = new StackPane(gameRoot);

        // 🔥 root จริงของ Scene
        StackPane root = new StackPane(scalableLayer);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);
        stage.setScene(scene);
        GameLogic.setApp(this);

        // 🔥 ทำให้ scale ตามขนาดหน้าต่าง
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        GameLogic.setGameroot(gameRoot);

        // เปลี่ยน scene ตาม game state
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {

            switch (newState) {
                case INTRO -> {
                    gameRoot.getChildren().setAll(new MainMenuScene());
                    playMusic("Lobby",50);
                }

                case SELECTCHAR -> {
                    gameRoot.getChildren().setAll(new CookieSelectionScene());
                    playMusic("Cookies",50);
                }

                case INGAME -> {

                    CharactorData.getCurrent_Cookie().setHp(CharactorData.getCurrent_Cookie().getMaxhp());
                    CharactorData.getCurrent_Cookie().setCooldownTimer(0);
                    CharactorData.getCurrent_Cookie().setSkillCounter(0);
                    CharactorData.getCurrent_Cookie().setDead(false);
                    CharactorData.getCurrent_Cookie().reset();
                    GameLogic.setScore(0);
                    playMusic("SoundMAP" + GameLogic.getMap(),50);

                    GameplayScene inGameScene = new GameplayScene();
                    GameLogic.setCurrentGameScene(inGameScene);
                    gameRoot.getChildren().setAll(inGameScene);
                }
                case SELECTPET -> {
                    gameRoot.getChildren().setAll(new PetsSelectionScene());
                    playMusic("Pets",50);
                }

                case GAMEOVER -> {
                    gameRoot.getChildren().setAll(new GameOverScene());
                    playMusic("GameOver",50);
                }
            }
        });

        GameLogic.setCurScene(scene);
        GameLogic.setGameState(GameState.INTRO);
        stage.show();

        // scale ครั้งแรก
        updateScale(scalableLayer);
    }

    private void updateScale(StackPane scalableLayer) {

        double scaleX = scene.getWidth() / BASE_WIDTH;
        double scaleY = scene.getHeight() / BASE_HEIGHT;

        // 🔥 รักษาสัดส่วน (ไม่บิด)
        double scale = Math.min(scaleX, scaleY);

        scalableLayer.setScaleX(scale);
        scalableLayer.setScaleY(scale);
    }

    public static void main(String[] args) {
        launch();
    }

    private void playMusic(String key,int v) {
        MediaPlayer.getInstance().playBGM(key, true);
    }

}