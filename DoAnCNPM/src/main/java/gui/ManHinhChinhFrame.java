/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class ManHinhChinhFrame extends javax.swing.JFrame {

    //Gọi các panel
    QuanLiDiemSoPanel quanLiDiemSo = new QuanLiDiemSoPanel();
    QuanLyHocSinhPanel quanLiHocSinh = new QuanLyHocSinhPanel();
    QuanLyLopPanel quanLiLop = new QuanLyLopPanel();
    ThayDoiQuyDinhPanel thayDoiQuyDinh = new ThayDoiQuyDinhPanel();
    TongKetPanel tongKet = new TongKetPanel();

    //Khởi tạo màu mới
    Color menuCL = new Color(74,75,115);
    Color backgroundCL = new Color(52,56,68);
    
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
    }

    //Hàm chuyển đổi các giữa các JPanel 
    public void chenPanel(JPanel panel, JPanel menu, String tieuDe) {
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
        //Đổi màu menu chuột click
        menu.setBackground(menuCL);
    }

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
                QuanLyHocSinhPanel quanLyHocSinh = new QuanLyHocSinhPanel();
                QuanLiDiemSoPanel bangDiemMon = new QuanLiDiemSoPanel();
                QuanLyLopPanel danhSachLop = new QuanLyLopPanel();

                new ManHinhChinhFrame().setVisible(true);

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tblMainBoard = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnDashboard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnQuanLiHocSinh = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnQuanLiLop = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pnQuanLiDiemSo = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pnTongKet = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnThayDoiQuyDinh = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnThoat = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(tblMainBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, -38, 1150, 800));

        jPanel1.setBackground(new java.awt.Color(52, 56, 68));

        jPanel2.setBackground(new java.awt.Color(52, 56, 68));

        jPanel3.setBackground(new java.awt.Color(52, 56, 68));

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_yellow_file_32px.png"))); // NOI18N
        jLabel2.setText("Dashboard");

        javax.swing.GroupLayout pnDashboardLayout = new javax.swing.GroupLayout(pnDashboard);
        pnDashboard.setLayout(pnDashboardLayout);
        pnDashboardLayout.setHorizontalGroup(
            pnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        pnDashboardLayout.setVerticalGroup(
            pnDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/graduated.png"))); // NOI18N
        jLabel1.setText("Quản lý học sinh");

        javax.swing.GroupLayout pnQuanLiHocSinhLayout = new javax.swing.GroupLayout(pnQuanLiHocSinh);
        pnQuanLiHocSinh.setLayout(pnQuanLiHocSinhLayout);
        pnQuanLiHocSinhLayout.setHorizontalGroup(
            pnQuanLiHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiHocSinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnQuanLiHocSinhLayout.setVerticalGroup(
            pnQuanLiHocSinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiHocSinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/presentation.png"))); // NOI18N
        jLabel3.setText("Quản lý lớp");

        javax.swing.GroupLayout pnQuanLiLopLayout = new javax.swing.GroupLayout(pnQuanLiLop);
        pnQuanLiLop.setLayout(pnQuanLiLopLayout);
        pnQuanLiLopLayout.setHorizontalGroup(
            pnQuanLiLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiLopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        pnQuanLiLopLayout.setVerticalGroup(
            pnQuanLiLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiLopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/score (1).png"))); // NOI18N
        jLabel7.setText("Quản lý điểm số");

        javax.swing.GroupLayout pnQuanLiDiemSoLayout = new javax.swing.GroupLayout(pnQuanLiDiemSo);
        pnQuanLiDiemSo.setLayout(pnQuanLiDiemSoLayout);
        pnQuanLiDiemSoLayout.setHorizontalGroup(
            pnQuanLiDiemSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiDiemSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        pnQuanLiDiemSoLayout.setVerticalGroup(
            pnQuanLiDiemSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLiDiemSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/report (1).png"))); // NOI18N
        jLabel8.setText("Tổng kết");

        javax.swing.GroupLayout pnTongKetLayout = new javax.swing.GroupLayout(pnTongKet);
        pnTongKet.setLayout(pnTongKetLayout);
        pnTongKetLayout.setHorizontalGroup(
            pnTongKetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTongKetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        pnTongKetLayout.setVerticalGroup(
            pnTongKetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTongKetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exchange_1.png"))); // NOI18N
        jLabel9.setText("Thay đổi quy định");

        javax.swing.GroupLayout pnThayDoiQuyDinhLayout = new javax.swing.GroupLayout(pnThayDoiQuyDinh);
        pnThayDoiQuyDinh.setLayout(pnThayDoiQuyDinhLayout);
        pnThayDoiQuyDinhLayout.setHorizontalGroup(
            pnThayDoiQuyDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThayDoiQuyDinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pnThayDoiQuyDinhLayout.setVerticalGroup(
            pnThayDoiQuyDinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThayDoiQuyDinhLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
                .addContainerGap(124, Short.MAX_VALUE))
        );
        pnThoatLayout.setVerticalGroup(
            pnThoatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThoatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnQuanLiHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnQuanLiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnQuanLiDiemSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnTongKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnThayDoiQuyDinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnQuanLiHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnQuanLiLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnQuanLiDiemSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnTongKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnThayDoiQuyDinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(pnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 760));

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
        chenPanel(quanLiHocSinh, pnQuanLiHocSinh, "2");
    }//GEN-LAST:event_pnQuanLiHocSinhMousePressed

    private void pnQuanLiLopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMousePressed
        chenPanel(quanLiLop, pnQuanLiLop, "3");
    }//GEN-LAST:event_pnQuanLiLopMousePressed

    private void pnDashboardMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnDashboardMousePressed

    private void pnDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMouseEntered
        pnDashboard.setBackground(menuCL);
    }//GEN-LAST:event_pnDashboardMouseEntered

    private void pnDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnDashboardMouseExited
        pnDashboard.setBackground(backgroundCL);
    }//GEN-LAST:event_pnDashboardMouseExited

    private void pnQuanLiHocSinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiHocSinhMouseEntered
        pnQuanLiHocSinh.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiHocSinhMouseEntered

    private void pnQuanLiHocSinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiHocSinhMouseExited
        if (tblMainBoard.getSelectedComponent()==quanLiHocSinh)
            pnQuanLiHocSinh.setBackground(menuCL);
        else pnQuanLiHocSinh.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiHocSinhMouseExited

    private void pnQuanLiLopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMouseEntered
        pnQuanLiLop.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiLopMouseEntered

    private void pnQuanLiLopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiLopMouseExited
        if (tblMainBoard.getSelectedComponent()==quanLiLop)
            pnQuanLiLop.setBackground(menuCL);
        else pnQuanLiLop.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiLopMouseExited

    private void pnQuanLiDiemSoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMouseEntered
        pnQuanLiDiemSo.setBackground(menuCL);
    }//GEN-LAST:event_pnQuanLiDiemSoMouseEntered

    private void pnQuanLiDiemSoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMouseExited
        if (tblMainBoard.getSelectedComponent()==quanLiDiemSo)
            pnQuanLiDiemSo.setBackground(menuCL);
        else pnQuanLiDiemSo.setBackground(backgroundCL);
    }//GEN-LAST:event_pnQuanLiDiemSoMouseExited

    private void pnTongKetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMouseEntered
        pnTongKet.setBackground(menuCL);
    }//GEN-LAST:event_pnTongKetMouseEntered

    private void pnTongKetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMouseExited
        if (tblMainBoard.getSelectedComponent()==tongKet)
            pnTongKet.setBackground(menuCL);
        else pnTongKet.setBackground(backgroundCL);
    }//GEN-LAST:event_pnTongKetMouseExited

    private void pnThayDoiQuyDinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMouseEntered
        pnThayDoiQuyDinh.setBackground(menuCL);
    }//GEN-LAST:event_pnThayDoiQuyDinhMouseEntered

    private void pnThayDoiQuyDinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMouseExited
        if (tblMainBoard.getSelectedComponent()==thayDoiQuyDinh)
            pnThayDoiQuyDinh.setBackground(menuCL);
        else pnThayDoiQuyDinh.setBackground(backgroundCL);
    }//GEN-LAST:event_pnThayDoiQuyDinhMouseExited

    private void pnQuanLiDiemSoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnQuanLiDiemSoMousePressed
        chenPanel(quanLiDiemSo, pnQuanLiDiemSo, "4");
    }//GEN-LAST:event_pnQuanLiDiemSoMousePressed

    private void pnThoatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoatMouseEntered
        pnThoat.setBackground(menuCL);
    }//GEN-LAST:event_pnThoatMouseEntered

    private void pnThoatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThoatMouseExited
        pnThoat.setBackground(backgroundCL);
    }//GEN-LAST:event_pnThoatMouseExited

    private void pnTongKetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnTongKetMousePressed
        chenPanel(tongKet, pnTongKet, "5");
    }//GEN-LAST:event_pnTongKetMousePressed

    private void pnThayDoiQuyDinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnThayDoiQuyDinhMousePressed
        chenPanel(thayDoiQuyDinh, pnThayDoiQuyDinh, "6");
    }//GEN-LAST:event_pnThayDoiQuyDinhMousePressed
    private void SetIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/edu/poly/poly/app/icons/16x16/graduated.png")));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
