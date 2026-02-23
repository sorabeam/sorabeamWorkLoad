package CleanCode.UI.CookiesUI;

import CleanCode.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import supakorn.Asset;
import supakorn.Button.FavBtn;
import supakorn.Image.OutlineText;

public class DataDisplay extends VBox {

    public DataDisplay (String txt, CookieSelectionScene scene){


        scene.setRecord(new OutlineText("Best Record : 123,569,574",'C',20));

        scene.getRecord().setPadding(new Insets(0,0,20,0));

        ImageView HpBar = Asset.createImageView("CookieHpBar",0,300);
        FavBtn Fav = new FavBtn();
        Fav.setHeight(40);

        HBox SubHBox = new HBox(HpBar,Fav);
        SubHBox.setSpacing(10);
        SubHBox.setMaxHeight(100);
        SubHBox.setAlignment(Pos.CENTER);

        scene.setSkillVideo(Asset.createImageView("S5",0,440));

        scene.setDescription(new OutlineText(txt,'M',18));
        getChildren().addAll(scene.getRecord(),SubHBox,scene.getSkillVideo(),scene.getDescription());
    }

}
