package service;

import dao.UserHibernateDAO;
import model.User;

import java.util.List;

public class UserService {

    private static UserService instance;
    private final UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> getAllUsers() {
        return userHibernateDAO.getAllUsers();
    }

    public User getUser(Long userId) {
        return userHibernateDAO.getUser(userId);
    }

    public void addUser(User user) {
        userHibernateDAO.addUser(user);
    }

    public void updateUser(User user) {
        userHibernateDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userHibernateDAO.deleteUser(user);
    }
}
