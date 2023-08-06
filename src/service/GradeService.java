/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOInterface;
import database.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Grade;
import model.Student;
import view.ManagePoin;

/**
 *
 * @author tranv
 */
public class GradeService implements DAOInterface<Grade> {

    @Override
    public int them(Grade t) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "insert into Grade (Masv , TiengAnh , TinHoc , GDTC) values\n"
                    + "(? , ? , ? , ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getSinhVien().getMaSv());
            preparedStatement.setFloat(2, t.getTiengAnh());
            preparedStatement.setFloat(3, t.getTinHoc());
            preparedStatement.setFloat(4, t.getGDTC());

            ketQua = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");
            ConnectDB.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int capNhap(Grade t) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "update Grade\n"
                    + "set TiengAnh = ? , TinHoc = ? , GDTC = ?\n"
                    + "where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, t.getTiengAnh());
            preparedStatement.setFloat(2, t.getTinHoc());
            preparedStatement.setFloat(3, t.getGDTC());

            preparedStatement.setInt(4, ManagePoin.id);

            ketQua = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Xóa " + ketQua + " sinh viên");
            ConnectDB.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int xoa(Grade t) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "delete from Grade\n"
                    + "where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ManagePoin.id);

            ketQua = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Xóa " + ketQua + " sinh viên");
            ConnectDB.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public List<Grade> selectAll() {
        List<Grade> list = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        String sql = "select top (3) ID , Grade.Masv , Hoten, TiengAnh , TinHoc , "
                + "GDTC from Grade join Students on Grade.Masv = Students.Masv";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setId(rs.getInt("ID"));
                Student student = new Student();
                student.setMaSv(rs.getString("Masv"));
                student.setHoTen(rs.getString("Hoten"));
                grade.setSinhVien(student);
                grade.setTiengAnh(rs.getFloat("TiengAnh"));
                grade.setTinHoc(rs.getFloat("TinHoc"));
                grade.setGDTC(rs.getFloat("GDTC"));
                list.add(grade);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Grade selectById(String t) {
        Grade grade = null;
        Connection connection = ConnectDB.getConnection();
        String sql = "SELECT Grade.Masv AS Masv, Hoten, ID, TiengAnh, TinHoc, GDTC\n"
                + "FROM Grade\n"
                + "JOIN Students ON Grade.Masv = Students.Masv\n"
                + "WHERE Grade.Masv = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                grade = new Grade();
                grade.setId(rs.getInt("ID"));
                Student student = new Student();
                student.setMaSv(rs.getString("Masv")); // Xác định rõ cột "Masv" từ bảng "Grade"
                student.setHoTen(rs.getString("Hoten"));
                grade.setSinhVien(student);
                grade.setTiengAnh(rs.getFloat("TiengAnh"));
                grade.setTinHoc(rs.getFloat("TinHoc"));
                grade.setGDTC(rs.getFloat("GDTC"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return grade;
    }

    
}
