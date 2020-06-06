package service;
//UserService rename + interface UserServiceImpl
//dbhelper сюда

import dao.factory.UserDaoFactory;
import model.User;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserServise {
    private static UserServiceImpl instance;
    UserDaoFactory userDaoFactory = new UserDaoFactory();

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return userDaoFactory.getDao().getAllUsers();
    }

    @Override
    public User getUserById(Long userId) throws IOException {
        return userDaoFactory.getDao().getUserById(userId);
    }

    @Override
    public User getUserByNamePassword(String name, String password) throws IOException {
        return userDaoFactory.getDao().getUserByNamePassword(name, password);
    }

    @Override
    public boolean isExistUser(String name, String password) throws IOException {
        return userDaoFactory.getDao().isExistUser(name, password);
    }

    @Override
    public void addUser(User user) throws IOException {
        userDaoFactory.getDao().addUser(user);
    }

    @Override
    public void updateUser(User user) throws IOException {
        userDaoFactory.getDao().updateUser(user);
    }

    @Override
    public void deleteUser(User user) throws IOException {
        userDaoFactory.getDao().deleteUser(user);
    }
}
