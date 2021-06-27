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
    static int siSoToiDa = 0;
    static int diemQuaMon = 0;
    static int diemLenLop = 0;

    public static int getSiSoToiDa() {
        return siSoToiDa;
    }

    public static int getDiemQuaMon() {
        return diemQuaMon;
    }

    public static int getDiemLenLop() {
        return diemLenLop;
    }
    public static int getTuoiToiThieu() {
        return tuoiToiThieu;
    }

    public static int getTuoiToiDa() {
        return tuoiToiDa;
    }

    public void ketNoiCoSoDulieu() {
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT GiaTri FROM THAMSO where TenThamSo='TuoiToiThieu'");
            while (rs.next()) {
                tuoiToiThieu = rs.getInt("GiaTri");
            }
            rs = statement.executeQuery("SELECT GiaTri FROM THAMSO where TenThamSo='TuoiToiDa'");
            while (rs.next()) {
                tuoiToiDa = rs.getInt("GiaTri");
            }
            rs = statement.executeQuery("SELECT GiaTri FROM THAMSO where TenThamSo='SiSoToiDa'");
            while (rs.next()) {
                siSoToiDa = rs.getInt("GiaTri");
            }
            rs = statement.executeQuery("SELECT GiaTri FROM THAMSO where TenThamSo='DiemQuaMon'");
            while (rs.next()) {
                diemQuaMon = rs.getInt("GiaTri");
            }
            rs = statement.executeQuery("SELECT GiaTri FROM THAMSO where TenThamSo='DiemLenLop'");
            while (rs.next()) {
                diemLenLop = rs.getInt("GiaTri");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LayThamSo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
