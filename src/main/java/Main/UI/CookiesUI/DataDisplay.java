package Main.UI.CookiesUI;

import Main.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Main.Asset;
import Main.Button.FavoriteButton;
import Main.CharacterData;
import Main.Image.OutlineTextImage;

/**
 * Displays detailed information about the selected cookie.
 * <p>
 * This component organizes several UI elements vertically, including
 * the best record text, HP bar with a favorite button, skill preview
 * image, and a description of the character.
 */
public class DataDisplay extends VBox {

    /**
     * Creates and arranges character data components including best record text,
     * HP bar, favorite button, skill preview image, and description text.
     * <p>
     * The constructor retrieves the current cookie data from {@link CharacterData},
     * creates the corresponding UI components, and adds them sequentially
     * into the VBox layout.
     *
     * @param txt   the description text of the selected cookie
     * @param scene the {@link CookieSelectionScene} used to store and access
     *              UI components such as record text, skill preview, and description
     */
    public DataDisplay (String txt, CookieSelectionScene scene){

        scene.setRecord(new OutlineTextImage("Best Record : " + CharacterData.getCurrent_Cookie().get_Score(),'M',20));

        scene.getRecord().setPadding(new Insets(0,0,20,0));

        ImageView HpBar = Asset.createImageView("CookieHpBar",0,300);
        FavoriteButton Fav = new FavoriteButton();
        Fav.setHeight(40);

        HBox SubHBox = new HBox(HpBar,Fav);
        SubHBox.setSpacing(10);
        SubHBox.setMaxHeight(100);
        SubHBox.setAlignment(Pos.CENTER);

        scene.setSkillVideo(Asset.createImageView(CharacterData.getCurrent_Cookie().get_Sid(),0,440));

        scene.setDescription(new OutlineTextImage(txt,'C',15));
        getChildren().addAll(scene.getRecord(),SubHBox,scene.getSkillVideo(),scene.getDescription());
    }

}
