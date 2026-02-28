package Beam.Cookies;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.Asset;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cookie {

    protected Animate cookie;
    protected ImageView cookieImg;
    protected String imgURL;
    protected double accumulator;
    protected double skillcoodown;
    protected double cdvalues = 10;

    protected Pane gameLayer;
    protected Rectangle hitbox;

    private DoubleProperty hitboxRatio = new SimpleDoubleProperty(0.85);
    private final double slideHitboxRatio = 0.45;

    private double velocity;
    private int jumpCount;

    private boolean onGround;

    // tuning
    private final double gravity = 0.4;
    private final double jumpSpeed = -13;
    private final double maxFallSpeed = 12;

    int id;
    int hp;
    int score = 0;
    double skillTimer=0;

    String Bid;
    String Sid;
    String name;
    String desc;

    public Cookie(int id,String name,int hp, String desc) {
        this.id = id;
        this.hp = hp;
        Bid = "B" + id;
        Sid = "S" + id;
        this.name = name;
        this.desc = desc;

    }

    public int get_Id() { return id; }
    public int get_Hp() { return hp; }
    public String get_Bid() { return Bid; }
    public String get_Sid() { return Sid; }
    public String get_Name() { return name; }
    public String get_Desc() { return desc; }
    public int get_Score() {return score;}

    public void setScore(int score) {
        this.score = score;
    }

    public abstract void useSkill();

    public void takeDamage(int damage){
        hp -= damage;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void heal(int healunit){
        hp += healunit;
    }

    public void die(){
    }

    protected void playSkill(double duration) {

        skillTimer = duration;
        cookie.changeAnimationState(AnimationType.SKILL);

    }

    public Animate createCookie(){

        jumpCount=2;

        cookie = new Animate(
                Asset.getImage(imgURL),
                6,4,1,1,
                5,4,1,1,
                5,
                400,400);

        hitbox = new Rectangle();
        hitbox.widthProperty().bind(cookie.fitWidthProperty().multiply(0.6));
        hitbox.heightProperty().bind(
                cookie.fitHeightProperty().multiply(hitboxRatio)
        );

        // DEBUG MODE (เปิดดู hitbox)
        hitbox.setStroke(Color.RED);
        hitbox.setFill(Color.TRANSPARENT);

        // bind ตำแหน่งกับ player
        hitbox.layoutXProperty().bind(cookie.layoutXProperty().add(cookie.fitWidthProperty().multiply(0.2)));
        hitbox.layoutYProperty().bind(
                cookie.layoutYProperty().add(
                        cookie.fitHeightProperty().subtract(hitbox.heightProperty())
                )
        );

        return cookie;
    }

    public void update(double deltaTime){

        boolean usingSkill = skillTimer > 0;

        if (skillTimer > 0) {

            skillTimer -= deltaTime;

            if (skillTimer <= 0) {
                skillTimer = 0;
            }
        }

        double scale = deltaTime * 60;

        // ---------- Physics ----------

        velocity += gravity * scale;
        velocity = Math.min(velocity, maxFallSpeed);

        cookie.setLayoutY(cookie.getLayoutY() + velocity * scale);

        double feet = cookie.getLayoutY() + cookie.getBoundsInParent().getHeight();

        // ---------- Ground Check ----------
        double groundY = gameLayer.getHeight() - 80;

        if (feet > groundY) {

            cookie.setLayoutY(groundY - cookie.getBoundsInParent().getHeight());
            velocity = 0;

            jumpCount = 0;
            onGround = true;

            if (!usingSkill) {
                if (cookie.getAnimationState().equals(AnimationType.SLIDE)) {
                    cookie.changeAnimationState(AnimationType.SLIDE);
                } else {
                    cookie.changeAnimationState(AnimationType.RUN);
                }
            }
        }
    }

    public void jump() {

        if (jumpCount >= getMaxJump()) return;

        if (jumpCount == 0) {
            cookie.changeAnimationState(AnimationType.JUMP_UP);
        } else {
            cookie.changeAnimationState(AnimationType.DOUBLE_JUMP);
        }

        setHitbox();
        velocity = jumpSpeed;
        jumpCount++;
        onGround = false;
    }

    public void slide() {

        setHitbox();

        if ( isPerformingSkill() || !onGround || cookie.getAnimationState().equals(AnimationType.SLIDE)) { return; }

        cookie.changeAnimationState(AnimationType.SLIDE);

    }

    public void upFromSlide() {


        if ( isPerformingSkill() || !onGround) {
            setHitbox();
            return;
        }
        cookie.changeAnimationState(AnimationType.RUN);
        setHitbox();

    }

    public void setHitbox(){
        if (cookie.getAnimationState().equals(AnimationType.SLIDE)){
            hitboxRatio.set(slideHitboxRatio);
        }
        else {
            hitboxRatio.set(0.85);
        }
    }

    private boolean isPerformingSkill() {
        return cookie.getAnimationState().equals(AnimationType.SKILL);
    }

    protected Pane getParentLayer() {
        return gameLayer;
    }
    public void setGameLayer(Pane layer) {
        this.gameLayer = layer;
    }
    protected int getMaxJump() {
        return 2;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Animate getCookie() {
        return cookie;
    }

    public void setCookie(Animate cookie) {
        this.cookie = cookie;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
