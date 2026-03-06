package Main.Image;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Main.Asset;

/**
 * A UI component that renders text with a black outline effect.
 * This class uses two layered {@link Text} nodes: one for the stroke (outline)
 * and one for the fill (main text color). The layers are stacked using a StackPane
 * to create an outlined text appearance commonly used for game UI.
 */
public class OutlineTextImage extends StackPane {

    /**
     * Custom Chango font loaded from resources.
     */
    public static final Font FONT_CHANGO =
            Font.loadFont(Asset.class.getResourceAsStream("/Fonts/Chango-Regular.ttf"), 30);

    /**
     * Custom Mali font loaded from resources.
     */
    public static final Font FONT_MALI =
            Font.loadFont(Asset.class.getResourceAsStream("/Fonts/Mali-Italic.ttf"), 30);

    /**
     * Text layer used to render the outline (black stroke).
     */
    private final Text stroke = new Text();

    /**
     * Text layer used to render the main fill color.
     */
    private final Text fill = new Text();

    /**
     * Creates outlined text using the specified string, font type,
     * and font size. The constructor selects the font (M for Mali,
     * C for Chango), configures the stroke and fill layers, and
     * adds both layers to the StackPane to produce the outline effect.
     *
     * @param text the text content to display
     * @param F the font type ('M' for Mali, 'C' for Chango)
     * @param size the font size of the text
     */
    public OutlineTextImage(String text, char F, int size) {

        Font textFont;
        if(F == 'M'){
            textFont = FONT_MALI;
        } else if(F == 'C'){
            textFont = FONT_CHANGO;
        } else{
            textFont = FONT_MALI;
        }

        stroke.setText(text);
        stroke.setFont(Font.font(textFont.getFamily(), size));
        stroke.setFill(Color.TRANSPARENT);
        stroke.setStroke(Color.BLACK);
        stroke.setStrokeWidth(6);

        fill.setText(text);
        fill.setFont(Font.font(textFont.getFamily(), size));
        fill.setFill(Color.WHITE);

        setMaxHeight(20);
        getChildren().addAll(stroke, fill);
    }

    /**
     * Updates the text content for both the stroke and fill layers.
     *
     * @param text the new text to display
     */
    public void setText(String text){
        stroke.setText(text);
        fill.setText(text);
    }

    /**
     * Sets the text alignment for both the stroke and fill layers.
     *
     * @param textAlignment the alignment to apply to the text
     */
    public void setTextAlignment(TextAlignment textAlignment){
        stroke.setTextAlignment(textAlignment);
        fill.setTextAlignment(textAlignment);
    }

    /**
     * Applies a drop shadow effect to the stroke layer,
     * enhancing the visibility of the text outline.
     *
     * @param d the DropShadow effect to apply
     */
    public void setDropShadow(DropShadow d) {
        stroke.setEffect(d);
    }

    /**
     * Changes the fill color of the text while keeping
     * the black outline unchanged.
     *
     * @param c the new fill color
     */
    public void setColor(Color c){
        fill.setFill(c);
    }

}
