package supakorn.ObsticleAndItem;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import supakorn.ObsticleAndItem.Items.HealingPotion;

import java.util.Iterator;
import java.util.Random;

public class Spawner {

    private Pane gameLayer;
    private double sceneWidth;
    private double sceneHeight;
    private Random random = new Random();

    private long lastObstacleSpawn = 0;
    private long lastItemSpawn = 0;

    private long obstacleSpawnInterval = 1_500_000_000L;
    private long itemSpawnInterval = 3_000_000_000L;

    public Spawner(Pane gameLayer, double sceneWidth, double sceneHeight) {
        this.gameLayer = gameLayer;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    public void start() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                spawnObstacle(now);
                updateObstacles();
                spawnItem(now);
                updateItem();
            }
        };
        timer.start();
    }

    private void spawnObstacle(long now) {
        if (now - lastObstacleSpawn < obstacleSpawnInterval) return;
        lastObstacleSpawn = now;

        //double y = random.nextDouble(sceneHeight - 100);
        double y = 650; //<================================= position y

        ObstacleView obs = new ObstacleView(
                new BaseObstacle("Obs1", 10),
                -1, // speed of object
                0);

        obs.setTranslateX(sceneWidth + 50);
        obs.setTranslateY(y);

        gameLayer.getChildren().add(obs);
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
        if (now - lastItemSpawn < itemSpawnInterval) return;
        lastItemSpawn = now;

        double y = 650;

        ItemView item = new ItemView(
                new HealingPotion("HealingPotion",100),
                -1,
                0
        );

        item.setTranslateX(sceneWidth + 50);
        item.setTranslateY(y);

        gameLayer.getChildren().add(item);
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
}