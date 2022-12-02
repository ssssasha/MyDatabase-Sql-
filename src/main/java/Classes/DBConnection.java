package Classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Logger LOGGER = LogManager.getLogger(DBConnection.class);
    private static DBConnection instance;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private DBConnection() {
        try {
            LOGGER.info("Register driver ...");
            Class.forName(DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection newInstance() {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection (String url, String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e ) {
            LOGGER.info("Connection isn`t created! Method returned NULL!");
            LOGGER.error(e);
            return null;
        }
    }
}
