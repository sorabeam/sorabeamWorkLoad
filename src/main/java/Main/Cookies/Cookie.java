package Main.Cookies;

import Main.Animation.Animate;
import Main.Animation.AnimationType;
import Main.Asset;
import Main.MediaPlayer;
import Main.GameLogic.GameLogic;
import Main.GameLogic.GameState;
import Main.ObjectInGame.Spawner;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract base class representing a playable cookie character.
 *
 * This class manages character properties such as health, score,
 * animation control, movement mechanics, skill usage, and collision
 * hitbox handling during gameplay.
 */
public abstract class Cookie {

    /**
     * Animation controller used to render the cookie character.
     */
    protected Animate cookie;

    /**
     * Image resource URL used for the cookie sprite sheet.
     */
    protected String imgURL;

    /**
     * Indicates whether the cookie is currently invincible.
     */
    protected boolean invincible = false;

    /**
     * Timer used to track invincibility duration.
     */
    protected double invincibleTimer = 0;

    /**
     * Total duration of the invincibility effect.
     */
    protected double invincibleDuration = 0;

    /**
     * Game layer where the cookie is rendered.
     */
    protected Pane gameLayer;

    /**
     * Hitbox rectangle used for collision detection.
     */
    protected Rectangle hitBox;

    /**
     * Ratio used to scale the hitbox height relative to the cookie sprite.
     */
    private DoubleProperty hitboxRatio = new SimpleDoubleProperty(0.7);

    /**
     * Hitbox ratio used when the cookie is sliding.
     */
    private final double slideHitboxRatio = 0.3;

    /**
     * Current vertical velocity used for movement physics.
     */
    private double velocity;

    /**
     * Current number of jumps performed.
     */
    private int jumpCount;

    /**
     * Indicates whether the cookie is currently on the ground.
     */
    private boolean onGround;

    /**
     * Indicates whether the cookie skill has a cooldown.
     */
    protected boolean hasCooldown = false;

    /**
     * Current cooldown timer for the skill.
     */
    protected double cooldownTimer;

    /**
     * Total cooldown duration of the skill.
     */
    protected double skillCooldown;

    /**
     * Counter used for tracking Condition for Skill usage.
     */
    protected int skillCounter;

    /**
     * Counter used for tracking skill usage.
     */
    private boolean isMagnetic;

    /**
     * Indicates whether the magnetic effect is active.
     */
    private boolean isSpeeding;

    /**
     * Timer used to control damage animation duration.
     */
    protected double damageTimer = 0;

    /**
     * Timer used to control damage animation duration.
     */
    private boolean isDead = false;

    /**
     * Timer used before triggering the game over state.
     */
    private double deathTimer = 0;

    /**
     * Unique identifier of the cookie character.
     */
    int id;

    /**
     * Maximum health points of the cookie.
     */
    int maxHp;

    /**
     * Current health points of the cookie.
     */
    int hp;

    /**
     * Current score obtained by the cookie.
     */
    int score = 0;

    /**
     * Timer used to track skill animation duration.
     */
    double skillTimer=0;

    /**
     * Identifier used for the cookie box image.
     */
    String boxImageId;

    /**
     * Identifier used for the cookie skill image.
     */
    String skillImageId;

    /**
     * Name of the cookie character.
     */
    String name;

    /**
     * Description of the cookie character.
     */
    String description;

    /**
     * Profile image of the cookie used in UI.
     */
    Image profileImg;

    /**
     * Initializes a cookie with basic character information.
     *
     * @param id unique identifier of the cookie
     * @param name name of the cookie
     * @param hp maximum health points of the cookie
     * @param description description of the cookie character
     */
    public Cookie(int id,String name,int hp, String description) {

        this.id = id;
        this.hp = hp;
        this.maxHp = hp;
        boxImageId = "B" + id;
        skillImageId = "S" + id;
        this.name = name;
        this.description = description;

    }

    /**
     * Returns the cookie ID.
     *
     * @return cookie ID
     */
    public int get_Id() { return id; }

    /**
     * Returns the current health points of the cookie.
     *
     * @return current HP
     */
    public int get_Hp() { return hp; }

    /**
     * Returns the box image identifier.
     *
     * @return box image id
     */
    public String get_Bid() { return boxImageId; }

    /**
     * Returns the skill image identifier.
     *
     * @return skill image id
     */
    public String get_Sid() { return skillImageId; }

    /**
     * Returns the cookie name.
     *
     * @return cookie name
     */
    public String get_Name() { return name; }

    /**
     * Returns the cookie description.
     *
     * @return cookie description
     */
    public String get_Desc() { return description; }

    /**
     * Returns the current score.
     *
     * @return current score
     */
    public int get_Score() {return score;}

    /**
     * Sets the current score of the cookie.
     *
     * @param score new score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Activates the cookie skill.
     *
     * This method must be implemented by subclasses.
     */
    public abstract void useSkill();

    /**
     * Applies damage to the cookie and triggers the damage animation.
     *
     * If HP reaches zero, the cookie will die.
     *
     * @param damage amount of damage taken
     */
    public void takeDamage(int damage){
        if(isDead) return;
        hp -= damage;
        GameLogic.getHpBar().updateHpBar(GameLogic.getCurrentGameScene().getDeltatime());
        System.out.println("Cookie take " + damage + " damage");

        System.out.println(hp);
        MediaPlayer.getInstance().playSFX("Hit");

        cookie.changeAnimationState(AnimationType.TAKE_DAMAGE);
        damageTimer = 0.5;
        setInvincible(1);


        if(hp <= 0){
            die();
        }
    }

    /**
     * Applies periodic damage to the cookie.
     */
    public void takeDamageByTime(){
        hp -= 2;
        GameLogic.getHpBar().updateHpBar(GameLogic.getCurrentGameScene().getDeltatime());

        if(hp <= 0){
            die();
        }
    }

    /**
     * Sets the health points of the cookie.
     *
     * The value cannot exceed the maximum HP.
     *
     * @param hp new health value
     */
    public void setHp(int hp) {
        this.hp = Math.min(maxHp,hp);
    }

    /**
     * Restores health points to the cookie.
     *
     * @param healunit amount of HP restored
     */
    public void heal(int healunit){

        GameLogic.getHpBar().updateHpBar(GameLogic.getCurrentGameScene().getDeltatime());
        hp = Math.min(maxHp,hp + healunit);;
        System.out.println("Cookie get " + healunit + " heathPoint");
    }

    /**
     * Handles the death state of the cookie.
     *
     * Plays death animation and stops the game environment.
     */
    public void die(){
        if(isDead) return;

        isDead = true;
        deathTimer = 3;

        cookie.changeAnimationState(AnimationType.DIE);

        velocity = -5;
        GameLogic.getCurrentGameScene().stopEnvironment();
    }

    /**
     * Plays the skill animation for a specified duration.
     *
     * @param duration duration of the skill animation
     */
    protected void playSkill(double duration) {

        skillTimer = duration;
        cookie.changeAnimationState(AnimationType.SKILL);

    }

    /**
     * Creates the cookie animation object and initializes the hitbox.
     *
     * @return Animate object representing the cookie animation
     */
    public Animate createCookie(){

        jumpCount=2;

        cookie = new Animate(
                Asset.getImage(imgURL),
                6,4,1,1,
                5,4,1,1,
                5,
                400,400);

        hitBox = new Rectangle();
        hitBox.widthProperty().bind(cookie.fitWidthProperty().multiply(0.4));
        hitBox.heightProperty().bind(
                cookie.fitHeightProperty().multiply(hitboxRatio)
        );

        //hitBox.setStroke(Color.RED);
        hitBox.setFill(Color.TRANSPARENT);

        hitBox.layoutXProperty().bind(cookie.layoutXProperty().add(cookie.fitWidthProperty().multiply(0.2)).add(15));
        hitBox.layoutYProperty().bind(
                cookie.layoutYProperty().add(
                        cookie.fitHeightProperty().subtract(hitBox.heightProperty())
                                .subtract(20)
                )
        );

        return cookie;
    }

    public boolean isHasCooldown() {
        return hasCooldown;
    }

    public void setHasCooldown(boolean hasCooldown) {
        this.hasCooldown = hasCooldown;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Image getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Image profileImg) {
        this.profileImg = profileImg;
    }

    /**
     * Updates cookie movement, animation states, and timers.
     *
     * @param deltaTime time passed since the last update
     */
    public void update(double deltaTime){

        if(isDead){

            deathTimer -= deltaTime;

            if(deathTimer <= 0){
                GameLogic.setGameState(GameState.GAME_OVER);
            }

            return;
        }

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

        // tuning
        double gravity = 0.4;
        velocity += gravity * scale;
        double maxFallSpeed = 12;
        velocity = Math.min(velocity, maxFallSpeed);

        cookie.setLayoutY(cookie.getLayoutY() + velocity * scale);

        if (!onGround && velocity > 0 && !cookie.getAnimationState().equals(AnimationType.DOUBLE_JUMP)) {

            if (!cookie.getAnimationState().equals(AnimationType.JUMP_DOWN)) {
                cookie.changeAnimationState(AnimationType.JUMP_DOWN);
            }
        }

        double feet = cookie.getLayoutY() + cookie.getBoundsInParent().getHeight();

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

    /**
     * Makes the cookie jump or double jump depending on jump count.
     */
    public void jump() {

        if(isDead) return;

        if (jumpCount >= getMaxJump()) return;

        if (jumpCount == 0) {
            cookie.changeAnimationState(AnimationType.JUMP_UP);
        } else {
            cookie.changeAnimationState(AnimationType.DOUBLE_JUMP);
        }

        MediaPlayer.getInstance().playSFX("JUMP");
        setHitbox();
        velocity = -13;
        jumpCount++;
        onGround = false;
    }

    /**
     * Makes the cookie perform a slide action.
     */
    public void slide() {

        if(isDead) return;

        setHitbox();

        if ( isPerformingSkill() || !onGround || cookie.getAnimationState().equals(AnimationType.SLIDE)) { return; }

        MediaPlayer.getInstance().playSFX("SLIDE");
        cookie.changeAnimationState(AnimationType.SLIDE);
    }

    /**
     * Returns the cookie from slide state to running state.
     */
    public void upFromSlide() {


        if ( isPerformingSkill() || !onGround) {
            setHitbox();
            return;
        }
        cookie.changeAnimationState(AnimationType.RUN);
        setHitbox();

    }

    /**
     * Updates the hitbox ratio depending on the current animation state.
     *
     * When the cookie is sliding, a smaller hitbox ratio is used.
     */
    public void setHitbox(){
        if (cookie.getAnimationState().equals(AnimationType.SLIDE)){
            hitboxRatio.set(slideHitboxRatio);
        }
        else {
            hitboxRatio.set(0.7);
        }
    }

    /**
     * Sets the cookie to an invincible state for a duration.
     *
     * @param duration duration of invincibility
     */
    public void setInvincible(double duration){
        invincible = true;
        invincibleDuration = duration;
        invincibleTimer = 0;
    }

    /**
     * Resets temporary effects such as magnetic or speed boost.
     */
    public void reset() {
        setMagnetic(false);
        setSpeeding(false);
        Spawner.resetSpeed();
    }

    /**
     * Returns the current cooldown timer value.
     *
     * @return cooldown timer
     */
    public double getCooldownTimer() {
        return cooldownTimer;
    }

    /**
     * Sets the cooldown timer value.
     *
     * @param cooldownTimer new cooldown timer value
     */
    public void setCooldownTimer(double cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }

    /**
     * Returns the skill cooldown duration.
     *
     * @return skill cooldown duration
     */
    public double getSkillCooldown() {
        return skillCooldown;
    }

    /**
     * Sets the skill cooldown duration.
     *
     * @param skillCooldown skill cooldown duration
     */
    public void setSkillCooldown(double skillCooldown) {
        this.skillCooldown = skillCooldown;
    }

    /**
     * Returns the skill counter value.
     *
     * @return skill counter
     */
    public int getSkillCounter() {
        return skillCounter;
    }

    /**
     * Sets the skill counter value.
     *
     * @param skillCounter skill counter value
     */
    public void setSkillCounter(int skillCounter) {
        this.skillCounter = skillCounter;
    }

    /**
     * Returns whether the cookie is currently invincible.
     *
     * @return true if invincible
     */
    public boolean isInvincible(){
        return invincible;
    }

    /**
     * Checks whether the cookie is currently performing a skill.
     *
     * @return true if the skill animation is playing
     */
    private boolean isPerformingSkill() {
        return cookie.getAnimationState().equals(AnimationType.SKILL);
    }

    /**
     * Returns the parent game layer containing the cookie.
     *
     * @return parent Pane layer
     */
    protected Pane getParentLayer() {
        return gameLayer;
    }

    /**
     * Sets the game layer where the cookie is rendered.
     *
     * @param layer game layer pane
     */
    public void setGameLayer(Pane layer) {
        this.gameLayer = layer;
    }

    /**
     * Returns the maximum number of jumps allowed.
     *
     * @return maximum jump count
     */
    protected int getMaxJump() {
        return 2;
    }

    /**
     * Returns the hitbox rectangle used for collision detection.
     *
     * @return cookie hitbox
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * Sets the sprite image URL for the cookie.
     *
     * @param imgURL image resource identifier
     */
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    /**
     * Returns the cooldown progress of the cookie skill.
     *
     * @return cooldown progress value
     */
    public double getCooldownProgress(){
        return 0;
    }

    /**
     * Returns the animation controller of the cookie.
     *
     * @return Animate object
     */
    public Animate getCookie() {
        return cookie;
    }

    /**
     * Sets the animation controller of the cookie.
     *
     * @param cookie Animate object used for the cookie animation
     */
    public void setCookie(Animate cookie) {
        this.cookie = cookie;
    }

    /**
     * Returns whether the cookie is currently on the ground.
     *
     * @return true if on ground
     */
    public boolean isOnGround() {
        return onGround;
    }

    /**
     * Returns whether the cookie has magnetic ability active.
     *
     * @return true if magnetic
     */
    public boolean isMagnetic() {
        return isMagnetic;
    }

    /**
     * Enables or disables the magnetic effect.
     *
     * @param magnetic true to enable magnetic ability
     */
    public void setMagnetic(boolean magnetic) {
        isMagnetic = magnetic;
    }

    /**
     * Enables or disables the speed boost effect.
     *
     * @param speeding true to enable speed boost
     */
    public void setSpeeding(boolean speeding) {
        isSpeeding = speeding;
    }

    /**
     * Returns whether the cookie is currently speeding.
     *
     * @return true if speed boost is active
     */
    public boolean isSpeeding() {
        return isSpeeding;
    }

    /**
     * Returns whether the cookie is dead.
     *
     * @return true if dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets the dead state of the cookie.
     *
     * @param dead true if the cookie is dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
