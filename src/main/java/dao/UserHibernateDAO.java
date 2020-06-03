package dao;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.persistence.Query;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static UserHibernateDAO instance;
    private final DBHelper dbHelper = DBHelper.getInstance();

    public static UserHibernateDAO getInstance() {
        if (instance == null) {
            instance = new UserHibernateDAO();
        }
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User").list();
            transaction.commit();
            session.close();
            System.out.println("hiber all users");
            return users;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        try {
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            transaction.commit();
            session.close();
            System.out.println("hiber get users");
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
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.name = :name and u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            user = (User) query.getResultList().get(0);
            transaction.commit();
            session.close();
            System.out.println("hiber get users");
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
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User u where u.name = :name and u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            user = (User) query.getResultList().get(0);
            Boolean isExist = session.contains(user);
            transaction.commit();
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
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            System.out.println("hiber add users");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            System.out.println("hiber update users");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            Session session = dbHelper.getConfiguration().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
            System.out.println("hiber delete users");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
