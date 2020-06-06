package service;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserServise {

    List<User> getAllUsers() throws IOException;

    User getUserById(Long userId) throws IOException;

    User getUserByNamePassword(String name, String password) throws IOException;

    boolean isExistUser(String name, String password) throws IOException;

    void addUser(User user) throws IOException;

    void updateUser(User user) throws IOException;

    void deleteUser(User user) throws IOException;
}
