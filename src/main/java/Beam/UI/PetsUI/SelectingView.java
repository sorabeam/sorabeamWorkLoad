package Beam.UI.PetsUI;

import Beam.CharactorData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import Beam.Asset;
import Beam.Button.BaseButton;
import Beam.Image.OutlineText;

import static javafx.geometry.Pos.*;

public class SelectingView extends StackPane {
    public SelectingView(StackPane root){

        setPrefSize(400, 865);
        setMinSize(400, 550);
        setMaxSize(400, 865);

        ImageView Show = CharactorData.getCurrent_Pet().getView();
        ImageView SelectingBg = CharactorData.getCurrent_Pet().getBg();
        BaseButton DeployBtn = new BaseButton( Asset.createImageView("DeplayBtn",0,330));

        SelectingBg.setPreserveRatio(false);
        SelectingBg.fitHeightProperty().bind(root.heightProperty());
        StackPane.setAlignment(SelectingBg,CENTER);

        OutlineText PName = new OutlineText(CharactorData.getCurrent_Pet().getName(),'C',40);

        OutlineText description = new OutlineText(CharactorData.getCurrent_Pet().getDesc(),'M',20);
        description.setTextAlignment(TextAlignment.CENTER);

        VBox vbox = new VBox(Show,PName,DeployBtn,description);

        vbox.setAlignment(Pos.CENTER);
        setAlignment(vbox, Pos.CENTER);
        vbox.setSpacing(30);

        getChildren().addAll(SelectingBg, vbox);

    }
}

