package com.company.controller;

import com.company.dao.EventCategoryDao;
import com.company.model.EventCategory;

public class EventCategoryController {

    private EventCategoryDao eventCategoryDao = new EventCategoryDao();

    public boolean addEventCategory(String title, String description) {
        EventCategory eventCategory = new EventCategory(title, description);
        return eventCategoryDao.saveEventCategory(eventCategory);
    }
}
