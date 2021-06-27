/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupframe;

import gui.QuanLyLopPanel;
import gui.JDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThemHocSinh_DanhSachHocSinhPanel extends javax.swing.JFrame {

    /**
     * Creates new form themhocsinhDialog
     */
    QuanLyLopPanel dsl;
    ListSelectionModel listSelectionModel;
    String malop;
    String nam;
//    boolean check(String mshs) {
//        String sql1 = "select * from HOCSINH where MaHocSinh= '" + mshs + "'";
//        String sql2 ="select * from QUATRINHHOC WHERE MaHocSinh='"+mshs+"'AND  MaLop is null";
//         System.out.println(sql2);
//        try {
//            Connection cn = JDBCConnection.ketNoiJBDC();
//            Statement sta = cn.createStatement();
//            ResultSet r = sta.executeQuery(sql1);
//
//            if (r.next()) {
//
//                this.warningLabel.setText("Hoc sinh:" + r.getString("HoTen"));
//                System.out.print("^^^^^^^^");
//            } else {
//                this.warningLabel.setText("Khong tim thay");
//                System.out.println("NO DATA");
//                return false;
//            }
//            r = sta.executeQuery(sql2);
//            if (!r.next()) {
//                System.out.println(sql2);
//                System.out.println("data  sql2 in themhocsinh_danhsachhocsinh");
//                 this.warningLabel.setText("Hoc sinh da co lop");
//                return false;
//
//            }
//        } catch (SQLException e) {
//            System.out.println("errror");
//        }
//        return true;
//
//    }

    private void addStudent(int indicesRow[]) {

        if (!(indicesRow.length > 0)) {
             JOptionPane.showMessageDialog(this, "Chưa có học sinh nào đc chọn");
            return;
        }
        String thongbao="";
        String mshs = "";
       DefaultTableModel model = (DefaultTableModel) this.tableHocSinh.getModel();
        for (int indices : indicesRow) {
            mshs = this.tableHocSinh.getValueAt(indices, 1).toString();
            thongbao=thongbao+mshs+" ";
            System.out.println(mshs);
            dsl.addintoTable(mshs);
            dsl.addstu(mshs);
             
        }
         JOptionPane.showMessageDialog(this, "đã thêm các học sinh có mã số "+thongbao+" vào lớp "+malop+" nam học "+nam);
         this.removeSelectedRows();
          // DefaultTableModel model = (DefaultTableModel) this.tableHocSinh.getModel();
//           for(int i:indicesRow){
//           model.removeRow(i);
//             System.out.println(i);
//           
//           }
        

    }
    public void removeSelectedRows(){
   DefaultTableModel model = (DefaultTableModel) this.tableHocSinh.getModel();
   int[] rows = tableHocSinh.getSelectedRows();
   for(int i=0;i<rows.length;i++){
     model.removeRow(rows[i]-i);
   }
}
    public void loadtable() {
         DefaultTableModel model = (DefaultTableModel) this.tableHocSinh.getModel();
        model.setRowCount(0);
        String sql = "select DISTINCT HOCSINH.MaHocSinh,HoTen,NgaySinh,GioiTinh,DiaChi,Email from HOCSINH WHERE   IDHocSinh  NOT IN( SELECT IDHocSinh FROM QUATRINHHOC ,HOCKI_NAM where HOCKI_NAM.IDHocKi=QUATRINHHOC.IDHocKi and Nam="+nam+" and IDLop is not null)";
       // String sql2 ="select * from HOCSINH  WHERE    HOCSINH.IDHocSinh NOT IN (SELECT QUATRINHHOC.IDHocSinh FROM QUATRINHHOC ,HOCKI_NAM WHERE  QUATRINHHOC.IDHocKi=HOCKI_NAM.IDHocKi AND Nam=" +nam +")";
        System.out.println(sql);
       
         int i = 0;
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
         ResultSet   r = sta.executeQuery(sql);
             while (r.next()) {
                i++;
                String name = r.getString("HoTen");
                String date = r.getString("NgaySinh");
                String mahs = r.getString("MaHocSinh");
                System.out.println(mahs);
                String email = r.getString("Email");
                String address = r.getString("DiaChi");
                String s = r.getString("GioiTinh");
                String datab[] = {Integer.toString(i), mahs, name, date, s, address, email};
                DefaultTableModel tblM = (DefaultTableModel) this.tableHocSinh.getModel();
                tblM.addRow(datab);
            }
            
        } catch (SQLException e) {

        }
        
        

    }

    public ThemHocSinh_DanhSachHocSinhPanel() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //  tableHocSinh.setRowSelectionAllowed(true);
        //  tableHocSinh.setSelectionMode(listSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public ThemHocSinh_DanhSachHocSinhPanel(QuanLyLopPanel t) {
        initComponents();
        System.out.println(" ThemHocSinh_DanhSachHocSinhPanel");
        dsl = t;
        loadtable();
        malop = dsl.selectedClas;
        nam = dsl.selectedYear;
        System.out.println(nam + ":::" + malop);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        ThemMoi1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(239, 247, 248));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane1.setFocusable(false);
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        tableHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tableHocSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã học sinh", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableHocSinh.setFocusable(false);
        tableHocSinh.setGridColor(new java.awt.Color(255, 255, 255));
        tableHocSinh.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableHocSinh.setSelectionBackground(new java.awt.Color(0, 176, 239));
        tableHocSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableHocSinhMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableHocSinh);

        ThemMoi1.setBackground(new java.awt.Color(0, 176, 239));
        ThemMoi1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ThemMoi1.setForeground(new java.awt.Color(255, 255, 255));
        ThemMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        ThemMoi1.setText("Thêm");
        ThemMoi1.setBorder(null);
        ThemMoi1.setFocusPainted(false);
        ThemMoi1.setFocusable(false);
        ThemMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(ThemMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(ThemMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableHocSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHocSinhMousePressed
//        try {
//            DiemBanDau = tableHocSinh.getValueAt(tableHocSinh.getSelectedRow(), tableHocSinh.getSelectedColumn()).toString();
//        } catch (Exception e) {
//            DiemBanDau = "";
//        }
    }//GEN-LAST:event_tableHocSinhMousePressed

    private void ThemMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMoi1ActionPerformed
        // TODO add your handling code here:
        int index[] = this.tableHocSinh.getSelectedRows();
        System.out.println("them HS");
        for (var i : index) {
            System.out.println(Integer.toString(i));
        }
        addStudent(index);
        this.dsl.refreshSTT();


    }//GEN-LAST:event_ThemMoi1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ThemMoi1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableHocSinh;
    // End of variables declaration//GEN-END:variables
}
