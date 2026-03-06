package Main.UI.CookiesUI;

import Main.Asset;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import Main.Image.FloorFade;
import javafx.scene.paint.Color;

/**
 * Creates the background layout for the cookie selection scene.
 * <p>
 * This component displays a scalable background image and adds
 * a fade effect at the bottom of the screen. The background image
 * automatically resizes with the container to maintain full coverage.
 */
public class CookieSelectBG extends StackPane {

    /**
     * Creates the background layout for the cookie selection scene.
     * <p>
     * The constructor loads the background image using {@link Asset},
     * binds its size to the container so it scales with the window,
     * adds a {@link FloorFade} effect aligned at the bottom, and
     * sets the container background color to white.
     */
    public CookieSelectBG(){

        ImageView MBg = Asset.createBackgroundView("CBG",1,1);

        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,fade);

        setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
    }

}
