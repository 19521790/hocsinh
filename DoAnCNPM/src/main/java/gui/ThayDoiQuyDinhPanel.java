/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.JDBCConnection;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import popupframe.ThemLop_ThayDoiQuyDinhPanel;
import thamso.LayThamSo;

/**
 *
 * @author Admin
 */
public class ThayDoiQuyDinhPanel extends javax.swing.JPanel {

    ThemLop_ThayDoiQuyDinhPanel themLop = new ThemLop_ThayDoiQuyDinhPanel();
    DefaultTableModel duLieuLop = new DefaultTableModel();
    DefaultTableModel duLieuMon = new DefaultTableModel();
    DefaultTableModel duLieuNam = new DefaultTableModel();

    public ThayDoiQuyDinhPanel() {
        initComponents();
        txtTuoiToiDa.setText(String.valueOf(new LayThamSo().getTuoiToiDa()));
        txtTuoiToiThieu.setText(String.valueOf(new LayThamSo().getTuoiToiThieu()));
        txtSiso.setText(String.valueOf(new LayThamSo().getSiSoToiDa()));
        txtDiemLenLop.setText(String.valueOf(new LayThamSo().getDiemLenLop()));
        txtDiemQuaMon.setText(String.valueOf(new LayThamSo().getDiemQuaMon()));
        //Tạo column cho bảng dữ liệu

        String[] colTitle = {"Mã Lớp", "Lớp", "Khối"};
        boolean[] isEditable = {false, true, false};
        duLieuLop = new DefaultTableModel(colTitle, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return isEditable[column];
            }
        };
        String[] colTitle2 = {"Mã môn", "Môn"};
        boolean[] isEditable2 = {false, true};
        duLieuMon = new DefaultTableModel(colTitle2, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return isEditable2[column];
            }
        };

        //cho can them
        String[] colTitle3 = {"STT", "Năm"};
        boolean[] isEditable3 = {false, false};
        duLieuNam = new DefaultTableModel(colTitle3, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return isEditable3[column];
            }
        };

        Connection con = JDBCConnection.ketNoiJBDC();
        String sql;
        try {
            Statement statement = con.createStatement();
            //Thêm dữ liệu vào bảng lớp
            sql = "select * from LOP";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString("MaLop");
                String khoi = rs.getString("MaKhoi");
                String lop = rs.getString("TenLop");
                Object data[] = {ma, lop, khoi};
                duLieuLop.addRow(data);
            }
            tbLop.setModel(duLieuLop);

            //Thêm dữ liệu vào bảng môn
            sql = "select * from MONHOC";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString("MaMonHoc");
                String monHoc = rs.getString("TenMonHoc");
                Object data[] = {ma, monHoc};
                duLieuMon.addRow(data);
            }
            tbMonHoc.setModel(duLieuMon);

            //Thêm dữ liệu vào bảng môn
            int stt = 1;
            sql = "select distinct Nam from HOCKI_NAM order by Nam";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String nam = rs.getString("Nam");
                Object data[] = {stt, nam};
                duLieuNam.addRow(data);
                stt++;
            }
            tbNam.setModel(duLieuNam);
        } catch (SQLException ex) {
            Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ThemLopTrongTruongListen() {
        int khoi = Integer.parseInt(JOptionPane.showInputDialog("Nhập khối"));

        if (!(khoi == 10 || khoi == 11 || khoi == 12)) {
            JOptionPane.showMessageDialog(null, "Điền sai. Khối bao gồm 10, 11, 12.");
        } else {
            String lop = String.valueOf(JOptionPane.showInputDialog("Nhập tên lớp"));
            String khoi_string = "K" + String.valueOf(khoi);
            Object data[] = {"", lop, khoi_string};
            Connection con = JDBCConnection.ketNoiJBDC();

            CallableStatement callStatement;
            try {
                callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_ThemLop(?,?)}");
                callStatement.setString(1, khoi_string);
                callStatement.setString(2, lop);
                callStatement.execute();
                duLieuLop.addRow(data);
            } catch (SQLException ex) {
                Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void XoaLopTrongTruongListen() {
        if (tbLop.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng dữ liệu");
        }
        if (tbLop.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được xóa 1 dòng dữ liệu mỗi lần");
        }
        if (tbLop.getSelectedRowCount() == 1) {
            Connection con = JDBCConnection.ketNoiJBDC();
            try {
                String lop = duLieuLop.getValueAt(tbLop.getSelectedRow(), 1).toString();
                //Kiểm tra thông tin môn học
                Statement statement = con.createStatement();
                String sql = "select * from LOP l, QUATRINHHOC qth\n"
                        + "where l.IDLop = qth.IDLop\n"
                        + "and TenLOP = N'" + lop + "'";
                ResultSet rs = statement.executeQuery(sql);
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count != 0) {
                    int exit = JOptionPane.showConfirmDialog(null, "Lớp có dữ liệu, bạn vẫn muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
                    if (exit == JOptionPane.YES_OPTION) {
                        CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_XoaLop(?)}");
                        callStatement.setString(1, lop);
                        boolean check = callStatement.execute();
                        if (!check) {
                            JOptionPane.showMessageDialog(this, "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa thất bại");
                        }
                        duLieuLop.removeRow(tbLop.getSelectedRow());
                    }
                } else {
                    CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_XoaLop(?)}");
                    callStatement.setString(1, lop);
                    boolean check = callStatement.execute();
                    if (!check) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại");
                    }
                    duLieuLop.removeRow(tbLop.getSelectedRow());
                }

            } catch (SQLException ex) {
                Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void XoaMonHocListen() {
        if (tbMonHoc.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng dữ liệu");
        }
        if (tbMonHoc.getSelectedRowCount() > 1) {
            JOptionPane.showMessageDialog(this, "Chỉ được xóa 1 dòng dữ liệu mỗi lần");
        }
        if (tbMonHoc.getSelectedRowCount() == 1) {
            Connection con = JDBCConnection.ketNoiJBDC();
            try {
                String monHoc = duLieuMon.getValueAt(tbMonHoc.getSelectedRow(), 1).toString();
                //Kiểm tra thông tin môn học
                Statement statement = con.createStatement();
                String sql = "select * from MONHOC mh, BANGDIEMMON bdm\n"
                        + "where mh.IDMonHoc = bdm.IDMonHoc\n"
                        + "and TenMonHoc = N'" + monHoc + "'";
                ResultSet rs = statement.executeQuery(sql);
                int count = 0;
                while (rs.next()) {
                    count++;
                }
                if (count != 0) {
                    int exit = JOptionPane.showConfirmDialog(null, "Môn học có dữ liệu, bạn vẫn muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
                    if (exit == JOptionPane.YES_OPTION) {
                        CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_XoaMon(?)}");
                        callStatement.setString(1, monHoc);
                        boolean check = callStatement.execute();
                        if (!check) {
                            JOptionPane.showMessageDialog(this, "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Xóa thất bại");
                        }
                        duLieuMon.removeRow(tbMonHoc.getSelectedRow());
                    }
                } else {
                    CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_XoaMon(?)}");
                    callStatement.setString(1, monHoc);
                    boolean check = callStatement.execute();
                    if (!check) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại");
                    }
                    duLieuMon.removeRow(tbMonHoc.getSelectedRow());
                }

            } catch (SQLException ex) {
                Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ThemMonHocListen() {
        String mon = JOptionPane.showInputDialog("Nhập tên môn học");
        int count = duLieuMon.getRowCount();
        count++;
        Object data[] = {count, mon};
        duLieuMon.addRow(data);
        Connection con = JDBCConnection.ketNoiJBDC();
        try {
            CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_ThemMonHoc(?)}");
            callStatement.setString(1, mon);
            boolean check = callStatement.execute();
            if (check) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ThemNamHocListen() {
        Connection con = JDBCConnection.ketNoiJBDC();
        int nam = Integer.parseInt((String) duLieuNam.getValueAt(tbNam.getRowCount() - 1, 1)) + 1;
        try {
            int stt = Integer.parseInt(String.valueOf(duLieuNam.getValueAt(tbNam.getRowCount()-1, 0))) + 1;
            Object data[] = {stt,nam};
            CallableStatement callStatement = con.prepareCall("{call sp_ThayDoiQuyDinh_ThemNam(?)}");
            callStatement.setInt(1, nam);
            boolean check = callStatement.execute();
            if (!check) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                duLieuNam.addRow(data);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void CapNhatAction() {
        Connection con = JDBCConnection.ketNoiJBDC();

        try {
            Statement statement = con.createStatement();
            String sql = "update THAMSO set GiaTri = " + Integer.parseInt(txtTuoiToiThieu.getText()) + "where TenThamSo = 'TuoiToiThieu'";
            statement.execute(sql);
            sql = "update THAMSO set GiaTri = " + Integer.parseInt(txtTuoiToiDa.getText()) + "where TenThamSo = 'TuoiToiDa'";
            statement.execute(sql);
            sql = "update THAMSO set GiaTri = " + Integer.parseInt(txtSiso.getText()) + "where TenThamSo = 'SiSoToiDa'";
            statement.execute(sql);
            sql = "update THAMSO set GiaTri = " + Float.parseFloat(txtDiemLenLop.getText()) + "where TenThamSo = 'DiemLenLop'";
            statement.execute(sql);
            sql = "update THAMSO set GiaTri = " + Float.parseFloat(txtDiemQuaMon.getText()) + "where TenThamSo = 'DiemQuaMon'";
            statement.execute(sql);

            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

        } catch (SQLException ex) {
            Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LayThamSo().ketNoiCoSoDulieu();
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
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbLop = new javax.swing.JTable();
        XoaLop = new javax.swing.JButton();
        ThemLop = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtDiemQuaMon = new javax.swing.JTextField();
        txtDiemLenLop = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSiso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTuoiToiThieu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTuoiToiDa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CapNhat = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMonHoc = new javax.swing.JTable();
        ThemMonHoc = new javax.swing.JButton();
        XoaMonHoc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbNam = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        ThemNamHoc = new javax.swing.JButton();

        setBackground(new java.awt.Color(239, 247, 248));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Thay đổi lớp trong trường");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, -1));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbLop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Lớp", "Lớp", "Khối"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLop.setFocusable(false);
        tbLop.setGridColor(new java.awt.Color(255, 255, 255));
        tbLop.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tbLop.setRowHeight(25);
        tbLop.setSelectionBackground(new java.awt.Color(0, 176, 239));
        tbLop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbLopMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbLop);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 280, 250));

        XoaLop.setBackground(new java.awt.Color(237, 98, 96));
        XoaLop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        XoaLop.setForeground(new java.awt.Color(255, 255, 255));
        XoaLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_delete_24px_1.png"))); // NOI18N
        XoaLop.setText("Xóa");
        XoaLop.setBorder(null);
        XoaLop.setFocusable(false);
        XoaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaLopActionPerformed(evt);
            }
        });
        jPanel1.add(XoaLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 100, 30));

        ThemLop.setBackground(new java.awt.Color(128, 99, 246));
        ThemLop.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ThemLop.setForeground(new java.awt.Color(255, 255, 255));
        ThemLop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_24px.png"))); // NOI18N
        ThemLop.setText("Thêm");
        ThemLop.setBorder(null);
        ThemLop.setFocusable(false);
        ThemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemLopActionPerformed(evt);
            }
        });
        jPanel1.add(ThemLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 100, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Điểm qua môn ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 212, -1, -1));

        txtDiemQuaMon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiemQuaMon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiemQuaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemQuaMonActionPerformed(evt);
            }
        });
        txtDiemQuaMon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiemQuaMonKeyTyped(evt);
            }
        });
        jPanel2.add(txtDiemQuaMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 210, 45, 20));

        txtDiemLenLop.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiemLenLop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiemLenLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemLenLopActionPerformed(evt);
            }
        });
        txtDiemLenLop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiemLenLopKeyTyped(evt);
            }
        });
        jPanel2.add(txtDiemLenLop, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 45, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Điểm lên lớp");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 212, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Sĩ số tối đa");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 126, 73, -1));

        txtSiso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSiso.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSisoKeyTyped(evt);
            }
        });
        jPanel2.add(txtSiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 126, 45, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tuổi tối thiểu");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 45, -1, -1));

        txtTuoiToiThieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTuoiToiThieu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTuoiToiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuoiToiThieuActionPerformed(evt);
            }
        });
        txtTuoiToiThieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTuoiToiThieuKeyTyped(evt);
            }
        });
        jPanel2.add(txtTuoiToiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 45, 45, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tuổi tối đa");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 45, 76, -1));

        txtTuoiToiDa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTuoiToiDa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTuoiToiDa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTuoiToiDaKeyTyped(evt);
            }
        });
        jPanel2.add(txtTuoiToiDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 45, 45, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thay đổi điểm qua môn/ điểm lên lớp");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 174, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Thay đổi sĩ số tối đa của lớp");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 83, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thay đổi độ tuổi đi học của học sinh");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 12, -1, -1));

        CapNhat.setBackground(new java.awt.Color(0, 176, 239));
        CapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CapNhat.setForeground(new java.awt.Color(255, 255, 255));
        CapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_upload_to_cloud_24px_1.png"))); // NOI18N
        CapNhat.setText("Cập nhật");
        CapNhat.setBorder(null);
        CapNhat.setFocusable(false);
        CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatActionPerformed(evt);
            }
        });
        jPanel2.add(CapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 116, 130, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("THAY ĐỔI QUY ĐỊNH");
        jLabel5.setFocusable(false);

        jButton2.setBackground(new java.awt.Color(114, 217, 118));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_refresh_24px.png"))); // NOI18N
        jButton2.setText("Làm mới");
        jButton2.setBorder(null);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Thay đổi môn học");

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã môn", "Môn học"
            }
        ));
        tbMonHoc.setFocusable(false);
        tbMonHoc.setGridColor(new java.awt.Color(255, 255, 255));
        tbMonHoc.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tbMonHoc.setRowHeight(25);
        tbMonHoc.setSelectionBackground(new java.awt.Color(0, 176, 239));
        tbMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbMonHocMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tbMonHoc);

        ThemMonHoc.setBackground(new java.awt.Color(128, 99, 246));
        ThemMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ThemMonHoc.setForeground(new java.awt.Color(255, 255, 255));
        ThemMonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_24px.png"))); // NOI18N
        ThemMonHoc.setText("Thêm");
        ThemMonHoc.setBorder(null);
        ThemMonHoc.setFocusable(false);
        ThemMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMonHocActionPerformed(evt);
            }
        });

        XoaMonHoc.setBackground(new java.awt.Color(237, 98, 96));
        XoaMonHoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        XoaMonHoc.setForeground(new java.awt.Color(255, 255, 255));
        XoaMonHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_delete_24px_1.png"))); // NOI18N
        XoaMonHoc.setText("Xóa");
        XoaMonHoc.setBorder(null);
        XoaMonHoc.setFocusable(false);
        XoaMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaMonHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(ThemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(XoaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThemMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XoaMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbNam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Năm học"
            }
        ));
        tbNam.setFocusable(false);
        tbNam.setGridColor(new java.awt.Color(255, 255, 255));
        tbNam.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tbNam.setRowHeight(25);
        tbNam.setSelectionBackground(new java.awt.Color(0, 176, 239));
        jScrollPane6.setViewportView(tbNam);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Thay đổi năm học");

        ThemNamHoc.setBackground(new java.awt.Color(128, 99, 246));
        ThemNamHoc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ThemNamHoc.setForeground(new java.awt.Color(255, 255, 255));
        ThemNamHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/poly/app/icons/icons8_add_24px.png"))); // NOI18N
        ThemNamHoc.setText("Thêm");
        ThemNamHoc.setBorder(null);
        ThemNamHoc.setFocusable(false);
        ThemNamHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemNamHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThemNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(ThemNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiemLenLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemLenLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemLenLopActionPerformed

    private void txtDiemQuaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemQuaMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemQuaMonActionPerformed

    private void txtTuoiToiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuoiToiThieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuoiToiThieuActionPerformed

    private void XoaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaLopActionPerformed

    }//GEN-LAST:event_XoaLopActionPerformed

    private void CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatActionPerformed


    }//GEN-LAST:event_CapNhatActionPerformed

    private void txtTuoiToiThieuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuoiToiThieuKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTuoiToiThieuKeyTyped

    private void txtTuoiToiDaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuoiToiDaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTuoiToiDaKeyTyped

    private void txtSisoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSisoKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSisoKeyTyped

    private void txtDiemQuaMonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiemQuaMonKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDiemQuaMonKeyTyped

    private void txtDiemLenLopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiemLenLopKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDiemLenLopKeyTyped

    private void ThemMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMonHocActionPerformed

    }//GEN-LAST:event_ThemMonHocActionPerformed

    private void ThemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemLopActionPerformed

    }//GEN-LAST:event_ThemLopActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new LayThamSo().ketNoiCoSoDulieu();
        txtTuoiToiDa.setText(String.valueOf(new LayThamSo().getTuoiToiDa()));
        txtTuoiToiThieu.setText(String.valueOf(new LayThamSo().getTuoiToiThieu()));
        txtSiso.setText(String.valueOf(new LayThamSo().getSiSoToiDa()));
        txtDiemLenLop.setText(String.valueOf(new LayThamSo().getDiemLenLop()));
        txtDiemQuaMon.setText(String.valueOf(new LayThamSo().getDiemQuaMon()));

        duLieuLop.setRowCount(0);
        duLieuMon.setRowCount(0);
        duLieuNam.setRowCount(0);

        Connection con = JDBCConnection.ketNoiJBDC();
        String sql;
        try {
            Statement statement = con.createStatement();
            //Thêm dữ liệu vào bảng lớp
            sql = "select * from LOP";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String ma = rs.getString("MaLop");
                String khoi = rs.getString("MaKhoi");
                String lop = rs.getString("TenLop");
                Object data[] = {ma, lop, khoi};
                duLieuLop.addRow(data);
            }
            tbLop.setModel(duLieuLop);

            //Thêm dữ liệu vào bảng môn
            sql = "select * from MONHOC";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString("MaMonHoc");
                String monHoc = rs.getString("TenMonHoc");
                Object data[] = {ma, monHoc};
                duLieuMon.addRow(data);
            }
            tbMonHoc.setModel(duLieuMon);

            //Thêm dữ liệu vào bảng môn
            int stt = 1;
            sql = "select distinct Nam from HOCKI_NAM order by Nam";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String nam = rs.getString("Nam");
                Object data[] = {stt, nam};
                duLieuNam.addRow(data);
                stt++;
            }
            tbNam.setModel(duLieuNam);
        } catch (SQLException ex) {
            Logger.getLogger(ThayDoiQuyDinhPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void XoaMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaMonHocActionPerformed

    }//GEN-LAST:event_XoaMonHocActionPerformed

    private void ThemNamHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemNamHocActionPerformed

    }//GEN-LAST:event_ThemNamHocActionPerformed
    String DiemBanDau = "";
    private void tbLopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLopMousePressed
        DiemBanDau = tbLop.getValueAt(tbLop.getSelectedRow(), tbLop.getSelectedColumn()).toString();
    }//GEN-LAST:event_tbLopMousePressed

    private void tbMonHocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMonHocMousePressed
        DiemBanDau = tbMonHoc.getValueAt(tbMonHoc.getSelectedRow(), tbMonHoc.getSelectedColumn()).toString();
    }//GEN-LAST:event_tbMonHocMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CapNhat;
    public javax.swing.JButton ThemLop;
    public javax.swing.JButton ThemMonHoc;
    public javax.swing.JButton ThemNamHoc;
    public javax.swing.JButton XoaLop;
    public javax.swing.JButton XoaMonHoc;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JTable tbLop;
    public javax.swing.JTable tbMonHoc;
    private javax.swing.JTable tbNam;
    private javax.swing.JTextField txtDiemLenLop;
    private javax.swing.JTextField txtDiemQuaMon;
    private javax.swing.JTextField txtSiso;
    private javax.swing.JTextField txtTuoiToiDa;
    private javax.swing.JTextField txtTuoiToiThieu;
    // End of variables declaration//GEN-END:variables
}
