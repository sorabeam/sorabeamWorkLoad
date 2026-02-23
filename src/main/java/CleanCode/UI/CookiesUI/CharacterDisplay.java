package CleanCode.UI.CookiesUI;

import CleanCode.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import supakorn.Image.OutlineText;
import supakorn.Scene.CookiesSelectionScene;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class CharacterDisplay extends StackPane {

    public CharacterDisplay(CookieSelectionScene scene){

        scene.setName( new OutlineText("White Lilly",'C',30) );
        scene.getName().setPadding(new Insets(0,0,40,0));
        StackPane.setAlignment(scene.getName(), BOTTOM_CENTER);

        getChildren().add(scene.getName());
        setPrefWidth(400);
        setPrefHeight(500);
        setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(0),new Insets(0,0,0,0))));

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

}
