package Classes;

import models.Columns;
import models.Rows;
import models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColumnsDAO extends AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(ColumnsDAO.class);

    public Columns getColumnByID(int id) {
        Columns column = new Columns();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM columns WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    column.setId(resultSet.getInt("id"));
                    column.setTable(new TablesDAO().getTableByID(resultSet.getInt("tableId")));
                    column.setDataType(new DataTypesDAO().getDataTypeByID(resultSet.getInt("dataTypeId")));
                    column.setName(resultSet.getString("name"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return column;
    }

    public void createColumn(Columns column) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "columns (tableId,dataTypeId,name) VALUES (?,?,?)");
            statement.setInt(1, column.getTable().getId());
            statement.setInt(2, column.getDataType().getId());
            statement.setString(3, column.getName());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateColumn(Columns column) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE columns SET tableId = ?, dataTypeId = ?, name = ? WHERE id = ?");
            statement.setInt(1, column.getTable().getId());
            statement.setInt(2, column.getDataType().getId());
            statement.setString(3, column.getName());
            statement.setInt(4, column.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteColumn(Columns column) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM columns WHERE id = ?");
            statement.setInt(1, column.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Columns> getAllColumns() {
        List<Columns> columnList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM columns");
            while (resultSet.next()) {
                Columns columns = new Columns();
                columns.setId(resultSet.getInt("id"));
                columns.setTable(new TablesDAO().getTableByID(resultSet.getInt("tableId")));
                columns.setDataType(new DataTypesDAO().getDataTypeByID(resultSet.getInt("dataTypeId")));
                columns.setName(resultSet.getString("name"));
                columnList.add(columns);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnList;
    }
}
