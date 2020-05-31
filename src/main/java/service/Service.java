package service;

import dao.UserDaoFactory;
import model.User;

import java.io.IOException;
import java.util.List;

public class Service {
    private static Service instance;
    UserDaoFactory userDaoFactory = new UserDaoFactory();

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public List<User> getAllUsers() throws IOException {
        return userDaoFactory.getDao().getAllUsers();
    }

    public User getUser(Long userId) throws IOException {
        return userDaoFactory.getDao().getUser(userId);
    }

    public void addUser(User user) throws IOException {
        userDaoFactory.getDao().addUser(user);
    }

    public void updateUser(User user) throws IOException {
        userDaoFactory.getDao().updateUser(user);
    }

    public void deleteUser(User user) throws IOException {
        userDaoFactory.getDao().deleteUser(user);
    }
}
