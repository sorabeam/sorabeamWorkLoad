package Pors.ObsticleAndItem;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Pors.ObsticleAndItem.Items.BaseItem;

public class ItemView extends ImageView {

    private BaseItem Item;
    private double vx;
    private double vy;

    public ItemView(BaseItem Item, double vx, double vy) {
        this.Item = Item;
        this.vx = vx;
        this.vy = vy;
        System.out.println("/Image/Items/" + Item.getName() + ".png");
        setImage(new Image("/Image/Items/" + Item.getName() + ".png"));
        setFitWidth(50);
        setFitHeight(50);
    }

    public void update() {
        setTranslateX(getTranslateX() + vx);
        setTranslateY(getTranslateY() + vy);
    }
}