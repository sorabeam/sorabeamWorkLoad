package Main.UI.MainMenuUI;

import Main.Button.MapPopupButton;
import Main.CharacterData;
import Main.GameLogic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Main.Asset;
import Main.Button.FavoriteButton;
import Main.Image.OutlineTextImage;

/**
 * UI component that displays the player's profile section in the Main Menu.
 *
 * <p>This layout shows the current character profile, character name,
 * best score, and provides buttons for marking the character as favorite
 * and selecting a map.</p>
 */
public class MainMenuProfile extends StackPane {

    /**
     * Creates and initializes the profile section of the main menu.
     *
     * <p>The constructor builds the profile UI by displaying the current
     * character image, character name, best score, a favorite button,
     * and a map selection button. All elements are positioned within
     * the layout using alignment and margin settings.</p>
     *
     * @param name the name of the current character displayed in the profile
     * @param score the best score value to display
     * @param root the root pane used for displaying the map selection popup
     */
    public MainMenuProfile(String name, String score, StackPane root) {

        ImageView LillyBar = Asset.createImageView("LillyBar",0,460);
        ImageView LillyProfile = new ImageView(CharacterData.getCurrent_Cookie().getProfileImg());
        LillyProfile.setPreserveRatio(true);
        LillyProfile.setFitWidth(460);

        FavoriteButton CfavIco = new FavoriteButton();
        CfavIco.setHeight(45);

        OutlineTextImage CharactorName = new OutlineTextImage(name,'C',35);
        StackPane.setAlignment(CharactorName, Pos.TOP_LEFT);
        CharactorName.setMaxHeight(1);
        CharactorName.setMaxWidth(1);

        OutlineTextImage BestScore = new OutlineTextImage("Best Score : " + score ,'M',30);
        StackPane.setAlignment(BestScore,Pos.TOP_LEFT);
        BestScore.setMaxHeight(1);
        BestScore.setMaxWidth(1);

        MapPopupButton SelectingMap =
                new MapPopupButton(
                        Asset.createImageView("MAP" + GameLogic.getMap() + "P",0,400),
                        root
                );

        setMaxWidth(500);
        getChildren().addAll(LillyBar,LillyProfile,BestScore,CharactorName,CfavIco,SelectingMap);

        // alignment / margin

        setAlignment(LillyBar,Pos.TOP_LEFT);
        setAlignment(LillyProfile,Pos.TOP_LEFT);
        setAlignment(CharactorName,Pos.TOP_LEFT);
        setAlignment(CfavIco,Pos.TOP_LEFT);
        setAlignment(SelectingMap,Pos.TOP_LEFT);

        setMargin(LillyBar,new Insets(20,0,0,20));
        setMargin(LillyProfile,new Insets(20,0,0,20));
        setMargin(CharactorName,new Insets(
                20 +  LillyProfile.getLayoutBounds().getHeight()/3.1,
                0,0,50
        ));
        setMargin(BestScore,new Insets(
                30 + LillyProfile.getLayoutBounds().getHeight(),
                0,0,30
        ));
        setMargin(CfavIco,new Insets(
                36 + LillyProfile.getLayoutBounds().getHeight(),
                0,0,
                10 + LillyBar.getLayoutBounds().getWidth() - 50
        ));
        setMargin(SelectingMap,new Insets(200,0,0,20));

    }
}
