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
    QuanLyDiemSoPanel quanLiDiemSo = new QuanLyDiemSoPanel();
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
        tblMainBoard.setSelectedComponent(panel);
        //Trả lại màu ban đầu cho menu
        pnDashboard.setBackground(backgroundCL);
        pnQuanLiDiemSo.setBackground(backgroundCL);
        pnQuanLiHocSinh.setBackground(backgroundCL);
        pnQuanLiLop.setBackground(backgroundCL);
        pnThayDoiQuyDinh.setBackground(backgroundCL);
        pnThoat.setBackground(backgroundCL);
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

        //Thêm sẵn các panel vào tabbed panel
        tblMainBoard.addTab("Quản lý học sinh", quanLiHocSinh);
        tblMainBoard.addTab("Quản lý điểm số", quanLiDiemSo);
        tblMainBoard.addTab("Quản lý lớp", quanLiLop);
        tblMainBoard.addTab("Tổng kết", tongKet);
        tblMainBoard.addTab("Thay đổi quy định", thayDoiQuyDinh);
        tblMainBoard.addTab("Dashboard", dashBoard);
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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinhFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LayThamSo().ketNoiCoSoDulieu();

                new ManHinhChinhFrame().setVisible(true);

            }
        });
    }

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
        jLabel1 = new javax.swing.JLabel();
        pnCheckQuanLyHocSinh = new javax.swing.JPanel();
        pnQuanLiLop = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnCheckQuanLyLop = new javax.swing.JPanel();
        pnQuanLiDiemSo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnCheckQuanLyDiemSo = new javax.swing.JPanel();
        pnTongKet = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnCheckTongKet = new javax.swing.JPanel();
        pnThayDoiQuyDinh = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnCheckThayDoiQuyDinh = new javax.swing.JPanel();
        pnThoat = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/graduated.png"))); // NOI18N
        jLabel1.setText("Quản lý học sinh");
        pnQuanLiHocSinh.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/presentation.png"))); // NOI18N
        jLabel3.setText("Quản lý lớp");
        pnQuanLiLop.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/score (1).png"))); // NOI18N
        jLabel7.setText("Quản lý điểm số");
        pnQuanLiDiemSo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/report (1).png"))); // NOI18N
        jLabel8.setText("Tổng kết");
        pnTongKet.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exchange_1.png"))); // NOI18N
        jLabel9.setText("Thay đổi quy định");
        pnThayDoiQuyDinh.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

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

        pnThoat.setBackground(new java.awt.Color(52, 56, 68));
        pnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnThoatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnThoatMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnThoatMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exit (1).png"))); // NOI18N
        jLabel10.setText("Thoát");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnThoatLayout = new javax.swing.GroupLayout(pnThoat);
        pnThoat.setLayout(pnThoatLayout);
        pnThoatLayout.setHorizontalGroup(
            pnThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnThoatLayout.setVerticalGroup(
            pnThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(pnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 643, 246, -1));

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

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MousePressed

    private void pnThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoatMousePressed
        int exit = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát ứng dụng", "Thoát", JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_pnThoatMousePressed

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

    private void pnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoatMouseEntered
        pnThoat.setBackground(menuCL);
    }//GEN-LAST:event_pnThoatMouseEntered

    private void pnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoatMouseExited
        pnThoat.setBackground(backgroundCL);
    }//GEN-LAST:event_pnThoatMouseExited

    private void pnTongKetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMousePressed
        chenPanel(tongKet, pnTongKet, pnCheckTongKet, "5");
    }//GEN-LAST:event_pnTongKetMousePressed

    private void pnThayDoiQuyDinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMousePressed
        chenPanel(thayDoiQuyDinh, pnThayDoiQuyDinh, pnCheckThayDoiQuyDinh, "6");
    }//GEN-LAST:event_pnThayDoiQuyDinhMousePressed
    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/edu/poly/poly/app/icons/16x16/graduated.png")));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel pnDashboard;
    private javax.swing.JPanel pnQuanLiDiemSo;
    private javax.swing.JPanel pnQuanLiHocSinh;
    private javax.swing.JPanel pnQuanLiLop;
    private javax.swing.JPanel pnThayDoiQuyDinh;
    private javax.swing.JPanel pnThoat;
    private javax.swing.JPanel pnTongKet;
    private javax.swing.JTabbedPane tblMainBoard;
    // End of variables declaration//GEN-END:variables
}
