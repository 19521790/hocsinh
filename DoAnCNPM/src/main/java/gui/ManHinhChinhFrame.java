/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class ManHinhChinhFrame extends javax.swing.JFrame {

    //Gọi các panel
    public QuanLyDiemSoPanel quanLiDiemSo = new QuanLyDiemSoPanel();
    QuanLyHocSinhPanel quanLiHocSinh = new QuanLyHocSinhPanel();
    QuanLyLopPanel quanLiLop = new QuanLyLopPanel();
    ThayDoiQuyDinhPanel thayDoiQuyDinh = new ThayDoiQuyDinhPanel();
    TongKetPanel tongKet = new TongKetPanel();
    DashboardPanel dashBoard = new DashboardPanel();
    //Khởi tạo màu mới
    Color menuCL = new Color(74, 75, 115);
    Color backgroundCL = new Color(52, 56, 68);

    //Hàm chèn panel vào tabbed panel 
    public void chenPanel(JPanel panel, JPanel menu, JPanel check, String tieuDe) {
        if (panel == null) {
            tblMainBoard.addTab(tieuDe, panel);
        }
        try {
            tblMainBoard.setSelectedComponent(panel);
        } catch (Exception e) {
            System.out.println("tab khong ton tai");
        }

        //Trả lại màu ban đầu cho menu
        pnDashboard.setBackground(backgroundCL);
        pnQuanLiDiemSo.setBackground(backgroundCL);
        pnQuanLiHocSinh.setBackground(backgroundCL);
        pnQuanLiLop.setBackground(backgroundCL);
        pnThayDoiQuyDinh.setBackground(backgroundCL);
        pnDangXuat.setBackground(backgroundCL);
        pnTongKet.setBackground(backgroundCL);
        pnCheckDashBoard.setBackground(backgroundCL);
        pnCheckQuanLyDiemSo.setBackground(backgroundCL);
        pnCheckQuanLyHocSinh.setBackground(backgroundCL);
        pnCheckQuanLyLop.setBackground(backgroundCL);
        pnCheckThayDoiQuyDinh.setBackground(backgroundCL);
        pnCheckTongKet.setBackground(backgroundCL);
        //Đổi màu menu chuột click
        menu.setBackground(menuCL);
        check.setBackground(Color.WHITE);
    }

    public void resetPanel() {

        quanLiHocSinh = new QuanLyHocSinhPanel();
        tblMainBoard.setComponentAt(1, quanLiHocSinh);
        quanLiLop = new QuanLyLopPanel();
        tblMainBoard.setComponentAt(2, quanLiLop);
        quanLiDiemSo = new QuanLyDiemSoPanel();
        tblMainBoard.setComponentAt(3, quanLiDiemSo);
        tongKet = new TongKetPanel();
        tblMainBoard.setComponentAt(4, tongKet);
    }

    public ManHinhChinhFrame() {
        initComponents();
        //Đặt màn hình chính giữa màn hình

        setLocationRelativeTo(null);
        SetIcon();
        tblMainBoard.removeAll();
        //Thêm sẵn các panel vào tabbed panel
        tblMainBoard.addTab("Dashboard", dashBoard);
        tblMainBoard.addTab("Quản lý học sinh", quanLiHocSinh);
        tblMainBoard.addTab("Quản lý lớp", quanLiLop);
        tblMainBoard.addTab("Quản lý điểm số", quanLiDiemSo);

        tblMainBoard.addTab("Tổng kết", tongKet);
        tblMainBoard.addTab("Thay đổi quy định", thayDoiQuyDinh);

        //Chọn sẵn Dashboard
        tblMainBoard.setSelectedComponent(dashBoard);
        pnDashboard.setBackground(menuCL);

        thayDoiQuyDinh.CapNhat.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.CapNhatAction();
                new LayThamSo().ketNoiCoSoDulieu();
                resetPanel();
            }
        });
        thayDoiQuyDinh.ThemNamHoc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.ThemNamHocListen();

                resetPanel();
            }
        });
        thayDoiQuyDinh.ThemMonHoc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.ThemMonHocListen();

                resetPanel();
            }
        });
        thayDoiQuyDinh.XoaMonHoc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.XoaMonHocListen();

                resetPanel();
            }
        });
        thayDoiQuyDinh.XoaLop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.XoaLopTrongTruongListen();

                resetPanel();
            }
        });
        thayDoiQuyDinh.ThemLop.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                thayDoiQuyDinh.ThemLopTrongTruongListen();

                resetPanel();
            }
        });

        thayDoiQuyDinh.tbLop.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int column = thayDoiQuyDinh.tbLop.getSelectedColumn();
                int row = thayDoiQuyDinh.tbLop.getSelectedRow();
                //cho can them
                Boolean kiemtraDung = true;
                for (int i = 0; i < row - 1; i++) {
                    if (thayDoiQuyDinh.tbLop.getValueAt(row, column).equals(thayDoiQuyDinh.tbLop.getValueAt(i, 1))) {
                        JOptionPane.showMessageDialog(null, "Tên của bạn nhập đã trùng");

                        kiemtraDung = false;
                        thayDoiQuyDinh.tbLop.setValueAt(thayDoiQuyDinh.DiemBanDau, row, column);
                        break;
                    }
                }
                for (int i = row + 1; i < thayDoiQuyDinh.tbLop.getRowCount(); i++) {
                    if (thayDoiQuyDinh.tbLop.getValueAt(row, column).equals(thayDoiQuyDinh.tbLop.getValueAt(i, 1))) {
                        JOptionPane.showMessageDialog(null, "Tên của bạn nhập đã trùng");

                        kiemtraDung = false;
                        thayDoiQuyDinh.tbLop.setValueAt(thayDoiQuyDinh.DiemBanDau, row, column);
                        break;
                    }
                }
                if (kiemtraDung == true) {
                    // cho can them
                    Connection con = JDBCConnection.ketNoiJBDC();
                    try {
                        Statement mystm = con.createStatement();
                        String sql = "  Update LOP\n"
                                + "  SET TenLop='" + thayDoiQuyDinh.tbLop.getValueAt(row, column) + "'\n"
                                + "  WHERE MaLop='" + thayDoiQuyDinh.tbLop.getValueAt(row, 0) + "'";
                        mystm.executeUpdate(sql);
                        resetPanel();
                        JOptionPane.showMessageDialog(null, "Update tên lớp thành công");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManHinhChinhFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
        thayDoiQuyDinh.tbMonHoc.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void editingStopped(ChangeEvent e) {

                int column = thayDoiQuyDinh.tbMonHoc.getSelectedColumn();
                int row = thayDoiQuyDinh.tbMonHoc.getSelectedRow();
                //cho can them
                Boolean kiemtraDung = true;
                for (int i = 0; i < row - 1; i++) {

                    if (thayDoiQuyDinh.tbMonHoc.getValueAt(row, column).equals(thayDoiQuyDinh.tbMonHoc.getValueAt(i, 1))) {
                        JOptionPane.showMessageDialog(null, "Tên của bạn nhập đã trùng");

                        kiemtraDung = false;
                        thayDoiQuyDinh.tbMonHoc.setValueAt(thayDoiQuyDinh.DiemBanDau, row, column);
                        break;
                    }
                }
                for (int i = row + 1; i < thayDoiQuyDinh.tbLop.getRowCount(); i++) {
                    if (thayDoiQuyDinh.tbMonHoc.getValueAt(row, column).equals(thayDoiQuyDinh.tbMonHoc.getValueAt(i, 1))) {
                        JOptionPane.showMessageDialog(null, "Tên của bạn nhập đã trùng");

                        kiemtraDung = false;
                        thayDoiQuyDinh.tbMonHoc.setValueAt(thayDoiQuyDinh.DiemBanDau, row, column);
                        break;
                    }
                }
                if (kiemtraDung == true) {
                    // cho can them  
                    Connection con = JDBCConnection.ketNoiJBDC();
                    try {
                        Statement mystm = con.createStatement();
                        String sql = "   Update MONHOC\n"
                                + "  SET TenMonHoc='" + thayDoiQuyDinh.tbMonHoc.getValueAt(row, column) + "'\n"
                                + "  WHERE MaMonHoc='" + thayDoiQuyDinh.tbMonHoc.getValueAt(row, 0) + "'";
                        mystm.executeUpdate(sql);
                        resetPanel();
                        JOptionPane.showMessageDialog(null, "Update tên môn học thành công");
                    } catch (SQLException ex) {
                        Logger.getLogger(ManHinhChinhFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

    }

    //Hàm chuyển đổi các giữa các JPanel 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnDashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnCheckDashBoard = new javax.swing.JPanel();
        pnQuanLiHocSinh = new javax.swing.JPanel();
        disableQuanLyHS = new javax.swing.JLabel();
        pnCheckQuanLyHocSinh = new javax.swing.JPanel();
        pnQuanLiLop = new javax.swing.JPanel();
        disableQlLop = new javax.swing.JLabel();
        pnCheckQuanLyLop = new javax.swing.JPanel();
        pnQuanLiDiemSo = new javax.swing.JPanel();
        disableQLDiemSo = new javax.swing.JLabel();
        pnCheckQuanLyDiemSo = new javax.swing.JPanel();
        pnTongKet = new javax.swing.JPanel();
        disableTongKet = new javax.swing.JLabel();
        pnCheckTongKet = new javax.swing.JPanel();
        pnThayDoiQuyDinh = new javax.swing.JPanel();
        disableTdQuyDinh = new javax.swing.JLabel();
        pnCheckThayDoiQuyDinh = new javax.swing.JPanel();
        pnDangXuat = new javax.swing.JPanel();
        DangXuat = new javax.swing.JLabel();
        pnThoat1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tblMainBoard = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(52, 56, 68));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quản lý học sinh");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_student_male_125px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(33, 33, 33))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 246, -1));

        pnDashboard.setBackground(new java.awt.Color(52, 56, 68));
        pnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnDashboardMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnDashboardMousePressed(evt);
            }
        });
        pnDashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_dashboard_32px_1.png"))); // NOI18N
        jLabel2.setText("Dashboard");
        pnDashboard.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckDashBoard.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckDashBoardLayout = new javax.swing.GroupLayout(pnCheckDashBoard);
        pnCheckDashBoard.setLayout(pnCheckDashBoardLayout);
        pnCheckDashBoardLayout.setHorizontalGroup(
            pnCheckDashBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckDashBoardLayout.setVerticalGroup(
            pnCheckDashBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnDashboard.add(pnCheckDashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 259, 246, 63));

        pnQuanLiHocSinh.setBackground(new java.awt.Color(52, 56, 68));
        pnQuanLiHocSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuanLiHocSinhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuanLiHocSinhMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnQuanLiHocSinhMousePressed(evt);
            }
        });
        pnQuanLiHocSinh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disableQuanLyHS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        disableQuanLyHS.setForeground(new java.awt.Color(255, 255, 255));
        disableQuanLyHS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/graduated.png"))); // NOI18N
        disableQuanLyHS.setText("Quản lý học sinh");
        pnQuanLiHocSinh.add(disableQuanLyHS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckQuanLyHocSinh.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckQuanLyHocSinhLayout = new javax.swing.GroupLayout(pnCheckQuanLyHocSinh);
        pnCheckQuanLyHocSinh.setLayout(pnCheckQuanLyHocSinhLayout);
        pnCheckQuanLyHocSinhLayout.setHorizontalGroup(
            pnCheckQuanLyHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckQuanLyHocSinhLayout.setVerticalGroup(
            pnCheckQuanLyHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnQuanLiHocSinh.add(pnCheckQuanLyHocSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnQuanLiHocSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 322, 246, 63));

        pnQuanLiLop.setBackground(new java.awt.Color(52, 56, 68));
        pnQuanLiLop.setFocusCycleRoot(true);
        pnQuanLiLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuanLiLopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuanLiLopMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnQuanLiLopMousePressed(evt);
            }
        });
        pnQuanLiLop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disableQlLop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        disableQlLop.setForeground(new java.awt.Color(255, 255, 255));
        disableQlLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/presentation.png"))); // NOI18N
        disableQlLop.setText("Quản lý lớp");
        pnQuanLiLop.add(disableQlLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckQuanLyLop.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckQuanLyLopLayout = new javax.swing.GroupLayout(pnCheckQuanLyLop);
        pnCheckQuanLyLop.setLayout(pnCheckQuanLyLopLayout);
        pnCheckQuanLyLopLayout.setHorizontalGroup(
            pnCheckQuanLyLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckQuanLyLopLayout.setVerticalGroup(
            pnCheckQuanLyLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnQuanLiLop.add(pnCheckQuanLyLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnQuanLiLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 385, 246, 63));

        pnQuanLiDiemSo.setBackground(new java.awt.Color(52, 56, 68));
        pnQuanLiDiemSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnQuanLiDiemSoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnQuanLiDiemSoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnQuanLiDiemSoMousePressed(evt);
            }
        });
        pnQuanLiDiemSo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disableQLDiemSo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        disableQLDiemSo.setForeground(new java.awt.Color(255, 255, 255));
        disableQLDiemSo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/score (1).png"))); // NOI18N
        disableQLDiemSo.setText("Quản lý điểm số");
        pnQuanLiDiemSo.add(disableQLDiemSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckQuanLyDiemSo.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckQuanLyDiemSoLayout = new javax.swing.GroupLayout(pnCheckQuanLyDiemSo);
        pnCheckQuanLyDiemSo.setLayout(pnCheckQuanLyDiemSoLayout);
        pnCheckQuanLyDiemSoLayout.setHorizontalGroup(
            pnCheckQuanLyDiemSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckQuanLyDiemSoLayout.setVerticalGroup(
            pnCheckQuanLyDiemSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnQuanLiDiemSo.add(pnCheckQuanLyDiemSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnQuanLiDiemSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 448, 246, 63));

        pnTongKet.setBackground(new java.awt.Color(52, 56, 68));
        pnTongKet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnTongKetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnTongKetMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnTongKetMousePressed(evt);
            }
        });
        pnTongKet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disableTongKet.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        disableTongKet.setForeground(new java.awt.Color(255, 255, 255));
        disableTongKet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/report (1).png"))); // NOI18N
        disableTongKet.setText("Tổng kết");
        pnTongKet.add(disableTongKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckTongKet.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckTongKetLayout = new javax.swing.GroupLayout(pnCheckTongKet);
        pnCheckTongKet.setLayout(pnCheckTongKetLayout);
        pnCheckTongKetLayout.setHorizontalGroup(
            pnCheckTongKetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckTongKetLayout.setVerticalGroup(
            pnCheckTongKetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnTongKet.add(pnCheckTongKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnTongKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 511, 246, 63));

        pnThayDoiQuyDinh.setBackground(new java.awt.Color(52, 56, 68));
        pnThayDoiQuyDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnThayDoiQuyDinhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnThayDoiQuyDinhMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnThayDoiQuyDinhMousePressed(evt);
            }
        });
        pnThayDoiQuyDinh.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        disableTdQuyDinh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        disableTdQuyDinh.setForeground(new java.awt.Color(255, 255, 255));
        disableTdQuyDinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exchange_1.png"))); // NOI18N
        disableTdQuyDinh.setText("Thay đổi quy định");
        pnThayDoiQuyDinh.add(disableTdQuyDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pnCheckThayDoiQuyDinh.setBackground(new java.awt.Color(52, 56, 68));

        javax.swing.GroupLayout pnCheckThayDoiQuyDinhLayout = new javax.swing.GroupLayout(pnCheckThayDoiQuyDinh);
        pnCheckThayDoiQuyDinh.setLayout(pnCheckThayDoiQuyDinhLayout);
        pnCheckThayDoiQuyDinhLayout.setHorizontalGroup(
            pnCheckThayDoiQuyDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        pnCheckThayDoiQuyDinhLayout.setVerticalGroup(
            pnCheckThayDoiQuyDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        pnThayDoiQuyDinh.add(pnCheckThayDoiQuyDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 5, -1));

        jPanel1.add(pnThayDoiQuyDinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 574, 246, 63));

        pnDangXuat.setBackground(new java.awt.Color(52, 56, 68));

        DangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exit (1).png"))); // NOI18N
        DangXuat.setText("Đăng xuất");

        javax.swing.GroupLayout pnDangXuatLayout = new javax.swing.GroupLayout(pnDangXuat);
        pnDangXuat.setLayout(pnDangXuatLayout);
        pnDangXuatLayout.setHorizontalGroup(
            pnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DangXuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnDangXuatLayout.setVerticalGroup(
            pnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDangXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DangXuat)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(pnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 643, 246, -1));

        pnThoat1.setBackground(new java.awt.Color(52, 56, 68));
        pnThoat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnThoat1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnThoat1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnThoat1MousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exit (1).png"))); // NOI18N
        jLabel11.setText("Thoát");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnThoat1Layout = new javax.swing.GroupLayout(pnThoat1);
        pnThoat1.setLayout(pnThoat1Layout);
        pnThoat1Layout.setHorizontalGroup(
            pnThoat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        pnThoat1Layout.setVerticalGroup(
            pnThoat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(pnThoat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 246, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 760));

        tblMainBoard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1145, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 766, Short.MAX_VALUE)
        );

        tblMainBoard.addTab("tab1", jPanel4);

        getContentPane().add(tblMainBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, -38, 1150, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pnQuanLiHocSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiHocSinhMousePressed
        chenPanel(quanLiHocSinh, pnQuanLiHocSinh, pnCheckQuanLyHocSinh, "2");
    }//GEN-LAST:event_pnQuanLiHocSinhMousePressed

    private void pnQuanLiLopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMousePressed
        chenPanel(quanLiLop, pnQuanLiLop, pnCheckQuanLyLop, "3");
    }//GEN-LAST:event_pnQuanLiLopMousePressed

    private void pnDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMousePressed
        dashBoard = new DashboardPanel();
        tblMainBoard.setComponentAt(0, dashBoard);
        chenPanel(dashBoard, pnDashboard, pnCheckDashBoard, "1");

    }//GEN-LAST:event_pnDashboardMousePressed

    private void pnDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMouseEntered

        pnDashboard.setBackground(menuCL);
    }//GEN-LAST:event_pnDashboardMouseEntered

    private void pnDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMouseExited
        if (tblMainBoard.getSelectedComponent() == dashBoard)
            pnDashboard.setBackground(menuCL);
        else
            pnDashboard.setBackground(backgroundCL);
    }//GEN-LAST:event_pnDashboardMouseExited

    private void pnQuanLiHocSinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiHocSinhMouseEntered
        pnQuanLiHocSinh.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiHocSinhMouseEntered

    private void pnQuanLiHocSinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiHocSinhMouseExited
        if (tblMainBoard.getSelectedComponent() == quanLiHocSinh)
            pnQuanLiHocSinh.setBackground(menuCL);
        else
            pnQuanLiHocSinh.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiHocSinhMouseExited

    private void pnQuanLiLopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMouseEntered
        pnQuanLiLop.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiLopMouseEntered

    private void pnQuanLiLopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMouseExited
        if (tblMainBoard.getSelectedComponent() == quanLiLop)
            pnQuanLiLop.setBackground(menuCL);
        else
            pnQuanLiLop.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiLopMouseExited

    private void pnQuanLiDiemSoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMouseEntered
        pnQuanLiDiemSo.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiDiemSoMouseEntered

    private void pnQuanLiDiemSoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMouseExited
        if (tblMainBoard.getSelectedComponent() == quanLiDiemSo)
            pnQuanLiDiemSo.setBackground(menuCL);
        else
            pnQuanLiDiemSo.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiDiemSoMouseExited

    private void pnTongKetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMouseEntered
        pnTongKet.setBackground(menuCL);
    }//GEN-LAST:event_pnTongKetMouseEntered

    private void pnTongKetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMouseExited
        if (tblMainBoard.getSelectedComponent() == tongKet)
            pnTongKet.setBackground(menuCL);
        else
            pnTongKet.setBackground(backgroundCL);
    }//GEN-LAST:event_pnTongKetMouseExited

    private void pnThayDoiQuyDinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMouseEntered
        pnThayDoiQuyDinh.setBackground(menuCL);
    }//GEN-LAST:event_pnThayDoiQuyDinhMouseEntered

    private void pnThayDoiQuyDinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMouseExited
        if (tblMainBoard.getSelectedComponent() == thayDoiQuyDinh)
            pnThayDoiQuyDinh.setBackground(menuCL);
        else
            pnThayDoiQuyDinh.setBackground(backgroundCL);
    }//GEN-LAST:event_pnThayDoiQuyDinhMouseExited

    private void pnQuanLiDiemSoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMousePressed
        chenPanel(quanLiDiemSo, pnQuanLiDiemSo, pnCheckQuanLyDiemSo, "4");
    }//GEN-LAST:event_pnQuanLiDiemSoMousePressed

    private void pnTongKetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMousePressed
        chenPanel(tongKet, pnTongKet, pnCheckTongKet, "5");
    }//GEN-LAST:event_pnTongKetMousePressed

    private void pnThayDoiQuyDinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMousePressed
        chenPanel(thayDoiQuyDinh, pnThayDoiQuyDinh, pnCheckThayDoiQuyDinh, "6");
    }//GEN-LAST:event_pnThayDoiQuyDinhMousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel11MousePressed

    private void pnThoat1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoat1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnThoat1MouseEntered

    private void pnThoat1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoat1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnThoat1MouseExited

    private void pnThoat1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoat1MousePressed
        int xacNhan = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_pnThoat1MousePressed
    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/edu/poly/poly/app/icons/16x16/graduated.png")));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel DangXuat;
    public javax.swing.JLabel disableQLDiemSo;
    public javax.swing.JLabel disableQlLop;
    public javax.swing.JLabel disableQuanLyHS;
    public javax.swing.JLabel disableTdQuyDinh;
    public javax.swing.JLabel disableTongKet;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pnCheckDashBoard;
    private javax.swing.JPanel pnCheckQuanLyDiemSo;
    private javax.swing.JPanel pnCheckQuanLyHocSinh;
    private javax.swing.JPanel pnCheckQuanLyLop;
    private javax.swing.JPanel pnCheckThayDoiQuyDinh;
    private javax.swing.JPanel pnCheckTongKet;
    public javax.swing.JPanel pnDangXuat;
    private javax.swing.JPanel pnDashboard;
    private javax.swing.JPanel pnQuanLiDiemSo;
    private javax.swing.JPanel pnQuanLiHocSinh;
    private javax.swing.JPanel pnQuanLiLop;
    private javax.swing.JPanel pnThayDoiQuyDinh;
    private javax.swing.JPanel pnThoat1;
    private javax.swing.JPanel pnTongKet;
    public javax.swing.JTabbedPane tblMainBoard;
    // End of variables declaration//GEN-END:variables
}
