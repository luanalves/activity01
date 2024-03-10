package com.company.model;

import java.time.LocalDateTime;

public class Event {
    private int entityId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Construtor padrão
    public Event() {}

    // Construtor com todos os parâmetros
    public Event(int entityId, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.entityId = entityId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Construtor para novos eventos (sem entityId, createdAt, updatedAt)
    public Event(String title, String description) {
        this.title = title;
        this.description = description;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
