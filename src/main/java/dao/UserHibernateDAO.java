package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.persistence.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO instance;       //INST
    private final SessionFactory sessionFactory;

    private UserHibernateDAO() {
        this.sessionFactory = DBHelper.getInstance().getConfiguration();
    }

    public static UserHibernateDAO getInstance() {  //INST
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            List<User> users = session.createQuery("from User").list();
            session.close();
            return users;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        try {
            Session session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            session.close();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByNamePassword(String name, String password) {
        User user;
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("from User u where u.name = :name and u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            user = (User) query.getResultList().get(0);
            session.close();
            return user;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistUser(String name, String password) {
        User user;
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("from User u where u.name = :name and u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            user = (User) query.getResultList().get(0);
            Boolean isExist = session.contains(user);
            session.close();
            return isExist;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void addUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
