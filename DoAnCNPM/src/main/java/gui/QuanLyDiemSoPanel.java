/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import popupframe.SuaDiem_QuanLyDiemSoPanel;

/**
 *
 * @author Admin
 */
public class QuanLyDiemSoPanel extends javax.swing.JPanel {

    DefaultTableModel bangDuLieu = new DefaultTableModel();
    SuaDiem_QuanLyDiemSoPanel editBangdiem = new SuaDiem_QuanLyDiemSoPanel();
    String DiemBanDau = "";
    /**
     * Creates new form BangDiemMonPanel
     */
    int selectedRow = -1;

    public QuanLyDiemSoPanel() {
        //khong tat chung cung chuong tinh chinh
        editBangdiem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        createYearList();
        createHocKiList();
        createMonHocList();
        createLopList();

        //tat han edit khi thoat khoi edit table
        tableDiem.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        //lay diem ban dau

        //khi diem thay doi thi update diem trung binh (khi edit truc tiep tren bang)
        tableDiem.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {

            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int SelectedRow = tableDiem.getSelectedRow();
                int SelectedColumn = tableDiem.getSelectedColumn();
                String DiemSauKhiSua = tableDiem.getValueAt(SelectedRow, SelectedColumn).toString();

                //check xem diem sau khi sua co chua ki tu , khong
                while (DiemSauKhiSua.trim().endsWith(",") == true) {
                    DiemSauKhiSua = DiemSauKhiSua.substring(0, DiemSauKhiSua.length() - 1);
                    tableDiem.setValueAt(DiemSauKhiSua, SelectedRow, SelectedColumn);

                };
                boolean Kiemtra = true;
                if (!DiemSauKhiSua.isEmpty()) {

                    String[] ArrayDiemSauKhiSua = DiemSauKhiSua.split(",");

                    for (int i = 0; i < ArrayDiemSauKhiSua.length; i++) {
                        try {
                            Float.parseFloat(ArrayDiemSauKhiSua[i]);
                        } catch (NumberFormatException evt) {
                            if (!DiemSauKhiSua.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Bạn đã điền sai form !!");
                                Kiemtra = false;
                                break;
                            }
                        }
                        float x = Float.parseFloat(ArrayDiemSauKhiSua[i]);
                        if (x > 10) {

                            Kiemtra = false;
                            JOptionPane.showMessageDialog(null, "Vui long nhap diem so <= 10 ");
                            break;

                        }
                        if (x < 0) {

                            Kiemtra = false;
                            JOptionPane.showMessageDialog(null, "Vui long nhap diem so >=0 ");
                            break;

                        }
                    }
                }

                //neu nhap dung thi tinh diem trung binh
                if (Kiemtra == true) {
                    SetDiemTB();
                } else {

                    tableDiem.setValueAt(DiemBanDau, SelectedRow, SelectedColumn);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e
            ) {

            }
        }
        );
        //tao model bang du lieu
        String colTitles[] = {"STT", "Họ tên", "Lớp", "Điểm 15 phút", "Điểm 1 tiết", "Điểm TB", "Mã học sinh"};
        boolean[] isEditable = {false, false, false, true, true, false, false};
        bangDuLieu = new DefaultTableModel(colTitles, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return isEditable[column];
            }
        };

        tableDiem.setModel(bangDuLieu);
//        set ma hoc sinh not visible

        tableDiem.getColumn(
                "Mã học sinh").setMinWidth(0); // Must be set before maxWidth!!
        tableDiem.getColumn(
                "Mã học sinh").setMaxWidth(0);
        tableDiem.getColumn(
                "Mã học sinh").setWidth(0);
        tableDiem.getColumn(
                "Lớp").setMinWidth(0); // Must be set before maxWidth!!
        tableDiem.getColumn(
                "Lớp").setMaxWidth(0);
        tableDiem.getColumn(
                "Lớp").setWidth(0);

    }

    void createLopList() {
        String sql = "  Select TenLop from LOP";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.Lop.addItem(r.getString("TenLop"));
            }
        } catch (SQLException e) {
            return;
        }
    }

    void createYearList() {
        String sql = " select distinct  NAM from HOCKI_NAM order by Nam DESC";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.Nam.addItem(r.getString("Nam"));
            }
        } catch (SQLException e) {
            return;
        }
    }

    void createHocKiList() {
        String sql = "select distinct  TenHocKi from HOCKI_NAM ";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.Hocky.addItem(r.getString("TenHocKi"));
            }
        } catch (SQLException e) {
            return;
        }
    }

    void createMonHocList() {
        String sql = "select TenMonHoc from MONHOC";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.Mon.addItem(r.getString("TenMonHoc"));

            }
        } catch (SQLException e) {
            return;
        }
    }

    public void SetDiemTB() {
        int SelectedRow = tableDiem.getSelectedRow();
        if (tableDiem.getValueAt(SelectedRow, 4) == null) {
            tableDiem.setValueAt("", SelectedRow, 4);
        }
        if (tableDiem.getValueAt(SelectedRow, 3) == null) {
            tableDiem.setValueAt("", SelectedRow, 3);
        }

        String[] chuoi1tiet = tableDiem.getValueAt(SelectedRow, 4).toString().split(",");
        String[] chuoi15p = tableDiem.getValueAt(SelectedRow, 3).toString().split(",");

        float tong15p = 0;
        float tong1tiet = 0;
        for (int i = 0; i < chuoi15p.length; i++) {

            try {
                tong15p += Float.parseFloat(chuoi15p[i]);
            } catch (NumberFormatException evt) {
                tong15p = -1;
            }

        }

        for (int i = 0; i < chuoi1tiet.length; i++) {

            try {
                tong1tiet += Float.parseFloat(chuoi1tiet[i]);
            } catch (NumberFormatException evt) {
                tong1tiet = -1;
            }

        }
        float DiemTb = 0;
        System.out.println(tong15p);
        System.out.println(tong1tiet);
        //xet tung truong hop co diem hay khong
        if (tong15p == -1 && tong1tiet == -1) {
            DiemTb = 0;
        } else if (tong15p != -1 && tong1tiet != -1) {
            DiemTb = (tong15p + tong1tiet * 2) / (chuoi1tiet.length * 2 + chuoi15p.length);

        } else if (tong15p == -1) {
            DiemTb = (tong1tiet * 2) / (chuoi1tiet.length * 2);
        } else if (tong1tiet == -1) {
            DiemTb = (tong15p) / (chuoi15p.length);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        DiemTb = Float.valueOf(df.format(DiemTb));
        tableDiem.setValueAt(Float.toString(DiemTb), SelectedRow, 5);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TimKiem = new javax.swing.JButton();
        Lop = new javax.swing.JComboBox<>();
        Mon = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Hocky = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Nam = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDiem = new javax.swing.JTable();
        EditTableButton = new javax.swing.JButton();
        SaveTableButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(239, 247, 248));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Lớp:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Môn:");

        TimKiem.setBackground(new java.awt.Color(0, 176, 239));
        TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TimKiem.setForeground(new java.awt.Color(255, 255, 255));
        TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_search_24px_1.png"))); // NOI18N
        TimKiem.setText("Tìm kiếm");
        TimKiem.setBorder(null);
        TimKiem.setFocusable(false);
        TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemActionPerformed(evt);
            }
        });

        Lop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Lop.setFocusable(false);

        Mon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Mon.setFocusable(false);
        Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Học kỳ:");

        Hocky.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Hocky.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Năm:");

        Nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nam.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Hocky, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Hocky, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 126, -1, 110));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setFocusable(false);
        jScrollPane1.setOpaque(false);

        tableDiem.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tableDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên", "Lớp", "Điểm 15 phút", "Điểm 1 tiết", "Điểm TBM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDiem.setFocusable(false);
        tableDiem.setGridColor(new java.awt.Color(255, 255, 255));
        tableDiem.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableDiem.setOpaque(false);
        tableDiem.setRowHeight(25);
        tableDiem.setSelectionBackground(new java.awt.Color(0, 176, 239));
        tableDiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableDiemMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableDiem);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 278, 945, -1));

        EditTableButton.setBackground(new java.awt.Color(254, 193, 6));
        EditTableButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditTableButton.setForeground(new java.awt.Color(255, 255, 255));
        EditTableButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_pencil_24px_1.png"))); // NOI18N
        EditTableButton.setText("Sửa");
        EditTableButton.setBorder(null);
        EditTableButton.setFocusable(false);
        EditTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTableButtonActionPerformed(evt);
            }
        });
        add(EditTableButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1003, 413, 130, 40));

        SaveTableButton.setBackground(new java.awt.Color(0, 176, 239));
        SaveTableButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SaveTableButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveTableButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_upload_to_cloud_24px_1.png"))); // NOI18N
        SaveTableButton.setText("Cập nhật");
        SaveTableButton.setBorder(null);
        SaveTableButton.setFocusable(false);
        SaveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTableButtonActionPerformed(evt);
            }
        });
        add(SaveTableButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1003, 461, 130, 40));

        jButton5.setBackground(new java.awt.Color(128, 99, 246));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton5.setText("In");
        jButton5.setBorder(null);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1003, 509, 130, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("Cách điền cột điểm: VD: 7,7.5,8");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(778, 251, 192, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Thông tin điểm môn học");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 247, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Nhập thông tin môn học cần tìm kiếm");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 95, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("QUẢN LÍ ĐIỂM SỐ");
        jLabel8.setFocusable(false);
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void MonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonActionPerformed

    private void EditTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditTableButtonActionPerformed

        selectedRow = tableDiem.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng bạn cần sửa!");
        } else {
            //hien bang
            if (tableDiem.getValueAt(selectedRow, 4) == null) {
                tableDiem.setValueAt("", selectedRow, 4);
            }
            if (tableDiem.getValueAt(selectedRow, 3) == null) {
                tableDiem.setValueAt("", selectedRow, 3);
            }
            editBangdiem.setVisible(true);
            //set diem
            editBangdiem.HoVaTen.setText(tableDiem.getValueAt(selectedRow, 1).toString());
            editBangdiem.Lop.setText(tableDiem.getValueAt(selectedRow, 2).toString());
            editBangdiem.Diem15p.setText(tableDiem.getValueAt(selectedRow, 3).toString());
            editBangdiem.Diem1Tiet.setText(tableDiem.getValueAt(selectedRow, 4).toString());
            //goi phuong thuc click button
            thehandler handle = new thehandler();
            editBangdiem.SaveWindowButton.addActionListener(handle);

        }
    }//GEN-LAST:event_EditTableButtonActionPerformed
    private class thehandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String diem1tiet = editBangdiem.Diem1Tiet.getText();
            String diem15p = editBangdiem.Diem15p.getText();

            while (diem15p.trim().endsWith(",") == true) {
                diem15p = diem15p.substring(0, diem15p.length() - 1);

            };
            while (diem1tiet.trim().endsWith(",") == true) {
                diem1tiet = diem1tiet.substring(0, diem1tiet.length() - 1);

            };
            int whatFault = -1;
            String[] chuoi1tiet = diem1tiet.split(",");
            String[] chuoi15p = diem15p.split(",");
            //kiem tra xem diem tai vi tri nay da hop le chua
            Boolean kiemtra = true;
            for (int i = 0; i < chuoi15p.length; i++) {
                try {
                    if (Float.parseFloat(chuoi15p[i]) > 10) {
                        kiemtra = false;
                        whatFault = 1;
                        break;
                    }
                    if (Float.parseFloat(chuoi15p[i]) < 0) {
                        kiemtra = false;
                        whatFault = 2;
                        break;
                    }

                } catch (NumberFormatException evt) {
                    if (!diem15p.isEmpty()) {
                        whatFault = 3;
                        kiemtra = false;
                        break;
                    }
                }
            }
            if (kiemtra == true) {
                for (int i = 0; i < chuoi1tiet.length; i++) {
                    try {
                        if (Float.parseFloat(chuoi1tiet[i]) > 10) {

                            kiemtra = false;
                            whatFault = 1;
                            break;
                        }
                        if (Float.parseFloat(chuoi1tiet[i]) < 0) {
                            kiemtra = false;
                            whatFault = 2;
                            break;
                        }
                    } catch (NumberFormatException evt) {

                        if (!diem1tiet.isEmpty()) {
                            whatFault = 3;
                            kiemtra = false;
                            break;
                        }
                    }
                }
            }
            if (kiemtra == true) {

                tableDiem.setValueAt(diem15p, selectedRow, 3);
                tableDiem.setValueAt(diem1tiet, selectedRow, 4);
                SetDiemTB();
                editBangdiem.setVisible(false);

            } else {
                if (whatFault == 3) {

                    JOptionPane.showMessageDialog(null, "Bạn đã điền sai quy tắc form!");
                } else if (whatFault == 1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm số <= 10!");

                } else if (whatFault == 2) {

                    JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm số >= 0!");
                }
            }
        }
    }
    private void TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemActionPerformed
        bangDuLieu.setRowCount(0);
        tableDiem.setModel(bangDuLieu);
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            CallableStatement mystm = con.prepareCall("{call sp_BangDiemMon_InBangDiem(?,?,?,?)}");
            int STT = 1;
            mystm.setInt(1, Integer.parseInt(Nam.getSelectedItem().toString()));
            mystm.setString(2, Hocky.getSelectedItem().toString());
            mystm.setString(3, Mon.getSelectedItem().toString());
            mystm.setString(4, Lop.getSelectedItem().toString());

            ResultSet rs = mystm.executeQuery();
            while (rs.next()) {
                String HoTen = rs.getString("HoTen");
                String MaLop = Lop.getSelectedItem().toString();
                String Diem15p = rs.getString("Diem15Phut");
                String Diem1tiet = rs.getString("Diem1Tiet");
                String DiemTB = rs.getString("DiemTBM");
                String MaHocSinh = rs.getString("MaHocSinh");
                String[] data = {Integer.toString(STT), HoTen, MaLop, Diem15p, Diem1tiet, DiemTB, MaHocSinh};
                bangDuLieu.addRow(data);

                STT++;
            }
            tableDiem.setModel(bangDuLieu);
            if (tableDiem.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "KHÔNG TÌM THẤY THÔNG TIN!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDiemSoPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_TimKiemActionPerformed

    private void SaveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveTableButtonActionPerformed

        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu thông tin những dòng này", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            Connection con = JDBCConnection.ketNoiJBDC();
            try {
                CallableStatement mystm = con.prepareCall("{call sp_BangDiemMon_ThemDiem(?,?,?,?,?,?,?) }");
                //so hang
                int row = tableDiem.getRowCount();
                //so cot

                mystm.setInt(1, Integer.parseInt(Nam.getSelectedItem().toString()));
                mystm.setString(2, Hocky.getSelectedItem().toString());
                mystm.setString(3, Mon.getSelectedItem().toString());
                mystm.setString(4, Lop.getSelectedItem().toString());

                //chay vong lap: i la vi tri hang, j la vi tri cot
                for (int i = 0; i < row; i++) {
                    mystm.setString(5, tableDiem.getValueAt(i, 6).toString());
                    mystm.setString(6, tableDiem.getValueAt(i, 3).toString());
                    mystm.setString(7, tableDiem.getValueAt(i, 4).toString());
                    mystm.execute();
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyDiemSoPanel.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SaveTableButtonActionPerformed

    private void tableDiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDiemMousePressed

        try {
            DiemBanDau = tableDiem.getValueAt(tableDiem.getSelectedRow(), tableDiem.getSelectedColumn()).toString();
        } catch (Exception e) {
            DiemBanDau = "";
        }


    }//GEN-LAST:event_tableDiemMousePressed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (tableDiem.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có thông tin gì để in");

        } else {
        
            try {

                tableDiem.print(JTable.PrintMode.FIT_WIDTH, null, null);
            } catch (PrinterException ex) {
                Logger.getLogger(QuanLyDiemSoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    // phuong thuc nghe lenh click button 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditTableButton;
    private javax.swing.JComboBox<String> Hocky;
    private javax.swing.JComboBox<String> Lop;
    private javax.swing.JComboBox<String> Mon;
    private javax.swing.JComboBox<String> Nam;
    private javax.swing.JButton SaveTableButton;
    private javax.swing.JButton TimKiem;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableDiem;
    // End of variables declaration//GEN-END:variables
}
