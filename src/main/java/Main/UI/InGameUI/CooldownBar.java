package Main.UI.InGameUI;

import Main.Cookies.Cookie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 * A visual cooldown bar that follows a cookie character during gameplay.
 * <p>
 * This class displays a small bar above the cookie to represent the
 * remaining cooldown time of the character's skill. The bar consists
 * of a frame, background, and a gradient fill that can shrink or grow
 * to indicate cooldown progress.
 */
public class CooldownBar extends StackPane {

    /**
     * Outer frame of the cooldown bar.
     * This rectangle acts as the border of the bar.
     */
    public Rectangle frame;

    /**
     * Inner dark background of the cooldown bar.
     * This represents the empty cooldown state.
     */
    public Rectangle background;

    /**
     * The fill rectangle representing the remaining cooldown time.
     * Its width can be adjusted dynamically to show cooldown progress.
     */
    public Rectangle fill;

    /**
     * Constructs a cooldown bar that is visually attached to a specific cookie.
     * <p>
     * The bar is composed of a frame, background, and gradient fill.
     * Its position is bound to the cookie's layout position so the bar
     * moves together with the cookie during gameplay.
     *
     * @param cookie the cookie whose position the cooldown bar follows
     */
    public CooldownBar(Cookie cookie) {
        frame = new Rectangle(84, 12);
        frame.setFill(Color.BLACK);
        frame.setArcWidth(10);
        frame.setArcHeight(10);

        background = new Rectangle(80, 8);
        background.setFill(Color.rgb(40, 40, 40));
        background.setArcWidth(8);
        background.setArcHeight(8);

        fill = new Rectangle(80, 8);
        fill.setFill(Color.LIMEGREEN);
        fill.setArcWidth(8);
        fill.setArcHeight(8);

        fill.setFill(new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#b9ff9f")),
                new Stop(0.35, Color.web("#7dff63")),
                new Stop(0.7, Color.web("#39d353")),
                new Stop(1, Color.web("#1faa2a"))
        ));

        background.setLayoutX(2);
        background.setLayoutY(2);

        fill.setLayoutX(2);
        fill.setLayoutY(2);

        StackPane.setAlignment(fill, Pos.CENTER_LEFT);
        StackPane.setMargin(fill,new Insets(0,0,0,3));
        StackPane.setAlignment(background, Pos.CENTER);

        getChildren().addAll(frame, background, fill);

        layoutXProperty().bind(
                cookie.getCookie().layoutXProperty().add(58)
        );

        layoutYProperty().bind(
                cookie.getCookie().layoutYProperty().subtract(18)
        );
    }
}
