/*
package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import model.User;

import java.util.List;

public class UserService {

    private static UserService instance;
    private final UserDAO userDAO = UserHibernateDAO.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUser(Long userId) {
        return userDAO.getUser(userId);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
*/
