package Main.UI.MainMenuUI;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.CharacterData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * A UI component that displays the currently selected character and pet
 * in the main menu. The cookie character is shown with an animation while
 * the pet is displayed as a static image.
 *
 * <p>The character and pet data are retrieved from {@link CharacterData}.
 * The cookie animation is set to the {@link AnimationType#IDLE} state and
 * both elements are styled with a drop shadow effect for visual emphasis.</p>
 */
public class CharacterShow extends StackPane {

    /** Animated view of the currently selected cookie character. */
    private final Animate cookieView = CharacterData.getCurrent_Cookie().createCookie();

    /**
     * Creates the character display component for the main menu UI.
     * <p>
     * This constructor initializes the animated cookie and the selected pet
     * from {@link CharacterData}. The cookie animation is set to the IDLE
     * state and both the cookie and pet views are styled and positioned
     * within the layout.
     * </p>
     */
    public CharacterShow() {
        cookieView.setFitWidth(400);
        cookieView.setPreserveRatio(true);
        cookieView.changeAnimationState(AnimationType.IDLE);
        cookieView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setMargin(cookieView, new Insets(-100, -50, 0, 0));

        ImageView petView = CharacterData.getCurrent_Pet().getView();
        petView.setFitHeight(150);
        petView.setFitWidth(150);
        petView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setAlignment(petView, Pos.TOP_LEFT);
        setMargin(petView, new Insets(90, 0, 0, 100));

        setMaxSize(600, 600);
        getChildren().addAll(petView, cookieView);
    }

    /**
     * Returns the animated cookie view used in the main menu.
     *
     * @return the {@link Animate} instance representing the current cookie character
     */
    public Animate getCookie(){
        return cookieView;
    }
}
