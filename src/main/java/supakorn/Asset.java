package supakorn;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import supakorn.Button.CDBtn;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Asset {

    private static final Map<String, Image> imageCache = new HashMap<>();

    public static Image getImage(String name) {

        return imageCache.computeIfAbsent(name, key -> {
            URL url = Asset.class.getResource("/Image/" + key + ".png");

            if (url == null) {
                throw new RuntimeException("Image not found: " + key);
            }

            return new Image(url.toExternalForm());
        });
    }

    public static ImageView createImageView(String key, double H, double W) {

        ImageView view = new ImageView(getImage(key));

        if (H == 0 && W > 0) {
            view.setFitWidth(W);
            view.setPreserveRatio(true);
        } else if (H > 0 && W == 0) {
            view.setFitHeight(H);
            view.setPreserveRatio(true);
        } else {
            view.setFitHeight(H);
            view.setFitWidth(W);
        }

        return view;
    }

    public static ImageView createBackgroundView(String key, double H, double W) {

        ImageView view = new ImageView(getBackGroundImage(key));

        if (H == 0 && W > 0) {
            view.setFitWidth(W);
            view.setPreserveRatio(true);
        } else if (H > 0 && W == 0) {
            view.setFitHeight(H);
            view.setPreserveRatio(true);
        } else {
            view.setFitHeight(H);
            view.setFitWidth(W);
        }

        return view;
    }

    public static Image getBackGroundImage(String name) {

        return imageCache.computeIfAbsent(name, key -> {
            URL url = Asset.class.getResource("/Image/BackGround/" + key + ".png");

            if (url == null) {
                throw new RuntimeException("Image not found: " + key);
            }

            return new Image(url.toExternalForm());
        });
    }

    public static CDBtn createGridButton(CD _char, double H, double W){

        String bid = _char.getBid();
        String name = _char.getName();
        String desc = _char.getDesc();

        Image Simg = getImage(_char.getSid());

        CDBtn btn = new CDBtn(createImageView(bid,H,W),name,desc,"Best Score : 123,456,789!",Simg);
        return btn;
    }
}
