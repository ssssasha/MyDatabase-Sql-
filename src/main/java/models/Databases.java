package models;

import java.util.List;

public class Databases {
    private int id;
    private String name;
    private List<Tables> tables;

    public Databases() {
    }

    public Databases(String name) {
        this.name = name;
    }

    public Databases(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Databases(int id, String name, List<Tables> tables) {
        this.id = id;
        this.name = name;
        this.tables = tables;
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

    public List<Tables> getTables() {
        return tables;
    }

    public void setTables(List<Tables> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()+ "\n " + "name: " + getName();
    }
}
