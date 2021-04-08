package game.field;

import game.utils.GameConstValue;

public class Pawn{
    private static int staticWhitePawnId = 1;
    private static int staticBlackPawnId = 1;

    private String color;
    private int pawnId;

    public Pawn(GameConstValue gameConstValue) {
       switch (gameConstValue) {
           case pawnWhite:
               this.color = "W";
               this.pawnId = Pawn.staticWhitePawnId;
               staticWhitePawnId++;
               break;
           case pawnBlack:
               this.color = "B";
               this.pawnId = Pawn.staticBlackPawnId;
               staticBlackPawnId++;
               break;
           default:
               System.out.println("Incorect type of pawn");
       }
    }
    public int getPawnId() {return this.pawnId;}
    public String getColor() {return this.color;}

    @Override
    public String toString() {
        return String.valueOf(this.color + " " + this.pawnId);
    }
}
