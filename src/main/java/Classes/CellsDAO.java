package Classes;

import models.Cells;
import models.Columns;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CellsDAO extends AbstractDAO {
    private static final Logger LOGGER = LogManager.getLogger(CellsDAO.class);

    public Cells getCellByID(int id) {
        Cells cell = new Cells();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM cells WHERE id = ?");
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cell.setId(resultSet.getInt("id"));
                    cell.setColumn(new ColumnsDAO().getColumnByID(resultSet.getInt("columnId")));
                    cell.setRow(new RowsDAO().getRowByID(resultSet.getInt("rowId")));
                    cell.setValue(resultSet.getString("value"));
                }
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeAll();
        }return cell;
    }

    public void createCell(Cells cell) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " +
                    "cells (columnId,rowId,value) VALUES (?,?,?)");
            statement.setInt(1, cell.getColumn().getId());
            statement.setInt(2, cell.getRow().getId());
            statement.setString(3, cell.getValue());
            int st = statement.executeUpdate();
            LOGGER.info(st + " records inserted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void updateCell(Cells cell) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE cells SET columnId = ?, rowId = ?, value = ? WHERE id = ?");
            statement.setInt(1, cell.getColumn().getId());
            statement.setInt(2, cell.getRow().getId());
            statement.setString(3, cell.getValue());
            statement.setInt(4, cell.getId());
            statement.executeUpdate();
            LOGGER.info("Successfully updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public void deleteCell(Cells cell) {
        try{
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM cells WHERE id = ?");
            statement.setInt(1, cell.getId());
            statement.execute();
            LOGGER.info("Successfully deleted");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
    }

    public List<Cells> getAllCells() {
        List<Cells> cellsList = new ArrayList<>();
        try {
            resultSet = getResultSet("SELECT * FROM cells");
            while (resultSet.next()) {
                Cells cells = new Cells();
                cells.setId(resultSet.getInt("id"));
                cells.setColumn(new ColumnsDAO().getColumnByID(resultSet.getInt("columnId")));
                cells.setRow(new RowsDAO().getRowByID(resultSet.getInt("rowId")));
                cells.setValue(resultSet.getString("value"));
                cellsList.add(cells);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cellsList;
    }
}
