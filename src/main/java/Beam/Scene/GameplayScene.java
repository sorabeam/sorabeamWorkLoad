package Beam.Scene;

import Beam.Animation.AnimateEffect;
import Beam.Animation.AnimationType;
import Beam.Asset;
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
import java.util.Objects;

public class GameplayScene extends BaseScene {

    Pane gameLayer = new Pane();     // สำหรับ player / ground / obstacle
    StackPane uiLayer = new StackPane(); // สำหรับ UI

    private MoveGround ground;

    private boolean shiftHeld = false;
//    double groundSpeedDefault = Spawner.getSpeed();
//    double groundSpeed = Spawner.getSpeed();

    SettingZone settingZone = new SettingZone(this,spacer('H'));
    HpDisplayZone hpzone = new HpDisplayZone();
    ShowScore sc = new ShowScore();

    private final double groundH = -150;
    public static double groundY;

    private AnimationTimer timer;
    private Spawner spawner;
    public boolean isUpdate = true;
    private InGameBG bg = new InGameBG(root);
    private double deltatime;

    public GameplayScene(){
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
        AnimateEffect flame = new AnimateEffect(Asset.getImage("FireSpriteSheet"), 125, 125, 9, 4, 0.3);
        flame.setLoop(true);
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

        Spawner.setSpeed(-350);
        //ground
        ground = new MoveGround(gameLayer, scene.getWidth());
        gameLayer.getChildren().add(ground);
        ground.start();

        player.setGameLayer(gameLayer);
        player.createCookie();
        CoodownBar cdBar = new CoodownBar(player);

        gameLayer.getChildren().addAll(
                flame,
                player.getCookie(),
                player.getHitbox(),
                cdBar
        );





//        pet.getView().setLayoutX(150);
        pet.getView().setFitWidth(80);
        pet.getView().setFitHeight(80);
        gameLayer.getChildren().add(pet.getView());

        player.getCookie().setFitWidth(200);
        player.getCookie().setFitHeight(200);
        player.getCookie().setLayoutX(200);

//        flame.setManaged(false);
        flame.setFitWidth(350);
        flame.setFitHeight(350);
        flame.setPreserveRatio(true);
        double flameW = flame.getFitWidth();
        double flameH = flame.getFitHeight();
        flame.layoutXProperty().bind(
                player.getCookie().layoutXProperty()
                        .add(player.getCookie().fitWidthProperty().divide(2)).add(60)
        );

        flame.layoutYProperty().bind(
                player.getCookie().layoutYProperty()
                        .add(player.getCookie().fitHeightProperty().divide(2))
        );

        root.getChildren().add(gameLayer);
        root.getChildren().add(uiLayer);

        timer = new AnimationTimer() {

            long last = 0;
            double petCooldownTimer = pet.getCooldowntime()/1000.0;
            double tarPetPosY = 0;
            double damageTimer;

            @Override
            public void handle(long now) {

                if (!isUpdate) {
                    last = 0;
                    return;
                }

                if (last == 0) {
                    last = now;
                    return;
                }

                deltatime = (now - last) / 1e9;
                last = now;

                groundY = ground.getGroundY() - groundH - 80;

                double groundWidth = scene.getWidth();
                double groundSpeed = Spawner.getSpeed();

                damageTimer += deltatime;

                if (damageTimer >= 2.0) {
                    player.takeDamageByTime();
                    damageTimer = 0;
                }

                hpzone.updateHpBar(deltatime);

                player.update(deltatime);          // physics + movement
                player.getCookie().update(deltatime);
//                pet.getView().layoutYProperty().bind(player.getCookie().layoutYProperty().add(30));
                if(player.isCooldownable()){
                    double progress = player.getCooldownProgress();
                    cdBar.fill.setWidth(80 * progress);
                    cdBar.fill.setVisible(true);
                }else{
                    cdBar.fill.setVisible(false);
                    cdBar.frame.setVisible(false);
                    cdBar.background.setVisible(false);
                }

                if(player.isSpeeding()) {
                    flame.setVisible(true);
                    if(player.getCookie().getAnimationState()== AnimationType.SLIDE) {
                        flame.setRotate(270);
                        flame.setTranslateX(-flameH/2-100);
                        flame.setTranslateY(-flameW/2+100);
                    } else {
                        flame.setRotate(0);
                        flame.setTranslateX(-flameW/2-50);
                        flame.setTranslateY(-flameH/2-50);
                    }
                    flame.update(deltatime);
                } else {
                    flame.setVisible(false);
                    flame.restart();
                }

                petCooldownTimer -= deltatime;
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

                    //System.out.println(player.getCookie().getLayoutY());
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

                pet.update(deltatime);

                spawner.update(now, deltatime);

                if (shiftHeld && player.isOnGround()) {
                    player.slide();
                    //System.out.println("slide");
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

                        pearl.update(deltatime);

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
                        croissant.vy += gravity * deltatime;

                        view.setTranslateY(view.getTranslateY() + croissant.vy * deltatime);

                        double bottom = view.getTranslateY() + view.getBoundsInLocal().getHeight();

                        if (bottom >= GameplayScene.groundY) {

                            view.setTranslateY(GameplayScene.groundY - view.getBoundsInLocal().getHeight());

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

                    tomyum.updateSkill(deltatime);

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
            if(player.isDead()) return;

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
            if(player.isDead()) return;
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
        isUpdate = false;
        bg.stop();
        ground.stop();
    }

    public void resumeGameByBool() {
        isUpdate = true;
        bg.start();
        ground.start();
    }

    public void stopEnvironment() {
        Spawner.setSpeed(0);
        bg.stop();
        ground.stop();
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean upadate) {
        isUpdate = upadate;
    }

    public double getDeltatime() {
        return deltatime;
    }

    public void setDeltatime(double dt) {
        this.deltatime = dt;
    }
}

