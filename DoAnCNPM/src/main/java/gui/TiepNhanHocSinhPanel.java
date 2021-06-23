/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
        Date maxSelected = new GregorianCalendar(NamSinhCuaTuoiToiThieu, 11, 31).getTime();
        Date minSelected = new GregorianCalendar(NamSinhCuaTuoiToiDa, 0, 1).getTime();

        datechoose.getDateEditor().setMaxSelectableDate(maxSelected);
        datechoose.getDateEditor().setMinSelectableDate(minSelected);

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
        ThemMoi = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHocSinh = new javax.swing.JTable();
        CapNhat = new javax.swing.JButton();
        Xoa = new javax.swing.JButton();

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

        ThemMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        ThemMoi.setText("Thêm mới");
        ThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMoiActionPerformed(evt);
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
                        .addComponent(ThemMoi)
                        .addGap(42, 42, 42)
                        .addComponent(LamMoi)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textChuThichTuoi)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtHoTen)
                                                .addGap(81, 81, 81)
                                                .addComponent(jLabel8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(datechoose, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbNu))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Xoa)
                    .addComponent(CapNhat))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CapNhat;
    private javax.swing.JButton LamMoi;
    private javax.swing.JButton ThemMoi;
    private javax.swing.JButton Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datechoose;
    private javax.swing.JComboBox<String> gmail;
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

    private void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
