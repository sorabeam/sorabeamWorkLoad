package Pors.ObjectInGame.Items;

import Pors.ObjectInGame.Interactable;

public abstract class BaseItem implements Interactable {
    private String name;

    public BaseItem(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean useGravity() {
        return false;
    }
}