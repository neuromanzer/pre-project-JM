package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByNamePassword(String name, String password);

    boolean isExistUser(String name, String password);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

}
