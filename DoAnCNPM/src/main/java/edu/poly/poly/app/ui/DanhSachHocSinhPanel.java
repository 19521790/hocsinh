/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.poly.app.ui;
import java.util.Date; 
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
     
/**
 *
 * @author Admin
 */
public class DanhSachHocSinhPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhapLop
     */
    String selectedClas="";
    String selectedYear="";
    String mshsChosen="";
    themhocsinhDialog addstudent= new themhocsinhDialog(this);
    List<String > deletequery= new ArrayList<>();
    List<String> mahsList = new ArrayList<>();
    List<String> procquery= new ArrayList<>();
    void modifyDB(){
        for(var t :procquery){
            System.out.println(t);
        try{
             Connection  cn= JDBCConnection.ketNoiJBDC();
          java.sql.CallableStatement cst=cn.prepareCall(t);
            ResultSet r = cst.executeQuery(); 
        }
        catch(SQLException e){
                
        }
        }
        
        for(var t :deletequery){
         try {
             Connection  cn= JDBCConnection.ketNoiJBDC();
             Statement cst=cn.createStatement();
               int  r = cst.executeUpdate(t);
             
         }
         catch(SQLException e){
             
         }
        
        }
    }
    public void addintoTable(String ms){
         String sql="select HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh  from HOCSINH where HOCSINH.MaHocSinh ='"+ms+"'";
          try{
          Connection  cn= JDBCConnection.ketNoiJBDC();
          Statement sta=cn.createStatement();
          ResultSet r = sta.executeQuery(sql); 
          while(r.next()){
          String name =r.getString("HoTen");
          String date= r.getString("NgaySinh");
          String mahs =r.getString("MaHocSinh");
          System.out.println(mahs);
          String email= r.getString("Email");
          String address=  r.getString("DiaChi");
          String s= r.getString("GioiTinh");
          String datab[]={mahs,name,s,date,address};
          DefaultTableModel tblM= (DefaultTableModel)this.infoTable.getModel();
          tblM.addRow(datab);
          }
          }
          catch (SQLException e){
          
          }
     
     }
    public boolean  addstu(String ms){
       String sql="select * from HOCSINH where HOCSINH.MaHocSinh='"+ms+"'";
       try{
             Connection  cn= JDBCConnection.ketNoiJBDC();
            Statement sta=cn.createStatement();
             ResultSet r = sta.executeQuery(sql); 
             if(!r.next()) return false;
             System.out.println(r.getString("HoTen"));
             procquery.add("exec thembangdiem 0 ,'"+ms+"','"+"LOP"+this.selectedClas+"','"+this.selectedYear+"K1"+"'");
             procquery.add("exec thembangdiem 0 ,'"+ms+"','"+"LOP"+this.selectedClas+"','"+this.selectedYear+"K2"+"'");             
       }
       catch(SQLException e){
           return  false;
       }
            return true;
    }
    
    void getInfo(String mshs){
        String sql="select * from HOCSINH where HOCSINH.MaHocSinh='"+mshs+"'";
       try{
          Connection  cn= JDBCConnection.ketNoiJBDC();
          Statement sta=cn.createStatement();
          ResultSet r = sta.executeQuery(sql); 
          while(r.next()){
          this.nameBox.setText(r.getString("HoTen"));
         this.dateBox.setText(r.getString("NgaySinh"));
         this.emailBox.setText(r.getString("Email"));
         this.addressBox.setText(r.getString("DiaChi"));
          
          String s= r.getString("GioiTinh");
        if(s=="0"){
            this.rbNam2.setSelected(true);
            this.rbNu2.setSelected(false);
        }
        else{
           this.rbNam2.setSelected(false);
            this.rbNu2.setSelected(true);
          }
         
          
          }
          }
          catch (SQLException e){
          
          }
    
    
    }
    void seek(String c, String y){
        
          
          String sql ="select DISTINCT  HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh  from HOCSINH  , QUATRINHHOC,HOCKI ,LOP WHERE HOCSINH.MaHocSinh=QUATRINHHOC.MaHocSinh AND QUATRINHHOC.MaHocKi=HOCKI.MaHocKi  AND QUATRINHHOC.MaLop=LOP.MaLop AND LOP.TenLop='"+c+"' AND HOCKI.Nam="+y;
          try{
          Connection  cn= JDBCConnection.ketNoiJBDC();
          Statement sta=cn.createStatement();
          ResultSet r = sta.executeQuery(sql); 
          while(r.next()){
          String name =r.getString("HoTen");
          String date= r.getString("NgaySinh");
          String mahs =r.getString("MaHocSinh");
          System.out.println(mahs);
          String email= r.getString("Email");
          String address=  r.getString("DiaChi");
          String s= r.getString("GioiTinh");
          String datab[]={mahs,name,s,date,address};
          DefaultTableModel tblM= (DefaultTableModel)this.infoTable.getModel();
          tblM.addRow(datab);
          }
          }
          catch (SQLException e){
          
          }
    
   
    }
    void loadContentYearList(int curYear){
    this.yearList.addItem(Integer.toString(curYear));
    this.yearList.addItem(Integer.toString(curYear-1));
    this.yearList.addItem(Integer.toString(curYear-2));

}
    public DanhSachHocSinhPanel() {
           initComponents();
         Date d= new Date();
    this.loadContentYearList(d.getYear()+1900);
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
        clasList = new javax.swing.JComboBox<>();
        validateSiSo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        yearList = new javax.swing.JComboBox<>();
        seekButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        nameBox = new javax.swing.JTextField();
        emailBox = new javax.swing.JTextField();
        dateBox = new javax.swing.JTextField();
        rbNam2 = new javax.swing.JRadioButton();
        rbNu2 = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        addressBox = new javax.swing.JTextArea();
        deleteButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập lớp cần tìm"));

        jLabel1.setText("Lớp:");

        clasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10A1", "10A2", "10A3", "10A4", "11A1", "11A2", "11A3", "12A1", "12A2" }));

        jLabel2.setText("Năm học:");

        yearList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2021", "2020", "2019", "2018" }));

        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seekButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(seekButton)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lớp"));

        infoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MSSV", "Họ tên", "Năm sinh", "Địa chỉ"
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
        jScrollPane1.setViewportView(infoTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin học sinh"));

        jLabel19.setText("Họ tên:");

        jLabel20.setText("Email:");

        jLabel21.setText("Giới tính:");

        jLabel22.setText("Ngày sinh:");

        nameBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameBoxActionPerformed(evt);
            }
        });

        emailBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailBoxActionPerformed(evt);
            }
        });

        rbNam2.setText("Nam");
        rbNam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNam2ActionPerformed(evt);
            }
        });

        rbNu2.setText("Nữ");
        rbNu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNu2ActionPerformed(evt);
            }
        });

        jLabel23.setText("Địa chỉ:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("*");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("*");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("*");

        addressBox.setColumns(20);
        addressBox.setRows(5);
        jScrollPane4.setViewportView(addressBox);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameBox, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(emailBox)
                    .addComponent(dateBox))
                .addGap(71, 71, 71)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbNam2)
                        .addGap(40, 40, 40)
                        .addComponent(rbNu2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(nameBox)
                    .addComponent(jLabel21)
                    .addComponent(rbNam2)
                    .addComponent(rbNu2)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(dateBox)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailBox)
                            .addComponent(jLabel20))
                        .addGap(36, 36, 36))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        deleteButton.setText("Xóa");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Xóa học sinh trong danh sách");

        jLabel12.setText("Thêm học sinh vào danh sách. (Thêm bằng MSSV)");

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        addButton.setText("Thêm mới");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/diskette.png"))); // NOI18N
        saveButton.setText("Lưu");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Cập nhật");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addButton)
                        .addComponent(saveButton)
                        .addComponent(jLabel12)
                        .addComponent(jButton1))
                    .addComponent(deleteButton)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void saveTABLE(java.awt.event.ActionEvent evt){
        
    }
    private void emailBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailBoxActionPerformed

    private void nameBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
            this.addstudent.setVisible(true);
            
            
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
               
        int index =this.infoTable.getSelectedRow();
        TableModel md= this.infoTable.getModel();
       this.deletequery.add("update QUATRINHHOC set MaLop=null where MaHocSinh='"+md.getValueAt(index, 0).toString()+"'");
       int numRows = infoTable.getSelectedRows().length;
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
for(int i=0; i<numRows ; i++ ) {

    model.removeRow(infoTable.getSelectedRow());
}
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void seekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekButtonActionPerformed
        // TODO add your handling code here:
        
        this.deletequery=new  ArrayList<>();
            this.procquery=new  ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
model.setRowCount(0);
         System.out.println((String)this.yearList.getSelectedItem());
         this.selectedClas=(String)this.clasList.getSelectedItem();
         this.selectedYear= (String)this.yearList.getSelectedItem();
        seek((String)this.clasList.getSelectedItem(),(String)this.yearList.getSelectedItem());
    }//GEN-LAST:event_seekButtonActionPerformed

    private void infoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTableMouseClicked
        // TODO add your handling code here:
         int index =this.infoTable.getSelectedRow();
        TableModel md= this.infoTable.getModel();
        this.getInfo(this.mshsChosen=md.getValueAt(index, 0).toString());
        
    }//GEN-LAST:event_infoTableMouseClicked

    private void rbNam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNam2ActionPerformed
        // TODO add your handling code here:
          if(this.rbNam2.isSelected())
        {
         this.rbNu2.setSelected(false);
        }
    }//GEN-LAST:event_rbNam2ActionPerformed

    private void rbNu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNu2ActionPerformed
          // TODO add your handling code here:
        if(this.rbNu2.isSelected())
        {
         this.rbNam2.setSelected(false);      
    }//GEN-LAST:event_rbNu2ActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        this.modifyDB();
        this.deletequery=new  ArrayList<>();
        this.procquery=new  ArrayList<>();
    }//GEN-LAST:event_saveButtonActionPerformed
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea addressBox;
    private javax.swing.JComboBox<String> clasList;
    private javax.swing.JTextField dateBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailBox;
    private javax.swing.JTable infoTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField nameBox;
    private javax.swing.JRadioButton rbNam2;
    private javax.swing.JRadioButton rbNu2;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton seekButton;
    private javax.swing.JLabel validateSiSo;
    private javax.swing.JComboBox<String> yearList;
    // End of variables declaration//GEN-END:variables
}
