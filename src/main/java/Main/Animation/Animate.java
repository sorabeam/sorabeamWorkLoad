package Main.Animation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A sprite animation manager that handles multiple animation states
 * such as Idle, Run, JumpUp-Down, Skill, and others.
 *
 * The class controls the frame sequence of each animation state,
 * adjusts animation speed depending on the current state, and
 * invokes DrawAnimation() to render the sprite accordingly.
 *
 * The animation system uses a sprite sheet where each animation
 * state is stored in a different row.
 */
public class Animate extends ImageView {

    /**
     * Current animation row in the sprite sheet.
     */
    protected int currentRow = 4;

    /**
     * Current frame index being displayed in the animation.
     */
    protected int currentFrame = 0;

    /**
     * Accumulates delta time for frame switching.
     */
    protected double accumulator = 0;

    /**
     * Determines whether the animation should loop when reaching the last frame.
     */
    private boolean isLoop = true;

    /**
     * Maximum frames per row for the current animation state.
     */
    private int maxFPR;// equal to

    /**
     * Width of each sprite in the sprite sheet.
     */
    private final int frameWidth; //Test On 120px

    /**
     * Height of each sprite in the sprite sheet.
     */
    private final int frameHeight; //Test On 139px

    /**
     * Duration (in seconds) each frame is displayed before switching.
     */
    private double frameDuration = 0.15;

    /**
     * Stores maximum frames per row for each animation type.
     */
    private final int[] maxFPRType = new int[9];

    /**
     * Property storing the current animation state as an enum.
     */
    private final ObjectProperty<AnimationType> state;

    /**
     * Creates a sprite animation manager.
     *
     * This animation controller manages multiple animation states
     * such as Idle, Run, JumpUp-Down, Skill, and others. It controls
     * the frame sequence for each state, adjusts animation speed
     * depending on the current state, and calls DrawAnimation()
     * to render the sprite frame.
     *
     * @param image sprite sheet image used for animation
     * @param idle number of frames in the idle animation
     * @param run number of frames in the run animation
     * @param jumpUp number of frames in the jump up animation
     * @param jumpDown number of frames in the jump down animation
     * @param doubleJump number of frames in the double jump animation
     * @param die number of frames in the die animation
     * @param slide number of frames in the slide animation
     * @param takeDamage number of frames in the take damage animation
     * @param skill number of frames in the skill animation
     * @param frameWidth width of each frame in the sprite sheet
     * @param frameHeight height of each frame in the sprite sheet
     */
    public Animate(Image image,int idle,int run,int jumpUp,int jumpDown,int doubleJump,int die,int slide,int takeDamage,
                   int skill,int frameWidth,int frameHeight){

        super(image);

        setFrameDuration(0.08f);

        int maxFPRIdle = Math.max(0, idle);
        int maxFPRRun = Math.max(0, run);
        int maxFPRJumpUp = Math.max(0, jumpUp);
        int maxFPRJumpDown = Math.max(0, jumpDown);
        int maxFPRDoubleJump = Math.max(0, doubleJump);
        int maxFPRDie = Math.max(0, die);
        int maxFPRSlide = Math.max(0, slide);
        int maxFPRTakeDamage = Math.max(0, takeDamage);
        int maxFPRSkill = Math.max(0, skill);

        maxFPRType[0] = maxFPRIdle;
        maxFPRType[1] = maxFPRRun;
        maxFPRType[2] = maxFPRJumpUp;
        maxFPRType[3] = maxFPRJumpDown;
        maxFPRType[4] = maxFPRDoubleJump;
        maxFPRType[5] = maxFPRDie;
        maxFPRType[6] = maxFPRSlide;
        maxFPRType[7] = maxFPRTakeDamage;
        maxFPRType[8] = maxFPRSkill;

        this.maxFPR = maxFPRDoubleJump;

        this.frameWidth = Math.max(0,frameWidth);
        this.frameHeight = Math.max(0,frameHeight);

        state = new SimpleObjectProperty<>(AnimationType.DOUBLE_JUMP);

        state.addListener((obs,
                           oldValue, newValue) -> {
            //System.out.println(newValue + " " + newValue.getRow() );
            currentRow = newValue.getRow();
            currentFrame = 0;
            maxFPR = maxFPRType[newValue.getRow()];

            isLoop = getAnimationState() != AnimationType.SKILL && getAnimationState() != AnimationType.DIE;

            if (getAnimationState() == AnimationType.RUN) {
                setFrameDuration(0.08f);
            } else if (getAnimationState() == AnimationType.DOUBLE_JUMP) {
                setFrameDuration(0.08f);
            } else if (getAnimationState() == AnimationType.SKILL) {
                setFrameDuration(0.12f);
            } else if (getAnimationState() == AnimationType.IDLE) {
                setFrameDuration(0.12f);
            }


        });

        DrawAnimation();
    }

    /**
     * Updates animation timing and moves to the next frame
     * based on accumulated delta time.
     *
     * @param deltaTime time passed since the previous update
     */
    public void update(double deltaTime) {

        accumulator += deltaTime;

        if (accumulator >= frameDuration) {
            accumulator -= frameDuration;

            currentFrame++;

            if (currentFrame >= maxFPR) {
                if (isLoop) {
                    currentFrame = 0;
                } else {
                    currentFrame = maxFPR - 1;
                }
            }

            DrawAnimation();
        }
    }

    /**
     * Sets the duration each frame will be displayed.
     *
     * @param frameDuration duration of a single animation frame
     */
    public void setFrameDuration(float frameDuration){
        this.frameDuration = frameDuration;
    }

    /**
     * Updates the viewport of the ImageView to display
     * the correct frame from the sprite sheet.
     */
    public void DrawAnimation() {

        setViewport(new Rectangle2D(currentFrame * frameWidth,
                currentRow * frameHeight,frameWidth, frameHeight));

    }

    /**
     * Changes the current animation state and resets
     * the current frame.
     *
     * @param state new animation state
     */
    public void changeAnimationState(AnimationType state) {
        this.state.set(state);

    }

    /**
     * Returns the current animation state.
     *
     * @return current animation state
     */
    public AnimationType getAnimationState() {
        return state.get();
    }

}
