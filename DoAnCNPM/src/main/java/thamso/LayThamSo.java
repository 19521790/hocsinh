/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thamso;

import gui.JDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class LayThamSo {
    static int tuoiToiThieu = 0;
    static int tuoiToiDa = 0;
       
  
    public int getTuoiToiThieu(){
     return tuoiToiThieu;
    }
    public int getTuoiToiDa(){
      return tuoiToiDa;
    }
    public void ketNoiCoSoDulieu(){
     Connection con = JDBCConnection.ketNoiJBDC();
        try {
            Statement statement = con.createStatement();

            ResultSet rsTuoiToiThieu = statement.executeQuery("SELECT THAMSO.GiaTri FROM dbo.THAMSO Where THAMSO.TenThamSo='TuoiToiThieu'");
            while (rsTuoiToiThieu.next()) {
                tuoiToiThieu = rsTuoiToiThieu.getInt("GiaTri");

            }
            ResultSet rsTuoiToiDa = statement.executeQuery("SELECT THAMSO.GiaTri FROM dbo.THAMSO Where THAMSO.TenThamSo='TuoiToiDa'");
            while (rsTuoiToiDa.next()) {
                tuoiToiDa = rsTuoiToiDa.getInt("GiaTri");

            }
        } catch (SQLException ex) {
            Logger.getLogger(LayThamSo.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
