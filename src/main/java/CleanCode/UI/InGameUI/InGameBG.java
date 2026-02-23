package CleanCode.UI.InGameUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Image.FloorFade;

public class InGameBG extends StackPane {

    public InGameBG(Scene scene){
        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        ImageView MBg = Asset.createImageView("BgLobby",1,1);

        MBg.fitWidthProperty().bind(scene.widthProperty());
        MBg.fitHeightProperty().bind(scene.heightProperty());

        ImageView ExpBar = Asset.createImageView("EXPBar",0,10);
        ExpBar.fitWidthProperty().bind(scene.widthProperty());
        StackPane.setAlignment(ExpBar,Pos.BOTTOM_CENTER);

        getChildren().addAll(MBg,ExpBar,fade);
    }
}
