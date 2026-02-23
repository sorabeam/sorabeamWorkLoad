package supakorn.Button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import supakorn.Image.OutlineText;

public class SelectPBtn extends BaseButton{
    public SelectPBtn(ImageView img, String text, int txtSize, double MarginBtm) {

        super(img);

        OutlineText txt = setText( text,txtSize,MarginBtm);
        FavBtn fav = BuildFav();
        StackPane newImg = new StackPane(fav,img,txt);
        super.setGraphic(newImg);
    }

    private OutlineText setText(String text,int txtSize,double MarginBtm){

        OutlineText txt = new OutlineText(text,'M',txtSize);
        StackPane.setAlignment(txt,Pos.BOTTOM_CENTER);
        StackPane.setMargin(txt,new Insets(0,0,MarginBtm,0));

        return txt;
    }

    private FavBtn BuildFav(){

        FavBtn fav = new FavBtn();

        StackPane.setAlignment(fav,Pos.BOTTOM_LEFT);
        StackPane.setMargin(fav , new Insets(0,0,18,40));

        return fav;
    }
}
