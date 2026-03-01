package Beam.Image;

import javafx.scene.layout.Region;

public class TopFade extends Region {

    public TopFade(double H) {

        setMaxWidth(Double.MAX_VALUE); // ให้ขยายเต็มความกว้าง
        setMaxHeight(H);

        setStyle("""
            -fx-background-color: linear-gradient(to bottom,
                rgba(255, 255, 255, 0.4),
                rgba(255, 255, 255, 0.0)
            );
        """);
    }
}
