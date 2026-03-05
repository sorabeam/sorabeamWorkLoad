package Beam.UI.CookiesUI;

import Beam.Scene.CookieSelectionScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Beam.Asset;
import Beam.Button.FavoriteButton;
import Beam.CharactorData;
import Beam.Image.OutlineTextImage;

public class DataDisplay extends VBox {

    public DataDisplay (String txt, CookieSelectionScene scene){


        scene.setRecord(new OutlineTextImage("Best Record : " + CharactorData.getCurrent_Cookie().get_Score(),'M',20));

        scene.getRecord().setPadding(new Insets(0,0,20,0));

        ImageView HpBar = Asset.createImageView("CookieHpBar",0,300);
        FavoriteButton Fav = new FavoriteButton();
        Fav.setHeight(40);

        HBox SubHBox = new HBox(HpBar,Fav);
        SubHBox.setSpacing(10);
        SubHBox.setMaxHeight(100);
        SubHBox.setAlignment(Pos.CENTER);

        scene.setSkillVideo(Asset.createImageView(CharactorData.getCurrent_Cookie().get_Sid(),0,440));

        scene.setDescription(new OutlineTextImage(txt,'C',15));
        getChildren().addAll(scene.getRecord(),SubHBox,scene.getSkillVideo(),scene.getDescription());
    }

}
