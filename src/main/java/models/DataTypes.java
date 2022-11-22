package models;

import java.util.List;

public class DataTypes {
    private int id;
    private String name;
    private List<Columns> columns;

    public DataTypes() {
    }

    public DataTypes(String name) {
        this.name = name;
    }

    public DataTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DataTypes(int id, String name, List<Columns> columns) {
        this.id = id;
        this.name = name;
        this.columns = columns;
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

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()+ "\n " + "name: " + getName();
    }
}
