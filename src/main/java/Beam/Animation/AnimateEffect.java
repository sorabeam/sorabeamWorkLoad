package Beam.Animation;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimateEffect extends ImageView {

    private final int frameWidth;
    private final int frameHeight;
    private final int totalFrames;
    private final int framesPerRow;

    private double frameDuration;
    private boolean loop = true;

    private int currentFrame = 0;
    private double accumulator = 0;

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

    public void restart() {
        currentFrame = 0;
        accumulator = 0;
        draw();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setFrameDuration(double frameDuration) {
        this.frameDuration = frameDuration;
    }
}