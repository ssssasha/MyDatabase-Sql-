package models;

public class Columns {
    private int id;
    private Tables table;
    private DataTypes dataType;
    private String name;

    public Columns() {
    }

    public Columns(int id , Tables table, DataTypes dataType, String name) {
        this.id = id;
        this.table = table;
        this.dataType = dataType;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public DataTypes getDataType() {
        return dataType;
    }

    public void setDataType(DataTypes dataType) {
        this.dataType =dataType;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()  + "\n\n " + "card type: " + getTable()  + "\n\n " + "client: " + getDataType()
                + "\n\n " + "number: " + getName();
    }
}
