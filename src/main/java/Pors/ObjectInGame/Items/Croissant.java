package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import Got.GameLogic.GameLogic;

public class Croissant extends BaseItem {

    private double vx;   // ไหลซ้าย
    public double vy = 0;    // ความเร็วแกน Y
    private double gravity = 1500;
    public boolean hasBounced = false;
    private boolean onGround = false;
    private CroissantType type;

    public Croissant(CroissantType type, double vx) {
        super(getImageName(type));
        this.type = type;
        this.vx = vx;
    }

    private static String getImageName(CroissantType type) {

        switch (type) {

            case BUTTER:
                return "CroissantButter";

            case STRAWBERRY:
                return "CroissantStrawberry";

            default:
                return "CroissantOriginal";
        }
    }

    public double getVx() {
        return vx;
    }

    public double getVy() {
        return vy;
    }

    public void updatePhysics(double dt, ItemView view, double groundY) {

        // เร่งลงตลอด
        vy += gravity * dt;

        // ขยับตำแหน่ง
        view.setTranslateX(view.getTranslateX() + vx * dt);
        view.setTranslateY(view.getTranslateY() + vy * dt);

        double bottom = view.getTranslateY() + view.getBoundsInLocal().getHeight();

        if (bottom >= groundY) {

            // วางบนพื้น
            view.setTranslateY(groundY - view.getBoundsInLocal().getHeight());

            if (!hasBounced) {
                vy = -300;       // เด้งขึ้นครั้งเดียว
                hasBounced = true;
            } else {
                vy = 0;          // หยุดแนวดิ่ง
            }
        }
    }

    @Override
    public void interact(Cookie player) {

        switch (type) {

            case ORIGINAL:
                GameLogic.addScore(3000);
                break;

            case BUTTER:
                GameLogic.addScore(1000);
                //player.addSpeedBoost(3);
                break;

            case STRAWBERRY:
                player.heal(30);
                GameLogic.addScore(1000);
                break;
        }
    }
}
