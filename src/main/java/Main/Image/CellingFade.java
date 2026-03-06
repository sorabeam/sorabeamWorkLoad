package Main.Image;

import javafx.scene.layout.Region;

/**
 * Represents a visual fade effect at the top of the screen.
 * This component is typically used as a decorative overlay
 * to smoothly blend the top area of the UI with the scene.
 */
public class CellingFade extends Region {

    /**
     * Creates a top fade overlay region with a specified height.
     * Sets maximum width to fill available horizontal space and
     * applies a vertical white-to-transparent gradient background.
     *
     * @param H the height of the fade region
     */
    public CellingFade(double H) {

        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(H);

        setStyle("""
            -fx-background-color: linear-gradient(to bottom,
                rgba(255, 255, 255, 0.4),
                rgba(255, 255, 255, 0.0)
            );
        """);
    }
}