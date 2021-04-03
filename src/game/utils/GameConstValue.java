package game.utils;

public enum GameConstValue {
    numberOfFields(10);

    private final int value;

    GameConstValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
