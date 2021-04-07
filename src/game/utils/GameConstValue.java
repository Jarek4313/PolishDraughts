package game.utils;

public enum GameConstValue {
    errorAddPawnToList(1),
    errorRemovePawnFromList(2),
    numberOfFields(10),
    pawnSelection(20),
    fieldSelection(21),
    pawnWhite(30),
    pawnBlack(40),
    writePawnsList(50),
    writeFieldList(51),
    whiteTurn(60),
    blackTurn(61);


    private final int value;

    GameConstValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
