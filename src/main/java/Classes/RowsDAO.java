package Classes;

import models.Databases;
import models.Rows;
import models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowsDAO extends AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(RowsDAO.class);

    public Rows getRowByID(int id) {
        Rows row = new Rows();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM rowss WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    row.setId(resultSet.getInt("id"));
                    row.setTable(new TablesDAO().getTableByID(resultSet.getInt("tableId")));
                    row.setNumber(resultSet.getInt("number"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return row;
    }

    public void createRow(Rows row) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "rowss (tableId,number) VALUES (?,?)");
            statement.setInt(1, row.getTable().getId());
            statement.setInt(2, row.getNumber());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateRow(Rows row) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE rowss SET tableId = ?, number = ? WHERE id = ?");
            statement.setInt(1, row.getTable().getId());
            statement.setInt(2, row.getNumber());
            statement.setInt(3, row.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteRow(Rows row) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM rowss WHERE id = ?");
            statement.setInt(1, row.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Rows> getAllRows() {
        List<Rows> rowsList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM rowss");
            while (resultSet.next()) {
                Rows rows = new Rows();
                rows.setId(resultSet.getInt("id"));
                rows.setTable(new TablesDAO().getTableByID(resultSet.getInt("tableId")));
                rows.setNumber(resultSet.getInt("number"));
                rowsList.add(rows);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowsList;
    }
}
