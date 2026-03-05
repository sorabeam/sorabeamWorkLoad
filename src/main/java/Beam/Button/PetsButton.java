package Beam.Button;

import Beam.Pets.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Image.OutlineTextImage;

public class PetsButton extends BaseButton{

    ImageView img;
    Image I,Bg;
    String name,D;
    Pet pet;
    public PetsButton(Pet pet , int txtSize, double MarginBtm) {
        super(new ImageView(pet.getBtnImage()));

        img = new ImageView(pet.getBtnImage());
        img.setFitWidth(230);
        img.setPreserveRatio(true);
        I = pet.getView().getImage();
        Bg = pet.getBgImage();
        name = pet.getName();
        D = pet.getDesc();

        img.setFitWidth(270);

        super.setGraphic(img);

        OutlineTextImage txt = setText(name,txtSize,MarginBtm);
        FavoriteButton fav = BuildFav();
        StackPane newImg = new StackPane(fav,img,txt);
        super.setGraphic(newImg);

        this.pet = pet;
    }

    private OutlineTextImage setText(String text, int txtSize, double MarginBtm){

        OutlineTextImage txt = new OutlineTextImage(text,'M',txtSize);
        StackPane.setAlignment(txt,Pos.BOTTOM_CENTER);
        StackPane.setMargin(txt,new Insets(0,0,MarginBtm,0));

        return txt;
    }

    private FavoriteButton BuildFav(){

        FavoriteButton fav = new FavoriteButton();
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
