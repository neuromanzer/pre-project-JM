package dao.factory;

import dao.UserDAO;

import java.io.IOException;

public interface FactoryDAO {
    UserDAO getDao() throws IOException;
}
