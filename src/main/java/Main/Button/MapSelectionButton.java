package Main.Button;

import Main.Asset;
import Main.GameLogic.GameLogic;
import javafx.scene.image.ImageView;

/**
 * Button used for selecting a map from the map selection popup.
 *
 * This class extends BaseButton and represents an individual
 * selectable map option.
 */
public class MapSelectionButton extends BaseButton{

    /**
     * Stores the map number associated with this button.
     */
    private final int mapNo;

    /**
     * Reference to the MapPopupButton that will be updated
     * after a map is selected.
     */
    private final MapPopupButton targetButton;

    /**
     * Initializes the map selection button with an image,
     * reference to the popup button, and the assigned map number.
     *
     * @param img image used as the button graphic
     * @param targetButton reference to the MapPopupButton
     * @param mapNo map number associated with this button
     */
    public MapSelectionButton(ImageView img , MapPopupButton targetButton, int mapNo) {
        super(img);
        this.mapNo = mapNo;
        this.targetButton = targetButton;
    }

    /**
     * Handles the map selection action.
     *
     * Sets the selected map in GameLogic and updates the
     * MapPopupButton image accordingly.
     */
    @Override
    public void handleClick() {
        super.handleClick();
        System.out.println(mapNo);
        GameLogic.setMap(mapNo);
        System.out.println(GameLogic.getMap());
        targetButton.setImg(Asset.createImageView("MAP"+mapNo+"P",0,400));
    }
}
