package Main.ObjectInGame.Obstacle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents the visual representation of an obstacle in the game.
 *
 * This class extends ImageView and is responsible for rendering the
 * obstacle image and updating its movement based on velocity.
 */
public class ObstacleView extends ImageView {

    /**
     * The logical obstacle associated with this view.
     */
    private BaseObstacle obstacle;

    /**
     * Horizontal velocity of the obstacle.
     */
    private double vx;

    /**
     * Vertical velocity of the obstacle.
     */
    private double vy;

    /**
     * Creates an ObstacleView for the specified obstacle with initial velocity.
     * The image and display size are determined based on the obstacle name.
     *
     * @param obstacle the obstacle associated with this view
     * @param vx the horizontal velocity
     * @param vy the vertical velocity
     */
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
            setFitWidth(125);
            setFitHeight(250);
        }
        else if(Objects.equals(obstacle.getName(), "Obs_1_3"))
        {
            setFitWidth(103);
            setPreserveRatio(true);
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

    /**
     * Updates the position of the obstacle based on its velocity.
     *
     * @param deltaTime the time elapsed since the last update
     */
    public void update(double deltaTime) {
        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);
    }

    /**
     * Sets the velocity of the obstacle.
     *
     * @param vx the new horizontal velocity
     * @param vy the new vertical velocity
     */
    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Returns the logical obstacle associated with this view.
     *
     * @return the BaseObstacle object
     */
    public BaseObstacle getObstacle() {
        return this.obstacle;
    }
}