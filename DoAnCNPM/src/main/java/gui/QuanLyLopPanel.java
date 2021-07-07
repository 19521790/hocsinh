/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import popupframe.ThemHocSinh_QuanLyLopPanel;
import popupframe.DiemHocSinh_QuanLyLopPanel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import popupframe.ChuyenLop_QuanLyLopPanel;
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
    public String selectedList;
    String mshsChosen = "";
    ChuyenLop_QuanLyLopPanel chuyen;
    ThemHocSinh_QuanLyLopPanel addstudent;
    DiemHocSinh_QuanLyLopPanel diemhs;
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

    public List<String> getSelectedItemInfotable() {

        List<String> list = new ArrayList<String>();
        int index[] = this.infoTable.getSelectedRows();
        TableModel md = this.infoTable.getModel();
        for (int i = 0; i < index.length; i++) {
            // this.deletequery.add("update QUATRINHHOC set IDLop =  NULL from HOCSINH where QUATRINHHOC.IDHocSinh =HOCSINH.IDHocSinh and HOCSINH.MaHocSinh = '" + md.getValueAt(index[i], 1).toString() + "'");
            list.add(md.getValueAt(index[i], 1).toString());
        }
        return list;
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

    public void seek(String c, String y) {
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        model.setRowCount(0);
        String sql = "select DISTINCT  HOCSINH.HoTen ,HOCSINH.MaHocSinh, HOCSINH.Email, HOCSINH.GioiTinh,HOCSINH.DiaChi, HOCSINH.NgaySinh from HOCSINH  , QUATRINHHOC,HOCKI_NAM ,LOP WHERE HOCSINH.IDHocSinh=QUATRINHHOC.IDHocSinh AND QUATRINHHOC.IDHocKi=HOCKI_NAM.IDHocKi  AND QUATRINHHOC.IDLop=LOP.IDLop AND LOP.TenLop='" + c + "' AND HOCKI_NAM.Nam=" + y + " ORDER BY MaHocSinh ASC";
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

    public boolean checkMSHS(String mahs) {
        int sohang = infoTable.getRowCount();
        for (int i = 0; i < sohang; i++) {

            if (mahs.equals(this.infoTable.getValueAt(i, 1))) {
                return false;
            }
        }

        return true;
    }

    public void refreshSTT() {

        int sohang = infoTable.getRowCount();
        for (int i = 0; i < sohang; i++) {
            this.infoTable.setValueAt(Integer.toString(i + 1), i, 0);
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
        jButton2 = new javax.swing.JButton();
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

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(239, 247, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(650, 100));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Lớp:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 35, 31, -1));

        clasList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        clasList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasListActionPerformed(evt);
            }
        });
        jPanel5.add(clasList, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 35, 115, 30));

        yearList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        yearList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearListActionPerformed(evt);
            }
        });
        jPanel5.add(yearList, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 35, 115, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Năm học:");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 35, -1, -1));

        seekButton.setBackground(new java.awt.Color(0, 176, 239));
        seekButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        seekButton.setForeground(new java.awt.Color(255, 255, 255));
        seekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_search_24px_1.png"))); // NOI18N
        seekButton.setText("Tìm kiếm");
        seekButton.setBorder(null);
        seekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekButtonActionPerformed(evt);
            }
        });
        jPanel5.add(seekButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 35, 130, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        infoTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        infoTable.setSelectionBackground(new java.awt.Color(0, 176, 239));
        infoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                infoTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(infoTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 260, 910, 360));

        addButton.setBackground(new java.awt.Color(128, 99, 246));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_administrator_24px.png"))); // NOI18N
        addButton.setText("Thêm mới");
        addButton.setBorder(null);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(981, 259, 130, 40));

        saveButton.setBackground(new java.awt.Color(0, 176, 239));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_upload_to_cloud_24px_1.png"))); // NOI18N
        saveButton.setText("Cập nhật");
        saveButton.setBorder(null);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel1.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(981, 375, 130, 40));

        deleteButton.setBackground(new java.awt.Color(237, 98, 96));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_delete_24px_1.png"))); // NOI18N
        deleteButton.setText("Xóa");
        deleteButton.setBorder(null);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jPanel1.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(981, 317, 130, 40));
        jPanel1.add(validateSiSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 38, 179, 22));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nhập thông tin lớp");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Danh sách học sinh ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 230, -1, -1));

        jButton5.setBackground(new java.awt.Color(128, 99, 246));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton5.setText("In");
        jButton5.setBorder(null);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(981, 433, 130, 40));

        jButton2.setText("Chuyển lớp");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 200, 130, 40));

        jTabbedPane1.addTab("Quản lý lớp", jPanel1);

        jPanel2.setBackground(new java.awt.Color(239, 247, 248));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        infoTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        infoTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MSSV", "Họ tên", "Lớp", "TBHK1", "TBHK2"
            }
        ));
        infoTable1.setGridColor(new java.awt.Color(255, 255, 255));
        infoTable1.setIntercellSpacing(new java.awt.Dimension(0, 1));
        infoTable1.setOpaque(false);
        infoTable1.setRowHeight(25);
        infoTable1.setSelectionBackground(new java.awt.Color(0, 176, 239));
        jScrollPane2.setViewportView(infoTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 270, 934, 360));

        jButton1.setBackground(new java.awt.Color(254, 193, 6));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(993, 270, 130, 40));

        jLabel5.setBackground(new java.awt.Color(165, 143, 241));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(165, 143, 241));
        jLabel5.setText("Coi chi tiết điểm học sinh");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(978, 316, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(650, 100));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Lớp");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 35, -1, -1));

        clasBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        clasBox.setBorder(null);
        clasBox.setFocusable(false);
        jPanel3.add(clasBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 35, 115, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Năm học:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 35, -1, -1));

        yearBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        yearBox.setBorder(null);
        yearBox.setFocusable(false);
        jPanel3.add(yearBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 35, 115, 30));

        seekButton1.setBackground(new java.awt.Color(0, 176, 239));
        seekButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        jPanel3.add(seekButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 130, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 75, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Danh sách học sinh");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 239, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nhập thông tin lớp cần tra cứu");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 35, -1, -1));

        jButton6.setBackground(new java.awt.Color(128, 99, 246));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_print_24px.png"))); // NOI18N
        jButton6.setText("In");
        jButton6.setBorder(null);
        jButton6.setFocusable(false);
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(993, 350, 130, 40));

        jTabbedPane1.addTab("Tra cứu điểm", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1150, 700));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("QUẢN LÍ LỚP");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
    private void saveTABLE(java.awt.event.ActionEvent evt) {

    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (this.checkhaveSelected()) {
            return;
        }
        addstudent = new ThemHocSinh_QuanLyLopPanel(this);
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
        for (int i = 0; i < index.length; i++) {
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

        System.out.println((String) this.yearList.getSelectedItem());
        this.selectedClas = (String) this.clasList.getSelectedItem();
        this.selectedYear = (String) this.yearList.getSelectedItem();
        seek((String) this.clasList.getSelectedItem(), (String) this.yearList.getSelectedItem());
        if (this.infoTable.getRowCount() < 1) {
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
        if (this.clasBox.getSelectedItem().toString() == "Chọn" || this.yearBox.getSelectedItem().toString() == "Chọn") {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn lớp hoặc năm ");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) this.infoTable1.getModel();
        model.setRowCount(0);
        loadtable();
        if (this.infoTable1.getRowCount() < 1) {
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

        this.diemhs = new DiemHocSinh_QuanLyLopPanel(this.clasBox.getSelectedItem().toString(), this.yearBox.getSelectedItem().toString(), mshs);
        // this.getInfo(this.mshsChosen=md.getValueAt(index, 0).toString());
        this.diemhs.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (infoTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có thông tin gì để in");

        } else {

            try {

                infoTable.print(JTable.PrintMode.FIT_WIDTH, null, null);
            } catch (PrinterException ex) {
                Logger.getLogger(QuanLyDiemSoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (this.checkhaveSelected()) {
            return;
        }
        chuyen = new ChuyenLop_QuanLyLopPanel(this);
        if (this.infoTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chọn học sinh để chuyển");

        } else if (!chuyen.check) {
            JOptionPane.showMessageDialog(this, "đây là lớp duy nhất");
        } else {
            this.chuyen.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> clasBox;
    private javax.swing.JComboBox<String> clasList;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTable infoTable;
    private javax.swing.JTable infoTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
