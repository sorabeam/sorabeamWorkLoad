package supakorn.Animation;

import javafx.animation.AnimationTimer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animate extends ImageView implements Animatable{

    protected int currentRow = 0;
    protected int currentFrame = 0;
    protected long lastTime = 0;
    protected double accumulator = 0;

    private int maxRow;                             //0
    private int maxFramePerRow;                     // equal to

    private int frameWidth;                         //Test On 120px
    private int frameHeight;                        //Test On 139px
    private double frameDuration = 0.05;

    private AnimationTimer animator;
    private ObjectProperty<AnimationType> state;

    public Animate(Image image,int maxRow,int maxFramePerRow,int frameWidth,int frameHeight){

        super(image);

        this.maxRow = Math.max(0,maxRow);
        this.maxFramePerRow = Math.max(0,maxFramePerRow);
        this.frameWidth = Math.max(0,frameWidth);
        this.frameHeight = Math.max(0,frameHeight);

        state = new SimpleObjectProperty<>(AnimationType.IDLE);

        state.addListener((obs,
              oldValue, newValue) -> {
            currentRow = newValue.getRow();
            currentFrame = 0;
        });

        DrawAnimation();
        PlayAnimation();
    }

    @Override
    public void PlayAnimation() {

        animator = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double deltaTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;

                accumulator += deltaTime;

                if (accumulator >= frameDuration) {

                    accumulator = 0;
                    currentFrame++;

                    if (currentFrame >= maxFramePerRow) {
                        currentFrame = 0;
                    }

                    DrawAnimation();
                }
            }
        };
        animator.start();
    }

    @Override
    public void DrawAnimation() {

        setViewport(new Rectangle2D(currentFrame * frameWidth,
                currentRow * frameHeight,frameWidth, frameHeight));

    }

    public void changeAnimationState(AnimationType state) {
        this.state.set(state);
    }

    public AnimationType getAnimationState() {
        return state.get();
    }

    public void setFrameDuration(int fd){
        this.frameDuration = fd;
    }

    public AnimationTimer getAnimator(){
        return animator;
    }

}
