/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupframe;

/**
 *
 * @author nguye
 */
public class SuaDiem_QuanLyDiemSoPanel extends javax.swing.JFrame {
    
    /**
     * Creates new form SuaDiem_BangDiemMonPanel
     */
    public SuaDiem_QuanLyDiemSoPanel() {
        
        initComponents();
        //khong the sua ho va ten, lop
        HoVaTen.setEditable(false);
        Lop.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        HoVaTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Lop = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Diem15p = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Diem1Tiet = new javax.swing.JTextField();
        SaveWindowButton = new javax.swing.JButton();
        ExitWindowButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Họ tên:");

        HoVaTen.setBackground(new java.awt.Color(240, 240, 240));
        HoVaTen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        HoVaTen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HoVaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HoVaTenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Lớp:");

        Lop.setBackground(new java.awt.Color(240, 240, 240));
        Lop.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Lop.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LopActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Điểm 15p:");

        Diem15p.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Diem15p.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Diem15p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Diem15pActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Điểm 1 tiết:");

        Diem1Tiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Diem1Tiet.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        SaveWindowButton.setBackground(new java.awt.Color(0, 176, 239));
        SaveWindowButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SaveWindowButton.setForeground(new java.awt.Color(255, 255, 255));
        SaveWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_save_24px.png"))); // NOI18N
        SaveWindowButton.setText("Lưu");
        SaveWindowButton.setBorder(null);

        ExitWindowButton.setBackground(new java.awt.Color(254, 193, 6));
        ExitWindowButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ExitWindowButton.setForeground(new java.awt.Color(255, 255, 255));
        ExitWindowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_exit_24px_1.png"))); // NOI18N
        ExitWindowButton.setText("Thoát");
        ExitWindowButton.setBorder(null);
        ExitWindowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitWindowButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Diem15p, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SaveWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(ExitWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Diem1Tiet, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(HoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Diem15p, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(Diem1Tiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void HoVaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HoVaTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HoVaTenActionPerformed

    private void Diem15pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Diem15pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Diem15pActionPerformed

    private void LopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LopActionPerformed

    private void ExitWindowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitWindowButtonActionPerformed
        
        this.setVisible(false);
        
    }//GEN-LAST:event_ExitWindowButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField Diem15p;
    public javax.swing.JTextField Diem1Tiet;
    public javax.swing.JButton ExitWindowButton;
    public javax.swing.JTextField HoVaTen;
    public javax.swing.JTextField Lop;
    public javax.swing.JButton SaveWindowButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}