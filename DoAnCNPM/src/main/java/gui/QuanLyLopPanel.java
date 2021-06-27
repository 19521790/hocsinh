/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Font;
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
    public String selectedClas = "";
    public String selectedYear = "";
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
                String arr[] = {Integer.toString(i), r.getString("MaHocSinh"), r.getString("HoTen"), r.getString("TenLop"), r.getString("TBHK1"), r.getString("TBHK2")};
                DefaultTableModel tblM = (DefaultTableModel) this.infoTable1.getModel();
                tblM.addRow(arr);
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

                this.clasBox.addItem(r.getString("TenLop"));
                this.clasList.addItem(r.getString("TenLop"));
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
            JOptionPane.showMessageDialog(this, "Chưa chọn lớp và năm học");
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
            int i=0;
            while (r.next()) {
                i++;
                String name = r.getString("HoTen");
                String date = r.getString("NgaySinh");
                String mahs = r.getString("MaHocSinh");
                System.out.println(mahs);
                String email = r.getString("Email");
                String address = r.getString("DiaChi");
                String s = r.getString("GioiTinh");
                String datab[] = {Integer.toString(i),mahs, name, s, date, address};
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

        String sql = "select DISTINCT  HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh from HOCSINH  , QUATRINHHOC,HOCKI_NAM ,LOP WHERE HOCSINH.IDHocSinh=QUATRINHHOC.IDHocSinh AND QUATRINHHOC.IDHocKi=HOCKI_NAM.IDHocKi  AND QUATRINHHOC.IDLop=LOP.IDLop AND LOP.TenLop='"+c+"' AND HOCKI_NAM.Nam="+y+" ORDER BY MaHocSinh ASC";
         System.out.println(sql);
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
    public void  refreshSTT(){
    
    int sohang= infoTable.getColumnCount();
    for(int i=0; i<sohang;i++){
        this.infoTable.setValueAt(Integer.toString(i+1), i, 0);
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
        loadtable();
       // this.loadContentYearList(d.getYear() + 1900);

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
        jLabel1 = new javax.swing.JLabel();
        clasList = new javax.swing.JComboBox<>();
        yearList = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        seekButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoTable = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        validateSiSo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        clasBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        seekButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(239, 247, 248));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setForeground(new java.awt.Color(128, 99, 246));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(239, 247, 248));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Lớp:");

        clasList.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        clasList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasListActionPerformed(evt);
            }
        });

        yearList.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        yearList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearListActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Năm học:");

        seekButton.setBackground(new java.awt.Color(0, 176, 239));
        seekButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seekButton.setForeground(new java.awt.Color(255, 255, 255));
        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_search_24px_1.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.setBorder(null);
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
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(seekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clasList, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(seekButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearList, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        infoTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoTable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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
        infoTable.setFocusable(false);
        infoTable.setGridColor(new java.awt.Color(255, 255, 255));
        infoTable.setIntercellSpacing(new java.awt.Dimension(0, 1));
        infoTable.setRowHeight(25);
        infoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(infoTable);

        addButton.setBackground(new java.awt.Color(128, 99, 246));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_administrator_24px.png"))); // NOI18N
        addButton.setText("Thêm mới");
        addButton.setBorder(null);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 176, 239));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_upload_to_cloud_24px_1.png"))); // NOI18N
        saveButton.setText("Cập nhật");
        saveButton.setBorder(null);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(237, 98, 96));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_delete_24px_1.png"))); // NOI18N
        deleteButton.setText("Xóa");
        deleteButton.setBorder(null);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nhập thông tin lớp");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Danh sách học sinh ");

        jButton5.setBackground(new java.awt.Color(128, 99, 246));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton5.setText("In");
        jButton5.setBorder(null);
        jButton5.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(35, 987, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(validateSiSo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản lý lớp", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 247, 248));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        infoTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        infoTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MSSV", "Họ tên", "Lớp", "TBHK1", "TBHK2"
            }
        ));
        infoTable1.setIntercellSpacing(new java.awt.Dimension(0, 1));
        infoTable1.setOpaque(false);
        infoTable1.setRowHeight(25);
        infoTable1.setSelectionBackground(new java.awt.Color(0, 176, 239));
        jScrollPane2.setViewportView(infoTable1);

        jButton1.setBackground(new java.awt.Color(254, 193, 6));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_scorecard_24px.png"))); // NOI18N
        jButton1.setText("Chi tiết");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(165, 143, 241));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(165, 143, 241));
        jLabel5.setText("Coi chi tiết điểm học sinh");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Lớp");

        clasBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        clasBox.setBorder(null);
        clasBox.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Năm:");

        yearBox.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        yearBox.setBorder(null);
        yearBox.setFocusable(false);

        seekButton1.setBackground(new java.awt.Color(0, 176, 239));
        seekButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seekButton1.setForeground(new java.awt.Color(255, 255, 255));
        seekButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_search_24px_1.png"))); // NOI18N
        seekButton1.setText("Tìm kiếm");
        seekButton1.setBorder(null);
        seekButton1.setFocusPainted(false);
        seekButton1.setFocusable(false);
        seekButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(seekButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clasBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seekButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Danh sách học sinh");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nhập thông tin lớp cần tra cứu");

        jButton6.setBackground(new java.awt.Color(128, 99, 246));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton6.setText("In");
        jButton6.setBorder(null);
        jButton6.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPane1.addTab("Tra cứu điểm", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1150, 700));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(128, 99, 246));
        jLabel6.setText("QUẢN LÝ LỚP");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));
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
        int index[] = this.infoTable.getSelectedRows();
        TableModel md = this.infoTable.getModel();
         for(int i=0;i<index.length;i++){
        this.deletequery.add("update QUATRINHHOC set IDLop =  NULL from HOCSINH where QUATRINHHOC.IDHocSinh =HOCSINH.IDHocSinh and HOCSINH.MaHocSinh = '" + md.getValueAt(index[i], 1).toString() + "'");
         }
        int numRows = infoTable.getSelectedRows().length;
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        for (int i = 0; i < numRows; i++) {

            model.removeRow(infoTable.getSelectedRow());
        }
        refreshSTT();
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
          if(this.infoTable.getRowCount()<1){
                    JOptionPane.showMessageDialog(this, "KHÔNG TÌM THẤY THÔNG TIN");

        }

        
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
            JOptionPane.showMessageDialog(this, "Chưa có sự thay đổi nào");
            return;
        }
        this.modifyDB();
        this.deletequery = new ArrayList<>();
        this.procquery = new ArrayList<>();
        JOptionPane.showMessageDialog(this, "Lưu thành công");
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
           if(this.infoTable1.getRowCount()<1){
                    JOptionPane.showMessageDialog(this, "KHÔNG TÌM THẤY THÔNG TIN");

        }
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
