package Classes;

import models.Columns;
import models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesDAO extends AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(TablesDAO.class);

    public Tables getTableByID(int id) {
        Tables table = new Tables();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM tables WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    table.setId(resultSet.getInt("id"));
                    table.setDatabase(new DatabasesDAO().getDatabaseByID(resultSet.getInt("databaseId")));
                    table.setName(resultSet.getString("name"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return table;
    }

    public void createTable(Tables table) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "tables (databaseId,name) VALUES (?,?)");
            statement.setInt(1, table.getDatabase().getId());
            statement.setString(2, table.getName());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateTable(Tables table) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE tables SET databaseId = ?, name = ? WHERE id = ?");
            statement.setInt(1, table.getDatabase().getId());
            statement.setString(2, table.getName());
            statement.setInt(3, table.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteTable(Tables table) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM tables WHERE id = ?");
            statement.setInt(1, table.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Tables> getAllTables() {
        List<Tables> tablesList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM tables");
            while (resultSet.next()) {
                Tables tables = new Tables();
                tables.setId(resultSet.getInt("id"));
                tables.setDatabase(new DatabasesDAO().getDatabaseByID(resultSet.getInt("databaseId")));
                tables.setName(resultSet.getString("name"));
                tablesList.add(tables);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tablesList;
    }

    public List<Tables> getTablesWithoutRows(){
        List<Tables> tablesList  = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT tables.id, tables.databaseId, tables.name FROM tables LEFT OUTER JOIN rowss on tables.id = rowss.tableId WHERE rowss.tableId IS NULL");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Tables table = new Tables();
                table.setId(resultSet.getInt("tables.id"));
                table.setDatabase(new DatabasesDAO().getDatabaseByID(resultSet.getInt("tables.databaseId")));
                table.setName(resultSet.getString("tables.name"));
                tablesList.add(table);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally{
            closeAll();
        }return tablesList;
    }
}
