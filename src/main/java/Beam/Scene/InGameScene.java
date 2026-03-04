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
import Pors.ObjectInGame.Items.*;
import Pors.ObjectInGame.Jelly.JellyView;
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
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

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
                hpzone,
                sc
        );

        GameLogic.setHpBar(hpzone);
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
        Image groundImg = new Image("/Image/BackGround/GroundLevel1.png");

        ImageView ground1 = new ImageView(groundImg);
        ImageView ground2 = new ImageView(groundImg);

        ground1.setFitHeight(groundH);
        ground2.setFitHeight(groundH);

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

        ground1.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(60));
        ground2.layoutYProperty().bind(gameLayer.heightProperty().subtract(groundH).subtract(60));

        gameLayer.getChildren().addAll(ground1, ground2);

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

//        pet.getView().setLayoutX(150);
        pet.getView().setFitWidth(50);
        pet.getView().setFitHeight(50);
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

                    if(tomyum.isRainReady()){

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

