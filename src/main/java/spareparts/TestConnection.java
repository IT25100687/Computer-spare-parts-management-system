package spareparts;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        System.out.println("Testing MSSQL Database Connection on Port 1436...");
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println(" MSSQL Connection Test Passed!");
        } else {
            System.out.println(" MSSQL Connection Test Failed!");
        }
    }
}
