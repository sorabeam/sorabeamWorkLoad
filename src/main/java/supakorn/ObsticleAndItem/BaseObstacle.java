package supakorn.ObsticleAndItem;

import supakorn.CD;

public class BaseObstacle implements Interactable {
    private String name;
    private int damage;

    public BaseObstacle(String name, int damage)
    {
        setName(name);
        setDamage(damage);
    }

    @Override
    public void interact(CD player) {
        //player.takeDamage(damage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
