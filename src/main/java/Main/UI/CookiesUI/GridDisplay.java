package Main.UI.CookiesUI;

import Main.Button.BaseButton;
import javafx.scene.layout.GridPane;
import Main.Button.CharacterButton;

/**
 * A grid layout used to display character selection buttons.
 * <p>
 * This class arranges four buttons in a 2×2 grid structure using a {@link GridPane}.
 * Horizontal and vertical spacing are configured to separate each button,
 * and the grid size is limited to a fixed display area.
 */
public class GridDisplay extends GridPane {

    /**
     * Creates a 2×2 grid layout for displaying character selection buttons.
     * <p>
     * The constructor sets the maximum size of the grid, defines horizontal
     * and vertical spacing between cells, and places each button at a
     * specific row and column position.
     *
     * @param B1 the first character button placed at row 0, column 0
     * @param B2 the second character button placed at row 0, column 1
     * @param B3 the third character button placed at row 1, column 0
     * @param B4 the fourth button placed at row 1, column 1
     */
    public GridDisplay(CharacterButton B1, CharacterButton B2, CharacterButton B3, BaseButton B4)
    {

        setMaxHeight(455);
        setMaxWidth(455);

        setHgap(15);
        setVgap(15);

        add(B1, 0, 0);
        add(B2, 1, 0);
        add(B3, 0, 1);
        add(B4, 1, 1);
    }
}
