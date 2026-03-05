package Pors.ObjectInGame;

import Got.GameLogic.GameLogic;

import java.util.ArrayList;
import java.util.List;

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

    public static List<SpawnAction> combine(Object... items) {
        List<SpawnAction> result = new ArrayList<>();
        for (Object obj : items) {
            if (obj instanceof SpawnAction action) {
                result.add(action);
            }
            else if (obj instanceof List<?> list) {
                for (Object o : list) {
                    result.add((SpawnAction) o);
                }
            }
        }
        return result;
    }

    public static SpawnAction jelly(long delay, int height, int type) {
        return new SpawnAction(SpawnAction.Type.JELLY, delay, "Jelly" + type, height);
    }

    public static List<SpawnAction> jellyWave(long delay, int type, int... heights) {
        List<SpawnAction> list = new ArrayList<>();
        for(int i=0;i<heights.length;i++) {
            if(i == 0)
            {
                list.add(new SpawnAction(
                        SpawnAction.Type.JELLY,
                        1_000_000_000L,
                        "Jelly" + type,
                        heights[i]
                ));
                continue;
            }
            list.add(new SpawnAction(
                    SpawnAction.Type.JELLY,
                    delay,
                    "Jelly" + type,
                    heights[i]
            ));
        }
        return list;
    }

    public static SpawnAction obstacle(long delay, int height, int level, int type) {
        return new SpawnAction(Type.OBSTACLE, delay, "Obs_" + level + "_" + type, height);
    }

    public static SpawnAction item(long delay, int height, String name) {
        return new SpawnAction(Type.ITEM, delay, name, height);
    }
}
