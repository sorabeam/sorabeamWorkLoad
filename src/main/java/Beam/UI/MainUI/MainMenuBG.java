package Beam.UI.MainUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Asset;
import Beam.Image.FloorFade;
import Beam.Image.OutlineTextImage;

public class MainMenuBG extends StackPane {

    public MainMenuBG(){

        ImageView MBg = Asset.createBackgroundView("BgLobby",1,1);

        // bind กับตัวเอง
        MBg.fitWidthProperty().bind(widthProperty());
        MBg.fitHeightProperty().bind(heightProperty());

        FloorFade fade = new FloorFade(200);
        StackPane.setAlignment(fade, Pos.BOTTOM_CENTER);

        OutlineTextImage name = new OutlineTextImage("sorabeam",'M',18);
        StackPane.setAlignment(name, Pos.BOTTOM_LEFT);
        StackPane.setMargin(name,new Insets(0,0,20,40));
        name.setMaxWidth(50);

        getChildren().addAll(MBg, fade, name);
    }
}

