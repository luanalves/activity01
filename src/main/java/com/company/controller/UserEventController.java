package com.company.controller;

import com.company.dao.UserEventDao;
import com.company.dao.EventDao;
import java.util.List;
import com.company.model.Event; 

public class UserEventController {
    
    private EventDao eventDao = new EventDao();
    private UserEventDao userEventDao = new UserEventDao();


    public void registerUserToEvent(int userId, int eventId) {
        userEventDao.addUserToEvent(userId, eventId);
        System.out.println("Usuário registrado no evento com sucesso!");
    }

    public List<Event> listAvailableEvents() {
        return eventDao.getAllEvents();
    }
}
