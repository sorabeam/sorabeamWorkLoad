package Main.UI.PetsUI;

import Main.CharacterData;
import Main.Scene.PetsSelectionScene;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import Main.Image.OutlineTextImage;

import static javafx.geometry.Pos.*;

/**
 * A UI component that displays the currently selected pet preview.
 *
 * <p>This view shows the pet image, background image, name, and description.
 * It also passes references of these UI elements back to the
 * {@link PetsSelectionScene} so the scene can dynamically update them
 * when the selected pet changes.</p>
 */
public class SelectingView extends StackPane {

    /**
     * Constructs the main pet preview panel.
     *
     * <p>The panel displays the selected pet with its background, image,
     * name, and description arranged vertically. The component sizes are
     * configured to keep the layout consistent across different screen sizes.
     * References to important UI elements are also sent back to the
     * {@code PetsSelectionScene} so that the scene can update them later
     * when the player selects another pet.</p>
     *
     * @param root the root container used to bind the background height
     * @param petsScene the pet selection scene that receives references
     *                  to the preview components for dynamic updates
     */
    public SelectingView(StackPane root, PetsSelectionScene petsScene){

        setPrefSize(600, 1000);
        setMinSize(400, 550);
        setMaxSize(600, 1000);

        System.out.println(CharacterData.getCurrent_Pet().getViewImage());
        ImageView show = new ImageView( CharacterData.getCurrent_Pet().getViewImage() );
        show.setPreserveRatio(true);

        ImageView selectingBackGround = new ImageView(
                CharacterData.getCurrent_Pet().getBackGroundImage()
        );

        selectingBackGround.setPreserveRatio(false);
        selectingBackGround.setFitWidth(350);
        selectingBackGround.fitHeightProperty().bind(root.heightProperty());
        StackPane.setAlignment(selectingBackGround, CENTER);

        OutlineTextImage pName =
                new OutlineTextImage(CharacterData.getCurrent_Pet().getName(),'C',40);

        OutlineTextImage description =
                new OutlineTextImage(CharacterData.getCurrent_Pet().getDescription(),'M',20);

        description.setTextAlignment(TextAlignment.CENTER);

        show.setFitWidth(450);
        show.setFitHeight(450);

        VBox vbox = new VBox(show, pName, description);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        setAlignment(vbox, Pos.CENTER);

        // send references back to the scene
        petsScene.setShowImage(show);
        petsScene.setBackGroundImage(selectingBackGround);
        petsScene.setName(pName);
        petsScene.setDescription(description);

        getChildren().addAll(selectingBackGround, vbox);
    }
}
