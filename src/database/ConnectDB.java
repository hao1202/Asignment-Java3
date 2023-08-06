/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author tranv
 */
public class ConnectDB {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName="
                        + "FPL_DaoTao;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String password = "123";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("kết nối thành công");
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
