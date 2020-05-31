package dao;

//import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    private static UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();
    private static UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();

    public UserDAO getDao() throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream fis = loader.getResourceAsStream(".properties");
        Properties p = new Properties();
        p.load(fis);
        UserDAO dao = null;

        switch (p.getProperty("daotype")) {
            case "jdbc":
                System.out.println("factory jdbc");
                return userJdbcDAO;
            case "hibernate":
                System.out.println("factory hiber");
                return userHibernateDAO;
        }
        return null;
    }
}
