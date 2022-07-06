package com.example.restapi2.DB;

import com.example.restapi2.Service.UserService;
import com.example.restapi2.hibernate.HibernateUserConfig;
import com.example.restapi2.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDB implements UserService {

    /**
     * save User
     * @param user
     */
    @Override
    public void saveUser(User user) {
        Session session = HibernateUserConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Update User
     * @param user
     */
    @Override
    public void updateUser(User user) {
        Session session = HibernateUserConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete User
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUserConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get User By ID
     * @param id
     * @return
     */
    @Override
    public User getUser(Long id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUserConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    /**
     * get score of people
     * @return list of users with their data
     */
    @Override
    public List<User> getInformationAboutUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try (Session session = HibernateUserConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    /**
     * get size of users
     * @return size of users
     */
    @Override
    public Integer getAllUsers() {
        Transaction transaction = null;
        List<User> listOfAccount = null;
        try (Session session = HibernateUserConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            listOfAccount = session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfAccount.size();
    }
}
