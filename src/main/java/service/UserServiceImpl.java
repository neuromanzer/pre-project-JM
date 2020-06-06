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
    public List<User> getAll() throws IOException {
        return userDaoFactory.getDao().getAll();
    }

    @Override
    public User getById(Long userId) throws IOException {
        return userDaoFactory.getDao().getById(userId);
    }

    @Override
    public User getByNamePassword(String name, String password) throws IOException {
        return userDaoFactory.getDao().getByNameAndPassword(name, password);
    }

    @Override
    public boolean isExist(String name, String password) throws IOException {
        return userDaoFactory.getDao().isExist(name, password);
    }

    @Override
    public void add(User user) throws IOException {
        userDaoFactory.getDao().add(user);
    }

    @Override
    public void update(User user) throws IOException {
        userDaoFactory.getDao().update(user);
    }

    @Override
    public void delete(User user) throws IOException {
        userDaoFactory.getDao().delete(user);
    }
}
