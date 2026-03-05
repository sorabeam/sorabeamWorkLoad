package Beam.UI.MainUI;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.CharactorData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CharacterShow extends StackPane {

    private final Animate cookieView = CharactorData.getCurrent_Cookie().createCookie();

    public CharacterShow() {
        cookieView.setFitWidth(400);
        cookieView.setPreserveRatio(true);
        cookieView.changeAnimationState(AnimationType.IDLE);
        cookieView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setMargin(cookieView, new Insets( -100,-50,0,0));

        ImageView petView = CharactorData.getCurrent_Pet().getView();
        petView.setFitHeight(150);
        petView.setFitWidth(150);
        petView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setAlignment(petView, Pos.TOP_LEFT);
        setMargin(petView, new Insets(90,0,0,100));

        setMaxSize(600,600);
        getChildren().addAll(petView, cookieView);
    }

    public Animate getCookie(){
        return cookieView;
    }
}
