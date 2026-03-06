package Main.Button;

import Main.CharacterData;
import Main.Cookies.Cookie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Button used for selecting a character in the character selection screen.
 *
 * This class extends BaseButton and stores character information
 * such as name, description, record, image, and the associated Cookie object.
 */
public class CharacterButton extends BaseButton {

    /**
     * Stores the character name.
     */
    private String name;

    /**
     * Stores the character description.
     */
    private String description;

    /**
     * Stores the character's best record or score.
     */
    private String record;

    /**
     * Stores the character image.
     */
    private Image image;

    /**
     * Stores the associated Cookie object of the character.
     */
    private Cookie cookie;

    /**
     * Initializes the character button with image view,
     * character information, and associated cookie data.
     *
     * @param imageView image view used as the button graphic
     * @param name character name
     * @param description character description
     * @param record character best record or score
     * @param image character image
     * @param cookie associated Cookie object
     */
    public CharacterButton(ImageView imageView, String name, String description, String record, Image image, Cookie cookie) {
        super(imageView);

        this.name = name;
        this.description = description;
        this.record = record;
        this.image = image;
        this.cookie = cookie;
    }

    /**
     * Handles the click event and sets the selected cookie
     * in CharacterData as the current character.
     */
    @Override
    public void handleClick() {
        super.handleClick();
        CharacterData.setCurrent_Cookie(cookie);
    }

    /**
     * Returns the character name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the character best record.
     */
    public String getRecord() {
        return record;
    }

    /**
     * Returns the character image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Returns the associated Cookie object.
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * Sets the character name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the character description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the character record.
     */
    public void setRecord(String rarity) {
        this.record = rarity;
    }

    /**
     * Sets the character image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Sets the associated Cookie object.
     */
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
