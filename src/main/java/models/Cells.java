package models;

public class Cells {
    private int id;
    private Columns column;
    private Rows row;
    private String value;

    public Cells() {
    }

    public Cells(int id , Columns column, Rows row, String value) {
        this.id = id;
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Columns getColumn() {
        return column;
    }

    public void setColumn(Columns column) {
        this.column = column;
    }

    public Rows getRow() {
        return row;
    }

    public void setRow(Rows row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()  + "\n\n " + "card type: " + getColumn()  + "\n\n " + "client: " + getRow()
                + "\n\n " + "number: " + getValue();
    }
}
