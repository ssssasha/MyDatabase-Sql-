import Classes.DataTypesDAO;
import Classes.DatabasesDAO;
import Classes.RowsDAO;
import Classes.TablesDAO;
import models.DataTypes;
import models.Databases;
import models.Rows;
import models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // creating
//        DataTypesDAO createDataTypeDAO = new DataTypesDAO();
//        DataTypes newDataType = new DataTypes( "float");
//        createDataTypeDAO.createDataType(newDataType);
       //reading
//        DataTypesDAO getDataType = new DataTypesDAO();
//        System.out.println(getDataType.getDataTypeByID(7).getName());

        DataTypesDAO getDataType = new DataTypesDAO();
        System.out.println(getDataType.getDataTypeByName("integer").getId());
//        //updating
//        DataTypesDAO updateDataTypeDAO = new DataTypesDAO();
//        DataTypes dataTypeToUpdate = new DataTypes( 7, "double");
//        updateDataTypeDAO.updateDataType(dataTypeToUpdate);
//        //deleting
//        DataTypesDAO deleteDataTypesDAO = new DataTypesDAO();
//        DataTypes dataTypeToDelete = new DataTypes(6);
//        deleteDataTypesDAO.deleteDataType(dataTypeToDelete);

//        DataTypesDAO dataTypes = new DataTypesDAO();
//        List<DataTypes> dataTypesList;
//        dataTypesList = dataTypes.getAllDataTypes();
//        for (DataTypes dt : dataTypesList){
//            System.out.println(dt);
//        }


        // table creating
//       TablesDAO createTableDAO = new TablesDAO();
//       Tables newTable = new Tables(new DatabasesDAO().getDatabaseByID(1),"table3");
//       createTableDAO.createTable(newTable);

        // row creating
//        RowsDAO createRowDAO = new RowsDAO();
//        Rows newRow = new Rows(new TablesDAO().getTableByID(3),1);
//        createRowDAO.createRow(newRow);

        //getting a list of tables
//        TablesDAO tables = new TablesDAO();
//        List<Tables> tablesList;
//        tablesList = tables.getTablesWithoutRows();
//        for(Tables t : tablesList){
//            System.out.println(t);
//        }
    }

}
