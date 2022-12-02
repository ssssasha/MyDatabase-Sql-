package models;

public class Tables {
    private int id;
    private Databases database;
    private String name;

    public Tables() {
    }

    public Tables(Databases database, String name) {
        this.database = database;
        this.name = name;
    }

    public Tables(int id ,Databases database, String name) {
        this.id = id;
        this.database = database;
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

    public Databases getDatabase() {
        return database;
    }

    public void setDatabase(Databases database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "\n id: " + getId()  + "\n\n " + "database: " + getDatabase()  + "\n\n " + "name: " + getName();
    }
}
