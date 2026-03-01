package Beam.UI.GameOverUI;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.CharactorData;
import Beam.Cookies.Cookie;
import Beam.Image.OutlineText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CharactorShow extends StackPane {

    private final Animate cookieView = CharactorData.getCurrent_Cookie().createCookie();

    public CharactorShow() {
        cookieView.setFitWidth(700);
        cookieView.setPreserveRatio(true);
        cookieView.changeAnimationState(AnimationType.IDLE);
        cookieView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );

        ImageView petView = CharactorData.getCurrent_Pet().getView();
        petView.setFitHeight(150);
        petView.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );
        setAlignment(petView, Pos.TOP_RIGHT);
        setMargin(petView, new Insets(60,-50,0,0));

        OutlineText username = new OutlineText(CharactorData.getCurrent_Cookie().get_Name(), 'C',24);
        setAlignment(username, Pos.TOP_CENTER);
        setMargin(username, new Insets(0,-60,0,0));

        setMaxSize(700,700);
        getChildren().addAll(petView, cookieView, username);
    }

    public Animate getCookie(){
        return cookieView;
    }
}
