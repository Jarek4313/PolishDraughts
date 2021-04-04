package game.field;

public class Pawn{
    private static int staticPawnId=1;
    private String color;
    private int pawnId;
    public Pawn(String color) {
        this.color = color;
        this.pawnId = staticPawnId;
        staticPawnId++;
    }
}
