package com.company.dao;

import com.company.model.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;


public class EventDao {

    public boolean saveEvent(Event event) {
        String sql = "INSERT INTO events (title, description) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            	int entityId = resultSet.getInt("entity_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
                events.add(new Event(entityId, title, description, createdAt, updatedAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    
    public boolean updateEvent(Event event) {
        String sql = "UPDATE events SET title = ?, description = ?, updated_at = ? WHERE entity_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            event.setUpdatedAt(LocalDateTime.now());

            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(event.getUpdatedAt()));
            statement.setInt(4, event.getEntityId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Event getEventById(int id) {
        String sql = "SELECT * FROM events WHERE entity_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int entityId = resultSet.getInt("entity_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

                return new Event(entityId, title, description, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
