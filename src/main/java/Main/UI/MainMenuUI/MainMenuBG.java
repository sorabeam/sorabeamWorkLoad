package Main.UI.MainMenuUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Main.Asset;
import Main.Image.FloorFade;
import Main.Image.OutlineTextImage;

/**
 * Background layout for the Main Menu screen.
 *
 * <p>This class creates the main background of the lobby using an image,
 * adds a bottom fade effect, and displays a small developer name label
 * at the bottom-left corner.</p>
 */
public class MainMenuBG extends StackPane {

    /**
     * Creates and initializes the main menu background layout.
     *
     * <p>The constructor loads the lobby background image and binds its
     * size to the current container size so it always fills the screen.
     * It also adds a {@link FloorFade} effect at the bottom and displays
     * a small developer name text using {@link OutlineTextImage}.</p>
     */
    public MainMenuBG(){

        ImageView MBg = Asset.createBackgroundView("BgLobby",1,1);

        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        OutlineTextImage name = new OutlineTextImage("sorabeam",'M',18);
        StackPane.setAlignment(name, Pos.BOTTOM_LEFT);
        StackPane.setMargin(name,new Insets(0,0,20,40));
        name.setMaxWidth(50);

        getChildren().addAll(MBg, fade, name);
    }
}
