package supakorn.Image;

import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class FloorFade extends Region {

    public FloorFade(double H) {

        setMaxWidth(Double.MAX_VALUE); // ให้ขยายเต็มความกว้าง
        setMaxHeight(H);

        setStyle("""
            -fx-background-color: linear-gradient(to top,
                rgba(2, 2, 2, 0.9),
                rgba(255, 255, 255, 0.0)
            );
        """);
    }
}
