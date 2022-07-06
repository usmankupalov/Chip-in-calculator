package com.example.restapi2.Service;

import com.example.restapi2.model.Event;

import java.util.List;

public interface EventService {
    void addEvent(Event event);
    void updateEvent(Event event);
    List<Event> getAllEvent();
}
