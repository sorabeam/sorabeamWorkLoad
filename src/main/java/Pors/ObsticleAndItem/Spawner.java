package Pors.ObsticleAndItem;

import Beam.Cookies.Cookie;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import Pors.ObsticleAndItem.Items.HealingPotion;

import java.util.Iterator;
import java.util.Random;

public class Spawner {

    private Pane gameLayer;
    private double sceneWidth;
    private double sceneHeight;
    private Cookie cookie;
    private double speed = -2.5;
    //private Random random = new Random();

    //Obstacle
    private long lastObstacleSpawn = 0;
    private long[] obstacleSpawnTimes = {1_000_000_000L, 3_000_000_000L, 3_000_000_000L, 3_000_000_000L};
    private int obstacleSpawnStep = 0;
    private boolean obstacleSpawnFinished = false;

    private String[] obsList = {"Obs1", "Obs1", "Obs1", "Obs1"};
    private int obsListIndex = 0;

    //Item
    private long lastItemSpawn = 0;
    private long[] itemSpawnTimes = {2_000_000_000L, 4_000_000_000L};
    private int itemSpawnStep = 0;
    private boolean itemSpawnFinished = false;

    private String[] itemList = {"HealingPotion", "HealingPotion"};
    private int itemListIndex = 0;

    public Spawner(Pane gameLayer, double sceneWidth, double sceneHeight, Cookie cookie) {
        this.gameLayer = gameLayer;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.cookie = cookie;
    }

    public void start() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                spawnObstacle(now);
                updateObstacles();
                spawnItem(now);
                updateItem();
                checkCollision(cookie);
            }
        };
        timer.start();
    }

    private void spawnObstacle(long now) {

        if (obstacleSpawnFinished) return;

        if (lastObstacleSpawn == 0) {
            lastObstacleSpawn = now;
            return;
        }

        if (obstacleSpawnStep >= obstacleSpawnTimes.length) {
            obstacleSpawnFinished = true;
            return;
        }

        long waitTime = obstacleSpawnTimes[obstacleSpawnStep];

        if (now - lastObstacleSpawn < waitTime) return;

        lastObstacleSpawn = now;

        double y = 625;

        ObstacleView obs = new ObstacleView(
                new BaseObstacle(obsList[obsListIndex], 10),
                speed,
                0
        );

        obs.setTranslateX(sceneWidth + 50);
        obs.setTranslateY(y);

        gameLayer.getChildren().add(obs);

        obstacleSpawnStep++;
        obsListIndex++;
    }

    private void updateObstacles() {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ObstacleView o) {
                o.update();

                if (o.getTranslateX() < -100 ||
                        o.getTranslateY() > sceneHeight + 100) {
                    it.remove();
                }
            }
        }
    }

    private void spawnItem(long now) {

        if (itemSpawnFinished) return;

        if (lastItemSpawn == 0) {
            lastItemSpawn = now;
            return;
        }

        if (itemSpawnStep >= itemSpawnTimes.length) {
            itemSpawnFinished = true;
            return;
        }

        long waitTime = itemSpawnTimes[itemSpawnStep];

        if (now - lastItemSpawn < waitTime) return;

        lastItemSpawn = now;

        double y = 650;

        ItemView item = new ItemView(
                new HealingPotion(itemList[itemListIndex], 100),
                speed,
                0
        );

        item.setTranslateX(sceneWidth + 50);
        item.setTranslateY(y);

        gameLayer.getChildren().add(item);

        itemSpawnStep++;
    }

    private void updateItem() {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ItemView i) {
                i.update();

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > sceneHeight + 100) {
                    it.remove();
                }
            }
        }
    }

    private void checkCollision(Cookie cookie) {

        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();

        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            //Obstacle
            if (node instanceof ObstacleView obs) {
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(obs.getBoundsInParent())) {
                    cookie.takeDamage(obs.getDamage());
                    it.remove();
                }
            }
            //Item
            if (node instanceof ItemView item) {
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(item.getBoundsInParent())) {
                    item.getItem().interact(cookie);
                    it.remove();
                }
            }
        }
    }
}