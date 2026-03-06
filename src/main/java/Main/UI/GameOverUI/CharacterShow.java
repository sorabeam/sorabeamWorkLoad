package Main.UI.GameOverUI;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.CharacterData;
import Main.Image.OutlineTextImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Displays the character result view in the Game Over screen.
 * <p>
 * This component shows the selected cookie character, the equipped pet,
 * and the cookie name. The cookie uses an animated view while the pet
 * is displayed as an image. All elements are arranged inside a
 * {@link StackPane}.
 */
public class CharacterShow extends StackPane {

    /**
     * Animated character view representing the current cookie
     * selected by the player.
     */
    private final Animate cookieView = CharacterData.getCurrent_Cookie().createCookie();

    /**
     * Initializes the Game Over character display.
     * <p>
     * The constructor creates the animated cookie view, configures
     * its animation state, and displays the currently selected pet.
     * It also shows the cookie name at the top and applies visual
     * effects such as drop shadows. All components are arranged
     * within the StackPane layout.
     */
    public CharacterShow() {
        cookieView.setFitWidth(700);
        cookieView.setPreserveRatio(true);
        cookieView.changeAnimationState(AnimationType.IDLE);
        cookieView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );

        ImageView petView = CharacterData.getCurrent_Pet().getView();

        if(CharacterData.getCurrent_Pet().getName().equals(CharacterData.CHILLY.getName())){
            System.out.println("hhhhhhhh");
            petView.setFitHeight(300);
            petView.setFitWidth(300);
        } else {
            petView.setFitHeight(200);
            petView.setFitWidth(200);
        }

        petView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setAlignment(petView, Pos.TOP_RIGHT);
        setMargin(petView, new Insets(60,-50,0,0));

        OutlineTextImage username = new OutlineTextImage(CharacterData.getCurrent_Cookie().get_Name(), 'C',24);
        setAlignment(username, Pos.TOP_CENTER);
        setMargin(username, new Insets(0,-60,0,0));

        setMaxSize(700,700);
        getChildren().addAll(petView, cookieView, username);
    }

    /**
     * Returns the animated cookie view used in the character display.
     * <p>
     * This allows other classes to access the cookie animation
     * for further control or updates.
     *
     * @return the animated cookie view
     */
    public Animate getCookie(){
        return cookieView;
    }
}
