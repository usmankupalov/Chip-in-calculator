package com.example.restapi2.DB;

import com.example.restapi2.Service.EventUserService;
import com.example.restapi2.hibernate.HibernateEventUserConfig;
import com.example.restapi2.model.EventUser;
import org.hibernate.Session;

public class EventUserDB implements EventUserService {

    /**
     * adding user and event
     * @param eventUser
     */
    @Override
    public void addUserAndEvent(EventUser eventUser) {
        Session session = HibernateEventUserConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(eventUser);
        session.getTransaction().commit();
        session.close();
    }
}
