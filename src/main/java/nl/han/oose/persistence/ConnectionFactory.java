package nl.han.oose.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/spotitube?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Properties properties;

    public ConnectionFactory() {
        properties = getProperties();
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        String propertiesPath = getClass()
                .getClassLoader()
                .getResource("")
                .getPath() + "database.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);

        } catch (IOException e) {
            properties.setProperty("db.url", DB_CONNECTION_URL);
            properties.setProperty("db.user", DB_USER);
            properties.setProperty("db.password", DB_PASSWORD);
            properties.setProperty("db.driver", MYSQL_JDBC_DRIVER);

            e.printStackTrace();
        }
        return properties;
    }

    Connection getConnection() throws SpotitubePersistenceException {
        loadDriver();
        try {
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
