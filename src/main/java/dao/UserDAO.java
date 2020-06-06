package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getById(Long userId);

    User getByNameAndPassword(String name, String password);

    boolean isExist(String name, String password);

    void add(User user);

    void update(User user);

    void delete(User user);

}
