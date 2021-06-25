/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.*;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class QuanLyHocSinhPanel extends javax.swing.JPanel {

    DefaultTableModel bangDuLieu = new DefaultTableModel();

    //goi ham return tuoi toi thieu tuoi toi da
    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiDa();
    int NamHienTai = Calendar.getInstance().get(Calendar.YEAR);
    String DiemBanDau = "";

    public QuanLyHocSinhPanel() {

        initComponents();
//        textChuThichTuoi.setText("Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);
//        //set format  date
//        //set locale viet nam
//
//        datechoose.setDateFormatString("dd/MM/yyyy");
//        datechoose.getCalendarButton().setVisible(false);
//        datechooseHocSinh.getCalendarButton().setVisible(false);
//        Date maxSelected = new GregorianCalendar(NamSinhCuaTuoiToiThieu, 11, 31).getTime();
//        Date minSelected = new GregorianCalendar(NamSinhCuaTuoiToiDa, 0, 1).getTime();
////nam sinh toi da dc chon
//        datechoose.getDateEditor().setMaxSelectableDate(maxSelected);
//        //nam sinh toi thieu đc chon
//        datechoose.getDateEditor().setMinSelectableDate(minSelected);
//        //tuong tu doi voi bang sua học sinh
//        datechooseHocSinh.getDateEditor().setMaxSelectableDate(maxSelected);
//        datechooseHocSinh.getDateEditor().setMinSelectableDate(minSelected);
//
//        bangDuLieu.addColumn("STT");
//
//        bangDuLieu.addColumn("Họ tên");
//
//        bangDuLieu.addColumn("Ngày sinh");
//        bangDuLieu.addColumn("Giới tính");
//        bangDuLieu.addColumn("Địa chỉ");
//        bangDuLieu.addColumn("Email");
//
//        tableHocSinh.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
//            @Override
//            @SuppressWarnings("empty-statement")
//            public void editingStopped(ChangeEvent e) {
//
//                int column = tableHocSinh.getSelectedColumn();
//                int row = tableHocSinh.getSelectedRow();
//                String checkValue = tableHocSinh.getValueAt(row, column).toString();
//                //xet truong hop sua ngay sinh
//                if (column == 2) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//                    if (checkValue.isEmpty()) {
//                        tableHocSinh.setValueAt(DiemBanDau, row, column);
//                    } else {
//                        try {
//
//                            Date date = sdf.parse(checkValue);
//                            int year = date.getYear() + 1900;
//
//                            if (NamSinhCuaTuoiToiDa > year || year > NamSinhCuaTuoiToiThieu) {
//                                JOptionPane.showMessageDialog(null, "Ngày sinh bạn nhập đã sai");
//                                tableHocSinh.setValueAt(DiemBanDau, row, column);
//
//                            }
//
//                        } catch (ParseException ex) {
//
//                            JOptionPane.showMessageDialog(null, "Ngày sinh bạn nhập đã sai");
//                            tableHocSinh.setValueAt(DiemBanDau, row, column);
//                        }
//
//                    }
//
//                }
//                if (column == 5) {
//                    System.out.println(checkValue);
//                    if (!(checkValue.contains("@") && checkValue.contains("."))) {
//                        JOptionPane.showMessageDialog(null, "Email bạn nhập đã sai");
//                        tableHocSinh.setValueAt(DiemBanDau, row, column);
//                    }
//                }
//                if (column == 3) {
//
//                    if (!"Nam".equals(checkValue) && !"Nu".equals(checkValue)) {
//                        JOptionPane.showMessageDialog(null, "Vui lòng nhập 2 giá trị Nam hoặc Nu");
//                        tableHocSinh.setValueAt(DiemBanDau, row, column);
//                    }
//                }
//            }
//
//            @Override
//            public void editingCanceled(ChangeEvent e) {
//
//            }
//        });
        //them listen vao bang dem xem du lieu chinh sua co dung hay khong

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        Xoa = new javax.swing.JButton();
        CapNhat = new javax.swing.JButton();
        ThemMoi1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        seekButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        Xoa.setText("Xóa");
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        CapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/updated.png"))); // NOI18N
        CapNhat.setText("Cập nhật");
        CapNhat.setToolTipText("");
        CapNhat.setAutoscrolls(true);
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });

        ThemMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        ThemMoi1.setText("Thêm mới");
        ThemMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoi1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin bạn đã nhập"));

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Sửa");

        jLabel1.setText("Tra cứu:");

        jTextField1.setText("Họ tên");

        jTextField2.setText("Mã học sinh");

        jLabel2.setText("Họ tên:");

        jLabel3.setText("Mã học sinh:");

        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Làm mới");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ThemMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(seekButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(seekButton))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(ThemMoi1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Xoa)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CapNhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    int STT = 1;
    private void XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaActionPerformed
        //Lấy vị trí đang chọn trên tableHocSinh
        int indexTB = tableHocSinh.getSelectedRow();
        //check xem chua chon dong nao
        if (indexTB == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng bạn cần xóa!");
        } else {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
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

    private void ThemMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMoi1ActionPerformed
        //add listen toi thay doi bang
//
//        int NamDaNhap = 0;
//
//        String gioiTinh = "1";
//        // chuyen sang dinh dang ngay thang nam
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
//        //format day sang string
//        String txtNgaysinh;
//        if (datechoose.getDate() == null) {
//            txtNgaysinh = "";
//        } else {
//            txtNgaysinh = sdf.format(datechoose.getDate());
//            NamDaNhap = Integer.parseInt(getYear.format(datechoose.getDate()));
//        }
//
//        //Lưu trữ thông tin 1 dòng vào mảng data
//        if (txtHoTen.getText().equals("") || txtNgaysinh.equals("") || gmail.getSelectedItem().toString().isEmpty() || ((!rbNam.isSelected()) && (!rbNu.isSelected()))) {
//            //Nếu các dòng này trống thì báo message
//            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
//        } else if (NamSinhCuaTuoiToiDa > NamDaNhap || NamDaNhap > NamSinhCuaTuoiToiThieu) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);
//        } else {
//
//            //kiem tra xem mail nhập nếu chọn khác có đúng không
//            if (!(gmail.getSelectedItem().toString().contains("@") && gmail.getSelectedItem().toString().contains("."))) {
//                JOptionPane.showMessageDialog(this, "Thông tin email bị sai!!");
//            } else {
//                if (rbNam.isSelected()) {
//                    gioiTinh = "Nam";
//                } else if (rbNu.isSelected()) {
//                    gioiTinh = "Nu";
//                }
//                String duoiGmail = gmail.getSelectedItem().toString();
//                if (txtEmail.getText().isEmpty()) {
//                    duoiGmail = "";
//                }
//
//                String data[] = {Integer.toString(STT), txtHoTen.getText(), txtNgaysinh, gioiTinh, txtAreaDiaChi.getText(), txtEmail.getText() + duoiGmail};
//                //Lưu data vào bangDuLieu
//                bangDuLieu.addRow(data);
//
//                //Gán bangDuLieu vào jTalbe tableHocSinh
//                tableHocSinh.setModel(bangDuLieu);
//
//                //Dọn textfield để thêm dữ liệu mới
//                txtHoTen.setText("");
//                txtEmail.setText("");
//                txtAreaDiaChi.setText("");
//                gmail.setSelectedIndex(0);
//                buttonGroup1.clearSelection();
//                datechoose.setCalendar(null);
//                STT++;
//            }
//            //Nếu điền đầy đủ, điền thông tin vào bangDuLieu
//            //Lưu thông tin giới tính từ rdNam, rdNu
//
//        }
    }//GEN-LAST:event_ThemMoi1ActionPerformed

    private void seekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekButtonActionPerformed
//        // TODO add your handling code here:
//        if (this.checkhaveSelected()) {
//            return;
//        }
//        this.mshsChosen = "";
//        this.deletequery = new ArrayList<>();
//        this.procquery = new ArrayList<>();
//        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
//        model.setRowCount(0);
//        System.out.println((String) this.yearList.getSelectedItem());
//        this.selectedClas = (String) this.clasList.getSelectedItem();
//        this.selectedYear = (String) this.yearList.getSelectedItem();
//        seek((String) this.clasList.getSelectedItem(), (String) this.yearList.getSelectedItem());
    }//GEN-LAST:event_seekButtonActionPerformed

    private void CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatActionPerformed
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin những dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            Connection con = JDBCConnection.ketNoiJBDC();

            try {
                //Tạo statement
                CallableStatement mystm = con.prepareCall("{call sp_TiepNhanHocSinh_ThemHocSinh(?,?,?,?,?)}");
                //so hang
                int row = tableHocSinh.getRowCount();
                //so cot
                int column = tableHocSinh.getColumnCount();

                //chay vong lap: i la vi tri hang, j la vi tri cot
                for (int i = 0; i < row; i++) {
                    int index = 1;
                    for (int j = 1; j < column; j++) {

                        //                        lay gia tri gioi tinh
                        switch (index) {
                            case 3 -> {
                                String Gioitinh = tableHocSinh.getValueAt(i, j).toString();
                                if ("Nam".equals(Gioitinh)) {
                                    mystm.setInt(index, 1);
                                } else if ("Nu".equals(Gioitinh)) {
                                    mystm.setInt(index, 0);
                                }
                            }

                            default -> //lay gia tri
                            mystm.setString(index, tableHocSinh.getValueAt(i, j).toString());
                        }
                        index++;
                    }

                    //                    chay du lieu vao sql
                    mystm.execute();

                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_CapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CapNhat;
    private javax.swing.JButton ThemMoi1;
    private javax.swing.JButton Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton seekButton;
    private javax.swing.JTable tableHocSinh;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
