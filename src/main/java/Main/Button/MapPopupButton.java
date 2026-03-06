package Main.Button;

import Main.Asset;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Button used to open the map selection popup.
 *
 * This class extends BaseButton and displays an overlay
 * containing selectable map buttons.
 */
public class MapPopupButton extends BaseButton{

    /**
     * Temporary overlay container used to display
     * the map selection popup.
     */
    private StackPane overlay;

    /**
     * Root container where the popup overlay is added.
     */
    private final StackPane root;

    /**
     * Map selection button for Map 1.
     */
    private final MapSelectionButton map1 = new MapSelectionButton(Asset.createImageView("MAP1P",400,400),this,1);

    /**
     * Map selection button for Map 2.
     */
    private final MapSelectionButton map2 = new MapSelectionButton(Asset.createImageView("MAP2P",400,400),this,2);

    /**
     * Map selection button for Map 3.
     */
    private final MapSelectionButton map3 = new MapSelectionButton(Asset.createImageView("MAP3P",400,400),this,3);

    /**
     * Initializes the popup button with an image and root container
     * reference, and prepares map selection buttons with deleteThis().
     *
     * @param img image used as the button graphic
     * @param root root container where the popup overlay will be added
     */
    public MapPopupButton(ImageView img, StackPane root) {
        super(img);
        this.root = root;

        deleteThis(map1);
        deleteThis(map2);
        deleteThis(map3);
    }

    /**
     * Updates the graphic image of the button.
     *
     * @param img new image view used as the button graphic
     */
    public void setImg(ImageView img){
        setGraphic(img);
    }

    /**
     * Displays an overlay with selectable maps by calling showMap().
     */
    @Override
    public void handleClick() {
        super.handleClick();

        showMap();
    }

    /**
     * Creates and displays the popup overlay containing
     * map selection buttons.
     */
    private void showMap() {
        overlay = new StackPane();
        ImageView BgPane = Asset.createImageView("MBGS",0,440);

        VBox MapVB = new VBox(map1,map2,map3);

        MapVB.setMaxHeight(400);
        MapVB.setMaxWidth(400);
        MapVB.setSpacing(10);

        overlay.getChildren().addAll(BgPane,MapVB);

        overlay.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        overlay.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        overlay.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane.setMargin(overlay,new Insets(-80,0,0,-80));
        StackPane.setMargin(MapVB,new Insets(20,0,0,0));
        root.getChildren().addAll(overlay);
    }

    /**
     * Wraps the button's action to remove the popup overlay
     * after a map selection is made.
     *
     * @param button map selection button to attach the action
     */
    private void deleteThis(MapSelectionButton button) {

        var oldAction = button.getOnAction();
        button.setOnAction(e -> {

            if (oldAction != null) {
                oldAction.handle(e);
            }

            root.getChildren().remove(overlay);
        });
    }

}
