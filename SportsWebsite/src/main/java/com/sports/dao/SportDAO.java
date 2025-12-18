package com.sports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sports.model.Sport;
import com.sports.util.DatabaseConnection;

/**
 * Data Access Object for Sport entity
 */
public class SportDAO {

    /**
     * Get all sports from the database
     * 
     * @return List of Sport objects
     */
    public List<Sport> getAllSports() {
        List<Sport> sports = new ArrayList<>();
        String sql = "SELECT * FROM sports ORDER BY sport_name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Sport sport = new Sport();
                sport.setSportId(rs.getInt("sport_id"));
                sport.setSportName(rs.getString("sport_name"));
                sport.setDescription(rs.getString("description"));
                sports.add(sport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sports;
    }
    
    /**
     * Get a specific sport by ID
     * 
     * @param sportId The ID of the sport to retrieve
     * @return Sport object or null if not found
     */
    public Sport getSportById(int sportId) {
        String sql = "SELECT * FROM sports WHERE sport_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, sportId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Sport sport = new Sport();
                    sport.setSportId(rs.getInt("sport_id"));
                    sport.setSportName(rs.getString("sport_name"));
                    sport.setDescription(rs.getString("description"));
                    return sport;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Search for sports by name
     * 
     * @param searchTerm The search term to look for in sport names
     * @return List of matching Sport objects
     */
    public List<Sport> searchSportsByName(String searchTerm) {
        List<Sport> sports = new ArrayList<>();
        String sql = "SELECT * FROM sports WHERE sport_name LIKE ? ORDER BY sport_name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + searchTerm + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sport sport = new Sport();
                    sport.setSportId(rs.getInt("sport_id"));
                    sport.setSportName(rs.getString("sport_name"));
                    sport.setDescription(rs.getString("description"));
                    sports.add(sport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sports;
    }
    
    /**
     * Add a new sport to the database
     * 
     * @param sport The Sport object to add
     * @return The ID of the newly added sport, or -1 if unsuccessful
     */
    public int addSport(Sport sport) {
        String sql = "INSERT INTO sports (sport_name, description) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, sport.getSportName());
            stmt.setString(2, sport.getDescription());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    /**
     * Update a sport in the database
     * 
     * @param sport The Sport object with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean updateSport(Sport sport) {
        String sql = "UPDATE sports SET sport_name = ?, description = ? WHERE sport_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sport.getSportName());
            stmt.setString(2, sport.getDescription());
            stmt.setInt(3, sport.getSportId());
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete a sport from the database
     * 
     * @param sportId The ID of the sport to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteSport(int sportId) {
        String sql = "DELETE FROM sports WHERE sport_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, sportId);
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
} 