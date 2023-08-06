/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;

/**
 *
 * @author tranv
 */
public class test {
    public static void main(String[] args) {
        Connection connection = ConnectDB.getConnection();
        ConnectDB.closeConnection(connection);
    }
}
