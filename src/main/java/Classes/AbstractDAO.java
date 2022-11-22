package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public abstract class AbstractDAO {
    private Statement statement;
    protected ResultSet resultSet;
    private Connection connection;

    ResourceBundle resource = ResourceBundle.getBundle("db");
    String url = resource.getString("db.url");
    String username = resource.getString("db.username");
    String password = resource.getString("db.password");

    public Connection getConnection () {
        DBConnection.newInstance();
        connection = DBConnection.getConnection(url, username, password);
        return connection;
    }

    public ResultSet getResultSet(String sqlQuery) throws SQLException {
        getConnection();
        statement = connection.createStatement();
        resultSet =  statement.executeQuery(sqlQuery);
        return resultSet;
    }

    public void closeAll() {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        if (statement !=  null)
            try {
                statement.close();
            } catch (SQLException ignored){
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException ignored){
            }
    }
}
