package Main.Pets;

import Main.ObjectInGame.Items.ItemView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Represents the base class for all pets in the game.
 *
 * A pet stores its identity, images, skill state, movement target,
 * speed, and the list of items it can spawn when using its skill.
 */
public abstract class Pet {

    /**
     * The unique identifier of the pet.
     */
    int id;

    /**
     * The cooldown time of the pet skill in milliseconds.
     */
    int cooldownTime;

    /**
     * The background identifier associated with this pet.
     */
    String BGid;

    /**
     * The display name of the pet.
     */
    String name;

    /**
     * A short description of the pet and its skill.
     */
    String description;

    /**
     * The visual representation of the pet.
     */
    ImageView view;

    /**
     * The main image used for the pet.
     */
    Image viewImage;

    /**
     * The background image used in the pet selection UI.
     */
    Image backGroundImage;

    /**
     * The button image used in the pet selection UI.
     */
    Image buttonImage;

    /**
     * Indicates whether the pet skill is ready to be used.
     */
    private boolean isSkillReady;

    /**
     * Indicates whether the pet is currently using its skill.
     */
    private boolean isUsingSkill;

    /**
     * The target X position the pet moves toward.
     */
    private double targetPosX;

    /**
     * The target Y position the pet moves toward.
     */
    private double targetPoxY;

    /**
     * The movement speed of the pet.
     */
    private double speed;

    /**
     * The currently selected index for the spawned item list.
     */
    private int indexRoll = -1;

    /**
     * The list of item suppliers that this pet can spawn.
     */
    private ArrayList<Supplier<ItemView>> spawnItemList;

    /**
     * The probability values used when random item selection is enabled.
     */
    private ArrayList<Double> probability;

    /**
     * Determines whether item selection uses random probability
     * instead of sequential order.
     */
    private boolean useRandomSpin;

    /**
     * Creates a pet with the specified id, name, and description.
     *
     * @param id the pet identifier
     * @param name the pet name
     * @param description the pet description
     */
    public Pet(int id, String name, String description) {
        this.id = id;

        BGid = "BG" + id;
        this.name = name;
        this.description = description;
        useRandomSpin = false;
    }

    /**
     * Returns whether the pet has reached its target position.
     *
     * @return true if the pet is close enough to the target position,
     *         otherwise false
     */
    public boolean hasArrived() {
        double dx = getTargetPosX()-getView().getLayoutX();
        double dy = getTargetPoxY()-getView().getLayoutY();
        return Math.hypot(dx, dy)<=3;
    }

    /**
     * Updates the pet position by moving it toward its target position.
     *
     * @param dt the time elapsed since the last update
     */
    public void update(double dt) {
        double x = getView().getLayoutX();
        double y = getView().getLayoutY();
        double ux = getTargetPosX()-x;
        double uy = getTargetPoxY()-y;
        double dist = Math.hypot(ux, uy);
        if(dist<1e-6) return;
        double step = Math.min(speed*dt, dist);
        view.setLayoutX(x+ux/dist*step);
        view.setLayoutY(y+uy/dist*step);
    }

    /**
     * Updates the current item index used when the pet spawns an item.
     *
     * If random spin is enabled, the index is selected using the
     * configured probability list. Otherwise, the index cycles
     * through the spawn item list in order.
     */
    public void updateIndex() {
        if(useRandomSpin) {
            double r = Math.random();
            double cumulative = 0;
            for (int i = 0; i < probability.size(); i++) {
                cumulative += probability.get(i);
                if (r <= cumulative) {
                    indexRoll = i;
                    return;
                }
            }
            indexRoll = probability.size() - 1;
        } else {
            indexRoll += 1;
            int sz = spawnItemList.size();
            if(indexRoll >= sz) indexRoll = 0;
        }
    }

    /**
     * Returns the pet id.
     *
     * @return the pet id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the pet name.
     *
     * @return the pet name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the background identifier of the pet.
     *
     * @return the background id
     */
    public String getBGid() {
        return BGid;
    }

    /**
     * Activates the pet skill.
     *
     * Each subclass defines its own skill behavior.
     */
    public abstract void useSkill();

    /**
     * Returns the visual representation of the pet.
     *
     * @return the pet view
     */
    public ImageView getView() {
        return view;
    }

    /**
     * Sets the visual representation of the pet.
     *
     * @param view the pet view
     */
    public void setView(ImageView view) {
        this.view = view;
    }

    /**
     * Returns the main pet image.
     *
     * @return the pet image
     */
    public Image getViewImage() {
        return viewImage;
    }

    /**
     * Sets the main pet image.
     *
     * @param viewImage the pet image
     */
    public void setViewImage(Image viewImage) {
        this.viewImage = viewImage;
    }

    /**
     * Returns the background image used for this pet.
     *
     * @return the background image
     */
    public Image getBackGroundImage() {
        return backGroundImage;
    }

    /**
     * Sets the background image used for this pet.
     *
     * @param backGroundImage the background image
     */
    public void setBackGroundImage(Image backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    /**
     * Returns the button image used for this pet.
     *
     * @return the button image
     */
    public Image getButtonImage() {
        return buttonImage;
    }

    /**
     * Sets the button image used for this pet.
     *
     * @param buttonImage the button image
     */
    public void setButtonImage(Image buttonImage) {
        this.buttonImage = buttonImage;
    }

    /**
     * Returns the pet description.
     *
     * @return the pet description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the pet description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the cooldown time of the pet skill.
     *
     * @return the cooldown time in milliseconds
     */
    public int getCooldownTime() {
        return cooldownTime;
    }

    /**
     * Sets the cooldown time of the pet skill.
     *
     * @param cooldownTime the cooldown time in milliseconds
     */
    public void setCooldownTime(int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    /**
     * Sets whether the pet is currently using its skill.
     *
     * @param usingSkill true if the pet is using its skill, otherwise false
     */
    public void setUsingSkill(boolean usingSkill) {
        isUsingSkill = usingSkill;
    }

    /**
     * Sets whether the pet skill is ready to be used.
     *
     * @param skillReady true if the skill is ready, otherwise false
     */
    public void setSkillReady(boolean skillReady) {
        isSkillReady = skillReady;
    }

    /**
     * Returns whether the pet is currently using its skill.
     *
     * @return true if the pet is using its skill, otherwise false
     */
    public boolean isUsingSkill() {
        return isUsingSkill;
    }

    /**
     * Returns whether the pet skill is ready.
     *
     * @return true if the skill is ready, otherwise false
     */
    public boolean isSkillReady() {
        return isSkillReady;
    }

    /**
     * Sets the target position for pet movement.
     *
     * @param targetPosX the target X position
     * @param targetPosY the target Y position
     */
    public void setTargetPos(double targetPosX, double targetPosY) {
        this.targetPosX = targetPosX;
        this.targetPoxY = targetPosY;
    }

    /**
     * Returns the target X position.
     *
     * @return the target X position
     */
    public double getTargetPosX() {
        return targetPosX;
    }

    /**
     * Returns the target Y position.
     *
     * @return the target Y position
     */
    public double getTargetPoxY() {
        return targetPoxY;
    }

    /**
     * Returns the movement speed of the pet.
     *
     * @return the pet speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the movement speed of the pet.
     *
     * @param speed the new speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns the currently selected spawned item.
     *
     * @return the current spawned item view
     */
    public ItemView getCurrentSpawnItem() {
        return spawnItemList.get(indexRoll).get();
    }

    /**
     * Sets the list of items that this pet can spawn.
     *
     * @param spawnItemList the list of item suppliers
     */
    public void setSpawnItemList(ArrayList<Supplier<ItemView>> spawnItemList) {
        this.spawnItemList = spawnItemList;
    }

    /**
     * Returns the list of items that this pet can spawn.
     *
     * @return the spawn item supplier list
     */
    public ArrayList<Supplier<ItemView>> getSpawnItemList() {
        return spawnItemList;
    }

    /**
     * Sets whether item selection should use random probability.
     *
     * @param useRandomSpin true to use random selection, otherwise false
     */
    public void setUseRandomSpin(boolean useRandomSpin) {
        this.useRandomSpin = useRandomSpin;
    }

    /**
     * Returns the probability list used for random item selection.
     *
     * @return the probability list
     */
    public ArrayList<Double> getProbability() {
        return probability;
    }

    /**
     * Sets the probability list used for random item selection.
     *
     * @param probability the probability list
     */
    public void setProbability(ArrayList<Double> probability) {
        this.probability = probability;
    }
}