package Pors.ObjectInGame;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Items.ItemView;
import Pors.ObjectInGame.Jelly.BaseJelly;
import Pors.ObjectInGame.Jelly.JellyView;
import Pors.ObjectInGame.Obstacle.BaseObstacle;
import Pors.ObjectInGame.Obstacle.ObstacleView;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import Pors.ObjectInGame.Items.HealingPotion;

import java.util.Iterator;
import java.util.List;

public class Spawner {

    private Pane gameLayer;
    private double sceneWidth;
    private double sceneHeight;
    private Cookie cookie;
    private double speed = -350;

    private long lastUpdateTime = 0;

    private List<List<SpawnAction>> spawnSets = List.of(
            //SET1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 2_000_000_000L, "Jelly1", 100,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,525),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 0L, "ObsTest", 10,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100,525),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 0L, "ObsTest", 10,650),
                    new SpawnAction(SpawnAction.Type.ITEM,     2_000_000_000L, "HealingPotion", 100,650)
            ),

            //SET2
            List.of(
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 1_000_000_000L, "ObsTest", 15,650),
                    new SpawnAction(SpawnAction.Type.ITEM,     1_000_000_000L, "HealingPotion", 50,650),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "ObsTest", 20,650),
                    new SpawnAction(SpawnAction.Type.JELLY, 2_000_000_000L, "Jelly1", 100,650)
            )
    );

    private int currentSetIndex = 0;
    private int currentActionIndex = 0;
    private long lastSpawnTime = 0;

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
                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }

                double deltaTime = (now - lastUpdateTime) / 1_000_000_000.0;
                lastUpdateTime = now;

                spawnBySet(now);
                updateObstacles(deltaTime);
                updateItem(deltaTime);
                updateJelly(deltaTime);
                checkCollision(cookie);
            }
        };
        timer.start();
    }

    private void spawnBySet(long now) {

        if (currentSetIndex >= spawnSets.size()) {
            currentSetIndex = 0;
            currentActionIndex = 0;
            lastSpawnTime = now;
            return;
        }

        List<SpawnAction> set = spawnSets.get(currentSetIndex);

        if (currentActionIndex >= set.size()) {
            // set จบ → ไป set ถัดไป
            currentSetIndex++;
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

        //double y = 650;

        if (action.type == SpawnAction.Type.OBSTACLE) {
            ObstacleView obs = new ObstacleView(
                    new BaseObstacle(action.name, action.value),
                    speed,
                    0
            );
            obs.setTranslateX(sceneWidth + 50);
            obs.setTranslateY(action.height);
            gameLayer.getChildren().add(obs);

        } else if (action.type == SpawnAction.Type.ITEM){
            ItemView item = new ItemView(
                    new HealingPotion(action.name, action.value),
                    speed,
                    0
            );
            item.setTranslateX(sceneWidth + 50);
            item.setTranslateY(action.height);
            gameLayer.getChildren().add(item);
        } else {
            JellyView jelly = new JellyView(
                    new BaseJelly(action.name, action.value),
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
                o.update(deltaTime);

                if (o.getTranslateX() < -100 ||
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
                i.update(deltaTime);

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
                i.update(deltaTime);

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
            else if (node instanceof ItemView item) {
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(item.getBoundsInParent())) {
                    item.getItem().interact(cookie);
                    it.remove();
                }
            }
            //Jelly
            else if (node instanceof JellyView jelly) {
                if (cookie.getHitbox()
                        .getBoundsInParent()
                        .intersects(jelly.getBoundsInParent())) {
                    jelly.getJelly().interact(cookie);
                    it.remove();
                }
            }
        }
    }
}