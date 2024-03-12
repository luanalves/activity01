package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.company.model.Event;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class UserEventDao {
	
    public boolean addUserToEvent(int userId, int eventId) {
        String sql = "INSERT INTO user_events (user_id, event_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, eventId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Event> findEventsByUserId(int userId) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT e.* FROM events e JOIN user_events ue ON e.entity_id = ue.event_id WHERE ue.user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int eventId = resultSet.getInt("entity_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

                Event event = new Event(eventId, title, description, createdAt, updatedAt);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
    
    public boolean removeUserFromEvent(int userId, int eventId) {
        String sql = "DELETE FROM user_events WHERE user_id = ? AND event_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, eventId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
