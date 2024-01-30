package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/main";
    private static final String USERNAME = "KEVIN";
    private static final String PASSWORD = "MyNewPass";

    private static Connection db;

    public Connection getConnection() throws SQLException {
        if (db == null || db.isClosed()) {
            db = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        return db;
    }
}