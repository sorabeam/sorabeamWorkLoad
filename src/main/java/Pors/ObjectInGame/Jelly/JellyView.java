package Pors.ObjectInGame.Jelly;

import Beam.Cookies.Cookie;
import Pors.ObjectInGame.Spawner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static Beam.Scene.InGameScene.groundY;

public class JellyView extends ImageView {

    private BaseJelly jelly;
    private double vx;
    private double vy;

    private boolean falling = false;
    private double gravity = 1000;
    private boolean hasBounced = false;

    public JellyView(BaseJelly jelly, double vx, double vy) {
        this.jelly = jelly;
        this.vx = vx;
        this.vy = vy;

        setImage(new Image("/Image/Jelly/" + jelly.getName() + ".png"));
        if(Objects.equals(jelly.getName(), "Jelly1"))
        {
            setFitWidth(50);
            setFitHeight(50);
        }
        else
        {
            setFitWidth(60);
            setFitHeight(60);
        }
    }

    public void update(double deltaTime) {

        if(falling){
            vy += gravity * deltaTime;
            setRotate(getRotate() + 360 * deltaTime);
        }

        setTranslateX(getTranslateX() + vx * deltaTime);
        setTranslateY(getTranslateY() + vy * deltaTime);

        if(falling){
            double height = getBoundsInLocal().getHeight();
            double bottom = getTranslateY() + getBoundsInLocal().getHeight();

            if(bottom >= groundY){

                setTranslateY(groundY - height);

                if (!hasBounced) {
                    vy = -400;        // แรงเด้งขึ้น (ปรับได้)
                    hasBounced = true;
                } else {
                    vy = 0;           // หลังเด้งครั้งเดียวแล้วหยุด
                    falling = false;  // (ถ้าต้องการให้หยุดตกถาวร)
                }
            }
        }
    }

    public void pullToPlayer(Cookie player, double dt) {
        double x = player.getCookie().getLayoutX()+player.getCookie().getFitWidth()/2+player.getCookie().getTranslateX();
        double y = player.getCookie().getLayoutY()+player.getCookie().getFitHeight()/2+player.getCookie().getTranslateY();
        double ux = x-getTranslateX();
        double uy = y-getTranslateY();
        double dist = Math.hypot(ux, uy);
        if(dist<=1) return;
        double step = Math.abs(Spawner.getSpeed())*dt*2;
        step = Math.min(step, dist);
        setTranslateX(getTranslateX()+ux/dist*step);
        setTranslateY(getTranslateY()+uy/dist*step);
    }

    public void setFalling(boolean falling){
        this.falling = falling;
    }

    public int getScore() {
        return jelly.getScore();
    }

    public BaseJelly getJelly() {
        return jelly;
    }

    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void setSpeedX(double vx) {
        this.vx = vx;
    }
}