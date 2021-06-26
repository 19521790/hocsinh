/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.CallableStatement;
import popupframe.ThemHocSinh_DanhSachHocSinhPanel;
import popupframe.DiemHocSinh_DanhSachLopPanel;
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
import javax.swing.JOptionPane;
import popupframe.ThongTinHocSinh_DanhSachHocSinhPanel;

/**
 *
 * @author Admin
 */
public class QuanLyLopPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhapLop
     */
    ThongTinHocSinh_DanhSachHocSinhPanel infoPanel = new ThongTinHocSinh_DanhSachHocSinhPanel();
    public String selectedClas = "Chọn";
    public String selectedYear = "Chọn";
    String mshsChosen = "";
    ThemHocSinh_DanhSachHocSinhPanel addstudent;
    DiemHocSinh_DanhSachLopPanel diemhs;
    List<String> deletequery = new ArrayList<>();
    List<String> mahsList = new ArrayList<>();
    List<String> procquery = new ArrayList<>();

    public void loadtable() {
        String sql = "exec sp_DanhSachLop_InDanhSach " + this.yearBox.getSelectedItem().toString() + ",'" + this.clasBox.getSelectedItem().toString() + "'";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            CallableStatement cst = cn.prepareCall(sql);
            ResultSet r = cst.executeQuery();
            int i = 0;
            System.out.println(sql);
            while (r.next()) {

                i++;
                String arr[] = {Integer.toString(i), r.getString("MaHocSinh"), r.getString("HoTen"), r.getString("MaLop"), r.getString("TBHK1"), r.getString("TBHK2")};
                DefaultTableModel tblM = (DefaultTableModel) this.infoTable1.getModel();
                tblM.addRow(arr);
            }
             if(i==0){
                    JOptionPane.showMessageDialog(this, "KHÔNG TÌM THẤY THÔNG TIN");

        }
        } catch (SQLException e) {
            return;
        }
       

    }

    void createClassList() {
        String sql = "select * from  LOP";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.clasBox.addItem(r.getString("MaLop"));
                this.clasList.addItem(r.getString("MaLop"));
            }
        } catch (SQLException e) {
            return;
        }

    }

    void createYearList() {
        String sql = " select distinct  HOCKI_NAM.NAM from HOCKI_NAM";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {

                this.yearBox.addItem(r.getString("Nam"));
                this.yearList.addItem(r.getString("Nam"));
            }
        } catch (SQLException e) {
            return;
        }
    }

    boolean checkhaveSelected() {

        if (this.selectedClas == "Chọn" || this.selectedYear == "Chọn") {
            System.out.println("checkhaveSelected");
            JOptionPane.showMessageDialog(this, "Chua chon Lop va Nam Hoc");
            return true;
        }
        return false;
    }

    void modifyDB() {
        for (var t : procquery) {
            System.out.println(t);
            try {
                Connection cn = JDBCConnection.ketNoiJBDC();
                java.sql.CallableStatement cst = cn.prepareCall(t);
                ResultSet r = cst.executeQuery();
                System.out.println(t);
            } catch (SQLException e) {

            }
        }

        for (var t : deletequery) {
            try {
                Connection cn = JDBCConnection.ketNoiJBDC();
                Statement cst = cn.createStatement();
                int r = cst.executeUpdate(t);
                System.out.println(t);
            } catch (SQLException e) {

            }

        }
    }

    public void addintoTable(String ms) {
        String sql = "select HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh  from HOCSINH where HOCSINH.MaHocSinh ='" + ms + "'";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {
                String name = r.getString("HoTen");
                String date = r.getString("NgaySinh");
                String mahs = r.getString("MaHocSinh");
                System.out.println(mahs);
                String email = r.getString("Email");
                String address = r.getString("DiaChi");
                String s = r.getString("GioiTinh");
                String datab[] = {mahs, name, s, date, address};
                DefaultTableModel tblM = (DefaultTableModel) this.infoTable.getModel();
                tblM.addRow(datab);
            }
        } catch (SQLException e) {

        }

    }

    public boolean addstu(String ms) {
        String sql = "select * from HOCSINH where HOCSINH.MaHocSinh='" + ms + "'";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            if (!r.next()) {
                return false;
            }
            System.out.println(r.getString("HoTen"));
            procquery.add("exec sp_DanhSachHocSinh_ThemHocSinhVaoLop '" + ms + "','" + this.selectedClas + "','" + this.selectedYear + "'");

        } catch (SQLException e) {
            System.out.println(" loi ############");
            return false;
        }
        return true;
    }

    void getInfo(String mshs) {
        String sql = "select * from HOCSINH where HOCSINH.MaHocSinh='" + mshs + "'";
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            while (r.next()) {
//          this.nameBox.setText(r.getString("HoTen"));
//         this.dateBox.setText(r.getString("NgaySinh"));
//         this.emailBox.setText(r.getString("Email"));
//         this.addressBox.setText(r.getString("DiaChi"));

                String s = r.getString("GioiTinh");
                if (s == "0") {
//            this.rbNam2.setSelected(true);
//            this.rbNu2.setSelected(false);
                } else {
//           this.rbNam2.setSelected(false);
//            this.rbNu2.setSelected(true);
                }

            }
        } catch (SQLException e) {

        }

    }

    void seek(String c, String y) {

        String sql = "select DISTINCT  HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh  from HOCSINH  , QUATRINHHOC,HOCKI_NAM ,LOP WHERE HOCSINH.MaHocSinh=QUATRINHHOC.MaHocSinh AND QUATRINHHOC.MaHocKi=HOCKI_NAM.MaHocKi  AND QUATRINHHOC.MaLop=LOP.MaLop AND LOP.MaLop='" + c + "' AND HOCKI_NAM.Nam=" + y;
        try {
            Connection cn = JDBCConnection.ketNoiJBDC();
            Statement sta = cn.createStatement();
            ResultSet r = sta.executeQuery(sql);
            int i = 0;
            while (r.next()) {
                i++;
                String name = r.getString("HoTen");
                String date = r.getString("NgaySinh");
                String mahs = r.getString("MaHocSinh");
                System.out.println(mahs);
                String email = r.getString("Email");
                String address = r.getString("DiaChi");
                String s = r.getString("GioiTinh");
                String datab[] = {Integer.toString(i), mahs, name, s, date, address};
                DefaultTableModel tblM = (DefaultTableModel) this.infoTable.getModel();
                tblM.addRow(datab);
            }
        } catch (SQLException e) {

        }

    }

    void loadContentYearList(int curYear) {
        this.yearList.addItem(Integer.toString(curYear));
        this.yearList.addItem(Integer.toString(curYear - 1));
        this.yearList.addItem(Integer.toString(curYear - 2));

    }

    public QuanLyLopPanel() {
        initComponents();
        Date d = new Date();
        System.out.println("ọ");
        this.createClassList();
        this.createYearList();
        this.loadContentYearList(d.getYear() + 1900);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoTable1 = new javax.swing.JTable();
        clasBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        seekButton1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clasList = new javax.swing.JComboBox<>();
        validateSiSo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        yearList = new javax.swing.JComboBox<>();
        seekButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(250, 224, 216));

        jLabel5.setText("QUẢN LÝ LỚP");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addContainerGap(976, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, -1));

        jLabel3.setText("Lớp");

        infoTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MSSV", "Họ tên", "Lớp", "TBHK1", "TBHK2"
            }
        ));
        jScrollPane2.setViewportView(infoTable1);

        clasBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));

        jLabel4.setText("Năm:");

        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));

        seekButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        seekButton1.setText("Tìm kiếm");
        seekButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButton1ActionPerformed(evt);
            }
        });

        jButton1.setText("Điểm số");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(seekButton1)
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)))
                .addGap(216, 216, 216))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seekButton1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jTabbedPane1.addTab("Tra cứu điểm", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập lớp cần tìm"));

        jLabel1.setText("Lớp:");

        clasList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));
        clasList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasListActionPerformed(evt);
            }
        });

        jLabel2.setText("Năm học:");

        yearList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn" }));
        yearList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearListActionPerformed(evt);
            }
        });

        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/search.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/diskette.png"))); // NOI18N
        saveButton.setText("Lưu");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/plus.png"))); // NOI18N
        addButton.setText("Thêm mới");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("Thêm học sinh vào danh sách. (Thêm bằng MSSV)");

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/16x16/remove.png"))); // NOI18N
        deleteButton.setText("Xóa");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Xóa học sinh trong danh sách");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách lớp"));

        infoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã học sinh", "Họ tên", "Giới tính", "Năm sinh", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(saveButton)
                                .addGap(18, 18, 18)
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(266, 266, 266)
                                .addComponent(deleteButton)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 279, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(seekButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seekButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(saveButton)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý lớp", jPanel1);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
    private void saveTABLE(java.awt.event.ActionEvent evt) {

    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (this.checkhaveSelected()) {
            return;
        }
        addstudent = new ThemHocSinh_DanhSachHocSinhPanel(this);
        addstudent.loadtable();
        this.addstudent.setVisible(true);


    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (this.checkhaveSelected()) {
            return;
        }
        if (this.checkhaveSelected()) {
            return;
        }
        if (this.mshsChosen == "") {
            return;
        }
        int index = this.infoTable.getSelectedRow();
        TableModel md = this.infoTable.getModel();
        this.deletequery.add("update QUATRINHHOC set MaLop=null where MaHocSinh='" + md.getValueAt(index, 1).toString() + "'");
        int numRows = infoTable.getSelectedRows().length;
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        for (int i = 0; i < numRows; i++) {

            model.removeRow(infoTable.getSelectedRow());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void seekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekButtonActionPerformed
        // TODO add your handling code here:
        if (this.checkhaveSelected()) {
            return;
        }
        this.mshsChosen = "";
        this.deletequery = new ArrayList<>();
        this.procquery = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        model.setRowCount(0);
        System.out.println((String) this.yearList.getSelectedItem());
        this.selectedClas = (String) this.clasList.getSelectedItem();
        this.selectedYear = (String) this.yearList.getSelectedItem();
        seek((String) this.clasList.getSelectedItem(), (String) this.yearList.getSelectedItem());
    }//GEN-LAST:event_seekButtonActionPerformed

    private void infoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_infoTableMouseClicked
        // TODO add your handling code here:
        int index = this.infoTable.getSelectedRow();
        TableModel md = this.infoTable.getModel();
        this.getInfo(this.mshsChosen = md.getValueAt(index, 0).toString());

    }//GEN-LAST:event_infoTableMouseClicked

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        for (var i : deletequery) {
            System.out.println(i);

        }
        if (this.checkhaveSelected()) {
            return;
        }
        if (this.deletequery.isEmpty() && this.procquery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chua co su thay doi nao ");
            return;
        }
        this.modifyDB();
        this.deletequery = new ArrayList<>();
        this.procquery = new ArrayList<>();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void clasListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clasListActionPerformed
        // TODO add your handling code here:
        this.selectedClas = this.clasList.getSelectedItem().toString();
        System.out.println("DanhSachLop_ lop combobox:   " + this.selectedClas);

    }//GEN-LAST:event_clasListActionPerformed

    private void yearListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearListActionPerformed
        // TODO add your handling code here:
        this.selectedYear = this.yearList.getSelectedItem().toString();
        System.out.println("DanhSachLop_ NAM combobox:   " + this.selectedYear);
    }//GEN-LAST:event_yearListActionPerformed

    private void seekButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekButton1ActionPerformed
        // TODO add your handling code here:
                if(this.clasBox.getSelectedItem().toString()=="Chọn"||this.yearBox.getSelectedItem().toString()=="Chọn")
                {
                            JOptionPane.showMessageDialog(this, "Bạn chưa chọn lớp hoặc năm ");
                            return;
                }
        DefaultTableModel model = (DefaultTableModel) this.infoTable1.getModel();
        model.setRowCount(0);
        loadtable();
    }//GEN-LAST:event_seekButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!(this.infoTable1.getSelectedColumn() != -1)) {

            JOptionPane.showMessageDialog(this, "Chưa chọn học sinh để xem điểm");
            return;
        }
        int index = this.infoTable1.getSelectedRow();
        TableModel md = this.infoTable1.getModel();
        String mshs = md.getValueAt(index, 1).toString();

        this.diemhs = new DiemHocSinh_DanhSachLopPanel(this.clasBox.getSelectedItem().toString(), this.yearBox.getSelectedItem().toString(), mshs);
        // this.getInfo(this.mshsChosen=md.getValueAt(index, 0).toString());
        this.diemhs.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> clasBox;
    private javax.swing.JComboBox<String> clasList;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTable infoTable;
    private javax.swing.JTable infoTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton seekButton;
    private javax.swing.JButton seekButton1;
    private javax.swing.JLabel validateSiSo;
    private javax.swing.JComboBox<String> yearBox;
    private javax.swing.JComboBox<String> yearList;
    // End of variables declaration//GEN-END:variables
}
