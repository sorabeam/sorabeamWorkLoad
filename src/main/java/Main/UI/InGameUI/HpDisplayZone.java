package Main.UI.InGameUI;

import Main.CharacterData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * A visual HP bar display for the current character during gameplay.
 * <p>
 * This class creates a large health bar positioned at the top of the screen.
 * The bar consists of a frame, background, and a dynamic fill that represents
 * the remaining HP of the currently selected cookie. The fill width updates
 * smoothly over time to create a gradual animation effect when HP changes.
 */
public class HpDisplayZone extends StackPane {

    /**
     * Maximum HP value of the current character.
     */
    int max_hp;

    /**
     * Current HP value retrieved from CharacterData.
     */
    int current_hp;

    /**
     * Width of the HP bar fill area.
     */
    double barWidth = 980;

    /**
     * Height of the HP bar fill area.
     */
    double barHeight = 32;

    /**
     * Current displayed HP percentage used for smooth animation.
     */
    private double displayPercent = 1.0;

    /**
     * Controls interpolation speed when updating the HP bar.
     */
    private double smoothSpeed = 5.0;

    /**
     * Dynamic fill rectangle representing the remaining HP.
     */
    Rectangle cdFill;

    /**
     * Constructs the HP display bar.
     * <p>
     * The constructor initializes the frame, background, and fill rectangles
     * that form the HP bar. The components are arranged inside a StackPane
     * and positioned at the top center of the screen.
     */
    public HpDisplayZone() {

        max_hp = CharacterData.getCurrent_Cookie().getMaxHp();

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

    }

    /**
     * Updates the HP bar based on the current character's HP value.
     * <p>
     * The method calculates the target HP percentage using the current
     * and maximum HP. The displayed HP value is interpolated smoothly
     * toward the target percentage to create an animated transition.
     *
     * @param dt delta time used to control the smooth animation speed
     */
    public void updateHpBar(double dt) {

        current_hp = CharacterData.getCurrent_Cookie().get_Hp();

        if (max_hp <= 0) return;

        double targetPercent = (double) current_hp / max_hp;

        targetPercent = Math.max(0, Math.min(1, targetPercent));

        displayPercent += (targetPercent - displayPercent) * smoothSpeed * dt;

        cdFill.setWidth(barWidth * displayPercent);

        Color color = Color.RED.interpolate(Color.WHITE, 0.3);

        cdFill.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, color.brighter()),
                new Stop(1, color.darker())
        ));
    }
}

