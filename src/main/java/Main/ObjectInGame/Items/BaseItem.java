package Main.ObjectInGame.Items;

import Main.ObjectInGame.Interactable;

/**
 * Abstract base class representing an item in the game.
 *
 * This class stores the common property of all items, which is the item name,
 * and implements the Interactable interface so that items can be interacted
 * with inside the game.
 */
public abstract class BaseItem implements Interactable {

    /**
     * The name of the item.
     */
    private String name;

    /**
     * Creates a new BaseItem with the specified name.
     *
     * @param name the name assigned to this item
     */
    public BaseItem(String name){
        setName(name);
    }

    /**
     * Returns the name of this item.
     *
     * @return the item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of this item.
     *
     * @param name the new name to assign to this item
     */
    public void setName(String name) {
        this.name = name;
    }
}