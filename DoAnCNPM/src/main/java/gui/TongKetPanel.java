/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import popupframe.DanhSach_TongKetHocKyPanel;
import popupframe.TongKetMon_BangDiemMonPanel;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class TongKetPanel extends javax.swing.JPanel {

    /**
     * Creates new form BaoCaoTongKetMon
     */
    DanhSach_TongKetHocKyPanel danhsach = new DanhSach_TongKetHocKyPanel();

    TongKetMon_BangDiemMonPanel BangDiemMon = new TongKetMon_BangDiemMonPanel();

    DefaultTableModel bangdulieu = new DefaultTableModel();
    DefaultTableModel bangDiemMonTableModel = new DefaultTableModel();

    void createYearList() {
        String sql = " select distinct  NAM from HOCKI_NAM order by Nam DESC";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.yearComboBox.addItem(r.getString("Nam"));
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

                this.hocky.addItem(r.getString("TenHocKi"));

                this.HocKi.addItem(r.getString("TenHocKi"));
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

    void fillTable() {

        String sql = "EXEC sp_TongKetHocKi_InDanhSach N'" + this.hocky.getSelectedItem().toString() + "'," + this.yearComboBox.getSelectedItem().toString();
        String sql2 = "EXEC sp_TongKetHocKi_CapNhatBang N'" + this.hocky.getSelectedItem().toString() + "'," + this.yearComboBox.getSelectedItem().toString();
        System.out.println(sql);
        System.out.println(sql2);
        Connection cn = JDBCConnection.ketNoiJBDC();

        try {
            CallableStatement cst = cn.prepareCall(sql2);
            int i = cst.executeUpdate();
        } catch (SQLException e) {

            return;
        }

        try {
            CallableStatement cst = cn.prepareCall(sql);
            ResultSet r = cst.executeQuery();
            int i = 0;
            while (r.next()) {
                i++;
                String arr[] = {Integer.toString(i), r.getString("TenLop"), r.getString("SiSo"), r.getString("SLDat"), r.getString("TiLe")};
                DefaultTableModel tblM = (DefaultTableModel) this.infoTable.getModel();
                tblM.addRow(arr);
            }
        } catch (SQLException e) {

            return;
        }

    }

    public void showBangDiemMon() {
        bangDiemMonTableModel.setRowCount(0);
        BangDiemMon.tableDiem_BangDiemMon.setModel(bangDiemMonTableModel);
        int row = tableTongKet.getSelectedRow();

        String lop = tableTongKet.getValueAt(row, 1).toString().trim();
        int namhoc = Integer.parseInt(this.Nam.getSelectedItem().toString());
        String hocki = HocKi.getSelectedItem().toString();
        String monhoc = Mon.getSelectedItem().toString();
        BangDiemMon.Tittle.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng điểm môn chi tiết của lớp " + lop + ", môn " + monhoc + ", " + hocki + ", năm " + namhoc));
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            CallableStatement mystm = con.prepareCall("{call sp_BangDiemMon_InBangDiem(?,?,?,?)}");
            int STT = 1;
            mystm.setInt(1, namhoc);
            mystm.setString(2, hocki);
            mystm.setString(3, monhoc);
            mystm.setString(4, lop);
            ResultSet rs = mystm.executeQuery();
            while (rs.next()) {
                String HoTen = rs.getString("HoTen");

                String Diem15p = rs.getString("Diem15Phut");
                String Diem1tiet = rs.getString("Diem1Tiet");
                String DiemTB = rs.getString("DiemTBM");
                System.out.println(Diem15p + " " + Diem1tiet + " " + HoTen);

                String[] data = {Integer.toString(STT), HoTen, Diem15p, Diem1tiet, DiemTB};

                bangDiemMonTableModel.addRow(data);

                STT++;
            }
            BangDiemMon.tableDiem_BangDiemMon.setModel(bangDiemMonTableModel);

        } catch (SQLException ex) {

        }

    }

    public TongKetPanel() {
        initComponents();
        createYearList();
        createHocKiList();
        createMonHocList();
        XemThongTin.setVisible(false);
        BangDiemMon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] colTitle = {"STT", "Lớp", "Sĩ Số", "Số lượng đạt", "Tỉ lệ"};
        bangdulieu = new DefaultTableModel(colTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        String[] colTitle_bangdiemmon = {"STT", "Họ tên", "Điểm 15p", "Điểm 1 tiết", "Điểm TBM"};
        bangDiemMonTableModel = new DefaultTableModel(colTitle_bangdiemmon, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        BangDiemMon.tableDiem_BangDiemMon.setModel(bangDiemMonTableModel);
        tableTongKet.setModel(bangdulieu);
        //event click đúp table
        tableTongKet.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() == 2 && tableTongKet.getSelectedRow() != -1) {
                    BangDiemMon.setVisible(true);
                    showBangDiemMon();
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        hocky = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox<>();
        seekButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Mon = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        HocKi = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Nam = new javax.swing.JComboBox<>();
        TimKiem = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        XemThongTin = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tableTongKet = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin"));

        jLabel5.setText("Học kỳ:");

        hocky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hockyActionPerformed(evt);
            }
        });

        jLabel10.setText("Năm:");

        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hocky, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(seekButton)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hocky, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(seekButton)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Báo cáo tổng kết môn"));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/printer (1).png"))); // NOI18N
        jButton6.setText("In");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        infoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Lớp", "Sĩ số", "Số lượng đạt", "Tỉ lệ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        infoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(infoTable);
        if (infoTable.getColumnModel().getColumnCount() > 0) {
            infoTable.getColumnModel().getColumn(0).setResizable(false);
            infoTable.getColumnModel().getColumn(1).setResizable(false);
            infoTable.getColumnModel().getColumn(2).setResizable(false);
            infoTable.getColumnModel().getColumn(3).setResizable(false);
            infoTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tổng kết học kì", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập thông tin"));

        jLabel2.setText("Môn:");

        Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonActionPerformed(evt);
            }
        });

        jLabel3.setText("Học kỳ:");

        HocKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HocKiActionPerformed(evt);
            }
        });

        jLabel9.setText("Năm:");

        TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        TimKiem.setText("Tìm kiếm");
        TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TimKiem)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(HocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(TimKiem)
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Báo cáo tổng kết môn"));

        XemThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        XemThongTin.setText("Xem thông tin");
        XemThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XemThongTinActionPerformed(evt);
            }
        });

        tableTongKet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Lớp", "Sĩ số", "Số lượng đạt", "Tỉ lệ"
            }
        ));
        tableTongKet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableTongKetMousePressed(evt);
            }
        });
        tableTongKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableTongKetKeyPressed(evt);
            }
        });
        jScrollPane.setViewportView(tableTongKet);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/printer (1).png"))); // NOI18N
        jButton7.setText("In");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(XemThongTin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7)
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(XemThongTin)
                    .addComponent(jButton7))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 300));

        jTabbedPane1.addTab("Tổng kết môn", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void hockyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hockyActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_hockyActionPerformed

    private void seekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        model.setRowCount(0);
        if (this.hocky.getSelectedItem().toString() == "Chọn" || this.yearComboBox.getSelectedItem().toString() == "Chọn") {
            JOptionPane.showMessageDialog(this, "Chưa chọn năm hoặc học kỳ");
            return;
        }
        this.fillTable();

    }//GEN-LAST:event_seekButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton6ActionPerformed

    private void infoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2 && infoTable.getSelectedRow() != -1) {
            // your valueChanged overridden method 
            int column = 1;
            int row = infoTable.getSelectedRow();
            danhsach.lop = infoTable.getModel().getValueAt(row, column).toString();
            danhsach.nam = this.yearComboBox.getSelectedItem().toString();
            System.out.println(danhsach.lop + "     " + danhsach.nam);
            danhsach.loaddata();
            danhsach.setVisible(true);

        }
    }//GEN-LAST:event_infoTableMouseClicked

    private void MonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonActionPerformed

    private void HocKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HocKiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HocKiActionPerformed

    private void TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemActionPerformed
        bangdulieu.setRowCount(0);
        tableTongKet.setModel(bangdulieu);
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            CallableStatement mystm = con.prepareCall("{call sp_TongKetMon_Inbang(?,?,?)}");
            //goi procedure update du lieu
            CallableStatement updateMystm = con.prepareCall("{call sp_TongKetMon_CapNhatBang(?,?,?)}");
            updateMystm.setString(1, Mon.getSelectedItem().toString());
            updateMystm.setString(2, HocKi.getSelectedItem().toString());
            updateMystm.setInt(3, Integer.parseInt(Nam.getSelectedItem().toString()));
            int updaters = updateMystm.executeUpdate();

            mystm.setString(1, Mon.getSelectedItem().toString());
            mystm.setString(2, HocKi.getSelectedItem().toString());
            mystm.setInt(3, Integer.parseInt(Nam.getSelectedItem().toString()));
            ResultSet rs = mystm.executeQuery();
            int STT = 0;
            while (rs.next()) {
                String MaLop = rs.getString("TenLop");
                String SiSo = rs.getString("SiSo");
                String SLDat = rs.getString("SLDat");
                String TiLe = rs.getString("TiLe");
                STT++;
                String[] row = {Integer.toString(STT), MaLop, SiSo, SLDat, TiLe};
                bangdulieu.addRow(row);

            }
            tableTongKet.setModel(bangdulieu);
            XemThongTin.setVisible(true);
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_TimKiemActionPerformed

    private void XemThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XemThongTinActionPerformed

        if (tableTongKet.getSelectedRow() != -1) {
            BangDiemMon.setVisible(true);
            showBangDiemMon();

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xem thông tin");

        }
    }//GEN-LAST:event_XemThongTinActionPerformed

    private void tableTongKetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTongKetMousePressed

    }//GEN-LAST:event_tableTongKetMousePressed

    private void tableTongKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTongKetKeyPressed
        if (tableTongKet.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                evt.consume();
                BangDiemMon.setVisible(true);
                showBangDiemMon();

            }
        }
    }//GEN-LAST:event_tableTongKetKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> HocKi;
    private javax.swing.JComboBox<String> Mon;
    private javax.swing.JComboBox<String> Nam;
    private javax.swing.JButton TimKiem;
    private javax.swing.JButton XemThongTin;
    private javax.swing.JComboBox<String> hocky;
    private javax.swing.JTable infoTable;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton seekButton;
    private javax.swing.JTable tableTongKet;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
