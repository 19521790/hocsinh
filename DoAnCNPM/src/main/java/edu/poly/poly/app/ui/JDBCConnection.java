/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.ui;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nguye
 */
public class JDBCConnection {
    public static Connection ketNoiJBDC(){
        
        String url="jdbc:sqlserver://DESKTOP-LK713D7;databaseName=QLHS";;
        String user="sa";
        String password="0000";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            try {
                System.out.println("Kết nối thành công");
                DriverManager.getConnection(url, user, password);

            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kết nối thất bại");
        }
        
        return null;
    }
}
