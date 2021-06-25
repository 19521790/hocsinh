/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class JDBCConnection {
     public static  Connection ketNoiJBDC() {

        String url = "jdbc:sqlserver://DESKTOP-LK713D7;databaseName=customer";
        String user = "sa";
        String password = "0000";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try {
                 System.out.println("Kết nối thành công");
               return  DriverManager.getConnection(url, user, password);
               
            } catch (SQLException ex) {
                Logger.getLogger(gui.JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ket noi that bai !!!!!!!!!!!!!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(gui.JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kết nối thất bại");

        }
       return null;
       
    }
}
