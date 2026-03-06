package Main.Pets;

import Main.Asset;

/**
 * Represents a simple example pet used as a template or placeholder.
 *
 * This class initializes a pet with basic images and information,
 * but does not implement any special skill behavior.
 */
public class SamplePet extends Pet {

    /**
     * Creates a SamplePet with the specified id, name, description,
     * and image key used for loading the pet image.
     *
     * @param id the unique identifier of the pet
     * @param name the name of the pet
     * @param description a short description of the pet
     * @param imageKey the asset key used to load the pet image
     */
    public SamplePet(int id, String name, String description, String imageKey) {
        super(id, name, description);

        setView(Asset.createImageView("Selecting_Boba",0,480));
        setViewImage(Asset.getImage(imageKey));
        setBackGroundImage(Asset.getImage("Selecting_Boba"));
        setButtonImage(Asset.getImage(imageKey));
    }

    /**
     * Activates the pet's skill.
     *
     * This implementation does nothing and serves as a placeholder
     * for subclasses or testing purposes.
     */
    @Override
    public void useSkill() {

    }
}