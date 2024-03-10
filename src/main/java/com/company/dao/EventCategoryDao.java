package com.company.dao;

import com.company.model.EventCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventCategoryDao {

    public boolean saveEventCategory(EventCategory eventCategory) {
        String sql = "INSERT INTO events_categories (title, description) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventCategory.getTitle());
            statement.setString(2, eventCategory.getDescription());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos adicionais para CRUD
}
