/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Grade;
import model.Student;
import service.GradeService;
import service.StudentService;

/**
 *
 * @author tranv
 */
public class ManagePoin extends javax.swing.JFrame {

    /**
     * Creates new form ManagePoin
     */
    private StudentService studentService;
    private GradeService gradeService;
    private DefaultTableModel model;
    private List<Grade> lists = new ArrayList<>();

    public ManagePoin() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Quản lí điểm nhân viên");
        studentService = new StudentService();
        gradeService = new GradeService();
        txtTen.setText("");
        txtMaSv.setText("");
        txtDiemTB.setText("0.0");
        model = new DefaultTableModel();
        model = (DefaultTableModel) tblHienThi.getModel();
        fillTable();
    }

    public void fillTable() {
        lists = gradeService.selectAll();
        model.setRowCount(0);
        for (Grade list : lists) {
            model.addRow(new Object[]{
                list.getSinhVien().getMaSv(),
                list.getSinhVien().getHoTen(),
                list.getTiengAnh(),
                list.getTinHoc(),
                list.getGDTC(),
                list.diemTrungBinh(),});
        }
    }

    public Grade getDataForm() {
        Grade grade = new Grade();
        Student student = new Student();
        student.setMaSv(txtMaSv.getText());
        grade.setSinhVien(student);
        grade.setTiengAnh(Float.parseFloat(txtTiengAnh.getText()));
        grade.setTinHoc(Float.parseFloat(txtTinHoc.getText()));
        grade.setGDTC(Float.parseFloat(txtGiaoDucTC.getText()));
        return grade;
    }

    public boolean isValidate() {
        String tiengAnh = txtTiengAnh.getText();
        if (tiengAnh.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap diem tieng anh");
            return false;
        }

        String tinHoc = txtTinHoc.getText();
        if (tinHoc.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap diem tin hoc");
            return false;
        }

        String gdtc = txtGiaoDucTC.getText();
        if (gdtc.equals("")) {
            JOptionPane.showMessageDialog(this, "chua nhap diem giao duc the chat");
            return false;
        }

        try {
            double diemTiengAnh = Float.parseFloat(tiengAnh);
            double diemTinHoc = Float.parseFloat(tinHoc);
            double diemGdtc = Float.parseFloat(gdtc);

            if (diemTiengAnh < 0 || diemTiengAnh > 10
                    || diemTinHoc < 0 || diemTinHoc > 10
                    || diemGdtc < 0 || diemGdtc > 10) {
                JOptionPane.showMessageDialog(this, "Diem phai lon hon 0 va nho hon hoac bang 10");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Diem phai la so");
            return false;
        }
        return true;
    }

    public void clear() {
        txtTen.setText("");
        txtMaSv.setText("");
        txtTinHoc.setText("");
        txtTiengAnh.setText("");
        txtGiaoDucTC.setText("");
        txtDiemTB.setText("0.0");
    }

    public static int id;

    public void search() {
        if (txtMasvSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Can nhap ma sinh vien muon tim");
            return;
        } else {
            Grade grade = gradeService.selectById(txtMasvSearch.getText());
            if (grade == null) {
                Student student = studentService.selectById(txtMasvSearch.getText());
                if (student == null) {
                    JOptionPane.showMessageDialog(this, "Khong co sinh vien ma ma sinh vien nay");
                    return;
                } else {
                    JOptionPane.showMessageDialog(this, "Tim thay");

                    txtMaSv.setText(student.getMaSv());
                    txtTen.setText(student.getHoTen());
                    txtDiemTB.setText("");
                    txtTiengAnh.setText("");
                    txtTinHoc.setText("");
                    txtGiaoDucTC.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tim thay");
                id = grade.getId();
                txtMaSv.setText(grade.getSinhVien().getMaSv());
                txtTen.setText(grade.getSinhVien().getHoTen());
                txtTiengAnh.setText(grade.getTiengAnh() + "");
                txtTinHoc.setText(grade.getTinHoc() + "");
                txtGiaoDucTC.setText(grade.getGDTC() + "");
                txtDiemTB.setText(grade.diemTrungBinh() + "");
            }
        }
    }

    public void detail(int index) {
        Grade grade = lists.get(index);
        id = grade.getId();
        txtTen.setText(grade.getSinhVien().getHoTen());
        txtMaSv.setText(grade.getSinhVien().getMaSv());
        txtTiengAnh.setText(grade.getTiengAnh() + "");
        txtTinHoc.setText(grade.getTinHoc() + "");
        txtGiaoDucTC.setText(grade.getGDTC() + "");
        txtDiemTB.setText(grade.diemTrungBinh() + "");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Container = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMasvSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        information = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTiengAnh = new javax.swing.JTextField();
        txtTinHoc = new javax.swing.JTextField();
        txtGiaoDucTC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiemTB = new javax.swing.JLabel();
        txtTen = new javax.swing.JLabel();
        txtMaSv = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lí Điểm Sinh Viên");

        Search.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/id.png"))); // NOI18N
        jLabel2.setText("Mã Sv");

        txtMasvSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMasvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasvSearchActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchLayout = new javax.swing.GroupLayout(Search);
        Search.setLayout(SearchLayout);
        SearchLayout.setHorizontalGroup(
            SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel2)
                .addGap(49, 49, 49)
                .addComponent(txtMasvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        SearchLayout.setVerticalGroup(
            SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(SearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnSearch)
                    .addComponent(txtMasvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        SearchLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSearch, jLabel2, txtMasvSearch});

        information.setBorder(javax.swing.BorderFactory.createTitledBorder("Information"));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N
        jLabel3.setText("Họ Và Tên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/id.png"))); // NOI18N
        jLabel4.setText("Mã");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/eng.png"))); // NOI18N
        jLabel5.setText("Tiếng Anh");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/technical-support.png"))); // NOI18N
        jLabel6.setText("Tin Học");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giáo Dục TC");

        txtTiengAnh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTinHoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtGiaoDucTC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("Điểm TB");

        txtDiemTB.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtDiemTB.setForeground(new java.awt.Color(51, 51, 255));
        txtDiemTB.setText("0.0");

        txtTen.setText("jLabel10");

        txtMaSv.setText("jLabel11");

        javax.swing.GroupLayout informationLayout = new javax.swing.GroupLayout(information);
        information.setLayout(informationLayout);
        informationLayout.setHorizontalGroup(
            informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informationLayout.createSequentialGroup()
                        .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTiengAnh)
                            .addComponent(txtTinHoc)
                            .addComponent(txtGiaoDucTC, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(txtMaSv))
                        .addGap(40, 40, 40)
                        .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiemTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        informationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        informationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtGiaoDucTC, txtMaSv, txtTen, txtTiengAnh, txtTinHoc});

        informationLayout.setVerticalGroup(
            informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen))
                .addGap(18, 18, 18)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSv))
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informationLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTiengAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(informationLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtDiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTinHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(informationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaoDucTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        informationLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, txtGiaoDucTC, txtMaSv, txtTen, txtTiengAnh, txtTinHoc});

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("3 sinh viên có điểm cao nhất");

        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SV", "Họ Tên", "Tiếng Anh", "Tin Học", "GDTC", "Điểm TB"
            }
        ));
        tblHienThi.setRowHeight(30);
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHienThi);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Left.png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ContainerLayout = new javax.swing.GroupLayout(Container);
        Container.setLayout(ContainerLayout);
        ContainerLayout.setHorizontalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ContainerLayout.createSequentialGroup()
                .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContainerLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ContainerLayout.createSequentialGroup()
                                .addComponent(information, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(ContainerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        ContainerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnDelete, btnNew, btnSave, btnUpdate});

        ContainerLayout.setVerticalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContainerLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate)
                        .addGap(26, 26, 26)
                        .addComponent(btnBack))
                    .addGroup(ContainerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(information, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnDelete, btnNew, btnSave, btnUpdate});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban can tim hoc sinh muon cap nhap diêm");
            return;
        } else {
            if (gradeService.selectById(txtMaSv.getText()) == null) {
                JOptionPane.showMessageDialog(this, "sinh vien nay chua co trong danh sach them vao roi sua");
                return;
            } else {
                if (isValidate()) {
                    int kq = JOptionPane.showConfirmDialog(this, "ban co muon cap nhap", "cap nhap", JOptionPane.YES_OPTION);
                    if (kq == 0) {
                        if (gradeService.capNhap(getDataForm()) != 0) {
                            JOptionPane.showMessageDialog(this, "thanh cong");
                            txtDiemTB.setText(getDataForm().diemTrungBinh() + "");
                            fillTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "that bai");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtMasvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasvSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMasvSearchActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new Choose().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban can tim hoc sinh muon them diem");
            return;
        } else {
            if (isValidate()) {
                if (gradeService.selectById(txtMaSv.getText()) == null) {
                    if (gradeService.them(getDataForm()) != 0) {
                        JOptionPane.showMessageDialog(this, "thanh cong");
                        fillTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "that bai");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "sinh vien nay da co diem roi");
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        // TODO add your handling code here:
        int index = tblHienThi.getSelectedRow();
        detail(index);
    }//GEN-LAST:event_tblHienThiMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (txtTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban can tim hoc sinh muon xoa diêm");
            return;
        } else {
            if (gradeService.selectById(txtMaSv.getText()) == null) {
                JOptionPane.showMessageDialog(this, "sinh vien nay chua co trong danh sach them vao roi xoa");
                return;
            } else {
                int kq = JOptionPane.showConfirmDialog(this, "ban co muon xoa", "xoa", JOptionPane.YES_OPTION);
                if (kq == 0) {
                    if (isValidate()) {
                        if (gradeService.xoa(getDataForm()) != 0) {
                            JOptionPane.showMessageDialog(this, "thanh cong");
                            fillTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "that bai");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagePoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePoin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePoin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JPanel Search;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel information;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JLabel txtDiemTB;
    private javax.swing.JTextField txtGiaoDucTC;
    private javax.swing.JLabel txtMaSv;
    private javax.swing.JTextField txtMasvSearch;
    private javax.swing.JLabel txtTen;
    private javax.swing.JTextField txtTiengAnh;
    private javax.swing.JTextField txtTinHoc;
    // End of variables declaration//GEN-END:variables
}
