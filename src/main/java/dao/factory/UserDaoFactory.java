package dao.factory;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory implements FactoryDAO {

    @Override
    public UserDAO getDao() throws IOException {

        UserDAO userDAO;

        ClassLoader loader = this.getClass().getClassLoader();
        InputStream fis = loader.getResourceAsStream(".properties");
        Properties p = new Properties();
        p.load(fis);

        switch (p.getProperty("daotype")) {
            case "j":
                userDAO = UserJdbcDAO.getInstance();
                return userDAO;
            case "h":
                userDAO = UserHibernateDAO.getInstance();
                return userDAO;
        }
        return null;
    }
}
