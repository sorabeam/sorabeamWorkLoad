package Main.ObjectInGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an action that spawns a game object after a specified delay.
 *
 * A SpawnAction defines what type of object should appear (obstacle, item,
 * or jelly), how long to wait before spawning it, its identifier name,
 * and its vertical position.
 */
public class SpawnAction {

    /**
     * The type of object that will be spawned.
     */
    public enum Type {
        /** Represents an obstacle object. */
        OBSTACLE,

        /** Represents an item object. */
        ITEM,

        /** Represents a jelly object. */
        JELLY
    }

    /**
     * The type of object to spawn.
     */
    public final Type type;

    /**
     * Delay in nanoseconds before the object is spawned.
     */
    public final long delay;

    /**
     * The name identifier used to determine the specific object.
     */
    public final String name;

    /**
     * The vertical spawn position.
     */
    public final int height;

    /**
     * Creates a SpawnAction with the specified parameters.
     *
     * @param type the type of object to spawn
     * @param delay the delay before spawning
     * @param name the object name identifier
     * @param height the vertical spawn position
     */
    public SpawnAction(Type type, long delay, String name, int height) {
        this.type = type;
        this.delay = delay;
        this.name = name;
        this.height = height;
    }

    /**
     * Combines multiple SpawnAction objects or lists of SpawnAction
     * into a single list.
     *
     * This method allows flexible creation of spawn sequences.
     *
     * @param items individual SpawnAction objects or lists of SpawnAction
     * @return a combined list of spawn actions
     */
    public static List<SpawnAction> combine(Object... items) {
        List<SpawnAction> result = new ArrayList<>();

        for (Object obj : items) {
            if (obj instanceof SpawnAction) {
                SpawnAction action = (SpawnAction) obj;
                result.add(action);
            }
            else if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;

                for (Object o : list) {
                    result.add((SpawnAction) o);
                }
            }
        }

        return result;
    }

    /**
     * Creates a SpawnAction that spawns a single jelly.
     *
     * @param delay the delay before spawning
     * @param height the vertical spawn position
     * @param type the jelly type identifier
     * @return a SpawnAction for spawning a jelly
     */
    public static SpawnAction jelly(long delay, int height, int type) {
        return new SpawnAction(SpawnAction.Type.JELLY, delay, "Jelly" + type, height);
    }

    /**
     * Creates a sequence of jelly spawn actions forming a wave pattern.
     *
     * The first jelly has a longer initial delay, while the rest follow
     * with the specified delay.
     *
     * @param delay the delay between each jelly spawn
     * @param type the jelly type identifier
     * @param heights the vertical positions for each jelly
     * @return a list of SpawnAction objects representing a jelly wave
     */
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

    /**
     * Creates a SpawnAction that spawns an obstacle.
     *
     * @param delay the delay before spawning
     * @param height the vertical spawn position
     * @param level the obstacle level identifier
     * @param type the obstacle type identifier
     * @return a SpawnAction for spawning an obstacle
     */
    public static SpawnAction obstacle(long delay, int height, int level, int type) {
        return new SpawnAction(Type.OBSTACLE, delay, "Obs_" + level + "_" + type, height);
    }

    /**
     * Creates a SpawnAction that spawns an item.
     *
     * @param delay the delay before spawning
     * @param height the vertical spawn position
     * @param name the item name identifier
     * @return a SpawnAction for spawning an item
     */
    public static SpawnAction item(long delay, int height, String name) {
        return new SpawnAction(Type.ITEM, delay, name, height);
    }
}