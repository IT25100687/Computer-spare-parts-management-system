package spareparts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://localhost:1436;databaseName=spareparts_db;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "Book@170";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to MSSQL Database (Port 1436) Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" MSSQL Database Connection Failed: " + e.getMessage());
        }
        return connection;
    }
}
