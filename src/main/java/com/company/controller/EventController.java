package com.company.controller;

import com.company.dao.EventDao;
import com.company.model.Event;

public class EventController {

    private EventDao eventDao = new EventDao();

    public boolean addEvent(String title, String description) {
        Event event = new Event(title, description);
        return eventDao.saveEvent(event);
    }

    // Métodos adicionais para manipulação de eventos
}
