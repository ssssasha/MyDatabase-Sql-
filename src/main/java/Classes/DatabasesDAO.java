package Classes;

import models.Databases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabasesDAO extends AbstractDAO {

    private static final Logger LOGGER = LogManager.getLogger(DatabasesDAO.class);

    public Databases getDatabaseByID(int id) {
        Databases database = new Databases();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM databasess WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    database.setId(resultSet.getInt("id"));
                    database.setName(resultSet.getString("name"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return database;
    }

    public void createDatabase(Databases database) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "databasess (name) VALUES (?)");
            statement.setString(1, database.getName());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateDatabase(Databases database) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE databasess SET name = ? WHERE id = ?");
            statement.setString(1, database.getName());
            statement.setInt(2, database.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteDatabase(Databases databases) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM databasess WHERE id = ?");
            statement.setInt(1, databases.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Databases> getAllCardTypes() {
        List<Databases> databasesList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM databasess");
            while (resultSet.next()) {
                Databases database = new Databases();
                database.setId(resultSet.getInt("id"));
                database.setName(resultSet.getString("name"));
                databasesList.add(database);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return databasesList;
    }
}
