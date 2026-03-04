package Beam.UI.CookiesUI;

import Beam.Animation.Animate;
import Beam.CharactorData;
import Beam.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import Beam.Image.OutlineText;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class CharacterDisplay extends StackPane {

    public CharacterDisplay(CookieSelectionScene scene){

        scene.setName( new OutlineText(CharactorData.getCurrent_Cookie().get_Name(), 'C',30) );
        scene.getName().setPadding(new Insets(0,0,40,0));

        Animate cookie = CharactorData.getCurrent_Cookie().createCookie();
        scene.setCookie(cookie);

        StackPane.setAlignment(scene.getName(), TOP_CENTER);
        scene.getName().setMaxWidth(1);
        scene.getName().setPadding(new Insets(80,0,0,0));
        //scene.getName().setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0),new Insets(0,0,0,0))));

        getChildren().add(scene.getName());

        getChildren().add(scene.getCookie());
        StackPane.setAlignment(scene.getCookie(),BOTTOM_CENTER);

        setPrefWidth(400);
        setPrefHeight(500);
        //setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0),new Insets(0,0,0,0))));

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

}
