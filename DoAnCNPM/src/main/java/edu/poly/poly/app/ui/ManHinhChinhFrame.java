/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.ui;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Admin
 */
public class ManHinhChinhFrame extends javax.swing.JFrame {

    public ManHinhChinhFrame() {
        
        initComponents();
        setLocationRelativeTo(null);
        SetIcon();
        
    }
    
    //Hàm chuyển đổi các giữa các JPanel 
    public void chenPanel(JPanel panel, String tieuDe){
        //Xóa toàn bộ bảng trên tblMainBoard
        tblMainBoard.removeAll();
        tblMainBoard.addTab(tieuDe, panel);
        tblMainBoard.setSelectedComponent(panel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        Main_exit = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        mnuManageStudent = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        NhaplopBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        Tracuuhocsinh = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        NhanBangDiem = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        BaoCaoTongKet = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        ThayDoiQuyDinh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tblMainBoard = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        Menu_exiy = new javax.swing.JMenu();
        Menu_exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        Main_exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Main_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exit (1).png"))); // NOI18N
        Main_exit.setText("Exit");
        Main_exit.setFocusable(false);
        Main_exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Main_exit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Main_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_exitActionPerformed(evt);
            }
        });
        jToolBar1.add(Main_exit);
        jToolBar1.add(jSeparator6);

        mnuManageStudent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnuManageStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/graduated.png"))); // NOI18N
        mnuManageStudent.setText("Tiếp nhận học sinh");
        mnuManageStudent.setFocusable(false);
        mnuManageStudent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mnuManageStudent.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mnuManageStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuManageStudentActionPerformed(evt);
            }
        });
        jToolBar1.add(mnuManageStudent);
        jToolBar1.add(jSeparator1);

        NhaplopBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NhaplopBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/presentation.png"))); // NOI18N
        NhaplopBtn.setText("Danh sách lớp");
        NhaplopBtn.setFocusable(false);
        NhaplopBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NhaplopBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NhaplopBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhaplopBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(NhaplopBtn);
        jToolBar1.add(jSeparator2);

        Tracuuhocsinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Tracuuhocsinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/search.png"))); // NOI18N
        Tracuuhocsinh.setText("Tra cứu học sinh");
        Tracuuhocsinh.setFocusable(false);
        Tracuuhocsinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Tracuuhocsinh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Tracuuhocsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TracuuhocsinhActionPerformed(evt);
            }
        });
        jToolBar1.add(Tracuuhocsinh);
        jToolBar1.add(jSeparator3);

        NhanBangDiem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NhanBangDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/score (1).png"))); // NOI18N
        NhanBangDiem.setText("Nhận bảng điểm môn");
        NhanBangDiem.setFocusable(false);
        NhanBangDiem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NhanBangDiem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NhanBangDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NhanBangDiemActionPerformed(evt);
            }
        });
        jToolBar1.add(NhanBangDiem);
        jToolBar1.add(jSeparator4);

        BaoCaoTongKet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BaoCaoTongKet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/report (1).png"))); // NOI18N
        BaoCaoTongKet.setText("Lập báo cáo tổng kết");
        BaoCaoTongKet.setFocusable(false);
        BaoCaoTongKet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BaoCaoTongKet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BaoCaoTongKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaoCaoTongKetActionPerformed(evt);
            }
        });
        jToolBar1.add(BaoCaoTongKet);
        jToolBar1.add(jSeparator5);

        ThayDoiQuyDinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ThayDoiQuyDinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/exchange_1.png"))); // NOI18N
        ThayDoiQuyDinh.setText("Thay đổi quy định");
        ThayDoiQuyDinh.setFocusable(false);
        ThayDoiQuyDinh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ThayDoiQuyDinh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ThayDoiQuyDinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThayDoiQuyDinhActionPerformed(evt);
            }
        });
        jToolBar1.add(ThayDoiQuyDinh);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/main2.png"))); // NOI18N

        tblMainBoard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Menu_exiy.setText("Hệ thống");
        Menu_exiy.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                Menu_exiyAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        Menu_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK));
        Menu_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/exit (1).png"))); // NOI18N
        Menu_exit.setText("Exit");
        Menu_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_exitActionPerformed(evt);
            }
        });
        Menu_exiy.add(Menu_exit);

        jMenuBar1.add(Menu_exiy);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tblMainBoard)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(194, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addContainerGap(194, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblMainBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(152, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(88, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ThayDoiQuyDinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThayDoiQuyDinhActionPerformed
        String tieuDe = "Thay đổi quy định";
        chenPanel(new ThayDoiQuyDinhPanel(), tieuDe);
    }//GEN-LAST:event_ThayDoiQuyDinhActionPerformed

    private void BaoCaoTongKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaoCaoTongKetActionPerformed
        String tieuDe = "Báo cáo tổng kết";
        chenPanel(new BaoCaoTongKetPanel(), tieuDe);
    }//GEN-LAST:event_BaoCaoTongKetActionPerformed

    private void NhanBangDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhanBangDiemActionPerformed
        String tieuDe = "Bảng điểm môn";
        chenPanel(new BangDiemMonPanel(), tieuDe);
    }//GEN-LAST:event_NhanBangDiemActionPerformed

    private void TracuuhocsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TracuuhocsinhActionPerformed
        String tieuDe = "Tra cứu học sinh";
        chenPanel(new TraCuuHocSinhPanel(), tieuDe);
    }//GEN-LAST:event_TracuuhocsinhActionPerformed

    private void NhaplopBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NhaplopBtnActionPerformed
        String tieuDe = "Lập danh sách lớp";
        chenPanel(new DanhSachLopPanel(), tieuDe);
    }//GEN-LAST:event_NhaplopBtnActionPerformed

    private void mnuManageStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuManageStudentActionPerformed
        String tieuDe = "Tiếp nhận học sinh";
        chenPanel(new TiepNhanHocSinhPanel(), tieuDe);
    }//GEN-LAST:event_mnuManageStudentActionPerformed

    private void Main_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Main_exitActionPerformed
        int exit=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát ứng dụng", "Thoát", JOptionPane.YES_NO_OPTION);
        if(exit==JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_Main_exitActionPerformed

    private void Menu_exiyAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Menu_exiyAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Menu_exiyAncestorMoved

    private void Menu_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Menu_exitActionPerformed
            private void SetIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/edu/poly/poly/app/icons/16x16/graduated.png")));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManHinhChinhFrame().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BaoCaoTongKet;
    private javax.swing.JButton Main_exit;
    private javax.swing.JMenuItem Menu_exit;
    private javax.swing.JMenu Menu_exiy;
    private javax.swing.JButton NhanBangDiem;
    private javax.swing.JButton NhaplopBtn;
    private javax.swing.JButton ThayDoiQuyDinh;
    private javax.swing.JButton Tracuuhocsinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton mnuManageStudent;
    private javax.swing.JTabbedPane tblMainBoard;
    // End of variables declaration//GEN-END:variables
}
