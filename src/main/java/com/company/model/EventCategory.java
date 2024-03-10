package com.company.model;

public class EventCategory {
    private int entityId;
    private String title;
    private String description;

    // Construtores
    public EventCategory() {}

    public EventCategory(String title, String description) {
        setTitle(title); // Use o setter para aplicar a validação
        setDescription(description); // Use o setter para aplicar a validação
    }

    // Getters
    public int getEntityId() {
        return entityId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Setters com validação
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public void setTitle(String title) {
        if (title != null && title.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 255) {
            throw new IllegalArgumentException("Description cannot exceed 255 characters");
        }
        this.description = description;
    }
}
