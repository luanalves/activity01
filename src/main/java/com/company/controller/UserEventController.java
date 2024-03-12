package com.company.controller;

import com.company.dao.UserEventDao;
import com.company.dao.UserDao;
import com.company.dao.EventDao;
import java.util.List;
import com.company.model.Event; 

public class UserEventController {

	private final UserEventDao userEventDao;
    private final EventDao eventDao;

    public UserEventController(UserEventDao userEventDao, EventDao eventDao) {
        this.userEventDao = userEventDao;
        this.eventDao = eventDao;
    }

    public void registerUserToEvent(int userId, int eventId) {
        userEventDao.addUserToEvent(userId, eventId);
        System.out.println("Usuário registrado no evento com sucesso!");
    }

    public List<Event> listAvailableEvents() {
        return eventDao.getAllEvents();
    }
}
