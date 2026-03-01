package Got.GameLogic;

import Beam.Scene.CookieSelectionScene;
import Beam.Scene.InGameScene;
import Beam.Scene.MainMenuScene;
import Beam.Scene.PetsSelectionScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestInGame2 extends Application {

    private StackPane gameRoot;
    private Scene scene;

    private final double BASE_WIDTH = 1440;
    private final double BASE_HEIGHT = 900;

    @Override
    public void start(Stage stage) {

        GameLogic.setStage(stage);

        // 🔥 ชั้นที่เอาไว้ใส่ scene เกมจริง
        gameRoot = new StackPane();
        gameRoot.setPrefSize(BASE_WIDTH, BASE_HEIGHT);

        // 🔥 ชั้นที่เอาไว้ scale ทั้งเกม
        StackPane scalableLayer = new StackPane(gameRoot);

        // 🔥 root จริงของ Scene
        StackPane root = new StackPane(scalableLayer);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);
        stage.setScene(scene);

        // 🔥 ทำให้ scale ตามขนาดหน้าต่าง
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            updateScale(scalableLayer);
        });

        // เปลี่ยน scene ตาม game state
        GameLogic.gameStateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case INTRO -> gameRoot.getChildren().setAll(new MainMenuScene());
                case SELECTCHAR -> gameRoot.getChildren().setAll(new CookieSelectionScene());
                case INGAME -> gameRoot.getChildren().setAll(new InGameScene());
                case SELECTPET -> gameRoot.getChildren().setAll(new PetsSelectionScene());
                case GAMEOVER -> gameRoot.getChildren().setAll(new MainMenuScene());
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
}
