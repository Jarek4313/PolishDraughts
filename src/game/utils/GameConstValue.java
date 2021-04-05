package game.utils;

public enum GameConstValue {
    errorAddPawnToList(1),
    numberOfFields(10),
    pawnSelection(20),
    pawnWhite(30),
    pawnBlack(40),
    wirtePawnsList(50);


    private final int value;

    GameConstValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
