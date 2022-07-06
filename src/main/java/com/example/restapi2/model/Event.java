package com.example.restapi2.model;

import javax.persistence.*;


@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_event")
    private String nameOfEvent;

    public Event(){}

    public Event(Long id, String nameOfEvent) {
        this.id = id;
        this.nameOfEvent = nameOfEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }
}
