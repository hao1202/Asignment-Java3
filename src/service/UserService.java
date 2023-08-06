package service;

import dao.DAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectDB;
import model.User;

public class UserService implements DAOInterface<User> {

    @Override
    public int them(User t) {
        int ketQua = 0;
        try {
            // Ket noi
            Connection connection = ConnectDB.getConnection();
            // Tao statement
            String sql = "insert into Users(username , password , role) "
                    + "VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getUserName());
            preparedStatement.setString(2, t.getPassword());
            preparedStatement.setString(3, t.getRole());

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
    public int capNhap(User t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capNhap'");
    }

    @Override
    public int xoa(User t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'xoa'");
    }

    @Override
    public List<User> selectAll() {
        List ketQua = new ArrayList();
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = ConnectDB.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM Users";
            PreparedStatement st = con.prepareStatement(sql);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                User user = new User(username, password, role);
                ketQua.add(user);
            }
            // Bước 5:
            ConnectDB.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public User selectById(String t) {
        User ketQua = null;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection con = ConnectDB.getConnection();

            // Bước 2: tạo ra đối tượng statement
            String sql = "SELECT * FROM Users where username=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);

            // Bước 3: thực thi câu lệnh SQL
            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            // Bước 4:
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                String role = rs.getString(3);

                ketQua = new User(username, password, role);

            }

            // Bước 5:
            ConnectDB.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

}
