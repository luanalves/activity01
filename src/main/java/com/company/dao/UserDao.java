package com.company.dao;

import com.company.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UserDao {

    public boolean saveUser(User user) {
        boolean rowInserted = false;
        String sql = "INSERT INTO users (full_name, username, password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUsername());
            String hashedPassword = hashFunction(user.getPassword());
            statement.setString(3, hashedPassword);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
    
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ? LIMIT 1";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    String storedPasswordHash = resultSet.getString("password");
                    return BCrypt.checkpw(password, storedPasswordHash);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    private String hashFunction(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

//    public boolean checkUserLogin(String username, String password) {
//        String hashedPasswordFromDb = "..."; 
//        return BCrypt.checkpw(password, hashedPasswordFromDb);
//    }
}
