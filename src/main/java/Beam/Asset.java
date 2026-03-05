package Beam;

import Beam.Cookies.Cookie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Beam.Button.CharacterButton;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Asset {

    private static final Map<String, Image> IMAGE_CACHE = new HashMap<>();

    public static Image getImage(String name) {

        return IMAGE_CACHE.computeIfAbsent(name, key -> {
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

        return IMAGE_CACHE.computeIfAbsent(name, key -> {
            URL url = Asset.class.getResource("/Image/Background/" + key + ".png");

            if (url == null) {
                throw new RuntimeException("Image not found: " + key);
            }

            return new Image(url.toExternalForm());
        });
    }

    public static CharacterButton createGridButton(Cookie _char, double H, double W){

        String bid = _char.get_Bid();
        String name = _char.get_Name();
        String desc = _char.get_Desc();
        String score = "Best Record : " + _char.get_Score();

        Image Simg = getImage(_char.get_Sid());

        CharacterButton btn = new CharacterButton(createImageView(bid,H,W),name,desc,score,Simg,_char);
        return btn;
    }
}