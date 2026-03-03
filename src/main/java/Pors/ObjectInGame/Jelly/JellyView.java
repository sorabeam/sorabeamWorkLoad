package Pors.ObjectInGame.Jelly;

import Pors.ObjectInGame.Items.BaseItem;
import Pors.ObjectInGame.Obstacle.BaseObstacle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class JellyView extends ImageView {

    private BaseJelly jelly;
    private double vx;
    private double vy;

    public JellyView(BaseJelly jelly, double vx, double vy) {
        this.jelly = jelly;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Jelly/" + jelly.getName() + ".png"));
        setFitWidth(50);
        setFitHeight(50);
    }

    public void update(double deltaTime) {
        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);
    }

    public int getScore() {
        return jelly.getScore();
    }

    public BaseJelly getJelly() {
        return jelly;
    }
}