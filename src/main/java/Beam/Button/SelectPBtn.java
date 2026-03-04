package Beam.Button;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Image.OutlineText;

public class SelectPBtn extends BaseButton{

    ImageView img;
    Image I,Bg;
    String name,D;

    public SelectPBtn(ImageView img, Image I , Image Bg, String name , String D, int txtSize, double MarginBtm) {

        super(img);

        img.setFitWidth(270);

        super.setGraphic(img);

        OutlineText txt = setText(name,txtSize,MarginBtm);
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
        fav.setHeight(50);

        StackPane.setAlignment(fav,Pos.BOTTOM_LEFT);
        StackPane.setMargin(fav , new Insets(0,0,18,40));

        return fav;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Image getI() {
        return I;
    }

    public void setI(Image i) {
        I = i;
    }

    public Image getBg() {
        return Bg;
    }

    public void setBg(Image bg) {
        Bg = bg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }
}
