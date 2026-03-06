package Main.ObjectInGame.Items;

import Main.Asset;
import javafx.scene.image.ImageView;

/**
 * Represents a Pearl object that moves horizontally across the screen.
 *
 * This class handles the visual representation and movement of the pearl
 * within the game.
 */
public class Pearl extends ImageView {

    /**
     * The horizontal movement speed of the pearl.
     */
    private final double speed = 1000;

    /**
     * Creates a Pearl at the specified position.
     * The pearl image is loaded using the Asset manager.
     *
     * @param x the initial X position
     * @param y the initial Y position
     */
    public Pearl(double x, double y) {

        super(Asset.getImage("Pearl"));

        setFitWidth(40);
        setFitHeight(40);

        setLayoutX(x);
        setLayoutY(y);
    }

    /**
     * Updates the pearl's position based on the elapsed time.
     * The pearl moves horizontally to the right at a constant speed.
     *
     * @param dt the time delta used for movement calculation
     */
    public void update(double dt) {
        setLayoutX(getLayoutX() + speed * dt);
    }
}