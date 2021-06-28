/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class JDBCConnection {

    public static Connection ketNoiJBDC() {
<<<<<<< HEAD
         String url = "jdbc:sqlserver://DESKTOP-8066J6E;databaseName=customer";
        String user = "sa";
        String password = "long";
=======
        String url = "jdbc:sqlserver://LAPTOP-6LEAMI4B\\SQLEXPRESS01:1466;databaseName=customer";
        String user = "vuong";
        String password = "1";
>>>>>>> 20f1735219207045d75a13c049823880539b5d5c

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try {
                System.out.println("Kết nối thành công");
                return DriverManager.getConnection(url, user, password);

            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ket noi that bai !!!!!!!!!!!!!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kết nối thất bại");

        }
        return null;

    }
}
