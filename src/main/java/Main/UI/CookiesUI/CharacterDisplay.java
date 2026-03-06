package Main.UI.CookiesUI;

import Main.Animation.Animate;
import Main.CharacterData;
import Main.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import Main.Image.OutlineTextImage;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

/**
 * Displays the currently selected cookie character in the cookie selection scene.
 * <p>
 * This component creates a layout that shows the cookie name at the top
 * and the animated cookie character at the bottom. The displayed cookie
 * is retrieved from {@link CharacterData}.
 */
public class CharacterDisplay extends StackPane {

    /**
     * Creates and arranges the selected character display.
     * <p>
     * The constructor sets the character name using {@link OutlineTextImage},
     * creates the animated cookie from {@link CharacterData}, and places the
     * elements inside the {@link StackPane}. The name is aligned to the top
     * and the cookie animation is aligned to the bottom. A preferred size
     * for the display area is also defined.
     *
     * @param scene the cookie selection scene that stores and manages
     *              the displayed cookie name and animation
     */
    public CharacterDisplay(CookieSelectionScene scene){

        scene.setName( new OutlineTextImage(CharacterData.getCurrent_Cookie().get_Name(), 'C',30) );
        scene.getName().setPadding(new Insets(0,0,40,0));

        Animate cookie = CharacterData.getCurrent_Cookie().createCookie();
        scene.setCookie(cookie);

        StackPane.setAlignment(scene.getName(), TOP_CENTER);
        scene.getName().setMaxWidth(1);
        scene.getName().setPadding(new Insets(80,0,0,0));

        getChildren().add(scene.getName());

        getChildren().add(scene.getCookie());
        StackPane.setAlignment(scene.getCookie(),BOTTOM_CENTER);

        setPrefWidth(400);
        setPrefHeight(500);

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

}
