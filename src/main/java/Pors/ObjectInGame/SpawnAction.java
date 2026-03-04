package Pors.ObjectInGame;

public class SpawnAction {

    public enum Type { OBSTACLE, ITEM, JELLY }

    public final Type type;
    public final long delay; // หน่วงจากตัวก่อนหน้า (ns)
    public final String name;
    public final int height;

    public SpawnAction(Type type, long delay, String name, int height) {
        this.type = type;
        this.delay = delay;
        this.name = name;
        this.height = height;
    }
}
