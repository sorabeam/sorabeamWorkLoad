package supakorn.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CDBtn extends BaseButton{
    String n;
    String r;
    String d;
    Image img;

    public CDBtn(ImageView i, String n, String d , String r, Image img) {

        super(i);

        this.n = n;
        this.d = d;
        this.r = r;
        this.img = img;

    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
