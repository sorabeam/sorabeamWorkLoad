package Main.ObjectInGame.Items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the visual representation of an item in the game.
 *
 * This class extends ImageView and is responsible for displaying
 * the item's image and handling its movement on screen.
 */
public class ItemView extends ImageView {

    /**
     * The logical item associated with this view.
     */
    private BaseItem item;

    /**
     * Horizontal velocity of the item.
     */
    private double vx;

    /**
     * Vertical velocity of the item.
     */
    private double vy;

    /**
     * Creates a new ItemView for the specified item with initial velocity.
     * The image and size are determined based on the item type.
     *
     * @param item the item associated with this view
     * @param vx the horizontal velocity
     * @param vy the vertical velocity
     */
    public ItemView(BaseItem item, double vx, double vy) {
        this.item = item;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Items/" + item.getName() + ".png"));

        if (item instanceof Croissant) {
            setFitWidth(200);
            setFitHeight(145);
        } else if (item instanceof BigHealingPotion) {
            setFitWidth(100);
            setFitHeight(100);
        } else {
            setFitWidth(50);
            setFitHeight(50);
        }
    }

    /**
     * Updates the position of the item view based on its velocity.
     *
     * @param deltaTime the time elapsed since the last update
     */
    public void update(double deltaTime) {
        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);
    }

    /**
     * Returns the healing amount if the item is a HealingPotion.
     *
     * @return the healing value of the potion, or 0 if the item
     *         does not provide healing
     */
    public int getHealAmount() {
        if(item instanceof HealingPotion)
        {
            return ((HealingPotion) item).getHealingStat();
        }
        else return 0;
    }

    /**
     * Returns the BaseItem associated with this view.
     *
     * @return the item represented by this view
     */
    public BaseItem getItem() {
        return item;
    }

    /**
     * Sets the velocity of this item view.
     *
     * @param vx the new horizontal velocity
     * @param vy the new vertical velocity
     */
    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}