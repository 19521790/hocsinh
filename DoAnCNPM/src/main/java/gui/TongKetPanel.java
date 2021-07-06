/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import popupframe.DanhSach_TongKetPanel;
import popupframe.TongKetMon_BangDiemMonPanel;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Admin
 */
public class TongKetPanel extends javax.swing.JPanel {

    /**
     * Creates new form BaoCaoTongKetMon
     */
    DanhSach_TongKetPanel danhsach = new DanhSach_TongKetPanel();

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Mon = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        HocKi = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Nam = new javax.swing.JComboBox<>();
        TimKiem = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        tableTongKet = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        XemThongTin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(239, 247, 248));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(239, 247, 248));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Học kỳ:");

        hocky.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hocky.setFocusable(false);
        hocky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hockyActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Năm:");

        yearComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        yearComboBox.setFocusable(false);

        seekButton.setBackground(new java.awt.Color(0, 176, 239));
        seekButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        seekButton.setForeground(new java.awt.Color(255, 255, 255));
        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_search_24px_1.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.setBorder(null);
        seekButton.setFocusable(false);
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(hocky, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addComponent(seekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hocky, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(seekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nhập thông tin tổng kết");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setFocusable(false);

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
        infoTable.setFocusable(false);
        infoTable.setGridColor(new java.awt.Color(255, 255, 255));
        infoTable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        infoTable.setRowHeight(25);
        infoTable.setSelectionBackground(new java.awt.Color(0, 176, 239));
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

        jButton6.setBackground(new java.awt.Color(165, 143, 241));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton6.setText("In");
        jButton6.setBorder(null);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nhập thông tin tổng kết");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane1.addTab("Tổng kết học kì", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 247, 248));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Môn:");

        Mon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Mon.setFocusable(false);
        Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Học kỳ:");

        HocKi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        HocKi.setFocusable(false);
        HocKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HocKiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Năm:");

        Nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nam.setFocusable(false);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(HocKi, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(HocKi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane.setFocusable(false);

        tableTongKet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Lớp", "Sĩ số", "Số lượng đạt", "Tỉ lệ"
            }
        ));
        tableTongKet.setGridColor(new java.awt.Color(255, 255, 255));
        tableTongKet.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tableTongKet.setRowHeight(25);
        tableTongKet.setSelectionBackground(new java.awt.Color(0, 176, 239));
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

        jButton7.setBackground(new java.awt.Color(165, 143, 241));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton7.setText("In");
        jButton7.setBorder(null);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        XemThongTin.setBackground(new java.awt.Color(254, 193, 6));
        XemThongTin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        XemThongTin.setForeground(new java.awt.Color(255, 255, 255));
        XemThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_scorecard_24px.png"))); // NOI18N
        XemThongTin.setText("Chi tiết");
        XemThongTin.setBorder(null);
        XemThongTin.setFocusable(false);
        XemThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XemThongTinActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhập thông tin môn học cần tổng kết");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Bảng tổng kết môn học");

        jLabel12.setBackground(new java.awt.Color(165, 143, 241));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel12.setText("Coi chi tiết điểm lớp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(XemThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(XemThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane1.addTab("Tổng kết môn", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1150, 700));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("TỔNG KẾT");
        jLabel6.setFocusable(false);
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, -1, -1));
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
        if (infoTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có thông tin gì để in");

        } else {

            try {

                infoTable.print(JTable.PrintMode.FIT_WIDTH, null, null);
            } catch (PrinterException ex) {
                Logger.getLogger(QuanLyDiemSoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (tableTongKet.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có thông tin gì để in");

        } else {

            try {

              tableTongKet.print(JTable.PrintMode.FIT_WIDTH, null, null);
            } catch (PrinterException ex) {
                Logger.getLogger(QuanLyDiemSoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tableTongKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableTongKetKeyPressed
        if (tableTongKet.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                evt.consume();
                BangDiemMon.setVisible(true);
                showBangDiemMon();

            }
        }
    }//GEN-LAST:event_tableTongKetKeyPressed

    private void tableTongKetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTongKetMousePressed

    }//GEN-LAST:event_tableTongKetMousePressed

    private void XemThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XemThongTinActionPerformed

        if (tableTongKet.getSelectedRow() != -1) {
            BangDiemMon.setVisible(true);
            showBangDiemMon();

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xem thông tin");

        }
    }//GEN-LAST:event_XemThongTinActionPerformed


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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton seekButton;
    private javax.swing.JTable tableTongKet;
    private javax.swing.JComboBox<String> yearComboBox;
    // End of variables declaration//GEN-END:variables
}
