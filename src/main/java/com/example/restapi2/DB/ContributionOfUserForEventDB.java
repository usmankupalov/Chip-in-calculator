package com.example.restapi2.DB;

import com.example.restapi2.Service.ContributionForHolidayService;
import com.example.restapi2.hibernate.HibernateContributionForHolidayConfig;
import com.example.restapi2.model.ContributionOfUserForEvent;
import com.example.restapi2.model.DebtsOfUserForEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContributionOfUserForEventDB implements ContributionForHolidayService {

    /**
     * save contribution
     * @param contributionForHoliday
     */
    @Override
    public void saveContributionOfUser(ContributionOfUserForEvent contributionForHoliday) {
        Session session = HibernateContributionForHolidayConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(contributionForHoliday);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * get contributions of users
     * @return list of users
     */
    @Override
    public List<ContributionOfUserForEvent> getContributionsOfUsersForEvent() {
        Transaction transaction = null;
        List<ContributionOfUserForEvent> contributionOfUserForHolidays = null;
        try (Session session = HibernateContributionForHolidayConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            contributionOfUserForHolidays = session.createQuery("FROM ContributionOfUserForEvent").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return contributionOfUserForHolidays;
    }

    /**
     * get total sum of users
     * @return total sum of user
     */
    @Override
    public List<ContributionOfUserForEvent> getTotalScoreOfContribution() {
        Transaction transaction = null;
        List<ContributionOfUserForEvent> usersInputMoney = null;
        try (Session session = HibernateContributionForHolidayConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            usersInputMoney = session.createQuery("SELECT SUM(inputMoneyOfUser) FROM ContributionOfUserForEvent WHERE id = id").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return usersInputMoney;
    }

    /**
     * get number of users
     * @return size of users
     */
    @Override
    public Integer getSizeUsersOfContribution() {
        Transaction transaction = null;
        List<ContributionOfUserForEvent> contributionForHolidays = null;
        try (Session session = HibernateContributionForHolidayConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            contributionForHolidays = session.createQuery("FROM ContributionOfUserForEvent").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return contributionForHolidays.size();
    }


    public List<ContributionOfUserForEvent> getInformationAboutUsersContribution() {
        Transaction transaction = null;
        List<ContributionOfUserForEvent> contributionOfUsers = null;
        try (Session session = HibernateContributionForHolidayConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            contributionOfUsers = session.createQuery("FROM ContributionOfUserForEvent").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return contributionOfUsers;
    }
}
