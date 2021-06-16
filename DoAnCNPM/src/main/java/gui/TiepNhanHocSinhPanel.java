/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class TiepNhanHocSinhPanel extends javax.swing.JPanel {

    DefaultTableModel bangDuLieu = new DefaultTableModel();
    //goi lay tham so
    LayThamSo thamso = new LayThamSo();
    //goi ham return tuoi toi thieu tuoi toi da
    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR)- thamso.getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa=  Calendar.getInstance().get(Calendar.YEAR)-thamso.getTuoiToiDa();
    int NamHienTai = Calendar.getInstance().get(Calendar.YEAR);
    public TiepNhanHocSinhPanel() {
       
        initComponents();
         textChuThichTuoi.setText("Vui lòng nhập năm sinh giữa "+ NamSinhCuaTuoiToiDa +" và "+ NamSinhCuaTuoiToiThieu);
        //set format  date
        //set locale viet nam
        datechoose.setLocale(Locale.forLanguageTag("vi-VN"));
        datechoose.getJCalendar().setPreferredSize(new Dimension(400, 250));
        datechoose.setDateFormatString("dd/MM/yyyy");
      
        bangDuLieu.addColumn("Họ tên");
        bangDuLieu.addColumn("Giới tính");
        bangDuLieu.addColumn("Ngày sinh");
        bangDuLieu.addColumn("Địa chỉ");
        bangDuLieu.addColumn("Email");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaDiaChi = new javax.swing.JTextArea();
        datechoose = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        textChuThichTuoi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin học sinh"));

        jLabel1.setText("Họ tên:");

        jLabel2.setText("Email:");

        jLabel3.setText("Giới tính");

        txtHoTen.setToolTipText("");
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngày sinh:");
        jLabel4.setAlignmentY(0.8F);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNam);
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setText("Nữ");

        jLabel6.setText("Địa chỉ");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        jButton1.setText("Thêm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*");

        txtAreaDiaChi.setColumns(20);
        txtAreaDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtAreaDiaChi);

        datechoose.setToolTipText("");
        datechoose.setDateFormatString("dd/MM/yyyy");
        datechoose.setMinimumSize(new java.awt.Dimension(200, 200));
        datechoose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                datechooseFocusGained(evt);
            }
        });
        datechoose.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                datechooseInputMethodTextChanged(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/recycle.png"))); // NOI18N
        jButton5.setText("Làm mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        textChuThichTuoi.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        textChuThichTuoi.setText("settext");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(40, 40, 40)
                        .addComponent(jButton5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textChuThichTuoi))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNam)
                        .addGap(40, 40, 40)
                        .addComponent(rbNu)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHoTen)
                    .addComponent(jLabel3)
                    .addComponent(rbNam)
                    .addComponent(rbNu)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail)
                            .addComponent(jLabel2))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textChuThichTuoi)
                                    .addGap(25, 25, 25))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin bạn đã nhập"));

        tableHocSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Email"
            }
        ));
        jScrollPane1.setViewportView(tableHocSinh);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/updated.png"))); // NOI18N
        jButton2.setText("Cập nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        jButton4.setText("Xóa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton2)
                .addGap(47, 47, 47)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        
        int NamDaNhap = 0;

        String gioiTinh = "1";
        // chuyen sang dinh dang ngay thang nam
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
        //format day sang string
        String txtNgaysinh;
        if (datechoose.getDate() == null) {
            txtNgaysinh = "";
        } else {
            txtNgaysinh = sdf.format(datechoose.getDate());
            NamDaNhap = Integer.parseInt(getYear.format(datechoose.getDate()));
        }

        //Lưu trữ thông tin 1 dòng vào mảng data
        if (txtHoTen.getText().equals("") || txtNgaysinh.equals("") || ((!rbNam.isSelected()) && (!rbNu.isSelected()))) {
            //Nếu các dòng này trống thì báo message
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
        } else if (NamSinhCuaTuoiToiDa > NamHienTai - NamDaNhap || NamHienTai - NamDaNhap > NamSinhCuaTuoiToiThieu) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh giữa "+ NamSinhCuaTuoiToiDa +" và "+ NamSinhCuaTuoiToiThieu);
        } else {
            //Nếu điền đầy đủ, điền thông tin vào bangDuLieu
            //Lưu thông tin giới tính từ rdNam, rdNu

            if (rbNam.isSelected()) {
                gioiTinh = "Nam";
            } else if (rbNu.isSelected()) {
                gioiTinh = "Nu";
            }

            String data[] = {txtHoTen.getText(), gioiTinh, txtNgaysinh, txtAreaDiaChi.getText(), txtEmail.getText()};
            //Lưu data vào bangDuLieu
            bangDuLieu.addRow(data);

            //Gán bangDuLieu vào jTalbe tableHocSinh
            tableHocSinh.setModel(bangDuLieu);

            //Dọn textfield để thêm dữ liệu mới
            txtHoTen.setText("");
            txtEmail.setText("");
            txtAreaDiaChi.setText("");
            buttonGroup1.clearSelection();
            datechoose.setCalendar(null);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin những dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            Connection con = JDBCConnection.ketNoiJBDC();

            try {
                //Tạo statement
                CallableStatement mystm = con.prepareCall("{call addstudent(?,?,?,?,?)}");
                //so hang
                int row = tableHocSinh.getRowCount();
                //so cot
                int column = tableHocSinh.getColumnCount();
                //chay vong lap: i la vi tri hang, j la vi tri cot
                for (int i = 0; i < row; i++) {
                    int index = 1;
                    for (int j = 0; j < column; j++) {
                        //lay gia tri gioi tinh
                        if (index == 2) {
                            String Gioitinh = tableHocSinh.getValueAt(i, j).toString();
                            if (Gioitinh == "Nam") {
                                mystm.setInt(index, 1);
                            } else if (Gioitinh == "Nu") {
                                mystm.setInt(index, 0);
                            }

                        } else {
                            //lay gia tri
                            mystm.setString(index, tableHocSinh.getValueAt(i, j).toString());
                        }
                        index++;
                    }
                    //chay du lieu vao sql
                    mystm.execute();
                }

            } catch (SQLException ex) {
                Logger.getLogger(TiepNhanHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Lấy vị trí đang chọn trên tableHocSinh
        int indexTB = tableHocSinh.getSelectedRow();
        //check xem chua chon dong nao
        if (indexTB == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng bạn cần xóa!");
        } else {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thông tin dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                bangDuLieu.removeRow(indexTB);
            }
        }
        //Xóa dòng đang chọn ra khỏi bangDuLieu

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        txtHoTen.setText("");
        txtEmail.setText("");
        txtAreaDiaChi.setText("");
        buttonGroup1.clearSelection();
        datechoose.setCalendar(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void datechooseInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datechooseInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseInputMethodTextChanged

    private void datechooseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_datechooseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseFocusGained

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datechoose;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tableHocSinh;
    private javax.swing.JLabel textChuThichTuoi;
    private javax.swing.JTextArea txtAreaDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    // End of variables declaration//GEN-END:variables
}
