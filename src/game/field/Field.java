package game.field;

public class Field {

    protected String type;
    protected int id;

    public String getField() {
        return this.type;
    }
    public void setField(String type) {
        this.type = type;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public Field(int id){
        this.id = id;
        this.type = "DEF";
    }

}
