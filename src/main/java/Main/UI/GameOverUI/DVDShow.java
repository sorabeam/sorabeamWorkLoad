package Main.UI.GameOverUI;

import Main.Asset;
import Main.Button.NavigationButton;
import Main.CharacterData;
import Main.Image.OutlineTextImage;
import Main.GameLogic.GameLogic;
import Main.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A UI component that displays the score statistics on the Game Over screen.
 * <p>
 * This class shows the player's current score, compares it with the stored
 * highest score of the selected cookie, and updates the record if a new
 * highest score is achieved. It also displays the currently selected
 * character and provides a button to navigate back to the character
 * selection screen.
 */
public class DVDShow extends StackPane {

    /**
     * Constructs the statistics display for the Game Over screen.
     * <p>
     * The constructor creates two main UI sections:
     * <ul>
     *     <li>A statistics panel showing the current score and highest score.</li>
     *     <li>A character preview panel that allows navigation to the character selection screen.</li>
     * </ul>
     * It also updates the stored highest score if the current score
     * exceeds the previous record.
     */
    public DVDShow(){
        StackPane wrapper = new StackPane();
        wrapper.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ImageView statsImg = Asset.createImageView("statsBox", 450, 0);
        VBox statVBox = new VBox();
        statVBox.setMaxHeight(50);
        StackPane.setAlignment(statVBox, Pos.TOP_CENTER);
        StackPane.setMargin(statVBox,new Insets(170,0,0,0));
        OutlineTextImage score = new OutlineTextImage(GameLogic.getScore()+" score!",'C',24);

        int bestscore = Math.max(CharacterData.getCurrent_Cookie().get_Score(),GameLogic.getScore());
        if(GameLogic.getScore() > CharacterData.getCurrent_Cookie().get_Score()){
            CharacterData.getCurrent_Cookie().setScore(GameLogic.getScore());
        }

        OutlineTextImage bestScore = new OutlineTextImage("Highest " + bestscore + " score!",'C',16);
        wrapper.setMaxHeight(500);
        wrapper.setMaxWidth(500);

        statVBox.setAlignment(Pos.CENTER);

        statVBox.getChildren().addAll(score, bestScore);

        wrapper.setPadding(new Insets(16));
        wrapper.getChildren().addAll(statsImg, statVBox);

        StackPane wrapper2 = new StackPane();
        wrapper2.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ImageView curCharBox = Asset.createImageView("charBox", 300, 0);

        ImageView img = Asset.createImageView(CharacterData.getCurrent_Cookie().get_Bid(),180,0);
        NavigationButton show = new NavigationButton(img, GameState.SELECT_CHAR);
        StackPane.setMargin(show,new Insets(-50,-60,0,0));
        wrapper2.getChildren().addAll(curCharBox,show);
        StackPane.setMargin(wrapper2,new Insets(0,475,0,0));
        StackPane.setAlignment(wrapper,Pos.CENTER_RIGHT);

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        getChildren().addAll(wrapper2,wrapper);
    }
}
