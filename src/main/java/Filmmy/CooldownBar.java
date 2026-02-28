//package Filmmy;
//
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import Filmmy.Player;
//
//public class CooldownBar {
//    private final Rectangle barBG;
//    private final Rectangle barFill;
//
//    private final Player player;
//
//    public CooldownBar(Player player) {
//
//        this.player = player;
//
//        barBG = new Rectangle(80, 8);
//        barBG.setFill(Color.GRAY);
//        barBG.setArcWidth(10);
//        barBG.setArcHeight(10);
//
//        barFill = new Rectangle(80, 8);
//        barFill.setFill(Color.LIMEGREEN);
//        barFill.setArcWidth(10);
//        barFill.setArcHeight(10);
//    }
//
//    public Rectangle getBarBG() {
//        return barBG;
//    }
//
//    public Rectangle getBarFill() {
//        return barFill;
//    }
//
//    public void update() {
//
//        double ratio = player.getCooldownRatio();
//
//        barBG.setLayoutX(player.getLayoutX() + 60);
//        barBG.setLayoutY(player.getLayoutY() - 15);
//
//        barFill.setLayoutX(player.getLayoutX() + 60);
//        barFill.setLayoutY(player.getLayoutY() - 15);
//
//        barFill.setScaleX(1 - ratio);
//    }
//}
