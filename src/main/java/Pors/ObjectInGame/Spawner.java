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
    private double speed = -2.5;

    private List<List<SpawnAction>> spawnSets = List.of(
            //SET1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 2_000_000_000L, "Jelly1", 100),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 100),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 1_000_000_000L, "ObsTest", 10),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 3_000_000_000L, "ObsTest", 10),
                    new SpawnAction(SpawnAction.Type.ITEM,     2_000_000_000L, "HealingPotion", 100)
            ),

            //SET2
            List.of(
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 1_000_000_000L, "ObsTest", 15),
                    new SpawnAction(SpawnAction.Type.ITEM,     1_000_000_000L, "HealingPotion", 50),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "ObsTest", 20),
                    new SpawnAction(SpawnAction.Type.JELLY, 2_000_000_000L, "Jelly1", 100)
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
                spawnBySet(now);
                //spawnObstacle(now);
                updateObstacles();
                //spawnItem(now);
                updateItem();
                updateJelly();
                checkCollision(cookie);
            }
        };
        timer.start();
    }

    private void spawnBySet(long now) {

        if (currentSetIndex >= spawnSets.size()) return;

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

        double y = 650;

        if (action.type == SpawnAction.Type.OBSTACLE) {
            ObstacleView obs = new ObstacleView(
                    new BaseObstacle(action.name, action.value),
                    speed,
                    0
            );
            obs.setTranslateX(sceneWidth + 50);
            obs.setTranslateY(y);
            gameLayer.getChildren().add(obs);

        } else if (action.type == SpawnAction.Type.ITEM){
            ItemView item = new ItemView(
                    new HealingPotion(action.name, action.value),
                    speed,
                    0
            );
            item.setTranslateX(sceneWidth + 50);
            item.setTranslateY(y);
            gameLayer.getChildren().add(item);
        } else {
            JellyView jelly = new JellyView(
                    new BaseJelly(action.name, action.value),
                    speed,
                    0
            );
            jelly.setTranslateX(sceneWidth + 50);
            jelly.setTranslateY(y);
            gameLayer.getChildren().add(jelly);
        }

        currentActionIndex++;
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

    private void updateItem() {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ItemView i) {
                if(i.getTranslateY() >= sceneHeight-180) {
                    i.setSpeed(getSpeed(), 0);
                }
                i.update();

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > sceneHeight + 100) {
                    it.remove();
                }
            }
        }
    }

    private void updateJelly() {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof JellyView i) {
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

    public double getSpeed() {
        return speed;
    }
}