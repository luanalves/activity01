package com.company.controller;

import com.company.dao.EventDao;
import com.company.model.Event;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class EventController {

    private EventDao eventDao = new EventDao();

    public boolean addEvent(String title, String description, LocalDate eventDate) {
        Event event = new Event(title, description, eventDate);
        return eventDao.saveEvent(event);
    }
    
    public List<Event> listEvents() {
        return eventDao.getAllEvents();
    }
    
    public boolean editEvent(Event event) {
        return eventDao.updateEvent(event);
    }
    
    public Event getEventById(int id) {
        return eventDao.getEventById(id);
    }
}
