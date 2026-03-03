package Pors.ObjectInGame;

public class SpawnAction {

    public enum Type { OBSTACLE, ITEM, JELLY }

    public final Type type;
    public final long delay; // หน่วงจากตัวก่อนหน้า (ns)
    public final String name;
    public final int value; // damage / heal
    public final int height;

    public SpawnAction(Type type, long delay, String name, int value, int height) {
        this.type = type;
        this.delay = delay;
        this.name = name;
        this.value = value;
        this.height = height;
    }
}
