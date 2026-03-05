package Beam.Cookies;

import Beam.Animation.Animate;
import Beam.Animation.AnimationType;
import Beam.Asset;
import Beam.Media.JooxBox;
import Got.GameLogic.GameLogic;
import Got.GameLogic.GameState;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cookie {

    protected Animate cookie;
    protected ImageView cookieImg;
    protected String imgURL;

    protected boolean invincible = false;
    protected double invincibleTimer = 0;
    protected double invincibleDuration = 0;

    protected Pane gameLayer;
    protected Rectangle hitbox;

    private DoubleProperty hitboxRatio = new SimpleDoubleProperty(0.7);
    private final double slideHitboxRatio = 0.3;

    protected Rectangle boostAura;

    private double velocity;
    private int jumpCount;

    private boolean onGround;

    // tuning
    private final double gravity = 0.4;
    private final double jumpSpeed = -13;
    private final double maxFallSpeed = 12;

    protected boolean Cooldownable = false;

    protected double cooldownTimer;
    protected double skillCooldown;
    protected int skillCounter;

    private boolean isMagnetic;

    protected double damageTimer = 0;

    int id;
    int maxhp;
    int hp;
    int score = 0;
    double skillTimer=0;

    String Bid;
    String Sid;
    String name;
    String desc;

    Image profileImg;

    public Cookie(int id,String name,int hp, String desc) {

        this.id = id;
        this.hp = hp;
        this.maxhp = hp;
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
        GameLogic.getHpBar().updateHpBar();
        System.out.println("Cookie take " + damage + " damage");

        System.out.println(hp);
        JooxBox.getInstance().playSFX("Hit");

        cookie.changeAnimationState(AnimationType.TAKE_DAMAGE);
        damageTimer = 0.5;
        setInvincible(1);


        if(hp <= 0){
            die();
        }
    }

    public void setHp(int hp) {
        this.hp = Math.min(maxhp,hp);
    }

    public void heal(int healunit){

        GameLogic.getHpBar().updateHpBar();
        hp = Math.min(maxhp,hp + healunit);;
        System.out.println("Cookie get " + healunit + " heathPoint");
    }

    public void die(){
        GameLogic.setGameState(GameState.GAMEOVER);
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
        hitbox.widthProperty().bind(cookie.fitWidthProperty().multiply(0.4));
        hitbox.heightProperty().bind(
                cookie.fitHeightProperty().multiply(hitboxRatio)
        );

        // DEBUG MODE (เปิดดู hitbox)
        //hitbox.setStroke(Color.RED);
        hitbox.setFill(Color.TRANSPARENT);

        // bind ตำแหน่งกับ player
        hitbox.layoutXProperty().bind(cookie.layoutXProperty().add(cookie.fitWidthProperty().multiply(0.2)).add(15));
        hitbox.layoutYProperty().bind(
                cookie.layoutYProperty().add(
                        cookie.fitHeightProperty().subtract(hitbox.heightProperty())
                                .subtract(20)
                )
        );

        return cookie;
    }

    public boolean isCooldownable() {
        return Cooldownable;
    }

    public void setCooldownable(boolean cooldownable) {
        Cooldownable = cooldownable;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public void setMaxhp(int maxhp) {
        this.maxhp = maxhp;
    }

    public Image getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Image profileImg) {
        this.profileImg = profileImg;
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

        if (damageTimer > 0) {
            damageTimer -= deltaTime;
        }

        // ---------- Physics ----------

        velocity += gravity * scale;
        velocity = Math.min(velocity, maxFallSpeed);

        cookie.setLayoutY(cookie.getLayoutY() + velocity * scale);

        double feet = cookie.getLayoutY() + cookie.getBoundsInParent().getHeight();

        // ---------- Ground Check ----------
        double groundY = gameLayer.getHeight() - 150;

        if (feet > groundY) {

            cookie.setLayoutY(groundY - cookie.getBoundsInParent().getHeight());
            velocity = 0;

            jumpCount = 0;
            onGround = true;

            if (!usingSkill && damageTimer <= 0) {
                if (cookie.getAnimationState().equals(AnimationType.SLIDE)) {
                    cookie.changeAnimationState(AnimationType.SLIDE);
                } else {
                    cookie.changeAnimationState(AnimationType.RUN);
                }
            }
        }

        if(invincible){
            invincibleTimer += deltaTime;

            if((int)(invincibleTimer * 10) % 2 == 0){
                cookie.setOpacity(0.3);
            } else {
                cookie.setOpacity(0.7);
            }

            if(invincibleTimer >= invincibleDuration){
                invincible = false;
                cookie.setOpacity(1);
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

        JooxBox.getInstance().playSFX("JUMP");
        setHitbox();
        velocity = jumpSpeed;
        jumpCount++;
        onGround = false;
    }

    public void slide() {

        setHitbox();

        if ( isPerformingSkill() || !onGround || cookie.getAnimationState().equals(AnimationType.SLIDE)) { return; }

        JooxBox.getInstance().playSFX("SLIDE");
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
            hitboxRatio.set(0.7);
        }
    }

    public void setInvincible(double duration){
        invincible = true;
        invincibleDuration = duration;
        invincibleTimer = 0;
    }

    public double getCooldownTimer() {
        return cooldownTimer;
    }

    public void setCooldownTimer(double cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }

    public double getSkillCooldown() {
        return skillCooldown;
    }

    public void setSkillCooldown(double skillCooldown) {
        this.skillCooldown = skillCooldown;
    }

    public int getSkillCounter() {
        return skillCounter;
    }

    public void setSkillCounter(int skillCounter) {
        this.skillCounter = skillCounter;
    }

    public boolean isInvincible(){
        return invincible;
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

    public double getCooldownProgress(){
        return 0;
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

    public boolean isMagnetic() {
        return isMagnetic;
    }

    public void setMagnetic(boolean magnetic) {
        isMagnetic = magnetic;
    }
}
