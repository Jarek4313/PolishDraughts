package game.utils;

public enum GameConstValue {
    numberOfFields(10),
    pawnSelection(20),
    pawnWhite(30),
    pawnBlack(40);


    private final int value;

    GameConstValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
