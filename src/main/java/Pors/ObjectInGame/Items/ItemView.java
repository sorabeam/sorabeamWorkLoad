package Pors.ObjectInGame.Items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemView extends ImageView {

    private BaseItem item;
    private double vx;
    private double vy;

    public ItemView(BaseItem item, double vx, double vy) {
        this.item = item;
        this.vx = vx;
        this.vy = vy;
        System.out.println("/Image/Items/" + item.getName() + ".png");
        setImage(new Image("/Image/Items/" + item.getName() + ".png"));
        setFitWidth(50);
        setFitHeight(50);
    }

    public void update() {
        setTranslateX(getTranslateX() + vx);
        setTranslateY(getTranslateY() + vy);
    }

    public int getHealAmount() {
        if(item instanceof HealingPotion)
        {
            return ((HealingPotion) item).getHealingStat();
        }
        else return 0;
    }

    public BaseItem getItem() {
        return item;
    }

    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}