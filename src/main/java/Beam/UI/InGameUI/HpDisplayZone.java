package Beam.UI.InGameUI;

import Beam.CharactorData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class HpDisplayZone extends StackPane {

    int max_hp;
    int current_hp;
    double percent;

    double barWidth = 980;   // ความกว้างเต็ม
    double barHeight = 32;   // ความสูงคงที่

    Rectangle cdFill;

    public HpDisplayZone() {

        max_hp = CharactorData.getCurrent_Cookie().getMaxhp();

        Rectangle cdFrame = new Rectangle(1000, 50);
        cdFrame.setFill(Color.BLACK);
        cdFrame.setArcWidth(10);
        cdFrame.setArcHeight(10);

        Rectangle cdBackground = new Rectangle(barWidth, barHeight);
        cdBackground.setFill(Color.DARKRED);
        cdBackground.setArcWidth(8);
        cdBackground.setArcHeight(8);

        cdFill = new Rectangle(barWidth, barHeight);
        cdFill.setArcWidth(8);
        cdFill.setArcHeight(8);

        cdBackground.setLayoutX(2);
        cdBackground.setLayoutY(2);

        cdFill.setLayoutX(2);
        cdFill.setLayoutY(2);

        StackPane cdPane = new StackPane(cdFrame, cdBackground, cdFill);
        cdPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        StackPane.setAlignment(cdFill,Pos.CENTER_LEFT);
        StackPane.setMargin(cdFill,new Insets(0,0,0,10));

        getChildren().add(cdPane);
        cdPane.setPadding(new Insets(30,0,0,0));
        StackPane.setAlignment(cdPane, Pos.TOP_CENTER);

        setMaxSize(USE_PREF_SIZE,USE_PREF_SIZE);

        updateHpBar(); // อัปเดตครั้งแรก
    }

    public void updateHpBar() {

        current_hp = CharactorData.getCurrent_Cookie().get_Hp();

        if (max_hp <= 0) return;

        percent = (double) current_hp / max_hp;

        // จำกัดค่า 0 - 1
        percent = Math.max(0, Math.min(1, percent));


        cdFill.setWidth(barWidth * percent);

        Color brightOrange = Color.RED.interpolate(Color.WHITE, 0.3);
        Color color =  Color.RED.interpolate(Color.WHITE, 0.3);

        cdFill.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, color.brighter()),
                new Stop(1, color.darker())
        ));
    }
}
