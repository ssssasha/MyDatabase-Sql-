package Classes;

import models.DataTypes;
import models.Databases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataTypesDAO extends AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(DataTypesDAO.class);

    public DataTypes getDataTypeByID(int id) {
        DataTypes dataTypes = new DataTypes();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM datatypes WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dataTypes.setId(resultSet.getInt("id"));
                    dataTypes.setName(resultSet.getString("name"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return dataTypes;
    }

    public void createDataType(DataTypes dataType) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "datatypes (name) VALUES (?)");
            statement.setString(1, dataType.getName());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateDataType(DataTypes dataType) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE datatypes SET name = ? WHERE id = ?");
            statement.setString(1, dataType.getName());
            statement.setInt(2, dataType.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteDataTypes(DataTypes dataType) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM datatypes WHERE id = ?");
            statement.setInt(1, dataType.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Databases> getAllDataTypes() {
        List<Databases> dataTypesList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM datatypes");
            while (resultSet.next()) {
                Databases database = new Databases();
                database.setId(resultSet.getInt("id"));
                database.setName(resultSet.getString("name"));
                dataTypesList.add(database);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataTypesList;
    }
}
