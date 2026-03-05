package Beam.UI.GameOverUI;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.CharactorData;
import Beam.Image.OutlineTextImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CharacterShow extends StackPane {

    private final Animate COOKIE_VIEW = CharactorData.getCurrent_Cookie().createCookie();

    public CharacterShow() {
        COOKIE_VIEW.setFitWidth(700);
        COOKIE_VIEW.setPreserveRatio(true);
        COOKIE_VIEW.changeAnimationState(AnimationType.IDLE);
        COOKIE_VIEW.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 40, 0.7, 0, 5);"
        );

        ImageView petView = CharactorData.getCurrent_Pet().getView();

        if(CharactorData.getCurrent_Pet().getName().equals(CharactorData.CHILLY.getName())){
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

        OutlineTextImage username = new OutlineTextImage(CharactorData.getCurrent_Cookie().get_Name(), 'C',24);
        setAlignment(username, Pos.TOP_CENTER);
        setMargin(username, new Insets(0,-60,0,0));

        setMaxSize(700,700);
        getChildren().addAll(petView, COOKIE_VIEW, username);
    }

    public Animate getCookie(){
        return COOKIE_VIEW;
    }
}
