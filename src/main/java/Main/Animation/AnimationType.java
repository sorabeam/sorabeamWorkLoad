package Main.Animation;

/**
 * Represents different animation states used in the sprite animation system.
 *
 * Each animation type corresponds to a specific row in the sprite sheet.
 * The row value determines where the frames of that animation are located.
 */
public enum AnimationType {

    /** Idle animation state. */
    IDLE(0),

    /** Running animation state. */
    RUN(1),

    /** Jumping upward animation state. */
    JUMP_UP(2),

    /** Falling downward animation state. */
    JUMP_DOWN(3),

    /** Double jump animation state. */
    DOUBLE_JUMP(4),

    /** Character death animation state. */
    DIE(5),

    /** Sliding animation state. */
    SLIDE(6),

    /** Taking damage animation state. */
    TAKE_DAMAGE(7),

    /** Skill animation state. */
    SKILL(8);

    /**
     * The sprite sheet row used by this AnimationType.
     */
    private final int row;

    /**
     * Initializes an animation type with a specific row index
     * used to locate animation frames in the sprite sheet.
     *
     * @param row the row index in the sprite sheet
     */
    AnimationType(int row) {
        this.row = row;
    }

    /**
     * Returns the row index associated with the animation type.
     *
     * @return the sprite sheet row used for this animation
     */
    public int getRow() {
        return row;
    }
}