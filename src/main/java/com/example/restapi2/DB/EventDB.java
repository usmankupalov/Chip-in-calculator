package com.example.restapi2.DB;

import com.example.restapi2.Service.EventService;
import com.example.restapi2.hibernate.HibernateEventConfig;
import com.example.restapi2.model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class EventDB implements EventService {

    /**
     * add Event
     * @param event
     */
    @Override
    public void addEvent(Event event) {
        Session session = HibernateEventConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * update Event
     * @param event
     */
    @Override
    public void updateEvent(Event event) {
        Session session = HibernateEventConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(event);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * get all event
     * @return list of event
     */
    @Override
    public List<Event> getAllEvent() {
        Transaction transaction = null;
        List<Event> events = null;
        try (Session session = HibernateEventConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            events = session.createQuery("FROM Event").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return events;
    }
}
