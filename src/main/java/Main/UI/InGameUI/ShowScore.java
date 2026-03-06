package Main.UI.InGameUI;

import Main.GameLogic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Main.Asset;
import Main.Image.OutlineTextImage;

/**
 * Displays the player's score in the in-game interface.
 *
 * This component shows a background image with an outlined text displaying
 * the current score. The score text automatically updates when the value
 * in GameLogic.scoreProperty changes.
 */
public class ShowScore extends StackPane {

    /**
     * Background image displayed behind the score text.
     */
    ImageView fadeBg = Asset.createImageView("ScoreBG",100,0);

    /**
     * Text element used to display the current score with outline styling.
     */
    OutlineTextImage score = new OutlineTextImage("0 Score",'M',30);

    /**
     * Constructs the score display UI.
     *
     * The score text listens to GameLogic.scoreProperty and updates automatically
     * whenever the score changes. It also sets the initial score value when
     * the component is created.
     */
    public ShowScore(){

        setMaxSize(1,1);
        setAlignment(Pos.CENTER_RIGHT);

        score.setMaxSize(1,1);
        score.setPadding(new Insets(-5,30,0,0));

        GameLogic.scoreProperty().addListener((obs, oldVal, newVal) -> {
            score.setText(newVal + " Score");
        });

        // Set initial score value
        score.setText(GameLogic.getScore() + " Score");

        getChildren().addAll(
                fadeBg,
                score
        );
    }
}
