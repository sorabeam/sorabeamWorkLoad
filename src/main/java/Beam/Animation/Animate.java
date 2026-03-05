package Beam.Animation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animate extends ImageView {

    protected int currentRow = 4;
    protected int currentFrame = 0;
    protected long lastTime = 0;
    protected double accumulator = 0;

    private int maxFPRIdel;
    private int maxFPRRun;                             //
    private int maxFPRJumpUp;
    private int maxFPRJumpDown;
    private int maxFPRDoubleJump;
    private int maxFPRDie;
    private int maxFPRSlide;
    private int maxFPRTakeDamage;
    private int maxFPRSkill;

    private boolean isLoop = true;
    private int maxFPR;// equal to

    private int frameWidth;                         //Test On 120px
    private int frameHeight;                        //Test On 139px
    private double frameDuration = 0.15;
    private int[] maxFPRType = new int[9];

    private ObjectProperty<AnimationType> state;

    public Animate(Image image,int idle,int run,int jumpUp,int jumpDown,int doubleJump,int die,int slide,int takeDamage,
                   int skill,int frameWidth,int frameHeight){

        super(image);

        setFrameDuration(0.08f);

        this.maxFPRIdel = Math.max(0,idle);
        this.maxFPRRun = Math.max(0,run);
        this.maxFPRJumpUp = Math.max(0,jumpUp);
        this.maxFPRJumpDown = Math.max(0,jumpDown);
        this.maxFPRDoubleJump = Math.max(0,doubleJump);
        this.maxFPRDie = Math.max(0,die);
        this.maxFPRSlide = Math.max(0,slide);
        this.maxFPRTakeDamage = Math.max(0,takeDamage);
        this.maxFPRSkill = Math.max(0,skill);

        maxFPRType[0] = this.maxFPRIdel;
        maxFPRType[1] = this.maxFPRRun;
        maxFPRType[2] = this.maxFPRJumpUp;
        maxFPRType[3] = this.maxFPRJumpDown;
        maxFPRType[4] = this.maxFPRDoubleJump;
        maxFPRType[5] = this.maxFPRDie;
        maxFPRType[6] = this.maxFPRSlide;
        maxFPRType[7] = this.maxFPRTakeDamage;
        maxFPRType[8] = this.maxFPRSkill;

        this.maxFPR = this.maxFPRDoubleJump;

        this.frameWidth = Math.max(0,frameWidth);
        this.frameHeight = Math.max(0,frameHeight);

        state = new SimpleObjectProperty<>(AnimationType.DOUBLE_JUMP);

        state.addListener((obs,
                           oldValue, newValue) -> {
            System.out.println(newValue + " " + newValue.getRow() );
            currentRow = newValue.getRow();
            currentFrame = 0;
            maxFPR = maxFPRType[newValue.getRow()];

            if (getAnimationState() == AnimationType.SKILL){
                isLoop = false;
            } else {
                isLoop = true;
            }

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

            if ( (currentFrame >= maxFPR ) && isLoop) {
                currentFrame = 0;
            } else if ( (currentFrame >= maxFPR ) && !isLoop ) {
                currentFrame = maxFPR-1;
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

    public void setFrameDuration(int fd){
        this.frameDuration = fd;
    }

}
