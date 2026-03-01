package Beam.Scene;

import Beam.Cookies.BobaCookie;
import Beam.Cookies.Cookie;
import Beam.UI.InGameUI.*;
import Filmmy.Pearl;
import Pors.ObsticleAndItem.Spawner;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static Got.GameLogic.GameLogic.getStage;

public class InGameScene extends BaseRoot{

    Pane gameLayer = new Pane();     // สำหรับ player / ground / obstacle
    StackPane uiLayer = new StackPane(); // สำหรับ UI

    private boolean shiftHeld = false;

    SettingZone settingZone = new SettingZone(this,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();
    LastRecord lastRecord = new LastRecord();

    private final double groundH = 80;
    public static double groundY;

    public InGameScene(){
        super();

        getStage().setMinWidth(1440);
        getStage().setMinHeight(940);
        getStage().setResizable(false);

        setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

        root.getChildren().add(new InGameBG(root));

        uiLayer.getChildren().addAll(
                //new ExpBar(root),
                settingZone,
                lastRecord,
                hpzone,
                sc
        );

        StackPane.setMargin(lastRecord,new Insets(120,60,0,0));
        StackPane.setAlignment(lastRecord,Pos.TOP_RIGHT);
        StackPane.setAlignment(sc,Pos.CENTER_RIGHT);
        StackPane.setMargin(sc,new Insets(200,0,0,0));
        StackPane.setAlignment(hpzone,Pos.TOP_CENTER);
        settingZone.setMaxWidth(50);
        StackPane.setAlignment(settingZone,Pos.TOP_RIGHT);
        StackPane.setMargin(settingZone,new Insets(20,20,0,0));

        Cookie player = new BobaCookie();

        Spawner spawner =
                new Spawner(
                        gameLayer,
                        scene.getWidth(),
                        scene.getHeight(),
                        player
                );

        spawner.start();

        //ground
        Rectangle ground = new Rectangle();
        ground.setHeight(groundH);
        ground.setLayoutX(0);
        ground.widthProperty().bind(root.widthProperty());
        ground.layoutYProperty().bind(root.heightProperty().subtract(groundH));
        ground.setFill(Color.LIGHTGRAY);
        gameLayer.getChildren().add(ground);

        //Dummy obstacle
        Rectangle obstacle = new Rectangle(80, 120);
        obstacle.setFill(Color.BLUE);

        obstacle.setLayoutX(600);
        obstacle.layoutYProperty().bind(
                gameLayer.heightProperty().subtract(groundH + 120)
        );

        gameLayer.getChildren().add(obstacle);

        player.setGameLayer(gameLayer);
        player.createCookie();

        gameLayer.getChildren().add(player.getCookie());
        gameLayer.getChildren().add(player.getHitbox());

        player.getCookie().setFitWidth(200);
        player.getCookie().setFitHeight(200);
        player.getCookie().setLayoutX(200);

        root.getChildren().add(gameLayer);
        root.getChildren().add(uiLayer);

        AnimationTimer timer = new AnimationTimer() {

            long last = 0;

            @Override
            public void handle(long now) {

                if (last == 0) {
                    last = now;
                    return;
                }

                double dt = (now - last) / 1e9;
                last = now;

                groundY = gameLayer.getHeight() - groundH;

                player.update(dt);          // physics + movement
                player.getCookie().update(dt);

                if (shiftHeld && player.isOnGround()) {
                    player.slide();
                    System.out.println("slide");
                }

                //Pew-Pew Pearl And Obstacle
                List<Node> toRemove = new ArrayList<>();
                double screenWidth = getWidth();

                for (Node node : gameLayer.getChildren()) {

                    if (node instanceof Pearl pearl) {

                        pearl.update(dt);

                        if (gameLayer.getChildren().contains(obstacle)) {

                            if (pearl.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {

                                toRemove.add(pearl);
                                toRemove.add(obstacle);
                                continue; //No need to check
                            }
                        }

                        if (pearl.getLayoutX() > screenWidth - 50) {
                            toRemove.add(pearl);
                        }
                    }
                }

                // 3️⃣ ลบทีเดียวหลัง loop
                gameLayer.getChildren().removeAll(toRemove);
            }
        };

        timer.start();

        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE -> player.jump();
                case SHIFT -> {
                    shiftHeld = true;
                    player.slide();
                }
            }
        });

        setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SHIFT ) {
                shiftHeld = false;
                player.upFromSlide();
            }
        });


        setFocusTraversable(true);
        Platform.runLater(this::requestFocus);

    }
}

