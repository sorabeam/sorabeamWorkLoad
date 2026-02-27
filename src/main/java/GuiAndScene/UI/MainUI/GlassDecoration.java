package GuiAndScene.UI.MainUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import supakorn.Asset;
import supakorn.Button.BaseButton;

public class GlassDecoration extends StackPane {

    public GlassDecoration() {

        BaseButton glass1 = new BaseButton(Asset.createImageView("Glass1", 0, 500));
        BaseButton glass2 = new BaseButton(Asset.createImageView("Glass2", 0, 500));
        BaseButton glass3 = new BaseButton(Asset.createImageView("Glass3", 0, 500));

        setAlignment(glass1, Pos.CENTER_RIGHT);
        setAlignment(glass2, Pos.CENTER_RIGHT);
        setAlignment(glass3, Pos.CENTER_RIGHT);

        setMargin(glass1, new Insets(0, 20, 300, 0));
        setMargin(glass2, new Insets(0, -50, 20, 20));
        setMargin(glass3, new Insets(390, 50, 0, 0));

        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        getChildren().addAll(glass3, glass2, glass1);
    }
}
