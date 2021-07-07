/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupframe;

import gui.JDBCConnection;
import gui.QuanLyLopPanel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ChuyenLop_QuanLyLopPanel extends javax.swing.JFrame {

    /**
     * Creates new form ChuyenLop_QuanLyLopPanel
     */
    QuanLyLopPanel dsl;
    public boolean check = true;

    void loadClas(String name) {
        String t = name.substring(0, 2);
        String sql = "select  * from  LOP  where TenLop like '" + t + "%' and TenLop<> '" + name + "'";
        System.out.println(sql);
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            if (!r.next()) {
                this.check = false;
            }
            r = sta.executeQuery(sql);
            while (r.next()) {
                this.clasBox.addItem(r.getString("TenLop"));
            }
        } catch (SQLException e) {

        }
    }

    void chuyen(String t) {

        String desClas = "";
        desClas = this.clasBox.getSelectedItem().toString();
        if (desClas == "") {
            return;
        }
        String sql = "declare @id int\n"
                + "set @id =(select HOCSINH.IDHocSinh from HOCSINH where MaHocSinh='"+t+"')\n"
                + " declare @des int\n"
                + "set @des= (select LOP.IDLop from LOP where  TenLop='" + desClas + "')\n"
                + "declare @sour int \n"
                + "set @sour= (select LOP.IDLop from LOP where  TenLop='" + this.dsl.selectedClas + "')\n"
                + "update QUATRINHHOC  set  IDLop= @des where IDLop=@sour  AND IDHocSinh=@id and  QUATRINHHOC.IDHocKi IN\n"
                + "(\n"
                + "SELECT HOCKI_NAM.IDHocKi\n"
                + "FROM HOCKI_NAM WHERE Nam=" + this.dsl.selectedYear + "\n"
                + ")";
        System.out.println(sql);
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement cst = cn.createStatement();
            int r = cst.executeUpdate(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Quá số lượng học sinh");

        }
    }

    public ChuyenLop_QuanLyLopPanel(QuanLyLopPanel t) {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.loadClas(t.selectedClas);
        dsl = t;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clasBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lớp:");

        jButton1.setText("Chuyển Lớp");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(48, Short.MAX_VALUE)
                        .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "đã chuyễn  các học sinh lớp" + this.dsl.selectedClas + " vào lớp " + this.clasBox.getSelectedItem() + " nam học " + this.dsl.selectedYear);
        List<String> list = dsl.getSelectedItemInfotable();
        list.forEach(maso -> {
            System.out.println();
            System.out.println(maso);
             chuyen(maso);
        });
       
        this.dsl.seek(dsl.selectedClas, dsl.selectedYear);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> clasBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
