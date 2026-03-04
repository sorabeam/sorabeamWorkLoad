package Beam.UI.PetsUI;

import Beam.CharactorData;
import Beam.Scene.PetsSelectionScene;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import Beam.Asset;
import Beam.Button.BaseButton;
import Beam.Image.OutlineText;

import static javafx.geometry.Pos.*;

public class SelectingView extends StackPane {

    public SelectingView(StackPane root, PetsSelectionScene mscene){

        setPrefSize(600, 1000);
        setMinSize(400, 550);
        setMaxSize(600, 1000);

        System.out.println(CharactorData.getCurrent_Pet().getViewImage());
        ImageView show = new ImageView( CharactorData.getCurrent_Pet().getViewImage() );
        show.setPreserveRatio(true);

        ImageView selectingBg = new ImageView(
                CharactorData.getCurrent_Pet().getBgImage()
        );

        selectingBg.setPreserveRatio(false);
        selectingBg.setFitWidth(350);
        selectingBg.fitHeightProperty().bind(root.heightProperty());
        StackPane.setAlignment(selectingBg, CENTER);

        OutlineText pName =
                new OutlineText(CharactorData.getCurrent_Pet().getName(),'C',40);

        OutlineText description =
                new OutlineText(CharactorData.getCurrent_Pet().getDesc(),'M',20);

        description.setTextAlignment(TextAlignment.CENTER);

        show.setFitWidth(450);
        show.setFitHeight(450);

        VBox vbox = new VBox(show, pName, description);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        setAlignment(vbox, Pos.CENTER);

        // ส่ง reference กลับไป scene
        mscene.setShowi(show);
        mscene.setBgi(selectingBg);
        mscene.setName(pName);
        mscene.setDescription(description);

        getChildren().addAll(selectingBg, vbox);
    }
}
