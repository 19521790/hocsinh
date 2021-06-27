/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupframe;

import gui.JDBCConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class SuaHocSinh_QuanLiHocSinh extends javax.swing.JDialog {

    public static String getMaHocSinh = "";

    /**
     * Creates new form SuaHocSinh_QuanLiHocSinh
     */
    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiDa();

    public SuaHocSinh_QuanLiHocSinh() {

        initComponents();
        datechooseHocSinh.getCalendarButton().setVisible(false);
        Date maxSelected = new GregorianCalendar(NamSinhCuaTuoiToiThieu, 11, 31).getTime();
        Date minSelected = new GregorianCalendar(NamSinhCuaTuoiToiDa, 0, 1).getTime();
        datechooseHocSinh.getDateEditor().setMaxSelectableDate(maxSelected);
        datechooseHocSinh.getDateEditor().setMinSelectableDate(minSelected);
        textChuThichTuoi.setText("Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void capNhatHS() {

        int STT = -1;
        boolean KiemTra = true;
        if (!(txtEmailHocSinh.getText().contains("@") && txtEmailHocSinh.getText().contains("."))) {
            KiemTra = false;
            STT = 1;
        }
        Date checkYear = datechooseHocSinh.getDate();
        int year = checkYear.getYear() + 1900;
        if (year < NamSinhCuaTuoiToiDa || year > NamSinhCuaTuoiToiThieu) {
            KiemTra = false;
            STT = 2;
        }
        if (KiemTra == true) {
            int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin những dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {

                Connection con = JDBCConnection.ketNoiJBDC();

                //Tạo statement
                CallableStatement mystm;
                try {
                    mystm = con.prepareCall("{call sp_SuaHocSinh_SuaThongTin(?,?,?,?,?,?)}");
                    mystm.setString(1, getMaHocSinh);
                    mystm.setString(2, txtHoTenHocSinh.getText());
                    mystm.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(datechooseHocSinh.getDate()));
                    if (rbNamHocSinh.isSelected()) {
                        mystm.setString(4, "Nam");
                    } else {
                        mystm.setString(4, "Nu");
                    }

                    mystm.setString(5, txtAreaDiaChiHocSinh.getText());
                    mystm.setString(6, txtEmailHocSinh.getText());

                    mystm.execute();
                    this.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(SuaHocSinh_QuanLiHocSinh.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            if (STT == 1) {
                JOptionPane.showMessageDialog(null, "Email bạn nhập đã sai!!");
            }
            if (STT == 2) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập năm sinh giữa 2003 và 2006!!");
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtHoTenHocSinh = new javax.swing.JTextField();
        String datePattern = "dd/MM/yyyy";
        String dateMask = "##/##/####";
        datechooseHocSinh = new com.toedter.calendar.JDateChooser(datePattern, dateMask, '_');
        textChuThichTuoi = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmailHocSinh = new javax.swing.JTextField();
        CapNhatHocSinh = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaDiaChiHocSinh = new javax.swing.JTextArea();
        rbNamHocSinh = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        rbNuHocSinh = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Họ tên:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Ngày sinh:");
        jLabel11.setAlignmentY(0.8F);

        txtHoTenHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        datechooseHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        datechooseHocSinh.setToolTipText("");
        datechooseHocSinh.setDateFormatString("dd/MM/yyyy");
        datechooseHocSinh.setMinimumSize(new java.awt.Dimension(200, 200));

        textChuThichTuoi.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        textChuThichTuoi.setText("settext");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Email:");

        txtEmailHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        CapNhatHocSinh.setBackground(new java.awt.Color(0, 176, 239));
        CapNhatHocSinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CapNhatHocSinh.setForeground(new java.awt.Color(255, 255, 255));
        CapNhatHocSinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_save_24px.png"))); // NOI18N
        CapNhatHocSinh.setText("Lưu");
        CapNhatHocSinh.setToolTipText("");
        CapNhatHocSinh.setAutoscrolls(true);
        CapNhatHocSinh.setBorder(null);
        CapNhatHocSinh.setFocusable(false);
        CapNhatHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatHocSinhActionPerformed(evt);
            }
        });

        Close.setBackground(new java.awt.Color(254, 193, 6));
        Close.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_exit_24px_1.png"))); // NOI18N
        Close.setText("Thoát");
        Close.setBorder(null);
        Close.setFocusable(false);
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Địa chỉ:");

        txtAreaDiaChiHocSinh.setColumns(12);
        txtAreaDiaChiHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtAreaDiaChiHocSinh.setRows(5);
        txtAreaDiaChiHocSinh.setToolTipText(""); // NOI18N
        txtAreaDiaChiHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtAreaDiaChiHocSinh);

        buttonGroup1.add(rbNamHocSinh);
        rbNamHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbNamHocSinh.setText("Nam");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Giới tính:");

        buttonGroup1.add(rbNuHocSinh);
        rbNuHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        rbNuHocSinh.setText("Nữ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(datechooseHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtHoTenHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textChuThichTuoi)
                            .addComponent(txtEmailHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CapNhatHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbNamHocSinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbNuHocSinh))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtHoTenHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(rbNamHocSinh)
                    .addComponent(rbNuHocSinh))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jLabel12)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(datechooseHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textChuThichTuoi)
                                .addGap(11, 11, 11))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapNhatHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CloseActionPerformed

    private void CapNhatHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatHocSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapNhatHocSinhActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CapNhatHocSinh;
    private javax.swing.JButton Close;
    private javax.swing.ButtonGroup buttonGroup1;
    public com.toedter.calendar.JDateChooser datechooseHocSinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JRadioButton rbNamHocSinh;
    public javax.swing.JRadioButton rbNuHocSinh;
    private javax.swing.JLabel textChuThichTuoi;
    public javax.swing.JTextArea txtAreaDiaChiHocSinh;
    public javax.swing.JTextField txtEmailHocSinh;
    public javax.swing.JTextField txtHoTenHocSinh;
    // End of variables declaration//GEN-END:variables
}
