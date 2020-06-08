package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.persistence.NoResultException;
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
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
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
    public User getById(Long userId) {
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
    public User getByNameAndPassword(String name, String password) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User u where u.name = :name and u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            return (User) query.setMaxResults(1).getSingleResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (NoResultException nre) {
            return null;
        }
        return null;
    }

    @Override
    public boolean isExist(String name, String password) {
        User user = getByNameAndPassword(name, password);
        return user != null;
    }

    @Override
    public void add(User user) {
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
    public void update(User user) {
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
    public void delete(User user) {
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
