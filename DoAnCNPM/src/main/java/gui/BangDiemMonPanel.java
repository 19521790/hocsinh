/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import popupframe.SuaDiemBangDiemMonPanel;

/**
 *
 * @author Admin
 */
public class BangDiemMonPanel extends javax.swing.JPanel {

    DefaultTableModel bangDuLieu = new DefaultTableModel();
    SuaDiemBangDiemMonPanel editBangdiem = new SuaDiemBangDiemMonPanel();
    String DiemBanDau = "";
    /**
     * Creates new form BangDiemMonPanel
     */
    int selectedRow = -1;

    public BangDiemMonPanel() {
        //khong tat chung cung chuong tinh chinh
        editBangdiem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();

        //tat han edit khi thoat khoi edit table
        tableDiem.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        //lay diem ban dau
        tableDiem.getSelectionModel().addListSelectionListener((ListSelectionEvent le) -> {
            try {
                 DiemBanDau = tableDiem.getValueAt(tableDiem.getSelectedRow(), tableDiem.getSelectedColumn()).toString();
            } catch (Exception e) {
                 DiemBanDau = "";
            }
           

        });

        //khi diem thay doi thi update diem trung binh (khi edit truc tiep tren bang)
        tableDiem.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {

            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int SelectedRow = tableDiem.getSelectedRow();
                int SelectedColumn = tableDiem.getSelectedColumn();
                String DiemSauKhiSua = tableDiem.getValueAt(SelectedRow, SelectedColumn).toString();
                if (DiemSauKhiSua != null) {
                }
                String[] ArrayDiemSauKhiSua = DiemSauKhiSua.split(",");
                boolean Kiemtra = true;
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
                }
                //neu nhap dung thi tinh diem trung binh
                if (Kiemtra == true) {
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
                    //xet tung truong hop co diem hay khong
                    if (tong15p == -1 && tong1tiet == -1) {
                        DiemTb = 0;
                    } else if (tong15p == -1) {
                        DiemTb = (tong1tiet * 2) / (chuoi1tiet.length * 2);
                    } else if (tong1tiet == -1) {
                        DiemTb = (tong15p) / (chuoi15p.length);
                    } else {
                        DiemTb = (tong15p + tong1tiet * 2) / (chuoi1tiet.length * 2 + chuoi15p.length);
                    }

                    DecimalFormat df = new DecimalFormat("#.00");
                    DiemTb = Float.valueOf(df.format(DiemTb));
                    tableDiem.setValueAt(Float.toString(DiemTb), SelectedRow, 5);

                    DiemBanDau = tableDiem.getValueAt(SelectedRow, SelectedColumn).toString();

                } else {

                    tableDiem.setValueAt(DiemBanDau, SelectedRow, SelectedColumn);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
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
        tableDiem.getColumn("Mã học sinh").setMinWidth(0); // Must be set before maxWidth!!
        tableDiem.getColumn("Mã học sinh").setMaxWidth(0);
        tableDiem.getColumn("Mã học sinh").setWidth(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel2 = new javax.swing.JPanel();
        EditTableButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDiem = new javax.swing.JTable();
        SaveTableButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        LamMoi = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lớp cần nhận"));

        jLabel1.setText("Lớp:");

        jLabel2.setText("Môn:");

        TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        TimKiem.setText("Tìm kiếm");
        TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemActionPerformed(evt);
            }
        });

        Lop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10A1", "10A2", "10A3", "10A4", "11A1", "11A2", "11A3", "12A1", "12A2" }));

        Mon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toán", "Lý", "Hóa", "Sinh", "Sử", "Địa", "Văn", "Đạo Đức", "Thể Dục" }));
        Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonActionPerformed(evt);
            }
        });

        jLabel3.setText("Học kỳ:");

        Hocky.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Học kì 1", "Học kì 2" }));

        jLabel9.setText("Năm:");

        Nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2020", "2019" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Hocky, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimKiem)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(Hocky, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(TimKiem)
                .addGap(14, 14, 14))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng điểm môn học"));

        EditTableButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/pencil.png"))); // NOI18N
        EditTableButton.setText("Sửa");
        EditTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTableButtonActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/printer (1).png"))); // NOI18N
        jButton5.setText("In");

        tableDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên", "Lớp", "Điểm 15 phút", "Điểm 1 tiết", "Điểm TB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDiem);

        SaveTableButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/diskette.png"))); // NOI18N
        SaveTableButton.setText("Lưu");
        SaveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveTableButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel4.setText("Cách điền cột điểm: VD: 7,7.5,8");

        LamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/recycle.png"))); // NOI18N
        LamMoi.setText("LamMoi");
        LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LamMoi)
                        .addGap(18, 18, 18)
                        .addComponent(EditTableButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveTableButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditTableButton)
                    .addComponent(jButton5)
                    .addComponent(SaveTableButton)
                    .addComponent(jLabel4)
                    .addComponent(LamMoi))
                .addGap(13, 13, 13))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
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

            String[] chuoi1tiet = diem1tiet.split(",");
            String[] chuoi15p = diem15p.split(",");
            //kiem tra xem diem tai vi tri nay da hop le chua
            Boolean kiemtra = true;
            for (int i = 0; i < chuoi15p.length; i++) {
                try {
                    Float.parseFloat(chuoi15p[i]);
                } catch (NumberFormatException evt) {
                    if (!diem15p.isEmpty()) {
                        System.out.println("hello");
                        JOptionPane.showMessageDialog(null, "Bạn đã điền sai form !!");
                        kiemtra = false;
                        break;
                    }
                }
            }
            if (kiemtra == true) {
                for (int i = 0; i < chuoi1tiet.length; i++) {
                    try {
                        Float.parseFloat(chuoi1tiet[i]);
                    } catch (NumberFormatException evt) {

                        if (!diem1tiet.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Bạn đã điền sai form !!");
                            kiemtra = false;
                            break;
                        }
                    }
                }
                if (kiemtra == true) {

                    tableDiem.setValueAt(diem15p, selectedRow, 3);
                    tableDiem.setValueAt(diem1tiet, selectedRow, 4);

                    editBangdiem.setVisible(false);

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

                String MaLop = rs.getString("MaLop");
                String Diem15p = rs.getString("Diem15Phut");
                String Diem1tiet = rs.getString("Diem1Tiet");
                String DiemTB = rs.getString("DiemTBHK");
                String MaHocSinh = rs.getString("MaHocSinh");
                String[] data = {Integer.toString(STT), HoTen, MaLop, Diem15p, Diem1tiet, DiemTB, MaHocSinh};
                bangDuLieu.addRow(data);
                tableDiem.setModel(bangDuLieu);
                STT++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BangDiemMonPanel.class.getName()).log(Level.SEVERE, null, ex);
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
                int column = tableDiem.getColumnCount();
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
                Logger.getLogger(BangDiemMonPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_SaveTableButtonActionPerformed

    private void LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LamMoiActionPerformed
          bangDuLieu.setRowCount(0);
          tableDiem.setModel(bangDuLieu);

    }//GEN-LAST:event_LamMoiActionPerformed
    // phuong thuc nghe lenh click button 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditTableButton;
    private javax.swing.JComboBox<String> Hocky;
    private javax.swing.JButton LamMoi;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDiem;
    // End of variables declaration//GEN-END:variables
}
