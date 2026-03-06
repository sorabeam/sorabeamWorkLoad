package Main.Button;

import Main.Pets.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import Main.Image.OutlineTextImage;

/**
 * Button used for selecting pets in the pet selection interface.
 *
 * This class extends BaseButton and displays pet information
 * including the pet image, name, and favorite button.
 */
public class PetsButton extends BaseButton{

    /**
     * Main image displayed on the button.
     */
    ImageView img;

    /**
     * Pet preview image.
     */
    Image petImage;

    /**
     * Background image associated with the pet.
     */
    Image backGround;

    /**
     * Name of the pet.
     */
    String name;

    /**
     * Description of the pet.
     */
    String description;

    /**
     * Reference to the Pet object represented by this button.
     */
    Pet pet;

    /**
     * Initializes the button using pet data, sets image properties,
     * creates outlined text, and overlays a FavoriteButton.
     *
     * @param pet pet object used to populate button data
     * @param txtSize text size for the pet name
     * @param MarginBtm margin used to position the text
     */
    public PetsButton(Pet pet, int txtSize, double MarginBtm) {
        super(new ImageView(pet.getButtonImage()));

        img = new ImageView(pet.getButtonImage());
        img.setFitWidth(230);
        img.setPreserveRatio(true);
        petImage = pet.getView().getImage();
        backGround = pet.getBackGroundImage();
        name = pet.getName();
        description = pet.getDescription();

        img.setFitWidth(270);

        super.setGraphic(img);

        OutlineTextImage txt = setText(name,txtSize,MarginBtm);
        FavoriteButton fav = buildFav();
        StackPane newImg = new StackPane(fav,img,txt);
        super.setGraphic(newImg);

        this.pet = pet;
    }

    /**
     * Set message and positions outlined text for this button.
     *
     * @param text text displayed on the button
     * @param txtSize text size
     * @param MarginBtm bottom margin for positioning
     * @return OutlineTextImage used to display the text
     */
    private OutlineTextImage setText(String text, int txtSize, double MarginBtm){

        OutlineTextImage txt = new OutlineTextImage(text,'M',txtSize);
        StackPane.setAlignment(txt,Pos.BOTTOM_CENTER);
        StackPane.setMargin(txt,new Insets(0,0,MarginBtm,0));

        return txt;
    }

    /**
     * Creates and positions a FavoriteButton overlay.
     *
     * @return configured FavoriteButton
     */
    private FavoriteButton buildFav(){

        FavoriteButton fav = new FavoriteButton();
        fav.setHeight(50);

        StackPane.setAlignment(fav,Pos.BOTTOM_LEFT);
        StackPane.setMargin(fav , new Insets(0,0,18,40));

        return fav;
    }

    /**
     * Returns the main image view of the button.
     *
     * @return main image view displayed on the button
     */
    public ImageView getImg() {
        return img;
    }

    /**
     * Sets the main image view of the button.
     *
     * @param img new image view displayed on the button
     */
    public void setImg(ImageView img) {
        this.img = img;
    }

    /**
     * Returns the pet preview image.
     *
     * @return pet preview image
     */
    public Image getPetImage() {
        return petImage;
    }

    /**
     * Sets the pet preview image.
     *
     * @param petImage pet preview image
     */
    public void setPetImage(Image petImage) {
        this.petImage = petImage;
    }

    /**
     * Returns the background image associated with the pet.
     *
     * @return background image of the pet
     */
    public Image getBackGround() {
        return backGround;
    }

    /**
     * Sets the background image associated with the pet.
     *
     * @param backGround background image of the pet
     */
    public void setBackGround(Image backGround) {
        this.backGround = backGround;
    }

    /**
     * Returns the name of the pet.
     *
     * @return pet name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the pet name.
     *
     * @param name pet name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the pet.
     *
     * @return pet description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the pet description.
     *
     * @param description pet description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the Pet object represented by this button.
     *
     * @return pet object represented by this button
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Sets the Pet object represented by this button.
     *
     * @param pet pet object represented by this button
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
