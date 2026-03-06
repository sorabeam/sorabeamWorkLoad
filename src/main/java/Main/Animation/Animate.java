package Main.Animation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animate extends ImageView {

    protected int currentRow = 4;
    protected int currentFrame = 0;
    protected double accumulator = 0;

    private boolean isLoop = true;
    private int maxFPR;// equal to

    private final int frameWidth; //Test On 120px
    private final int frameHeight; //Test On 139px
    private double frameDuration = 0.15;
    private final int[] maxFPRType = new int[9];

    private final ObjectProperty<AnimationType> state;

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

    public void update(double deltaTime) {

        accumulator += deltaTime;

        if (accumulator >= frameDuration) {
            accumulator -= frameDuration;   // ดีกว่า reset เป็น 0

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

    public void setFrameDuration(float frameDuration){
        this.frameDuration = frameDuration;
    }

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

}
