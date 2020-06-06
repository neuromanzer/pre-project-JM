package service;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserServise {

    List<User> getAll() throws IOException;

    User getById(Long userId) throws IOException;

    User getByNamePassword(String name, String password) throws IOException;

    boolean isExist(String name, String password) throws IOException;

    void add(User user) throws IOException;

    void update(User user) throws IOException;

    void delete(User user) throws IOException;
}
