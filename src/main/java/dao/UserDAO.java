package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUser(Long userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
