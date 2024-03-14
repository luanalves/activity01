package com.company.dao;

import com.company.model.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Date;
import java.time.LocalDate;

public class EventDao {

    public static final String TABLE_NAME = "events";
    public static final String COL_ENTITY_ID = "entity_id";
    public static final String COL_TITLE = "title";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_CREATED_AT = "created_at";
    public static final String COL_UPDATED_AT = "updated_at";
    public static final String COL_EVENT_DATE = "event_date";

    public boolean saveEvent(Event event) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + COL_TITLE + ", " + COL_DESCRIPTION + ", " + COL_EVENT_DATE + ") VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setDate(3, Date.valueOf(event.getEventDate()));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

        	while (resultSet.next()) {
        	    int entityId = resultSet.getInt(COL_ENTITY_ID);
        	    String title = resultSet.getString(COL_TITLE);
        	    String description = resultSet.getString(COL_DESCRIPTION);
        	    LocalDateTime createdAt = resultSet.getTimestamp(COL_CREATED_AT).toLocalDateTime();
        	    LocalDateTime updatedAt = resultSet.getTimestamp(COL_UPDATED_AT).toLocalDateTime();
        	    LocalDate eventDate = resultSet.getDate(COL_EVENT_DATE).toLocalDate();
        	    events.add(new Event(entityId, title, description, eventDate, createdAt, updatedAt));
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public boolean updateEvent(Event event) {
        String sql = "UPDATE " + TABLE_NAME + " SET " + COL_TITLE + " = ?, " + COL_DESCRIPTION + " = ?, " + COL_EVENT_DATE + " = ?, " + COL_UPDATED_AT + " = ? WHERE " + COL_ENTITY_ID + " = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            event.setUpdatedAt(LocalDateTime.now());

            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setDate(3, Date.valueOf(event.getEventDate()));
            statement.setTimestamp(4, Timestamp.valueOf(event.getUpdatedAt()));
            statement.setInt(5, event.getEntityId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Event getEventById(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ENTITY_ID + " = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int entityId = resultSet.getInt(COL_ENTITY_ID);
                String title = resultSet.getString(COL_TITLE);
                String description = resultSet.getString(COL_DESCRIPTION);
                LocalDateTime createdAt = resultSet.getTimestamp(COL_CREATED_AT).toLocalDateTime();
                LocalDateTime updatedAt = resultSet.getTimestamp(COL_UPDATED_AT).toLocalDateTime();
                LocalDate eventDate = resultSet.getDate(COL_EVENT_DATE).toLocalDate();

                return new Event(entityId, title, description, eventDate, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
