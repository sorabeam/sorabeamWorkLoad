package Pors.ObjectInGame;

import Beam.Cookies.Cookie;
import Beam.Media.MediaPlayer;
import Beam.Pets.Pet;
import Beam.Cookies.CrossiantCookie;
import Got.GameLogic.GameLogic;
import Pors.ObjectInGame.Items.*;
import Pors.ObjectInGame.Jelly.BaseJelly;
import Pors.ObjectInGame.Jelly.JellyView;
import Pors.ObjectInGame.Obstacle.BaseObstacle;
import Pors.ObjectInGame.Obstacle.ObstacleView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Pors.ObjectInGame.SpawnerLayout.level;

public class Spawner {

    private Pane gameLayer;
    private double sceneWidth;
    private double sceneHeight;
    private Cookie cookie;
    private static double defaultSpeed = -350;
    private static double speed = defaultSpeed;
    private Pet pet;
    private int begin = 0;

    private long lastUpdateTime = 0;

    //private AnimationTimer timer;

    private List<List<SpawnAction>> spawnSets = SpawnerLayout.getSpawnLayout();

    //private int currentSetIndex = spawnSets.size() - 1;
    private int currentSetIndex = 0;
    private int currentActionIndex = 0;
    private long lastSpawnTime = 0;

    public Spawner(Pane gameLayer, double sceneWidth, double sceneHeight, Cookie cookie, Pet pet) {
        this.gameLayer = gameLayer;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.cookie = cookie;
        this.pet = pet;
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
        /*if (currentSetIndex >= spawnSets.size()) {

            currentSetIndex = (int)(Math.random() * spawnSets.size());
            currentActionIndex = 0;
            lastSpawnTime = now;
            return;
        }*/
        List<SpawnAction> set = spawnSets.get(currentSetIndex);

        if (currentActionIndex >= set.size()) {

            if(begin == 0){
                currentSetIndex = 0;
                begin++;
            }
            else if(begin == 1){
                currentSetIndex = 3;
                begin++;
            }
            else if(begin == 2){
                currentSetIndex = 4;
                begin++;
            }
            else if(begin == 3){
                currentSetIndex = 9;
                begin++;
            }
            else{
                int min = (level - 1) * 4;
                int max = spawnSets.size() - 1 - ((3 - level) * 2);
                currentSetIndex = (int)(Math.random() * (max - min + 1)) + min;
            }

            System.out.println("New Pattern: " + currentSetIndex);

            currentActionIndex = 0;
            lastSpawnTime = now;
            return;
        }

        //List<SpawnAction> set = spawnSets.get(currentSetIndex);
        SpawnAction action = set.get(currentActionIndex);

        if (lastSpawnTime == 0) {
            lastSpawnTime = now;
            return;
        }

        if (now - lastSpawnTime < action.delay) return;

        lastSpawnTime = now;

        //double y = 650;

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
            obs.setTranslateX(sceneWidth + 50);
            obs.setTranslateY(action.height);

            gameLayer.getChildren().add(obs);

        } else if (action.type == SpawnAction.Type.ITEM){
            ItemView item = null;
            if(action.name=="Magnetic") {
                item = new ItemView(
                        new Magnetic(),
                        speed,
                        0
                );
            } else {
                item = new ItemView(
                        new HealingPotion(action.name),
                        speed,
                        0
                );
            }
            item.setTranslateX(sceneWidth + 50);
            item.setTranslateY(action.height);
            gameLayer.getChildren().add(item);
        } else {
            JellyView jelly = new JellyView(
                    new BaseJelly(action.name),
                    speed,
                    0
            );
            jelly.setTranslateX(sceneWidth + 50);
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
                        o.getTranslateY() > sceneHeight + 100) {
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
                            sceneHeight - 80
                    );
                } else {
                    i.update(deltaTime);
                }

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > sceneHeight + 100) {
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
                        i.getTranslateY() > sceneHeight + 100) {
                    it.remove();
                }
            }
        }
    }

    private void spawnCroissant(CroissantType type) {

        BaseItem croissant = new Croissant(type, speed);

        ItemView view = new ItemView(croissant, speed, 0);

        // เริ่มจากฟ้า
        view.setTranslateX(sceneWidth - 200);
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

            jelly.setTranslateX(sceneWidth + Math.random()*300);
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
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(obs.getBoundsInParent())
                        && !cookie.isInvincible()) {

                    cookie.takeDamage(obs.getDamage());
                    toRemove.add(node);
                }
            }

            else if (node instanceof ItemView item) {
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(item.getBoundsInParent())) {

                    item.getItem().interact(cookie);
                    MediaPlayer.getInstance().playSFX("Item");
                    toRemove.add(node);
                }
            }

            else if (node instanceof JellyView jelly) {
                if (cookie.getHitbox()
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
        Spawner.speed = defaultSpeed;
    }

    public static double getDefaultSpeed() {
        return defaultSpeed;
    }

    /*public void stop() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }

        lastUpdateTime = 0;
        lastSpawnTime = 0;
    }*/
}