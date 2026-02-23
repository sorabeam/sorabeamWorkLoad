package CleanCode.UI.CookiesUI;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import supakorn.Image.FloorFade;

public class CookieSelectBG extends StackPane {

    public CookieSelectBG(){

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        getChildren().addAll(fade);
    }


}
