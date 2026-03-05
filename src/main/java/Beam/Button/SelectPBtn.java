package Beam.Button;

import Beam.CharactorData;
import Beam.Pets.Pet;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Beam.Image.OutlineText;

import static Got.GameLogic.GameLogic.getStage;

public class SelectPBtn extends BaseButton{

    ImageView img;
    Image I,Bg;
    String name,D;
    Pet pet;
    public SelectPBtn(Pet pet ,int txtSize, double MarginBtm) {
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

        OutlineText txt = setText(name,txtSize,MarginBtm);
        FavBtn fav = BuildFav();
        StackPane newImg = new StackPane(fav,img,txt);
        super.setGraphic(newImg);

        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
