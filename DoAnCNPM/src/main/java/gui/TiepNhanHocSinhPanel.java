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
public class TiepNhanHocSinhPanel extends javax.swing.JPanel {

    DefaultTableModel bangDuLieu = new DefaultTableModel();

    //goi ham return tuoi toi thieu tuoi toi da
    int NamSinhCuaTuoiToiThieu = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiThieu();
    int NamSinhCuaTuoiToiDa = Calendar.getInstance().get(Calendar.YEAR) - new LayThamSo().getTuoiToiDa();
    int NamHienTai = Calendar.getInstance().get(Calendar.YEAR);
    String DiemBanDau = "";

    public TiepNhanHocSinhPanel() {

        initComponents();
        textChuThichTuoi.setText("Vui lòng nhập năm sinh giữa " + NamSinhCuaTuoiToiDa + " và " + NamSinhCuaTuoiToiThieu);
        //set format  date
        //set locale viet nam

        datechoose.setDateFormatString("dd/MM/yyyy");
        datechoose.getCalendarButton().setVisible(false);
        datechooseHocSinh.getCalendarButton().setVisible(false);
        Date maxSelected = new GregorianCalendar(NamSinhCuaTuoiToiThieu, 11, 31).getTime();
        Date minSelected = new GregorianCalendar(NamSinhCuaTuoiToiDa, 0, 1).getTime();
//nam sinh toi da dc chon
        datechoose.getDateEditor().setMaxSelectableDate(maxSelected);
        //nam sinh toi thieu đc chon
        datechoose.getDateEditor().setMinSelectableDate(minSelected);
        //tuong tu doi voi bang sua học sinh
        datechooseHocSinh.getDateEditor().setMaxSelectableDate(maxSelected);
        datechooseHocSinh.getDateEditor().setMinSelectableDate(minSelected);

        bangDuLieu.addColumn("STT");

        bangDuLieu.addColumn("Họ tên");

        bangDuLieu.addColumn("Ngày sinh");
        bangDuLieu.addColumn("Giới tính");
        bangDuLieu.addColumn("Địa chỉ");
        bangDuLieu.addColumn("Email");

        tableHocSinh.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int column = tableHocSinh.getSelectedColumn();
                int row = tableHocSinh.getSelectedRow();
                String checkValue = tableHocSinh.getValueAt(row, column).toString();
                //xet truong hop sua ngay sinh
                if (column == 2) {
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

                            }

                        } catch (ParseException ex) {

                            JOptionPane.showMessageDialog(null, "Ngày sinh bạn nhập đã sai");
                            tableHocSinh.setValueAt(DiemBanDau, row, column);
                        }

                    }

                }
                if (column == 5) {
                    System.out.println(checkValue);
                    if (!(checkValue.contains("@") && checkValue.contains("."))) {
                        JOptionPane.showMessageDialog(null, "Email bạn nhập đã sai");
                        tableHocSinh.setValueAt(DiemBanDau, row, column);
                    }
                }
                if (column == 3) {

                    if (!"Nam".equals(checkValue) && !"Nu".equals(checkValue)) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập 2 giá trị Nam hoặc Nu");
                        tableHocSinh.setValueAt(DiemBanDau, row, column);
                    }
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
        //them listen vao bang dem xem du lieu chinh sua co dung hay khong

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
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
        LamMoi = new javax.swing.JButton();
        textChuThichTuoi = new javax.swing.JLabel();
        gmail = new javax.swing.JComboBox<>();
        ThemMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        CapNhat = new javax.swing.JButton();
        Xoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        MaHocSinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TimKiemHocSinh = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtHoTenHocSinh = new javax.swing.JTextField();
        datechooseHocSinh = new com.toedter.calendar.JDateChooser(datePattern, dateMask, '_');
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmailHocSinh = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaDiaChiHocSinh = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rbNamHocSinh = new javax.swing.JRadioButton();
        rbNuHocSinh = new javax.swing.JRadioButton();
        CapNhatHocSinh = new javax.swing.JButton();

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
        datechoose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                datechooseFocusGained(evt);
            }
        });
        datechoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datechooseMousePressed(evt);
            }
        });
        datechoose.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                datechooseInputMethodTextChanged(evt);
            }
        });
        datechoose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datechooseKeyPressed(evt);
            }
        });

        LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/recycle.png"))); // NOI18N
        LamMoi.setText("Làm mới");
        LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LamMoiActionPerformed(evt);
            }
        });

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
        ThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoiActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(LamMoi)
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
                    .addComponent(LamMoi)
                    .addComponent(ThemMoi))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin bạn đã nhập"));

        tableHocSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
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

        CapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/updated.png"))); // NOI18N
        CapNhat.setText("Cập nhật");
        CapNhat.setToolTipText("");
        CapNhat.setAutoscrolls(true);
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });

        Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        Xoa.setText("Xóa");
        Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(CapNhat)
                .addGap(48, 48, 48)
                .addComponent(Xoa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Xoa)
                    .addComponent(CapNhat))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 142, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("Tiếp nhận học sinh", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập mã học sinh cần sửa"));

        jLabel9.setText("Mã học sinh:");

        TimKiemHocSinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        TimKiemHocSinh.setText("Tìm kiếm");
        TimKiemHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemHocSinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(MaHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TimKiemHocSinh))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(TimKiemHocSinh)
                .addGap(23, 23, 23))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin học sinh"));

        jLabel10.setText("Họ tên:");

        txtHoTenHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenHocSinhActionPerformed(evt);
            }
        });

        datechooseHocSinh.setToolTipText("");
        datechooseHocSinh.setDateFormatString("dd/MM/yyyy");
        datechooseHocSinh.setMinimumSize(new java.awt.Dimension(200, 200));
        datechooseHocSinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                datechooseHocSinhFocusGained(evt);
            }
        });
        datechooseHocSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                datechooseHocSinhMousePressed(evt);
            }
        });
        datechooseHocSinh.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                datechooseHocSinhInputMethodTextChanged(evt);
            }
        });
        datechooseHocSinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datechooseHocSinhKeyPressed(evt);
            }
        });

        jLabel11.setText("Ngày sinh:");
        jLabel11.setAlignmentY(0.8F);

        jLabel12.setText("Email:");

        txtEmailHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailHocSinhActionPerformed(evt);
            }
        });

        txtAreaDiaChiHocSinh.setColumns(12);
        txtAreaDiaChiHocSinh.setRows(5);
        txtAreaDiaChiHocSinh.setToolTipText(""); // NOI18N
        jScrollPane3.setViewportView(txtAreaDiaChiHocSinh);

        jLabel13.setText("Địa chỉ:");

        jLabel14.setText("Giới tính:");

        buttonGroup2.add(rbNamHocSinh);
        rbNamHocSinh.setText("Nam");

        buttonGroup2.add(rbNuHocSinh);
        rbNuHocSinh.setText("Nữ");

        CapNhatHocSinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/updated.png"))); // NOI18N
        CapNhatHocSinh.setText("Cập nhật");
        CapNhatHocSinh.setToolTipText("");
        CapNhatHocSinh.setAutoscrolls(true);
        CapNhatHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatHocSinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CapNhatHocSinh)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datechooseHocSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(txtHoTenHocSinh))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(rbNamHocSinh)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbNuHocSinh))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtEmailHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtHoTenHocSinh)
                    .addComponent(jLabel14)
                    .addComponent(rbNamHocSinh)
                    .addComponent(rbNuHocSinh))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailHocSinh)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(datechooseHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(CapNhatHocSinh)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sửa thông tin học sinh", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed
    int STT = 1;
//default stt
    private void ThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMoiActionPerformed
        //add listen toi thay doi bang

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
        if (txtHoTen.getText().equals("") || txtNgaysinh.equals("") || gmail.getSelectedItem().toString().isEmpty() || ((!rbNam.isSelected()) && (!rbNu.isSelected()))) {
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

                String data[] = {Integer.toString(STT), txtHoTen.getText(), txtNgaysinh, gioiTinh, txtAreaDiaChi.getText(), txtEmail.getText() + duoiGmail};
                //Lưu data vào bangDuLieu
                bangDuLieu.addRow(data);

                //Gán bangDuLieu vào jTalbe tableHocSinh
                tableHocSinh.setModel(bangDuLieu);

                //Dọn textfield để thêm dữ liệu mới
                txtHoTen.setText("");
                txtEmail.setText("");
                txtAreaDiaChi.setText("");
                gmail.setSelectedIndex(0);
                buttonGroup1.clearSelection();
                datechoose.setCalendar(null);
                STT++;
            }
            //Nếu điền đầy đủ, điền thông tin vào bangDuLieu
            //Lưu thông tin giới tính từ rdNam, rdNu

        }
    }//GEN-LAST:event_ThemMoiActionPerformed

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
                Logger.getLogger(TiepNhanHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_CapNhatActionPerformed

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

    private void LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LamMoiActionPerformed
        txtHoTen.setText("");
        txtEmail.setText("");
        txtAreaDiaChi.setText("");
        buttonGroup1.clearSelection();
        datechoose.setCalendar(null);
        gmail.setSelectedIndex(0);
    }//GEN-LAST:event_LamMoiActionPerformed

    private void datechooseInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datechooseInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseInputMethodTextChanged

    private void datechooseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_datechooseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseFocusGained

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void datechooseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datechooseKeyPressed


    }//GEN-LAST:event_datechooseKeyPressed

    private void datechooseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datechooseMousePressed

    }//GEN-LAST:event_datechooseMousePressed

    private void gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gmailActionPerformed
        if (gmail.getSelectedIndex() == 7) {
            gmail.setEditable(true);
            gmail.setSelectedItem("");

        } else {
            gmail.setEditable(false);
        }

    }//GEN-LAST:event_gmailActionPerformed

    private void tableHocSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHocSinhMousePressed
        try {
            DiemBanDau = tableHocSinh.getValueAt(tableHocSinh.getSelectedRow(), tableHocSinh.getSelectedColumn()).toString();
        } catch (Exception e) {
            DiemBanDau = "";
        }
    }//GEN-LAST:event_tableHocSinhMousePressed

    private void txtHoTenHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenHocSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenHocSinhActionPerformed

    private void datechooseHocSinhFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_datechooseHocSinhFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseHocSinhFocusGained

    private void datechooseHocSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datechooseHocSinhMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseHocSinhMousePressed

    private void datechooseHocSinhInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datechooseHocSinhInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseHocSinhInputMethodTextChanged

    private void datechooseHocSinhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datechooseHocSinhKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_datechooseHocSinhKeyPressed

    private void txtEmailHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailHocSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailHocSinhActionPerformed

    private void TimKiemHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemHocSinhActionPerformed
        rbNamHocSinh.setSelected(false);
        rbNuHocSinh.setSelected(false);
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            Statement mystm = con.createStatement();
            String sql = "SELECT * FROM HOCSINH WHERE MaHocSinh='" + MaHocSinh.getText() + "'";
            ResultSet rs = mystm.executeQuery(sql);
            String HoTen = "";
            while (rs.next()) {

                HoTen = rs.getString("HoTen");
                String NgaySinh = rs.getString("NgaySinh");
                String GioiTinh = rs.getString("GioiTinh");
                String DiaChi = rs.getString("DiaChi");
                String Email = rs.getString("Email");

                txtHoTenHocSinh.setText(HoTen);
                txtEmailHocSinh.setText(Email);
                txtAreaDiaChiHocSinh.setText(DiaChi);
              
                if ("Nam".equals(GioiTinh)) {
                    rbNamHocSinh.setSelected(true);
                } else  {
                    rbNuHocSinh.setSelected(true);
                }

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(NgaySinh);
                datechooseHocSinh.setDate(date);

            }
            if (HoTen.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả của mã học sinh này");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TiepNhanHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TiepNhanHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TimKiemHocSinhActionPerformed

    private void CapNhatHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatHocSinhActionPerformed
        int STT = -1;
        boolean KiemTra = true;
        if (!txtEmailHocSinh.getText().contains("@") && txtEmailHocSinh.getText().contains(".")) {
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

                try {
                    //Tạo statement
                    CallableStatement mystm = con.prepareCall("{call sp_SuaHocSinh_SuaThongTin(?,?,?,?,?,?)}");
                    mystm.setString(1, MaHocSinh.getText());
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

                } catch (SQLException ex) {
                    Logger.getLogger(TiepNhanHocSinhPanel.class.getName()).log(Level.SEVERE, null, ex);
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


    }//GEN-LAST:event_CapNhatHocSinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CapNhat;
    private javax.swing.JButton CapNhatHocSinh;
    private javax.swing.JButton LamMoi;
    private javax.swing.JTextField MaHocSinh;
    private javax.swing.JButton ThemMoi;
    private javax.swing.JButton TimKiemHocSinh;
    private javax.swing.JButton Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser datechoose;
    private com.toedter.calendar.JDateChooser datechooseHocSinh;
    private javax.swing.JComboBox<String> gmail;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNamHocSinh;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JRadioButton rbNuHocSinh;
    private javax.swing.JTable tableHocSinh;
    private javax.swing.JLabel textChuThichTuoi;
    private javax.swing.JTextArea txtAreaDiaChi;
    private javax.swing.JTextArea txtAreaDiaChiHocSinh;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailHocSinh;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTenHocSinh;
    // End of variables declaration//GEN-END:variables

    private void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
