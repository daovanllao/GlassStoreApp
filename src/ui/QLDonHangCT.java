/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import dao.DonHangChiTietDAO;
import entity.DonHangChiTiet;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import utils.JdbcHelper;
import utils.MsgBox;

/**
 *
 * @author WINDOWS
 */
public class QLDonHangCT extends javax.swing.JPanel {
    DonHangChiTietDAO dao = new DonHangChiTietDAO();
    int row = 0;
    
    /**
     * Creates new form QLDonHangCT
     */
    void getTenSP() {
        String tenSP = txtMaSP.getText();
        final String SELECT_tenSP = "SELECT SanPham.tenSP\n" +
                                    "FROM SanPham\n" +
                                    "INNER JOIN DonHangChiTiet ON SanPham.maSP = DonHangChiTiet.maSP\n" +
                                    "WHERE DonHangChiTiet.maSP = ?;";
        try {
            ResultSet rs = JdbcHelper.query(SELECT_tenSP, tenSP);
            if (rs.next()) {
                String tenSPValue = rs.getString("TenSP");
                txtTenSP.setText(tenSPValue);
            } else {
                txtTenSP.setText("???");
            }
            rs.close();
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    public QLDonHangCT() {
        initComponents();
        init();
    }
    void init() {
        fillTable();
        //setIconImage(XImage.getAppIcon());
    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblDonhangCT.getModel();
        model.setRowCount(0);
        
        try {
            List<DonHangChiTiet> list = dao.selectAll();
            
            for(DonHangChiTiet tk : list) {
                Object[] row = {
                    tk.getMaDHCT(),
                    tk.getMaSP(),
                    tk.getMaDH(),
                    tk.getSoLuong(),
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    
    
    void edit() {
        try {
            String maTK = (String)tblDonhangCT.getValueAt(this.row, 0);
            DonHangChiTiet model = dao.selectById(maTK);
            if(model != null)
            {
                setForm(model);
                updateStatus();
                getTenSP();
                //tabs.setSelectedIndex(0);
               
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    boolean validateInput() {
        String maNV = txtMaHDCT.getText().trim();
        String matKhau = txtMaSP.getText().trim();
        String hoTen = txtTenSP.getText().trim();
        String donGia = txtDonGia.getText().trim();
        String sdt = txtSoLuong.getText().trim();
        //String email = lblMaDH.getText().trim();

        if (maNV.isEmpty() || matKhau.isEmpty() || hoTen.isEmpty() || sdt.isEmpty() || donGia.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập đầy đủ thông tin!");
            return false;
        } 
        return true;
    }
    
    DonHangChiTiet getForm(){
        DonHangChiTiet DHCT = new DonHangChiTiet();
        DHCT.setMaDHCT(txtMaHDCT.getText());
        DHCT.setMaSP(txtMaSP.getText());
        DHCT.setMaDH(lblMaDH.getText());
        DHCT.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        return DHCT;
    }
    
    void setForm(DonHangChiTiet model) {
        txtMaHDCT.setText(model.getMaDHCT());
        txtMaSP.setText(model.getMaSP());
        lblMaDH.setText(model.getMaDH());
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
    }
    
    void updateStatus() {
        boolean edit = this.row >= 0;
        boolean first = this.row == 0;
        boolean last = this.row == tblDonhangCT.getRowCount() - 1;
        txtMaHDCT.setEditable(!edit);
        //khi insert thì k update, delete
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);
    
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    
    void clear() {
        DonHangChiTiet nv = new DonHangChiTiet();
        this.setForm(nv);
        this.row = -1;
        this.updateStatus();
    }
    
    void update() {
        if (validateInput() == true) {
            DonHangChiTiet model = getForm();       
            try {
                dao.update(model);
                this.fillTable();
                MsgBox.alert(this, "Cập nhật thành công!");
                clear();
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
            }
        }
    }
    void delete() {
        if(MsgBox.confirm(this, "Bạn thực sự muốn xóa Tài Khoản! này?")) {
            String maTK = txtMaSP.getText();
            try {
                dao.delete(maTK);
                this.fillTable();
                this.clear();
                MsgBox.alert(this, "Xóa thành công!");
                clear();
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }
    
    void first() {
        row = 0;
        edit();
    }
    void prev() {
        if(row > 0) {
            row--;
            edit();
        }
    }
    void next() {
        if(row < tblDonhangCT.getRowCount() - 1) {
            row++;
            edit();
        }
    }
    void last() {
        row = tblDonhangCT.getRowCount() - 1;
        edit();
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
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblMaDH = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMaHDCT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonhangCT = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSua.setBackground(new java.awt.Color(255, 255, 153));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Notes.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 153));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Mã Đơn Hàng:");

        jLabel3.setText("Tên SP:");

        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã SP:");

        jLabel5.setText("Số Lượng:");

        lblMaDH.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblMaDH.setText("...");

        jLabel10.setText("Mã Đơn hàng CT:");

        jLabel6.setText("Đơn Giá:");

        tblDonhangCT.setBackground(new java.awt.Color(204, 255, 204));
        tblDonhangCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã DHCT", "Mã SP", "Mã Đơn Hàng", "Đơn Giá", "Số Lượng"
            }
        ));
        tblDonhangCT.setSelectionBackground(new java.awt.Color(0, 102, 255));
        jScrollPane1.setViewportView(tblDonhangCT);

        btnFirst.setBackground(new java.awt.Color(255, 255, 153));
        btnFirst.setText("<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(255, 255, 153));
        btnLast.setText(">>");

        btnPrev.setBackground(new java.awt.Color(255, 255, 153));
        btnPrev.setText("<");

        btnNext.setBackground(new java.awt.Color(255, 255, 153));
        btnNext.setText(">");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(167, Short.MAX_VALUE)
                        .addComponent(btnSua)
                        .addGap(34, 34, 34)
                        .addComponent(btnXoa)
                        .addGap(69, 69, 69))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaHDCT)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblMaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSP)
                                    .addComponent(txtMaSP)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtDonGia))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFirst)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext)
                .addGap(12, 12, 12)
                .addComponent(btnLast)
                .addGap(131, 131, 131))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblMaDH))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa)
                            .addComponent(btnSua))
                        .addGap(43, 43, 43))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFirstActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaDH;
    private javax.swing.JTable tblDonhangCT;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHDCT;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
