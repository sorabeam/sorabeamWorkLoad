package Pors.ObjectInGame.Obstacle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ObstacleView extends ImageView {

    private BaseObstacle obstacle;
    private double vx;
    private double vy;

    public ObstacleView(BaseObstacle obstacle, double vx, double vy) {
        this.obstacle = obstacle;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Obstacle/" + obstacle.getName() + ".png"));
        if(Objects.equals(obstacle.getName(), "Obs_1_1"))
        {
            setFitWidth(150);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_1_2"))
        {
            //setFitWidth(200);
            //setFitHeight(400);
            setFitWidth(125);
            setFitHeight(250);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_1_3"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
            //System.out.println(getFitHeight());
        }
        else if(Objects.equals(obstacle.getName(), "Obs_1_4"))
        {
            setFitWidth(200);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_2_1"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_2_2"))
        {
            //setFitWidth(200);
            //setFitHeight(400);
            setFitWidth(125);
            setFitHeight(250);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_2_3"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_2_4"))
        {
            setFitWidth(200);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_3_1"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_3_2"))
        {
            //setFitWidth(200);
            //setFitHeight(400);
            setFitWidth(125);
            setFitHeight(250);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_3_3"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_3_4"))
        {
            setFitWidth(200);
            setPreserveRatio(true);
        }
        else
        {
            setFitWidth(60);
            setPreserveRatio(true);
        }
    }

    public void update(double deltaTime) {
        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);
    }

    public int getDamage() {
        return obstacle.getDamage();
    }

    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}