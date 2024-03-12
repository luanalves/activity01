package com.company.model;

public class UserEvent {
    private int entityId;
    private int userId;
    private int eventId;

    public UserEvent() {
    }

    public UserEvent(int entityId, int userId, int eventId) {
        this.entityId = entityId;
        this.userId = userId;
        this.eventId = eventId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
