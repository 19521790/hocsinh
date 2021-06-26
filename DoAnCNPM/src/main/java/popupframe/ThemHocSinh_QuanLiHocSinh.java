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
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaDiaChi = new javax.swing.JTextArea();
        String datePattern = "dd/MM/yyyy";
        String dateMask = "##/##/####";
        datechoose = new com.toedter.calendar.JDateChooser(datePattern, dateMask, '_');
        textChuThichTuoi = new javax.swing.JLabel();
        gmail = new javax.swing.JComboBox<>();
        ThemMoi = new javax.swing.JButton();
        Close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin học sinh"));

        jLabel1.setText("Họ tên:");

        jLabel2.setText("Email:");

        jLabel3.setText("Giới tính:");

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

        jLabel6.setText("Địa chỉ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("*");

        txtAreaDiaChi.setColumns(12);
        txtAreaDiaChi.setRows(5);
        txtAreaDiaChi.setToolTipText(""); // NOI18N
        jScrollPane2.setViewportView(txtAreaDiaChi);

        datechoose.setToolTipText("");
        datechoose.setDateFormatString("dd/MM/yyyy");
        datechoose.setMinimumSize(new java.awt.Dimension(200, 200));

        textChuThichTuoi.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        textChuThichTuoi.setText("settext");

        gmail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "@gmail.com", "@outlook.com", "@yahoo.com", "@hotmail.com", "@icloud.com", "@live.com", "@msn.com", "Khác..." }));
        gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gmailActionPerformed(evt);
            }
        });

        ThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        ThemMoi.setText("Thêm mới");

        Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/exit (1).png"))); // NOI18N
        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textChuThichTuoi)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbNu))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(ThemMoi)
                .addGap(30, 30, 30)
                .addComponent(Close)
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(rbNu))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail)
                            .addComponent(jLabel2)
                            .addComponent(gmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(textChuThichTuoi))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemMoi)
                    .addComponent(Close))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(51, 51, 51)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmailActionPerformed
        if (gmail.getSelectedIndex() == 7) {
            gmail.setEditable(true);
            gmail.setSelectedItem("");

        } else {
            gmail.setEditable(false);
        }
    }//GEN-LAST:event_gmailActionPerformed


    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CloseActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JLabel textChuThichTuoi;
    private javax.swing.JTextArea txtAreaDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    // End of variables declaration//GEN-END:variables
}
