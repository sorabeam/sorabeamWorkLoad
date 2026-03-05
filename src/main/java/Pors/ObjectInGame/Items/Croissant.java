package Pors.ObjectInGame.Items;

import Beam.Cookies.Cookie;
import GameLogic.GameLogic;

public class Croissant extends BaseItem {

    private double vx;
    public double vy = 0;
    private double gravity = 1500;
    public boolean hasBounced = false;
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

    public void updatePhysics(double dt, ItemView view, double groundY) {

        vy += gravity * dt;

        view.setTranslateX(view.getTranslateX() + vx * dt);
        view.setTranslateY(view.getTranslateY() + vy * dt);

        double bottom = view.getTranslateY() + view.getBoundsInLocal().getHeight();

        if (bottom >= groundY) {

            view.setTranslateY(groundY - view.getBoundsInLocal().getHeight());

            if (!hasBounced) {
                vy = -300;
                hasBounced = true;
            } else {
                vy = 0;
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
                new ChillyBoost().interact(player);
                break;

            case STRAWBERRY:
                player.heal(20);
                GameLogic.addScore(1000);
                break;
        }
    }

    public void setSpeed(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
}
