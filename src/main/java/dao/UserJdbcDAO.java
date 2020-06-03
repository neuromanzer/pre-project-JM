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

    @Override
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
                        rs.getString("password"),
                        rs.getString("role")));
            }
            System.out.println("factory all jdbc");
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) {
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
                        rs.getString("password"),
                        rs.getString("role"));
            }
            System.out.println("factory getUser jdbc");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByNamePassword(String name, String password) {
        String query = "select * from users where name = ? and password = ?";
        User user = null;
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistUser(String name, String password) {
        String query = "select * from users where name = ? and password = ?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name) && rs.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        String query = "insert into users (name, email, password, role) values(?, ?, ?, ?)";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
            System.out.println("factory add jdbc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "update users set name = ?, email = ?, password = ?, role = ? where id = ?";
        try (PreparedStatement ps = dbHelper.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
            System.out.println("factory update jdbc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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
