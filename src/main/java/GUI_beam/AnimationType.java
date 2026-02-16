package GUI_beam;

public enum AnimationType {
    IDLE(0),
    WALK(1),
    RUN(2),
    JUMP(3),
    SLIDE(4);

    private final int row;
    AnimationType(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }
}