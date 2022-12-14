package models;

public class Rows {
    private int id;
    private Tables table;
    private int number;

    public Rows() {
    }

    public Rows(Tables table, int number) {
        this.table = table;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()  + "\n\n " + "table: " + getTable()  + "\n\n " + "number: " + getNumber();
    }
}
