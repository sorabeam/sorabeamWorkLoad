package CleanCode.UI.PetsUI;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import supakorn.Asset;
import supakorn.Button.SelectPBtn;

public class PetsShow extends HBox {

    public PetsShow(Region spacer){

        SelectPBtn ins1 =new SelectPBtn(Asset.createImageView("DrogonFruit",0,230) ,"DragonFruit",28,50);
        SelectPBtn ins2 =new SelectPBtn(Asset.createImageView("Pitachio",0,230),"Pitachio",28,50);
        SelectPBtn ins3 =new SelectPBtn(Asset.createImageView("Lemon",0,230),"Lemon",28,50);
        SelectPBtn ins4 =new SelectPBtn(Asset.createImageView("Pancake",0,230),"Pancake",28,50);

        getChildren().addAll(spacer, ins1, ins2, ins3, ins4);
        setSpacing(-15);
        setPadding(new Insets(0,10,0,0));

    }
}
