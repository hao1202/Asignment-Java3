/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOInterface;
import database.ConnectDB;
import java.util.List;
import model.Student;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author tranv
 */
public class StudentService implements DAOInterface<Student> {

    @Override
    public int them(Student t) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "insert into Students(Masv , Hoten , Email , SoDT , GioiTinh , DiaChi , Hinh) "
                    + "values (? , ? , ? , ? , ? , ? , ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getMaSv());
            preparedStatement.setString(2, t.getHoTen());
            preparedStatement.setString(3, t.getEmail());
            preparedStatement.setString(4, t.getSoDT());
            preparedStatement.setInt(5, t.getGioiTinh());
            preparedStatement.setString(6, t.getDiaChi());
            preparedStatement.setString(7, t.getHinh());

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
    public int capNhap(Student t) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "update Students "
                    + "set Hoten = ?, Email = ?, SoDT = ?, GioiTinh = ?, DiaChi = ?, Hinh = ? "
                    + "where Masv = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getHoTen());
            preparedStatement.setString(2, t.getEmail());
            preparedStatement.setString(3, t.getSoDT());
            preparedStatement.setInt(4, t.getGioiTinh());
            preparedStatement.setString(5, t.getDiaChi());
            preparedStatement.setString(6, t.getHinh());
            preparedStatement.setString(7, t.getMaSv());

            ketQua = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("cap nhap " + ketQua + " sinh viên");
            ConnectDB.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int xoa(Student student) {
        int ketQua = 0;
        try {
            Connection connection = ConnectDB.getConnection();

            // Tao statement
            String sql = "delete from Students "
                    + "where Masv = ?";
            String sql2 = "delete from Grade\n"
                    + "where Masv = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getMaSv());
            
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.setString(1, student.getMaSv());
            int kq = preparedStatement1.executeUpdate();
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
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            // Bước 2: tạo ra đối tượng statement
            String sql = "select * from Students";
            PreparedStatement st = connection.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String maSv = rs.getString(1);
                String hoTen = rs.getString(2);
                String email = rs.getString(3);
                String soDt = rs.getString(4);
                int gioiTinh = rs.getInt(5);
                String diaChi = rs.getString(6);
                String hinh = rs.getString(7);

                Student student = new Student(maSv, hoTen, email,
                        soDt, gioiTinh, diaChi, hinh);
                list.add(student);
            }
            ConnectDB.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student selectById(String t) {
        Student student = null;
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            // Bước 2: tạo ra đối tượng statement
            String sql = "select * from Students where Masv = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, t);
            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String maSv = rs.getString(1);
                String hoTen = rs.getString(2);
                String email = rs.getString(3);
                String soDt = rs.getString(4);
                int gioiTinh = rs.getInt(5);
                String diaChi = rs.getString(6);
                String hinh = rs.getString(7);

                student = new Student(maSv, hoTen, email,
                        soDt, gioiTinh, diaChi, hinh);
            }
            ConnectDB.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

}
