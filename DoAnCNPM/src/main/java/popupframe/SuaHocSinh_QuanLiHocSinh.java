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
        if (!txtEmailHocSinh.getText().isEmpty()) {
            if (!(txtEmailHocSinh.getText().contains("@") && txtEmailHocSinh.getText().contains("."))) {
                KiemTra = false;
                STT = 1;
            }
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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Họ tên:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Ngày sinh:");
        jLabel11.setAlignmentY(0.8F);

        txtHoTenHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        datechooseHocSinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        datechooseHocSinh.setToolTipText("");
        datechooseHocSinh.setDateFormatString("dd/MM/yyyy");
        datechooseHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        datechooseHocSinh.setMinimumSize(new java.awt.Dimension(200, 200));

        textChuThichTuoi.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        textChuThichTuoi.setText("settext");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Email:");

        txtEmailHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmailHocSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        CapNhatHocSinh.setBackground(new java.awt.Color(0, 176, 239));
        CapNhatHocSinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        Close.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Địa chỉ:");

        txtAreaDiaChiHocSinh.setColumns(12);
        txtAreaDiaChiHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaDiaChiHocSinh.setRows(5);
        txtAreaDiaChiHocSinh.setToolTipText(""); // NOI18N
        txtAreaDiaChiHocSinh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setViewportView(txtAreaDiaChiHocSinh);

        buttonGroup1.add(rbNamHocSinh);
        rbNamHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbNamHocSinh.setText("Nam");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Giới tính:");

        buttonGroup1.add(rbNuHocSinh);
        rbNuHocSinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbNuHocSinh.setText("Nữ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(CapNhatHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtHoTenHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel14))
                            .addComponent(txtEmailHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(datechooseHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textChuThichTuoi))
                                .addGap(58, 58, 58)
                                .addComponent(jLabel13)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbNamHocSinh)
                                .addGap(2, 2, 2)
                                .addComponent(rbNuHocSinh))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtHoTenHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(rbNamHocSinh))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(rbNuHocSinh)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datechooseHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textChuThichTuoi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapNhatHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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