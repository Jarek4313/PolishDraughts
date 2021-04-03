package game.field;

public class Field {
    private String type;
    public Field (){
        this.type = "DEF";
    }
    public String getField() {
        return this.type;
    }
    public void setField(String type) {
        this.type = type;
    }
}
