package game.field;

import game.utils.GameConstValue;

public class Field {

    private Pawn pawn;
    private String type;
    private int id;

    public Field(int id){
        this.id = id;
        this.type = "DEF";
    }

    public void setPawn(GameConstValue gameConstValue) {
        this.pawn = new Pawn(gameConstValue);
        //System.out.println(this.pawn.getPawnId());
    }
    public Pawn getPawn() {
        return this.pawn;
    }
    public String getPawnColor() {
        return pawn.getColor();
    }
    public int getPawnId() {
        return pawn.getPawnId();
    }

    public String getField() {
        return this.type;
    }
    public void setField(String type) {
        this.type = type;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

}
