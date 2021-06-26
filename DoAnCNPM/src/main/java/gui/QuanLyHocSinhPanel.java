/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import popupframe.SuaHocSinh_QuanLiHocSinh;
import popupframe.ThemHocSinh_QuanLiHocSinh;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public final class QuanLyHocSinhPanel extends javax.swing.JPanel {

    SuaHocSinh_QuanLiHocSinh suahs = new SuaHocSinh_QuanLiHocSinh();
    ThemHocSinh_QuanLiHocSinh themhs = new ThemHocSinh_QuanLiHocSinh();

    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiDa();

    DefaultTableModel bangDuLieu = new DefaultTableModel();

    public DefaultTableModel getBangDuLieu() {
        return bangDuLieu;
    }
    //goi ham return tuoi toi thieu tuoi toi da

    String DiemBanDau = "";

    public QuanLyHocSinhPanel() {

        initComponents();
        suahs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        themhs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colTitle = {"STT", "Mã học sinh", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email"};
        boolean[] isEditable = {false, false, true, true, true, true, true};
        bangDuLieu = new DefaultTableModel(colTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return isEditable[column];
            }
        };
        tableHocSinh.setModel(bangDuLieu);

        HocSinhTrongTruong();
        themhs.ThemMoi.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                themhs.CapNhatDataBase();
                HocSinhTrongTruong();

            }
        });

        tableHocSinh.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int column = tableHocSinh.getSelectedColumn();
                int row = tableHocSinh.getSelectedRow();
                String checkValue = tableHocSinh.getValueAt(row, column).toString();
                String MaHS = tableHocSinh.getValueAt(row, 1).toString();
                String sql = "";

                if (column == 2) {
                    if (checkValue.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Họ tên không được để trống");
                        tableHocSinh.setValueAt(DiemBanDau, row, column);

                    } else {
                        sql = "Update HocSinh set HoTen=N'" + checkValue + "' where MaHocSinh='" + MaHS + "'";
                    }

                } //xet truong hop sua ngay sinh
                else if (column == 3) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    if (checkValue.isEmpty()) {
                        tableHocSinh.setValueAt(DiemBanDau, row, column);
                    } else {
                        try {

                            Date date = sdf.parse(checkValue);
                            int year = date.getYear() + 1900;

                            if (NamSinhCuaTuoiToiDa > year || year > NamSinhCuaTuoiToiThieu) {
                                JOptionPane.showMessageDialog(null, "Ngày sinh bạn nhập đã sai");   
                                tableHocSinh.setValueAt(DiemBanDau, row, column);

                            } else {
                                sql = "Update HocSinh set NgaySinh='" + checkValue + "' where MaHocSinh='" + MaHS + "'";
                            }

                        } catch (ParseException ex) {

                            JOptionPane.showMessageDialog(null, "Ngày sinh bạn nhập đã sai");
                            tableHocSinh.setValueAt(DiemBanDau, row, column);
                        }

                    }

                } else if (column == 4) {

                    if (!"Nam".equals(checkValue) && !"Nu".equals(checkValue)) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập 2 giá trị Nam hoặc Nu");
                        tableHocSinh.setValueAt(DiemBanDau, row, column);

                    } else {
                        sql = "Update HocSinh set GioiTinh='" + checkValue + "' where MaHocSinh='" + MaHS + "'";
                    }
                } else if (column == 5) {
                    sql = "Update HocSinh set DiaChi=N'" + checkValue + "' where MaHocSinh='" + MaHS + "'";
                } else if (column == 6) {

                    if (!(checkValue.contains("@") && checkValue.contains(".")) && !checkValue.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Email bạn nhập đã sai");
                        tableHocSinh.setValueAt(DiemBanDau, row, column);
                    } else {
                        sql = "Update HocSinh set Email='" + checkValue + "' where MaHocSinh='" + MaHS + "'";
                    }
                }
                Connection con = JDBCConnection.ketNoiJBDC();
                try {
                    Statement mystm = con.createStatement();

                    mystm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
        suahs.CapNhatHocSinh.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                suahs.capNhatHS();
                HocSinhTrongTruong();

            }
        });
    }

    public void HocSinhTrongTruong() {

        bangDuLieu.setRowCount(0);
        tableHocSinh.setModel(bangDuLieu);
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            Statement mystm = con.createStatement();
            String sql = "SELECT * FROM HOCSINH";
            ResultSet rs = mystm.executeQuery(sql);
            int STT = 1;
            while (rs.next()) {
                String HoTenHS = rs.getString("HoTen");
                String NgaySinh = rs.getString("NgaySinh");

                if (NgaySinh != null) {
                    Date dateNgaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(NgaySinh);
                    NgaySinh = new SimpleDateFormat("dd/MM/yyyy").format(dateNgaySinh);
                }

                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String Email = rs.getString("Email");
                String MaHocSinh = rs.getString("MaHocSinh");
                String[] data = {Integer.toString(STT), MaHocSinh, HoTenHS, NgaySinh, GioiTinh, DiaChi, Email};
                bangDuLieu.addRow(data);

                STT++;
            }
            tableHocSinh.setModel(bangDuLieu);

        } catch (SQLException ex) {
            System.out.println("knthanh cong");
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Xoa = new javax.swing.JButton();
        ThemMoi = new javax.swing.JButton();
        Sua = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MaHS = new javax.swing.JTextField();
        HoTen = new javax.swing.JTextField();
        TimKiembutton = new javax.swing.JButton();

        Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        Xoa.setText("Xóa");
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        ThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        ThemMoi.setText("Thêm mới");
        ThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoiActionPerformed(evt);
            }
        });

        Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/pencil.png"))); // NOI18N
        Sua.setText("Sửa");
        Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuaActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/recycle.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Học sinh trong trường"));

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
        tableHocSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableHocSinhMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableHocSinh);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(250, 224, 216));

        jLabel4.setText("QUẢN LÝ HỌC SINH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm "));

        jLabel2.setText("Họ tên:");

        jLabel3.setText("Mã học sinh:");

        MaHS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MaHSKeyReleased(evt);
            }
        });

        HoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HoTenKeyReleased(evt);
            }
        });

        TimKiembutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        TimKiembutton.setText("Tìm kiếm");
        TimKiembutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiembuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(MaHS, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(TimKiembutton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(MaHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimKiembutton))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThemMoi)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(ThemMoi)
                        .addGap(18, 18, 18)
                        .addComponent(Xoa)
                        .addGap(21, 21, 21)
                        .addComponent(Sua)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed
        //Lấy vị trí đang chọn trên tableHocSinh
        int indexTB = tableHocSinh.getSelectedRow();
        //check xem chua chon dong nao
        if (indexTB == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng bạn cần xóa!");
        } else {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Thông tin học sinh sẽ bị xóa vĩnh viễn", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                bangDuLieu.removeRow(indexTB);

                int row = tableHocSinh.getRowCount();

                for (int i = 0; i < row; i++) {
                    tableHocSinh.setValueAt(i + 1, i, 0);

                }

            }
        }

        //Xóa dòng đang chọn ra khỏi bangDuLieu

    }//GEN-LAST:event_XoaActionPerformed

    private void tableHocSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHocSinhMousePressed
        try {
            DiemBanDau = tableHocSinh.getValueAt(tableHocSinh.getSelectedRow(), tableHocSinh.getSelectedColumn()).toString();
        } catch (Exception e) {
            DiemBanDau = "";
        }
    }//GEN-LAST:event_tableHocSinhMousePressed

    private void ThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMoiActionPerformed

        themhs.setVisible(true);

    }//GEN-LAST:event_ThemMoiActionPerformed

    private void TimKiembuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiembuttonActionPerformed
        String MaHs = MaHS.getText();
        String HoTenHS = HoTen.getText();
        String sql = "";
        if (MaHs.isEmpty() && HoTenHS.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin để tìm kiếm");

        } else {
            bangDuLieu.setRowCount(0);
            tableHocSinh.setModel(bangDuLieu);
            if (!MaHs.isEmpty()) {
                sql = "SELECT * FROM HOCSINH WHERE MaHocSinh like '%" + MaHs + "%'";
            } else {
                sql = "SELECT * FROM HOCSINH WHERE HoTen like N'%" + HoTenHS + "%'";
            }

            Connection con = JDBCConnection.ketNoiJBDC();
            try {
                Statement mystm = con.createStatement();
                ResultSet rs = mystm.executeQuery(sql);
                int STT = 1;
                while (rs.next()) {

                    String HoTenHocSinh = rs.getString("HoTen");
                    String NgaySinh = rs.getString("NgaySinh");

                    String GioiTinh = rs.getString("GioiTinh");
                    String DiaChi = rs.getString("DiaChi");
                    String Email = rs.getString("Email");
                    String MaHocSinh = rs.getString("MaHocSinh");
                    String[] data = {Integer.toString(STT), MaHocSinh, HoTenHocSinh, NgaySinh, GioiTinh, DiaChi, Email};
                    bangDuLieu.addRow(data);

                    STT++;
                }
                tableHocSinh.setModel(bangDuLieu);

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_TimKiembuttonActionPerformed

    private void MaHSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MaHSKeyReleased
        if (!MaHS.getText().isEmpty()) {
            HoTen.setEnabled(false);
        } else {
            HoTen.setEnabled(true);
        }
    }//GEN-LAST:event_MaHSKeyReleased

    private void HoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HoTenKeyReleased
        if (!HoTen.getText().isEmpty()) {
            MaHS.setEnabled(false);
        } else {
            MaHS.setEnabled(true);
        }
    }//GEN-LAST:event_HoTenKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        HocSinhTrongTruong();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuaActionPerformed
        int row = tableHocSinh.getSelectedRow();
        if (row != -1) {
            suahs.setVisible(true);
            SuaHocSinh_QuanLiHocSinh.getMaHocSinh = tableHocSinh.getValueAt(row, 1).toString();
            suahs.txtHoTenHocSinh.setText(tableHocSinh.getValueAt(row, 2).toString());
            if ("Nam".equals(tableHocSinh.getValueAt(row, 4).toString())) {
                suahs.rbNamHocSinh.setSelected(true);
            } else {
                suahs.rbNuHocSinh.setSelected(true);
            }

            if (tableHocSinh.getValueAt(row, 5) != null) {
                suahs.txtAreaDiaChiHocSinh.setText(tableHocSinh.getValueAt(row, 5).toString());
            }
            if (tableHocSinh.getValueAt(row, 6) != null) {
                suahs.txtEmailHocSinh.setText(tableHocSinh.getValueAt(row, 6).toString());
            }
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(tableHocSinh.getValueAt(row, 3).toString());
                suahs.datechooseHocSinh.setDate(date);
            } catch (ParseException ex) {
                Logger.getLogger(QuanLyHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xem thông tin");

        }
    }//GEN-LAST:event_SuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HoTen;
    private javax.swing.JTextField MaHS;
    private javax.swing.JButton Sua;
    public javax.swing.JButton ThemMoi;
    private javax.swing.JButton TimKiembutton;
    private javax.swing.JButton Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tableHocSinh;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
