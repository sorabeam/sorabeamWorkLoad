package Main;

import Main.Cookies.Cookie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Main.Button.CharacterButton;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class responsible for loading and managing image resources used in the UI.
 * Images are cached to prevent loading the same file multiple times from disk,
 * improving performance and reducing resource usage.
 */
public class Asset {

    /**
     * A cache map used to store loaded images so that the same image
     * is not loaded multiple times from disk.
     */
    private static final Map<String, Image> IMAGE_CACHE = new HashMap<>();

    /**
     * Loads an image from the /Image/ directory. If the image has already
     * been loaded before, it will be returned from the cache instead of
     * loading the file again.
     *
     * @param name the image file name (without extension)
     * @return the loaded Image instance
     */
    public static Image getImage(String name) {

        return IMAGE_CACHE.computeIfAbsent(name, key -> {
            URL url = Asset.class.getResource("/Image/" + key + ".png");

            if (url == null) {
                throw new RuntimeException("Image not found: " + key);
            }

            return new Image(url.toExternalForm());
        });
    }

    /**
     * Creates an ImageView using the specified image key and sets its
     * width and height. If either width or height is set to 0, the
     * aspect ratio is preserved automatically.
     *
     * @param key the image key used to load the image
     * @param H the desired height of the ImageView
     * @param W the desired width of the ImageView
     * @return a configured ImageView displaying the requested image
     */
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

    /**
     * Creates an ImageView for background images located in the
     * /Image/Background/ directory. The image is resized based on
     * the specified width and height, preserving aspect ratio
     * when either dimension is set to 0.
     *
     * @param key the background image key
     * @param H the desired height of the background
     * @param W the desired width of the background
     * @return a configured ImageView for the background image
     */
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

    /**
     * Loads background images from the /Image/Background/ directory.
     * The loaded image is stored in the cache to avoid loading the
     * same resource multiple times.
     *
     * @param name the background image file name (without extension)
     * @return the loaded background Image
     */
    public static Image getBackGroundImage(String name) {

        return IMAGE_CACHE.computeIfAbsent(name, key -> {
            URL url = Asset.class.getResource("/Image/Background/" + key + ".png");

            if (url == null) {
                throw new RuntimeException("Image not found: " + key);
            }

            return new Image(url.toExternalForm());
        });
    }

    /**
     * Creates a CharacterButton used in the character selection grid.
     * The button is constructed using character information such as
     * name, description, best score, and sprite image.
     *
     * @param _char the character data used to create the button
     * @param H the height of the character icon
     * @param W the width of the character icon
     * @return a CharacterButton configured for the character selection grid
     */
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
