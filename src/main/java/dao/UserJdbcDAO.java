package dao;

import model.User;
import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private static UserJdbcDAO instance;
    private final DBHelper dbHelper = DBHelper.getInstance();

    public static UserJdbcDAO getInstance() {
        if (instance == null) {
            instance = new UserJdbcDAO();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        String query = "select * from users";
        List<User> users = new ArrayList<>();
        try (Statement st = dbHelper.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")));
            }
            System.out.println("factory all jdbc");
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(Long userId) {
        String query = "select * from users where id = ?";
        User user = null;
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
            System.out.println("factory getUser jdbc");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        String query = "insert into users (name, email, password) values(?, ?, ?)";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
            System.out.println("factory add jdbc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String query = "update users set name = ?, email = ?, password = ? where id = ?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setLong(4, user.getId());
            ps.executeUpdate();
            System.out.println("factory update jdbc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        String query = "delete from users where id = ?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setLong(1, user.getId());
            System.out.println("factory delete jdbc");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
