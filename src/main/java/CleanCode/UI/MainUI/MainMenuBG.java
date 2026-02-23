package CleanCode.UI.MainUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Image.FloorFade;
import supakorn.Image.OutlineText;

public class MainMenuBG extends StackPane {

    public MainMenuBG(Scene scene){

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        OutlineText name = new OutlineText("sorabeam"  ,'M',18);
        StackPane.setAlignment(name,Pos.BOTTOM_LEFT);
        name.setMaxHeight(1);
        name.setMaxWidth(1);
        StackPane.setMargin(name,new Insets(0,0,20,40));

        ImageView MBg = Asset.createImageView("BgLobby",1,1);

        MBg.fitWidthProperty().bind(scene.widthProperty());
        MBg.fitHeightProperty().bind(scene.heightProperty());

        getChildren().addAll(MBg,fade,name);

    }

}
