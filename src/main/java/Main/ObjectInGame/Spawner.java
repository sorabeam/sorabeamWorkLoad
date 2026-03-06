package Main.ObjectInGame;

import Main.Cookies.Cookie;
import Main.MediaPlayer;
import Main.Cookies.CrossiantCookie;
import Main.GameLogic.GameLogic;
import Main.ObjectInGame.Items.*;
import Main.ObjectInGame.Jelly.BaseJelly;
import Main.ObjectInGame.Jelly.JellyView;
import Main.ObjectInGame.Obstacle.BaseObstacle;
import Main.ObjectInGame.Obstacle.ObstacleView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Handles spawning, updating, and collision processing for obstacles,
 * items, and jellies in the game.
 *
 * This class reads predefined spawn patterns, creates game objects
 * at the correct time, updates their movement, and applies interaction
 * effects when they collide with the player.
 */
public class Spawner {

    /**
     * The pane that contains all spawned game objects.
     */
    private final Pane gameLayer;

    /**
     * The player character used for collision checks and item effects.
     */
    private final Cookie cookie;

    /**
     * The default horizontal movement speed of spawned objects.
     */
    private static final double DEFAULT_SPEED = -350;

    /**
     * The current horizontal movement speed applied to spawned objects.
     */
    private static double speed = DEFAULT_SPEED;

    /**
     * Tracks the current tutorial pattern position.
     */
    private int begin = 0;

    /**
     * The list of spawn sets used to control object spawning patterns.
     */
    private final List<List<SpawnAction>> spawnSets = SpawnerLayout.getSpawnLayout();

    /**
     * The index of the current spawn set being processed.
     */
    private int currentSetIndex = 0;

    /**
     * The index of the current action inside the active spawn set.
     */
    private int currentActionIndex = 0;

    /**
     * The timestamp of the last spawned action.
     */
    private long lastSpawnTime = 0;

    /**
     * The predefined tutorial spawn pattern indices.
     */
    private final int[] tutorialPatterns = {3,4,9};

    /**
     * Creates a spawner for the specified game layer and player.
     *
     * @param gameLayer the pane where spawned objects are displayed
     * @param cookie the player character
     */
    public Spawner(Pane gameLayer, Cookie cookie) {
        this.gameLayer = gameLayer;
        this.cookie = cookie;
    }

    /**
     * Updates the spawning system for the current frame.
     *
     * This method spawns new objects when needed, updates all active
     * obstacles, items, and jellies, checks collisions with the player,
     * and spawns a croissant if the current cookie is ready to create one.
     *
     * @param now the current timestamp
     * @param deltaTime the time elapsed since the last update
     */
    public void update(long now, double deltaTime) {
        spawnBySet(now);
        updateObstacles(deltaTime);
        updateItem(deltaTime);
        updateJelly(deltaTime);
        checkCollision(cookie);

        if (cookie instanceof CrossiantCookie) {
            CrossiantCookie croissant = (CrossiantCookie) cookie;

            if (croissant.isCroissantReady()) {
                CroissantType type = croissant.consumeCroissant();
                spawnCroissant(type);
            }
        }
    }

    /**
     * Spawns the next object in the current spawn set when its delay has passed.
     *
     * When all actions in the current set are finished, the spawner switches
     * to either a tutorial pattern or a random pattern based on the current map.
     *
     * @param now the current timestamp
     */
    private void spawnBySet(long now) {
        List<SpawnAction> set = spawnSets.get(currentSetIndex);

        if (currentActionIndex >= set.size()) {

            if(begin < tutorialPatterns.length){
                currentSetIndex = tutorialPatterns[begin];
                begin++;
            }
            else{
                int level = GameLogic.getMap();

                int min = (level - 1) * 4;
                int max = spawnSets.size() - 1 - ((3 - level) * 5);

                currentSetIndex = (int)(Math.random() * (max - min + 1)) + min;
            }
            System.out.println(currentSetIndex);
            currentActionIndex = 0;
            lastSpawnTime = now;
            return;
        }

        SpawnAction action = set.get(currentActionIndex);

        if (lastSpawnTime == 0) {
            lastSpawnTime = now;
            return;
        }

        if (now - lastSpawnTime < action.delay) return;

        lastSpawnTime = now;

        if (action.type == SpawnAction.Type.OBSTACLE) {
            int level = GameLogic.getMap();

            String name = action.name;

            /**
             * Replaces the map number in the obstacle name so the correct
             * obstacle variation is used for the current level.
             */
            name = name.replaceAll("Obs_\\d+_", "Obs_" + level + "_");

            ObstacleView obs = new ObstacleView(
                    new BaseObstacle(name),
                    speed,
                    0
            );
            obs.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            obs.setTranslateY(action.height);

            gameLayer.getChildren().add(obs);

        } else if (action.type == SpawnAction.Type.ITEM){
            ItemView item;
            if(Objects.equals(action.name, "BigHealingPotion")) {
                item = new ItemView(
                        new BigHealingPotion(),
                        speed,
                        0
                );
            } else if(Objects.equals(action.name, "SpeedBoost")) {
                item = new ItemView(
                        new SpeedBoost(),
                        speed,
                        0
                );
            } else if(Objects.equals(action.name, "Magnetic")) {
                item = new ItemView(
                        new Magnetic(),
                        speed,
                        0
                );
            } else {
                item = new ItemView(
                        new HealingPotion(),
                        speed,
                        0
                );
            }
            item.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            item.setTranslateY(action.height);
            gameLayer.getChildren().add(item);
        } else {
            JellyView jelly = new JellyView(
                    new BaseJelly(action.name),
                    speed,
                    0
            );
            jelly.setTranslateX(gameLayer.getLayoutBounds().getWidth() + 50);
            jelly.setTranslateY(action.height);
            gameLayer.getChildren().add(jelly);
        }

        currentActionIndex++;
    }

    /**
     * Updates all obstacle views currently in the game layer.
     *
     * Obstacles move using the current spawner speed and are removed
     * when they leave the visible area.
     *
     * @param deltaTime the time elapsed since the last update
     */
    private void updateObstacles(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ObstacleView) {
                ObstacleView o = (ObstacleView) node;

                o.setSpeed(getSpeed(), 0);
                o.update(deltaTime);

                if (o.getTranslateX() < -200 ||
                        o.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Updates all item views currently in the game layer.
     *
     * Normal items move with the current speed, while croissants use
     * their own physics update logic. Items are removed when they leave
     * the visible area.
     *
     * @param deltaTime the time elapsed since the last update
     */
    private void updateItem(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof ItemView) {
                ItemView i = (ItemView) node;

                i.setSpeed(getSpeed(), 0);

                if (i.getItem() instanceof Croissant) {
                    Croissant croissant = (Croissant) i.getItem();

                    croissant.updatePhysics(
                            deltaTime,
                            i,
                    gameLayer.getLayoutBounds().getHeight() - 80
                    );
                } else {
                    i.update(deltaTime);
                }

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Updates all jelly views currently in the game layer.
     *
     * If the player is magnetic, jellies are pulled toward the player.
     * Otherwise, they move with the current spawner speed. Jellies are
     * removed when they leave the visible area.
     *
     * @param deltaTime the time elapsed since the last update
     */
    private void updateJelly(double deltaTime) {
        Iterator<javafx.scene.Node> it = gameLayer.getChildren().iterator();
        while (it.hasNext()) {
            javafx.scene.Node node = it.next();

            if (node instanceof JellyView) {
                JellyView i = (JellyView) node;

                if (cookie.isMagnetic()) {
                    i.pullToPlayer(cookie, deltaTime);
                } else {
                    i.setSpeedX(getSpeed());
                }

                i.update(deltaTime);

                if (i.getTranslateX() < -100 ||
                        i.getTranslateY() > gameLayer.getLayoutBounds().getHeight() + 100) {
                    it.remove();
                }
            }
        }
    }

    /**
     * Spawns a croissant item generated by a CrossiantCookie.
     *
     * The croissant is created near the right side of the game layer
     * and starts falling into the scene.
     *
     * @param type the type of croissant to spawn
     */
    private void spawnCroissant(CroissantType type) {

        BaseItem croissant = new Croissant(type, speed);

        ItemView view = new ItemView(croissant, speed, 0);

        view.setTranslateX(gameLayer.getLayoutBounds().getWidth() - 200);
        view.setTranslateY(-50);

        gameLayer.getChildren().add(view);
    }

    /**
     * Spawns a rain of ingredient jellies from the top of the screen.
     *
     * Each jelly is given a random horizontal start position and falls
     * with physics enabled.
     */
    public void spawnIngredientRain(){

        String[] ingredients = {
                "ShrimpJelly",
                "GalangalJelly",
                "LemongrassJelly",
                "KaffirLeafJelly"
        };

        for(int i=0;i<20;i++){

            String type = ingredients[(int)(Math.random()*ingredients.length)];

            JellyView jelly = new JellyView(
                    new BaseJelly(type),
                    speed,
                    0
            );

            jelly.setFalling(true);

            jelly.setTranslateX(gameLayer.getLayoutBounds().getWidth() + Math.random()*300);
            jelly.setTranslateY(0 - Math.random()*200);

            gameLayer.getChildren().add(jelly);
        }
    }

    /**
     * Checks collisions between the player and all spawned objects.
     *
     * Obstacles damage the player, items activate their effects, and
     * jellies add score. Collected or hit objects are removed after
     * interaction. Sound effects are also played for items and jellies.
     *
     * @param cookie the player used for collision detection
     */
    private void checkCollision(Cookie cookie) {

        List<javafx.scene.Node> snapshot =
                new ArrayList<>(gameLayer.getChildren());

        List<javafx.scene.Node> toRemove = new ArrayList<>();

        for (javafx.scene.Node node : snapshot) {

            if (node instanceof ObstacleView) {
                ObstacleView obs = (ObstacleView) node;

                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(obs.getBoundsInParent())
                        && !cookie.isInvincible()) {

                    obs.getObstacle().interact(cookie);
                    toRemove.add(node);
                }
            }

            else if (node instanceof ItemView) {
                ItemView item = (ItemView) node;

                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(item.getBoundsInParent())) {

                    item.getItem().interact(cookie);
                    MediaPlayer.getInstance().playSFX("Item");
                    toRemove.add(node);
                }
            }

            else if (node instanceof JellyView) {
                JellyView jelly = (JellyView) node;

                if (cookie.getHitBox()
                        .getBoundsInParent()
                        .intersects(jelly.getBoundsInParent())) {

                    jelly.getJelly().interact(cookie);

                    if (cookie instanceof CrossiantCookie) {
                        CrossiantCookie croissant = (CrossiantCookie) cookie;
                        croissant.onJellyCollected();
                    }

                    MediaPlayer.getInstance().playSFX("Jelly");
                    toRemove.add(node);
                }
            }
        }

        gameLayer.getChildren().removeAll(toRemove);
    }

    /**
     * Sets the global movement speed used by spawned objects.
     *
     * @param speed the new spawner speed
     */
    public static void setSpeed(double speed) {
        Spawner.speed = speed;
    }

    /**
     * Returns the current movement speed used by spawned objects.
     *
     * @return the current spawner speed
     */
    public static double getSpeed() {
        return speed;
    }

    /**
     * Resets the spawner speed to its default value.
     */
    public static void resetSpeed() {
        Spawner.speed = DEFAULT_SPEED;
    }

    /**
     * Returns the default movement speed of spawned objects.
     *
     * @return the default spawner speed
     */
    public static double getDefaultSpeed() {
        return DEFAULT_SPEED;
    }
}