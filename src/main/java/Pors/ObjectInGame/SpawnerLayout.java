package Pors.ObjectInGame;

import java.util.List;

public class SpawnerLayout {
    public static final List<List<SpawnAction>> spawnlayout = List.of(

            // straight way jelly1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            // straight way jelly2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly2", 650)
            ),

            // testArea
            List.of(
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 1_000_000_000L, "ObsTest", 650),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "Obs_1_2", 475),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "Obs_1_3", 650),
                    new SpawnAction(SpawnAction.Type.OBSTACLE, 2_000_000_000L, "Obs_1_4", 0)
            ),

            // Pattern jelly1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 150_000_000L, "Jelly1", 650)
            ),

            // basic jump1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            // double jump1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            //double jump2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            // advanced jump1
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 150_000_000L, "Obs_1_2", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            //big jump 3 object
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 50_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 50_000_000L, "Jelly2", 475),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000L, "Jelly2", 525),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 425),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 50_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000L, "Jelly2", 400),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            //double jump2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650)
            ),

            //basic slice
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            //basic slice2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            //basic slice and jump
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            //basic slice and double jump and slice
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            // advanced jump and slice
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 150_000_000L, "Obs_1_2", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            // slice3 and double jump
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 600),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_2", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 600)
            ),

            // slice3 and jump2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),


                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            //big jump 3 object + advanced jumb + slice2
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 1_000_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 50_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 50_000_000L, "Jelly2", 475),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000L, "Jelly2", 525),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 425),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 50_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000L, "Jelly2", 400),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 550),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 150_000_000L, "Obs_1_2", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 500_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650)
            ),

            //slice + advanced jump + slice + basic jump
            List.of(
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly1", 400),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 150_000_000L, "Obs_1_2", 475),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 350),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 400),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 500),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 100_000_000L, "Obs_1_4", 0),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),

                    new SpawnAction(SpawnAction.Type.JELLY, 200_000_000L, "Jelly1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 525),

                    new SpawnAction(SpawnAction.Type.OBSTACLE, 125_000_000L, "Obs_1_1", 650),
                    new SpawnAction(SpawnAction.Type.JELLY, 100_000_000L, "Jelly2", 500),

                    new SpawnAction(SpawnAction.Type.JELLY, 225_000_000L, "Jelly1", 525),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 575),
                    new SpawnAction(SpawnAction.Type.JELLY, 125_000_000L, "Jelly1", 650)
            )
    );
}