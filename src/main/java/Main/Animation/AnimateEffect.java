package Main.Animation;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a sprite sheet animation effect.
 *
 * This class displays animated visual effects using a sprite sheet.
 * The animation advances frame by frame based on elapsed time
 * and frame duration.
 *
 * The first frame is displayed immediately after initialization.
 */
public class AnimateEffect extends ImageView {

    /**
     * Stores the width of a single animation frame in the sprite sheet.
     */
    private final int frameWidth;

    /**
     * Stores the height of a single animation frame in the sprite sheet.
     */
    private final int frameHeight;

    /**
     * Stores the total number of frames contained in the animation.
     */
    private final int totalFrames;

    /**
     * Stores the number of frames arranged in each row of the sprite sheet.
     */
    private final int framesPerRow;

    /**
     * Stores the time duration each frame should be displayed
     * before switching to the next frame.
     */
    private double frameDuration;

    /**
     * Determines whether the animation repeats continuously
     * after reaching the last frame.
     */
    private boolean loop = true;

    /**
     * Stores the index of the currently displayed animation frame.
     */
    private int currentFrame = 0;

    /**
     * Stores the accumulated elapsed time used to determine
     * when to advance to the next animation frame.
     */
    private double accumulator = 0;

    /**
     * Initializes the animation effect using a sprite sheet
     * and frame configuration, and displays the first frame
     * of the animation.
     *
     * @param spriteSheet the sprite sheet image containing animation frames
     * @param frameWidth width of each frame in the sprite sheet
     * @param frameHeight height of each frame in the sprite sheet
     * @param totalFrames total number of frames in the animation
     * @param framesPerRow number of frames per row in the sprite sheet
     * @param frameDuration duration each frame should be displayed
     */
    public AnimateEffect(Image spriteSheet,
                         int frameWidth,
                         int frameHeight,
                         int totalFrames,
                         int framesPerRow,
                         double frameDuration) {

        super(spriteSheet);

        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.totalFrames = totalFrames;
        this.framesPerRow = framesPerRow;
        this.frameDuration = frameDuration;

        draw(); // show first frame immediately
    }

    /**
     * Updates the animation by advancing frames based on
     * the elapsed time and frame duration.
     *
     * @param deltaTime elapsed time since the last update
     */
    public void update(double deltaTime) {

        accumulator += deltaTime;

        while (accumulator >= frameDuration) {

            accumulator -= frameDuration;
            currentFrame++;

            if (currentFrame >= totalFrames) {
                currentFrame = loop ? 0 : totalFrames - 1;
            }

            draw();
        }
    }

    /**
     * Updates the displayed portion of the sprite sheet by
     * setting the viewport to the current animation frame.
     */
    private void draw() {

        int col = currentFrame % framesPerRow;
        int row = currentFrame / framesPerRow;

        setViewport(new Rectangle2D(
                col * frameWidth,
                row * frameHeight,
                frameWidth,
                frameHeight
        ));
    }

    /**
     * Resets the animation to the first frame and clears
     * the accumulated time.
     */
    public void restart() {
        currentFrame = 0;
        accumulator = 0;
        draw();
    }

    /**
     * Sets whether the animation should loop continuously.
     *
     * @param loop true if the animation should repeat
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    /**
     * Sets the duration each frame should be displayed.
     *
     * @param frameDuration duration of a single frame
     */
    public void setFrameDuration(double frameDuration) {
        this.frameDuration = frameDuration;
    }
}