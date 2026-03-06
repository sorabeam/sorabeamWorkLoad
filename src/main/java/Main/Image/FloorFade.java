package Main.Image;

import javafx.scene.layout.Region;

/**
 * Represents a visual fade effect at the bottom of the screen.
 * This component is typically used as a decorative overlay
 * to smoothly darken the lower area of the UI.
 */
public class FloorFade extends Region {

    /**
     * Creates a bottom fade overlay region with the specified height.
     * Sets maximum width to fill available horizontal space and
     * applies a dark-to-transparent gradient background.
     *
     * @param H the height of the fade region
     */
    public FloorFade(double H) {

        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(H);

        setStyle("-fx-background-color: linear-gradient(to top,\n    rgba(2, 2, 2, 0.9),\n    rgba(255, 255, 255, 0.0)\n);");
    }
}
