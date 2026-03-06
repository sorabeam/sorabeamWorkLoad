package Main.ObjectInGame;

import Main.Cookies.Cookie;
import Main.MediaPlayer;
import Main.Cookies.CrossiantCookie;
import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Items.*;
import Main.ObjectInGame.Jelly.BaseJelly;
import Main.ObjectInGame.Jelly.JellyView;
import Main.ObjectInGame.Obstacle.BaseObstacle;
import Main.ObjectInGame.Obstacle.ObstacleView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Spawner {

    private final Pane gameLayer;
    private final double sceneWidth;
    private final double sceneHeight;
    private final Cookie cookie;
    private static final double DEFAULT_SPEED = -350;
    private static double speed = DEFAULT_SPEED;
    private int begin = 0;

    private final List<List<SpawnAction>> spawnSets = SpawnerLayout.getSpawnLayout();

    private int currentSetIndex = 0;
    private int currentActionIndex = 0;
    private long lastSpawnTime = 0;

    private final int[] tutorialPatterns = {3,4,9};

    public Spawner(Pane gameLayer, double sceneWidth, double sceneHeight, Cookie cookie) {
        this.gameLayer = gameLayer;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.cookie = cookie;
    }

    public void update(long now, double deltaTime) {
        spawnBySet(now);
        updateObstacles(deltaTime);
        updateItem(deltaTime);
        updateJelly(deltaTime);
        checkCollision(cookie);

        if (cookie instanceof CrossiantCookie croissant) {

            if (croissant.isCroissantReady()) {

                CroissantType type = croissant.consumeCroissant();
                spawnCroissant(type);
            }
        }
    }

    private void spawnBySet(long now) {
        List<SpawnAction> set = spawnSets.get(currentSetIndex);

        if (currentActionIndex >= set.size()) {

            if(begin < tutorialPatterns.length){
                currentSetIndex = tutorialPatterns[begin];
                begin++;
            }
            else{
                int level = GameLogic.getMap();

                int min = (level - 1) * 4;
                int max = spawnSets.size() - 1 - ((3 - level) * 5);

                currentSetIndex = (int)(Math.random() * (max - min + 1)) + min;
            }
            System.out.println(currentSetIndex);
            currentActionIndex = 0;
            lastSpawnTime = now;
            return;
        }

        SpawnAction action = set.get(currentActionIndex);

        if (lastSpawnTime == 0) {
            lastSpawnTime = now;
            return;
        }

        if (now - lastSpawnTime < action.delay) return;

        lastSpawnTime = now;

        if (action.type == SpawnAction.Type.OBSTACLE) {
            int level = GameLogic.getMap();

            String name = action.name;

            //replace map number
            name = name.replaceAll("Obs_\\d+_", "Obs_" + level + "_");

            ObstacleView obs = new ObstacleView(
                    new BaseObstacle(name),
                    speed,
                    0
            );
            obs.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            obs.setTranslateY(action.height);

            gameLayer.getChildren().add(obs);

        } else if (action.type == SpawnAction.Type.ITEM){
            ItemView item;
            if(Objects.equals(action.name, "BigHealingPotion")) {
                item = new ItemView(
                        new BigHealingPotion(),
                        speed,
                        0
                );
            } else if(Objects.equals(action.name, "SpeedBoost")) {
                item = new ItemView(
                        new SpeedBoost(),
                        speed,
                        0
                );
            } else if(Objects.equals(action.name, "Magnetic")) {
                item = new ItemView(
                        new Magnetic(),
                        speed,
                        0
                );
            } else {
                item = new ItemView(
                        new HealingPotion(),
                        speed,
                        0
                );
            }
            item.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            item.setTranslateY(action.height);
            gameLayer.getChildren().add(item);
        } else {
            JellyView jelly = new JellyView(
                    new BaseJelly(action.name),
                    speed,
                    0
            );
            jelly.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            jelly.setTranslateY(action.height);
            gameLayer.getChildren().add(jelly);
        }

        currentActionIndex++;
    }

    private void updateObstacles(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ObstacleView o) {
                //set speed
                o.setSpeed(getSpeed(), 0);
                o.update(deltaTime);

                if (o.getTranslateX() < -200 ||
                        o.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    private void updateItem(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ItemView i) {
                i.setSpeed(getSpeed(), 0);
                if (i.getItem() instanceof Croissant croissant) {

                    croissant.updatePhysics(
                            deltaTime,
                            i,
                            gameLayer.getLayoutBounds().getHeight() - 80
                    );
                } else {
                    i.update(deltaTime);
                }

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    private void updateJelly(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof JellyView i) {
                //update speed
                if(cookie.isMagnetic()) {
                    i.pullToPlayer(cookie, deltaTime);
                } else {
                    i.setSpeedX(getSpeed());
                }

                i.update(deltaTime);

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    private void spawnCroissant(CroissantType type) {

        BaseItem croissant = new Croissant(type, speed);

        ItemView view = new ItemView(croissant, speed, 0);

        view.setTranslateX(gameLayer.getLayoutBounds().getWidth() - 200);
        view.setTranslateY(-50);

        gameLayer.getChildren().add(view);
    }

    public void spawnIngredientRain(){

        String[] ingredients = {
                "ShrimpJelly",
                "GalangalJelly",
                "LemongrassJelly",
                "KaffirLeafJelly"
        };

        for(int i=0;i<20;i++){

            String type = ingredients[(int)(Math.random()*ingredients.length)];

            JellyView jelly = new JellyView(
                    new BaseJelly(type),
                    speed,
                    0
            );

            jelly.setFalling(true);

            jelly.setTranslateX(gameLayer.getLayoutBounds().getWidth() + Math.random()*300);
            jelly.setTranslateY(0 - Math.random()*200);

            gameLayer.getChildren().add(jelly);
        }
    }

    private void checkCollision(Cookie cookie) {

        List<javafx.scene.Node> snapshot =
                new ArrayList<>(gameLayer.getChildren());

        List<javafx.scene.Node> toRemove = new ArrayList<>();

        for (javafx.scene.Node node : snapshot) {

            if (node instanceof ObstacleView obs) {
                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(obs.getBoundsInParent())
                        && !cookie.isInvincible()) {

                    obs.getObstacle().interact(cookie);
                    toRemove.add(node);
                }
            }

            else if (node instanceof ItemView item) {
                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(item.getBoundsInParent())) {

                    item.getItem().interact(cookie);
                    MediaPlayer.getInstance().playSFX("Item");
                    toRemove.add(node);
                }
            }

            else if (node instanceof JellyView jelly) {
                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(jelly.getBoundsInParent())) {

                    jelly.getJelly().interact(cookie);

                    if (cookie instanceof CrossiantCookie croissant) {
                        croissant.onJellyCollected();
                    }

                    MediaPlayer.getInstance().playSFX("Jelly");
                    toRemove.add(node);
                }
            }
        }

        gameLayer.getChildren().removeAll(toRemove);
    }

    public static void setSpeed(double speed) {
        Spawner.speed = speed;
    }

    public static double getSpeed() {
        return speed;
    }

    public static void resetSpeed() {
        Spawner.speed = DEFAULT_SPEED;
    }

    public static double getDefaultSpeed() {
        return DEFAULT_SPEED;
    }
}