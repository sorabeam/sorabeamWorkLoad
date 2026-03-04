package Beam.Button;

import Beam.CharactorData;
import Beam.Cookies.Cookie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CDBtn extends BaseButton{
    String n;
    String r;
    String d;
    Image img;
    Cookie cookie;

    public CDBtn(ImageView i, String n, String d , String r, Image img , Cookie cookie) {

        super(i);

        this.n = n;
        this.d = d;
        this.r = r;
        this.img = img;
        this.cookie = cookie;
    }

    @Override
    public void handleClick() {
        super.handleClick();

        CharactorData.setCurrent_Cookie(cookie);
    }

    public String getN() {
        return n;
    }
    public String getD() {
        return d;
    }
    public String getR() {
        return r;
    }
    public Image getImg() { return img; }
    public Cookie getCookie() { return cookie; }

    public void setR(String r) {
        this.r = r;
    }
    public void setN(String n) {
        this.n = n;
    }
    public void setD(String d) {
        this.d = d;
    }
    public void setImg(Image img) { this.img = img; }
    public void setCookie(Cookie cookie) { this.cookie = cookie; }

}
