package Pors.ObsticleAndItem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObstacleView extends ImageView {

    private BaseObstacle obstacle;
    private double vx;
    private double vy;

    public ObstacleView(BaseObstacle obstacle, double vx, double vy) {
        this.obstacle = obstacle;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Obstacle/" + obstacle.getName() + ".png"));
        setFitWidth(150);
        setFitHeight(200);
    }

    public void update() {
        setTranslateX(getTranslateX() + vx);
        setTranslateY(getTranslateY() + vy);
    }

    public int getDamage() {
        return obstacle.getDamage();
    }
}