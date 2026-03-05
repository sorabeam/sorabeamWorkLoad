package Beam.UI.InGameUI;

import Beam.Cookies.Cookie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class CoodownBar extends StackPane {

    public Rectangle frame;
    public Rectangle background;
    public Rectangle fill;
    private Cookie cookie;

    public CoodownBar (Cookie cookie) {
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
