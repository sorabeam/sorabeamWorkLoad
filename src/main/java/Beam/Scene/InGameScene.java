package Beam.Scene;

import Beam.CharactorData;
import Beam.Cookies.BobaCookie;
import Beam.Cookies.Cookie;
import Beam.Cookies.CrossiantCookie;
import Beam.Cookies.TomYumCookie;
import Beam.Pets.Chilly;
import Beam.Pets.Pet;
import Beam.Pets.Salad;
import Beam.UI.InGameUI.*;
import Filmmy.Pearl;
import Got.GameLogic.GameLogic;
import Pors.ObjectInGame.Interactable;
import Pors.ObjectInGame.Items.*;
import Pors.ObjectInGame.Jelly.JellyView;
import Pors.ObjectInGame.Obstacle.ObstacleView;
import Pors.ObjectInGame.Spawner;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import Pors.ObjectInGame.Obstacle.BaseObstacle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Got.GameLogic.GameLogic.getStage;

public class InGameScene extends BaseRoot{

    Pane gameLayer = new Pane();     // สำหรับ player / ground / obstacle
    StackPane uiLayer = new StackPane(); // สำหรับ UI

    private boolean shiftHeld = false;
//    double groundSpeedDefault = Spawner.getSpeed();
//    double groundSpeed = Spawner.getSpeed();

    SettingZone settingZone = new SettingZone(this,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();
    LastRecord lastRecord = new LastRecord();

    private final double groundH = 150;
    public static double groundY;

    private AnimationTimer timer;
    private Spawner spawner;
    private boolean isUpadate = true;
    private InGameBG bg = new InGameBG(root);

    public InGameScene(){
        super();
        setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.LIME);
        shadow.setRadius(20);

        root.getChildren().add(bg);

        uiLayer.getChildren().addAll(
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

//       Cookie player = new BobaCookie();
        Cookie player = CharactorData.getCurrent_Cookie();
        Pet pet = CharactorData.getCurrent_Pet();
//        Pet pet = new Salad()s;
//        Pet pet = new Chilly();

        spawner =
                new Spawner(
                        gameLayer,
                        scene.getWidth(),
                        scene.getHeight(),
                        player,
                        pet
                );


        //ground
        Image groundImg = new Image("/Image/BackGround/GroundLevel" + GameLogic.getMap() + ".png");

        ImageView ground1 = new ImageView(groundImg);
        ImageView ground2 = new ImageView(groundImg);

        ground1.setFitHeight(groundH + 100);
        ground2.setFitHeight(groundH + 100);

        ground1.setFitWidth(scene.getWidth());
        ground2.setFitWidth(scene.getWidth());

        ground1.setPreserveRatio(false);
        ground2.setPreserveRatio(false);

        ground1.setScaleY(1.5);
        ground2.setScaleY(1.5);

        // wait until layout pass to get real width
        Platform.runLater(() -> {
            double groundWidth = scene.getWidth();

            ground1.setTranslateX(0);
            ground2.setTranslateX(groundWidth);
        });

        ground1.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));
        ground2.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(90));

        gameLayer.getChildren().addAll(ground1, ground2);

        player.setGameLayer(gameLayer);
        player.createCookie();

        gameLayer.getChildren().addAll(
                player.getBoostAura(),
                player.getCookie(),
                player.getHitbox()
        );

        //Cooldown Frame
        Rectangle cdFrame = new Rectangle(84,12);
        cdFrame.setFill(Color.BLACK);
        cdFrame.setArcWidth(10);
        cdFrame.setArcHeight(10);

        Rectangle cdBackground = new Rectangle(80,8);
        cdBackground.setFill(Color.rgb(40,40,40));
        cdBackground.setArcWidth(8);
        cdBackground.setArcHeight(8);

        Rectangle cdFill = new Rectangle(80,8);
        cdFill.setFill(Color.LIMEGREEN);
        cdFill.setArcWidth(8);
        cdFill.setArcHeight(8);

        cdFill.setFill(new LinearGradient(
                0,0,1,0,true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#b9ff9f")),
                new Stop(0.35, Color.web("#7dff63")),
                new Stop(0.7, Color.web("#39d353")),
                new Stop(1, Color.web("#1faa2a"))
        ));

        cdBackground.setLayoutX(2);
        cdBackground.setLayoutY(2);

        cdFill.setLayoutX(2);
        cdFill.setLayoutY(2);

        gameLayer.getChildren().addAll(cdFrame, cdBackground, cdFill);

        cdFrame.layoutXProperty().bind(
                player.getCookie().layoutXProperty().add(58)
        );

        cdFrame.layoutYProperty().bind(
                player.getCookie().layoutYProperty().subtract(18)
        );

        cdBackground.layoutXProperty().bind(cdFrame.layoutXProperty().add(2));
        cdBackground.layoutYProperty().bind(cdFrame.layoutYProperty().add(2));

        cdFill.layoutXProperty().bind(cdFrame.layoutXProperty().add(2));
        cdFill.layoutYProperty().bind(cdFrame.layoutYProperty().add(2));

//        pet.getView().setLayoutX(150);
        pet.getView().setFitWidth(80);
        pet.getView().setFitHeight(80);
        gameLayer.getChildren().add(pet.getView());

        player.getCookie().setFitWidth(200);
        player.getCookie().setFitHeight(200);
        player.getCookie().setLayoutX(200);

        root.getChildren().add(gameLayer);
        root.getChildren().add(uiLayer);

        timer = new AnimationTimer() {

            long last = 0;
            double petCooldownTimer = pet.getCooldowntime()/1000.0;
            double tarPetPosY = 0;

            @Override
            public void handle(long now) {

                if (!isUpadate) {
                    last = 0;
                    return;
                }

                if (last == 0) {
                    last = now;
                    return;
                }

                double dt = (now - last) / 1e9;
                last = now;

                groundY = gameLayer.getHeight() - groundH;

                double groundWidth = scene.getWidth();
                double groundSpeed = Spawner.getSpeed();

                ground1.setTranslateX(ground1.getTranslateX() + groundSpeed * dt);
                ground2.setTranslateX(ground2.getTranslateX() + groundSpeed * dt);

                if (ground1.getTranslateX() <= -groundWidth) {
                    ground1.setTranslateX(ground2.getTranslateX() + groundWidth);
                }

                if (ground2.getTranslateX() <= -groundWidth) {
                    ground2.setTranslateX(ground1.getTranslateX() + groundWidth);
                }

                player.update(dt);          // physics + movement
                player.getCookie().update(dt);
//                pet.getView().layoutYProperty().bind(player.getCookie().layoutYProperty().add(30));
                if(player.hasCooldownBar()){
                    double progress = player.getCooldownProgress();
                    cdFill.setWidth(80 * progress);
                    cdFill.setVisible(true);
                }else{
                    cdFill.setVisible(false);
                    cdFrame.setVisible(false);
                    cdBackground.setVisible(false);
                }

                petCooldownTimer -= dt;
                if(petCooldownTimer<=0) {
                    pet.useSkill();
                    petCooldownTimer = pet.getCooldowntime()/1000.0;
                }

                if(pet.isUsingSkill()) {
                    double tarPetPosX = Math.max(player.getCookie().getLayoutX()+100, getWidth()-100);

                    if(tarPetPosY == 0){
                        tarPetPosY = Math.max(400,player.getCookie().getLayoutY());
                        pet.getView().setEffect(shadow);
                    }

                    System.out.println(player.getCookie().getLayoutY());
                    pet.setTargetPos(tarPetPosX, tarPetPosY);
                    if(pet.hasArrived()) {
                        pet.updateIndex();
                        ItemView spawnItem = pet.getCurrentSpawnItem();
                        gameLayer.getChildren().add(spawnItem);
                        double petX = pet.getView().getLayoutX() + pet.getView().getTranslateX();
                        double petY = pet.getView().getLayoutY() + pet.getView().getTranslateY();
                        spawnItem.setTranslateX(petX);
                        spawnItem.setTranslateY(petY);
                        spawnItem.setSpeed(Spawner.getSpeed(), 0);
                        pet.setUsingSkill(false);
                        tarPetPosY = 0;
                        pet.getView().setEffect(null);
                    }
                } else {
                    double tarPetPosX = player.getCookie().getLayoutX()-30;
                    double tarPetPosY = player.getCookie().getLayoutY()+30;
                    pet.setTargetPos(tarPetPosX, tarPetPosY);
                }

                pet.update(dt);

                spawner.update(now, dt);

                if (shiftHeld && player.isOnGround()) {
                    player.slide();
                    System.out.println("slide");
                }

                //Pew-Pew Pearl And Obstacle
                List<Node> toRemove = new ArrayList<>();
                double screenWidth = getWidth();

                if (player instanceof CrossiantCookie croissant) {

                    if (croissant.isCroissantReady()) {

                        CroissantType type = croissant.consumeCroissant();
                        //spawnCroissant(type);
                    }
                }

                for (Node node : gameLayer.getChildren()) {

                    if (node instanceof Pearl pearl) {

                        pearl.update(dt);

                        for (Node other : gameLayer.getChildren()) {

                            if (other instanceof ObstacleView obstacle) {

                                if (pearl.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {

                                    GameLogic.addScore(7000);
                                    toRemove.add(pearl);
                                    toRemove.add(obstacle);
                                    break;
                                }
                            }

                        }

                        if (pearl.getLayoutX() > screenWidth - 50) {
                            toRemove.add(pearl);
                        }
                    }
                }

                for (Node node : gameLayer.getChildren()) {

                    if (node instanceof ItemView view &&
                            view.getItem() instanceof Croissant croissant) {

                        double gravity = 1500;
                        double bouncePower = -700;

                        // เพิ่มความเร็วตก
                        croissant.vy += gravity * dt;

                        view.setTranslateY(view.getTranslateY() + croissant.vy * dt);

                        double bottom = view.getTranslateY() + view.getBoundsInLocal().getHeight();

                        if (bottom >= groundY) {

                            view.setTranslateY(groundY - view.getBoundsInLocal().getHeight());

                            if (!croissant.hasBounced) {
                                croissant.vy = bouncePower;
                                croissant.hasBounced = true;
                            } else {
                                croissant.vy = 0;
                            }
                        }
                    }
                }

                if(player instanceof TomYumCookie tomyum){

                    tomyum.updateSkill(dt);

                    if(tomyum.isSkillReady()){

                        spawner.spawnIngredientRain();

                        tomyum.consumeRain();
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
                case Q -> {
                    player.useSkill();
                }
//                case T -> {
//                    pet.useSkill();
//                }
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

    public void stopGame() {
        if (timer != null) {
            timer.stop();
            bg.stop();
        }
    }

    public void stopGameByBool() {
        isUpadate = false;
        bg.stop();
    }

    public void resumeGameByBool() {
        isUpadate = true;
        bg.start();
    }
}

