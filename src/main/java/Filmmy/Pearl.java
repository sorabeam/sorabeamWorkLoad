package Filmmy;

import Beam.Asset;
import javafx.scene.image.ImageView;

public class Pearl extends ImageView {

    private double speed = 1000;

    public Pearl(double x, double y) {

        super(Asset.getImage("Pearl"));

        setFitWidth(40);
        setFitHeight(40);

        setLayoutX(x);
        setLayoutY(y);
    }

    public void update(double dt) {
        setLayoutX(getLayoutX() + speed * dt);
    }
}