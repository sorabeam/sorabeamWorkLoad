package Main.ObjectInGame;

import Main.GameLogic.GameLogic;

import java.util.List;

import static Main.ObjectInGame.SpawnAction.*;

/**
 * Defines predefined spawn patterns used by the spawner system.
 *
 * This class stores reusable obstacle, jelly, and item layouts that are
 * combined into larger spawn sets for different gameplay situations.
 */
public class SpawnerLayout {

    /**
     * The current map level used when creating level-based obstacle names.
     */
    static int level = GameLogic.getMap();

    /**
     * A basic jump pattern with jellies placed around a single obstacle.
     */
    public static List<SpawnAction> basicJump = List.of(
            jelly(200_000_000L, 650, 1),
            jelly(125_000_000L, 575, 1),
            jelly(125_000_000L, 525, 1),

            obstacle(125_000_000L,650,level,1),
            jelly(100_000_000L, 500, 2),

            jelly(225_000_000L, 525, 1),
            jelly(125_000_000L, 575, 1),
            jelly(125_000_000L, 650, 1)
    );

    /**
     * A double jump pattern with a higher obstacle and jelly trail.
     */
    public static final List<SpawnAction> doubleJump = List.of(
            jelly(200_000_000L, 550, 1),
            jelly(125_000_000L, 450, 1),
            jelly(125_000_000L, 350, 1),

            obstacle(100_000_000L,500,level,2),
            jelly(100_000_000L, 300, 2),

            jelly(225_000_000L, 350, 1),
            jelly(125_000_000L, 450, 1),
            jelly(125_000_000L, 550, 1)
    );

    /**
     * A basic slide pattern with a low obstacle and jellies after it.
     */
    public static final List<SpawnAction> basicSlice = List.of(
            obstacle(100_000_000L, 0,level, 4),
            jelly(100_000_000L, 650, 2),

            jelly(200_000_000L, 650, 1),
            jelly(200_000_000L, 650, 1)
    );

    /**
     * An advanced jump pattern that chains multiple jumps and obstacles.
     */
    public static final List<SpawnAction> advancedJump = List.of(
            jelly(200_000_000L, 650, 1),
            jelly(125_000_000L, 575, 1),
            jelly(125_000_000L, 525, 1),

            obstacle(100_000_000L, 650,level, 1),
            jelly(100_000_000L, 500, 2),

            jelly(100_000_000L, 400, 1),

            obstacle(150_000_000L, 500,level, 2),
            jelly(100_000_000L, 325, 2),

            jelly(225_000_000L, 350, 1),
            jelly(125_000_000L, 450, 1),
            jelly(200_000_000L, 550, 1)
    );

    /**
     * A large jump pattern containing several repeated obstacle jumps.
     */
    public static final List<SpawnAction> bigJump3 = List.of(
            jelly(200_000_000L, 650, 1),
            jelly(125_000_000L, 575, 1),
            jelly(125_000_000L, 525, 1),

            obstacle(50_000_000L, 650,level, 1),
            jelly(50_000_000L, 475, 2),

            jelly(200_000_000L, 500, 1),
            jelly(200_000_000L, 525, 1),

            obstacle(100_000_000L, 650,level, 1),
            jelly(100_000L, 525, 2),

            jelly(200_000_000L, 475, 1),
            jelly(200_000_000L, 425, 1),

            obstacle(50_000_000L, 650,level, 1),
            jelly(100_000L, 400, 2),

            jelly(225_000_000L, 475, 1),
            jelly(125_000_000L, 550, 1),
            jelly(125_000_000L, 650, 1)
    );

    /**
     * Returns all predefined spawn layouts used by the spawner.
     *
     * Each entry in the returned list represents one complete spawn pattern
     * made from obstacles, jellies, items, or combinations of reusable
     * smaller patterns.
     *
     * @return a list of spawn pattern sets
     */
    public static List<List<SpawnAction>> getSpawnLayout() {
        return List.of(
                //0 straight way jelly1
                List.of(
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //1 straight way jelly2
                List.of(
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2),
                        jelly(200_000_000L, 650, 2)
                ),

                //2 Pattern jelly1
                jellyWave(
                        150_000_000L, 1,
                        650, 600, 550, 500, 550, 600, 650,
                        600, 550, 500, 550, 600, 650,
                        600, 550, 500, 550, 600, 650
                ),

                //3 basic jump1
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //4 double jump1
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //5 double jump2
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //6 advanced jump1
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //7 big jump 3 object
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        bigJump3,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(500_000_000L, 650, 1)
                ),

                //8 double jump and basic
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //9 basic slice
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //10 healingPotion
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        item(200_000_000L,600,"HealingPotion"),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //11 basic slice2
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        jelly(200_000_000L, 600, 1),
                        jelly(200_000_000L, 600, 1),
                        jelly(200_000_000L, 600, 1),
                        jelly(200_000_000L, 600, 1),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //12 basic slice and jump
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //13 basic slice and double jump and slice
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //14 healingPotion
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        bigJump3,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        item(200_000_000L,600,"SpeedBoost"),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        bigJump3,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //15 advanced jump and slice
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(500_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //16 slice3 and double jump
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        basicSlice,

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        doubleJump
                ),

                //17 slice3 and jump2
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //18 big jump 3 object + advanced jumb + slice2
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        bigJump3,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(500_000_000L, 650, 1),

                        basicSlice,
                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //19 slice + advanced jump + slice + basic jump
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicSlice,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //20 slice + advanced jump + slice + basic jump
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        item(200_000_000L,600,"BigHealingPotion"),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        advancedJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //21 slice + advanced jump + slice + basic jump
                combine(
                        jelly(1_000_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        item(200_000_000L,650,"Magnetic"),

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),

                        basicJump,

                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1),
                        jelly(200_000_000L, 650, 1)
                ),

                //22
                jellyWave(
                        50_000_000L, 1,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500
                ),

                //23
                jellyWave(
                        50_000_000L, 2,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500,
                        650, 600, 550, 500
                )
        );
    }
}