package Main.Scene;

import Main.GameLogic.GameLogic;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Base class for all game scenes.
 *
 * This class provides a fixed scene size, clipping area,
 * background setup, and utility methods used by other scenes.
 */
public abstract class BaseScene extends StackPane {

    /**
     * The JavaFX scene associated with this UI container.
     */
    protected Scene scene;

    /**
     * The root layout of the scene.
     */
    protected StackPane root = this;

    /**
     * The base width used for all scenes.
     */
    private static final double BASE_WIDTH = 1440;

    /**
     * The base height used for all scenes.
     */
    private static final double BASE_HEIGHT = 900;

    /**
     * Creates a base scene with fixed dimensions, clipping area,
     * and a default background.
     */
    public BaseScene() {

        scene = GameLogic.getCurScene();

        setMinSize(BASE_WIDTH, BASE_HEIGHT);
        setPrefSize(BASE_WIDTH, BASE_HEIGHT);
        setMaxSize(BASE_WIDTH, BASE_HEIGHT);

        Rectangle clip = new Rectangle(BASE_WIDTH, BASE_HEIGHT);
        setClip(clip);

        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
    }

    /**
     * Creates a flexible spacer used to help arrange UI layouts.
     *
     * If 'V' is specified, the spacer expands vertically in a VBox.
     * If 'H' is specified, the spacer expands horizontally in an HBox.
     *
     * @param c the direction of expansion ('V' for vertical, 'H' for horizontal)
     * @return a Region acting as a layout spacer
     */
    public Region spacer(char c){

        // Similar to SwiftUI Spacer, used to simplify UI layout spacing.

        Region space = new Region();
        if(c == 'V'){
            VBox.setVgrow(space, Priority.ALWAYS);
        } else if (c == 'H') {
            HBox.setHgrow(space, Priority.ALWAYS);
        }
        return space;
    }
}