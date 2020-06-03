package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    private static final UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();
    private static final UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();

    public UserDAO getDao() throws IOException {
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream fis = loader.getResourceAsStream(".properties");
        Properties p = new Properties();
        p.load(fis);

        switch (p.getProperty("daotype")) {
            case "j":
                System.out.println("factory jdbc");
                return userJdbcDAO;
            case "h":
                System.out.println("factory hiber");
                return userHibernateDAO;
        }
        return null;
    }
}
