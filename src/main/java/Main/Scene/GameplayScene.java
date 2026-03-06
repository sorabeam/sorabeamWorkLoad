package Main.Scene;

import Main.Animation.AnimateEffect;
import Main.Animation.AnimationType;
import Main.Asset;
import Main.CharacterData;
import Main.Cookies.Cookie;
import Main.Cookies.CrossiantCookie;
import Main.Cookies.TomYumCookie;
import Main.Pets.Pet;
import Main.UI.InGameUI.*;
import Main.ObjectInGame.Items.Croissant;
import Main.ObjectInGame.Items.CroissantType;
import Main.ObjectInGame.Items.ItemView;
import Main.ObjectInGame.Items.Pearl;
import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Obstacle.ObstacleView;
import Main.ObjectInGame.Spawner;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Main gameplay scene of the game.
 *
 * This scene manages the player, pet, background, ground, UI,
 * spawning system, collision-related updates, and the main
 * real-time game loop.
 */
public class GameplayScene extends BaseScene {

    /**
     * Layer containing gameplay objects such as the player,
     * items, obstacles, jellies, and effects.
     */
    Pane gameLayer = new Pane();

    /**
     * Layer containing in-game UI elements.
     */
    StackPane uiLayer = new StackPane();

    /**
     * Moving ground used during gameplay.
     */
    private MoveGround ground;

    /**
     * Indicates whether the slide key is currently being held.
     */
    private boolean shiftHeld = false;

    /**
     * UI section for game settings.
     */
    SettingZone settingZone = new SettingZone(this, spacer('H'));

    /**
     * UI section displaying player health.
     */
    HpDisplayZone hpZone = new HpDisplayZone();

    /**
     * UI element displaying the current score.
     */
    ShowScore sc = new ShowScore();

    /**
     * Vertical offset used when calculating the ground position.
     */
    private final double groundH = -150;

    /**
     * Current Y position of the ground used by gameplay objects.
     */
    public static double groundY;

    /**
     * Main animation timer used for the game loop.
     */
    private final AnimationTimer timer;

    /**
     * Spawner responsible for generating game objects during gameplay.
     */
    private final Spawner spawner;

    /**
     * Controls whether the update loop should continue processing.
     */
    public boolean isUpdate = true;

    /**
     * Background used in the gameplay scene.
     */
    private final InGameBG bg = new InGameBG(root);

    /**
     * Time elapsed between frames.
     */
    private double deltatime;

    /**
     * Creates the gameplay scene and initializes the player,
     * pet, UI, background, ground, spawner, input handling,
     * and the main update loop.
     */
    public GameplayScene() {
        super();

        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.LIME);
        shadow.setRadius(20);

        root.getChildren().add(bg);

        uiLayer.getChildren().addAll(
                settingZone,
                hpZone,
                sc
        );

        GameLogic.setHpBar(hpZone);

        StackPane.setAlignment(sc, Pos.CENTER_RIGHT);
        StackPane.setMargin(sc, new Insets(200, 0, 0, 0));
        StackPane.setAlignment(hpZone, Pos.TOP_CENTER);

        settingZone.setMaxWidth(50);
        StackPane.setAlignment(settingZone, Pos.TOP_RIGHT);
        StackPane.setMargin(settingZone, new Insets(20, 20, 0, 0));

        Cookie player = CharacterData.getCurrent_Cookie();
        Pet pet = CharacterData.getCurrent_Pet();
        AnimateEffect flame = new AnimateEffect(Asset.getImage("FireSpriteSheet"), 125, 125, 9, 4, 0.3);
        flame.setLoop(true);

        spawner = new Spawner(gameLayer, player);

        Spawner.setSpeed(-350);

        ground = new MoveGround(gameLayer);
        gameLayer.getChildren().add(ground);
        ground.start();

        player.setGameLayer(gameLayer);
        player.createCookie();

        CooldownBar cdBar = new CooldownBar(player);

        gameLayer.getChildren().addAll(
                flame,
                player.getCookie(),
                player.getHitBox(),
                cdBar
        );

        pet.getView().setFitWidth(80);
        pet.getView().setFitHeight(80);
        gameLayer.getChildren().add(pet.getView());

        player.getCookie().setFitWidth(200);
        player.getCookie().setFitHeight(200);
        player.getCookie().setLayoutX(200);

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

        /**
         * Main game loop that updates movement, effects, pet behavior,
         * spawned objects, collisions, and special character skills.
         */
        timer = new AnimationTimer() {

            long last = 0;
            double petCooldownTimer = pet.getCooldownTime() / 1000.0;
            double tarPetPosY = 0;
            double damageTimer;

            /**
             * Updates the game state for the current frame.
             *
             * @param now the current timestamp in nanoseconds
             */
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

                damageTimer += deltatime;
                if (damageTimer >= 2.0) {
                    player.takeDamageByTime();
                    damageTimer = 0;
                }

                hpZone.updateHpBar(deltatime);

                player.update(deltatime);
                player.getCookie().update(deltatime);

                if (player.isHasCooldown()) {
                    double progress = player.getCooldownProgress();
                    cdBar.fill.setWidth(80 * progress);
                    cdBar.fill.setVisible(true);
                } else {
                    cdBar.fill.setVisible(false);
                    cdBar.frame.setVisible(false);
                    cdBar.background.setVisible(false);
                }

                if (player.isSpeeding()) {
                    flame.setVisible(true);
                    if (player.getCookie().getAnimationState() == AnimationType.SLIDE) {
                        flame.setRotate(270);
                        flame.setTranslateX(-flameH / 2 - 100);
                        flame.setTranslateY(-flameW / 2 + 100);
                    } else {
                        flame.setRotate(0);
                        flame.setTranslateX(-flameW / 2 - 50);
                        flame.setTranslateY(-flameH / 2 - 50);
                    }
                    flame.update(deltatime);
                } else {
                    flame.setVisible(false);
                    flame.restart();
                }

                petCooldownTimer -= deltatime;
                if (petCooldownTimer <= 0) {
                    pet.useSkill();
                    petCooldownTimer = pet.getCooldownTime() / 1000.0;
                }

                if (pet.isUsingSkill()) {
                    double tarPetPosX = Math.max(player.getCookie().getLayoutX() + 100, getWidth() - 100);

                    if (tarPetPosY == 0) {
                        tarPetPosY = Math.max(400, player.getCookie().getLayoutY());
                        pet.getView().setEffect(shadow);
                    }

                    pet.setTargetPos(tarPetPosX, tarPetPosY);

                    if (pet.hasArrived()) {
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
                    double tarPetPosX = player.getCookie().getLayoutX() - 30;
                    double tarPetPosY = player.getCookie().getLayoutY() + 30;
                    pet.setTargetPos(tarPetPosX, tarPetPosY);
                }

                pet.update(deltatime);

                spawner.update(now, deltatime);

                if (shiftHeld && player.isOnGround()) {
                    player.slide();
                }

                List<Node> toRemove = new ArrayList<>();

                if (player instanceof CrossiantCookie) {
                    CrossiantCookie croissant = (CrossiantCookie) player;
                    if (croissant.isCroissantReady()) {
                        CroissantType type = croissant.consumeCroissant();
                    }
                }

                for (Node node : gameLayer.getChildren()) {
                    if (node instanceof Pearl) {
                        Pearl pearl = (Pearl) node;

                        pearl.update(deltatime);

                        for (Node other : gameLayer.getChildren()) {
                            if (other instanceof ObstacleView) {
                                ObstacleView obstacle = (ObstacleView) other;
                                if (pearl.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                                    GameLogic.addScore(7000);
                                    toRemove.add(pearl);
                                    toRemove.add(obstacle);
                                    break;
                                }
                            }
                        }

                        if (pearl.getLayoutX() > getWidth() - 50) {
                            toRemove.add(pearl);
                        }
                    }
                }

                for (Node node : gameLayer.getChildren()) {
                    if (node instanceof ItemView) {
                        ItemView view = (ItemView) node;

                        if (view.getItem() instanceof Croissant) {
                            Croissant croissant = (Croissant) view.getItem();

                            double gravity = 1500;
                            double bouncePower = -700;

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
                }

                if (player instanceof TomYumCookie) {
                    TomYumCookie tomyum = (TomYumCookie) player;

                    tomyum.updateSkill(deltatime);

                    if (tomyum.isSkillReady()) {
                        spawner.spawnIngredientRain();
                        tomyum.consumeRain();
                    }
                }

                gameLayer.getChildren().removeAll(toRemove);
            }
        };

        timer.start();

        setOnKeyPressed(e -> {
            if (player.isDead()) return;

            switch (e.getCode()) {
                case SPACE: {
                    player.jump();
                    break;
                }
                case SHIFT: {
                    shiftHeld = true;
                    player.slide();
                    break;
                }
            }
        });

        setOnKeyReleased(e -> {
            if (player.isDead()) return;
            if (e.getCode() == KeyCode.SHIFT) {
                shiftHeld = false;
                player.upFromSlide();
            }
        });

        setFocusTraversable(true);
        Platform.runLater(this::requestFocus);
    }

    /**
     * Stops the gameplay timer and background animation completely.
     */
    public void stopGame() {
        if (timer != null) {
            timer.stop();
            bg.stop();
        }
    }

    /**
     * Stops gameplay updates by disabling the update loop
     * and stopping the background and ground movement.
     */
    public void stopGameByBool() {
        isUpdate = false;
        bg.stop();
        ground.stop();
    }

    /**
     * Resumes gameplay updates by enabling the update loop
     * and restarting the background and ground movement.
     */
    public void resumeGameByBool() {
        isUpdate = true;
        bg.start();
        ground.start();
    }

    /**
     * Stops environment movement by setting the spawner speed to zero
     * and stopping the background and ground.
     */
    public void stopEnvironment() {
        Spawner.setSpeed(0);
        bg.stop();
        ground.stop();
    }

    /**
     * Returns whether the gameplay scene is currently updating.
     *
     * @return true if updates are active, otherwise false
     */
    public boolean isUpdate() {
        return isUpdate;
    }

    /**
     * Sets whether the gameplay scene should continue updating.
     *
     * @param upadate true to enable updates, otherwise false
     */
    public void setUpdate(boolean upadate) {
        isUpdate = upadate;
    }

    /**
     * Returns the latest frame delta time.
     *
     * @return the delta time in seconds
     */
    public double getDeltatime() {
        return deltatime;
    }

    /**
     * Sets the frame delta time.
     *
     * @param dt the new delta time value
     */
    public void setDeltatime(double dt) {
        this.deltatime = dt;
    }
}