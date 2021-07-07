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
import javax.swing.JOptionPane;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class ThemHocSinh_QuanLiHocSinh extends javax.swing.JDialog {

    //goi ham return tuoi toi thieu tuoi toi da
    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiDa();
    int NamHienTai = Calendar.getInstance().get(Calendar.YEAR);
    String DiemBanDau = "";

    public ThemHocSinh_QuanLiHocSinh() {
  
        initComponents();

        textChuThichTuoi.setText("Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);
        //set format  date
        //set locale viet nam

        datechoose.setDateFormatString("dd/MM/yyyy");
        datechoose.getCalendarButton().setVisible(false);
        datechoose.getCalendarButton().setVisible(false);
        Date maxSelected = new GregorianCalendar(NamSinhCuaTuoiToiThieu, 11, 31).getTime();
        Date minSelected = new GregorianCalendar(NamSinhCuaTuoiToiDa, 0, 1).getTime();
//nam sinh toi da dc chon
        datechoose.getDateEditor().setMaxSelectableDate(maxSelected);
        //nam sinh toi thieu đc chon
        datechoose.getDateEditor().setMinSelectableDate(minSelected);
        //tuong tu doi voi bang sua học sinh
        datechoose.getDateEditor().setMaxSelectableDate(maxSelected);
        datechoose.getDateEditor().setMinSelectableDate(minSelected);

        
    }

    public void CapNhatDataBase() {
        System.out.println("hi");
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
        } else if (NamSinhCuaTuoiToiDa > NamDaNhap || NamDaNhap > NamSinhCuaTuoiToiThieu) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);
        } else {

            //kiem tra xem mail nhập nếu chọn khác có đúng không
            if (!(gmail.getSelectedItem().toString().contains("@") && gmail.getSelectedItem().toString().contains("."))) {
                JOptionPane.showMessageDialog(this, "Thông tin email bị sai!!");
            } else {
                if (rbNam.isSelected()) {
                    gioiTinh = "Nam";
                } else if (rbNu.isSelected()) {
                    gioiTinh = "Nu";
                }
                String duoiGmail = gmail.getSelectedItem().toString();
                if (txtEmail.getText().isEmpty()) {
                    duoiGmail = "";
                }
                Connection con = JDBCConnection.ketNoiJBDC();

                try {
                    try (CallableStatement mystm = con.prepareCall("{call sp_TiepNhanHocSinh_ThemHocSinh(?,?,?,?,?)}")) {
                        mystm.setString(1, txtHoTen.getText());
                        mystm.setString(2, txtNgaysinh);
                        mystm.setString(3, gioiTinh);
                        mystm.setString(4, txtAreaDiaChi.getText());
                        if (txtEmail.getText().isEmpty()) {
                            mystm.setString(5, "");
                        } else {
                            mystm.setString(5, txtEmail.getText() + duoiGmail);
                        }
                        mystm.executeQuery();
                        mystm.close();
                    }
                    con.close();
                } catch (SQLException ex) {

                    this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Lưu thành công");

                }

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        rbNam = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        rbNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaDiaChi = new javax.swing.JTextArea();
        textChuThichTuoi = new javax.swing.JLabel();
        gmail = new javax.swing.JComboBox<>();
        ThemMoi = new javax.swing.JButton();
        String datePattern = "dd/MM/yyyy";
        String dateMask = "##/##/####";
        datechoose = new com.toedter.calendar.JDateChooser(datePattern, dateMask, '_');
        jLabel7 = new javax.swing.JLabel();
        Close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Họ tên:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Giới tính:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Ngày sinh:");
        jLabel4.setAlignmentY(0.8F);

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbNam.setText("Nam");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNu);
        rbNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbNu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*");

        txtAreaDiaChi.setColumns(12);
        txtAreaDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAreaDiaChi.setRows(5);
        txtAreaDiaChi.setToolTipText(""); // NOI18N
        jScrollPane2.setViewportView(txtAreaDiaChi);

        textChuThichTuoi.setText("settext");

        gmail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "@gmail.com", "@outlook.com", "@yahoo.com", "@hotmail.com", "@icloud.com", "@live.com", "@msn.com", "Khác..." }));
        gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gmailActionPerformed(evt);
            }
        });

        ThemMoi.setBackground(new java.awt.Color(128, 99, 246));
        ThemMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ThemMoi.setForeground(new java.awt.Color(255, 255, 255));
        ThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_administrator_24px.png"))); // NOI18N
        ThemMoi.setText("Thêm mới");
        ThemMoi.setBorder(null);
        ThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoiActionPerformed(evt);
            }
        });

        datechoose.setBackground(new java.awt.Color(255, 255, 255));
        datechoose.setToolTipText("");
        datechoose.setDateFormatString("dd/MM/yyyy");
        datechoose.setMinimumSize(new java.awt.Dimension(200, 200));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("*");

        Close.setBackground(new java.awt.Color(254, 193, 6));
        Close.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Close.setForeground(new java.awt.Color(255, 255, 255));
        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_exit_24px_1.png"))); // NOI18N
        Close.setText("Thoát");
        Close.setBorder(null);
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(337, 337, 337)
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(rbNam)
                        .addGap(2, 2, 2)
                        .addComponent(rbNu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(gmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(textChuThichTuoi))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(rbNam))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(rbNu)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addComponent(textChuThichTuoi))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Close, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CloseActionPerformed

    private void gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmailActionPerformed
        if (gmail.getSelectedIndex() == 7) {
            gmail.setEditable(true);
            gmail.setSelectedItem("");

        } else {
            gmail.setEditable(false);
        }
    }//GEN-LAST:event_gmailActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void ThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThemMoiActionPerformed


    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Close;
    public javax.swing.JButton ThemMoi;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datechoose;
    public javax.swing.JComboBox<String> gmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JLabel textChuThichTuoi;
    private javax.swing.JTextArea txtAreaDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    // End of variables declaration//GEN-END:variables
}
