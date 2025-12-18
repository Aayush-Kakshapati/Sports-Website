package com.sports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.sports.model.SportDetail;
import com.sports.util.DatabaseConnection;

/**
 * Data Access Object for SportDetail entity
 */
public class SportDetailDAO {
    
    private static final Logger logger = Logger.getLogger(SportDetailDAO.class.getName());

    /**
     * Get sport details by sport ID
     * 
     * @param sportId The ID of the sport
     * @return SportDetail object or null if not found
     */
    public SportDetail getDetailBySportId(int sportId) {
        String sql = "SELECT * FROM sport_details WHERE sport_id = ?";
        logger.log(Level.INFO, "Fetching sport details for sport ID: {0}", sportId);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, sportId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    SportDetail detail = new SportDetail();
                    detail.setDetailId(rs.getInt("detail_id"));
                    detail.setSportId(rs.getInt("sport_id"));
                    detail.setRules(rs.getString("rules"));
                    detail.setHistory(rs.getString("history"));
                    detail.setEquipment(rs.getString("equipment"));
                    detail.setFamousPlayers(rs.getString("famous_players"));
                    logger.log(Level.INFO, "Found sport details for sport ID: {0}", sportId);
                    return detail;
                }
            }
            logger.log(Level.WARNING, "No sport details found for sport ID: {0}", sportId);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching sport details for sport ID: " + sportId, e);
        }
        
        return null;
    }
    
    /**
     * Add sport details to the database
     * 
     * @param detail The SportDetail object to add
     * @return The ID of the newly added detail record, or -1 if unsuccessful
     */
    public int addSportDetail(SportDetail detail) {
        String sql = "INSERT INTO sport_details (sport_id, rules, history, equipment, famous_players) " +
                     "VALUES (?, ?, ?, ?, ?)";
        logger.log(Level.INFO, "Adding new sport details for sport ID: {0}", detail.getSportId());
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, detail.getSportId());
            stmt.setString(2, detail.getRules());
            stmt.setString(3, detail.getHistory());
            stmt.setString(4, detail.getEquipment());
            stmt.setString(5, detail.getFamousPlayers());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        logger.log(Level.INFO, "Successfully added sport details with ID: {0}", newId);
                        return newId;
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding sport details for sport ID: " + detail.getSportId(), e);
        }
        
        return -1;
    }
    
    /**
     * Update sport details in the database
     * 
     * @param detail The SportDetail object to update
     * @return true if the update was successful, false otherwise
     */
    public boolean updateSportDetail(SportDetail detail) {
        String sql = "UPDATE sport_details " +
                     "SET rules = ?, history = ?, equipment = ?, famous_players = ? " +
                     "WHERE detail_id = ?";
        logger.log(Level.INFO, "Updating sport details with ID: {0}", detail.getDetailId());
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, detail.getRules());
            stmt.setString(2, detail.getHistory());
            stmt.setString(3, detail.getEquipment());
            stmt.setString(4, detail.getFamousPlayers());
            stmt.setInt(5, detail.getDetailId());
            
            int affectedRows = stmt.executeUpdate();
            boolean success = affectedRows > 0;
            if (success) {
                logger.log(Level.INFO, "Successfully updated sport details with ID: {0}", detail.getDetailId());
            } else {
                logger.log(Level.WARNING, "No sport details were updated for ID: {0}", detail.getDetailId());
            }
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating sport details with ID: " + detail.getDetailId(), e);
            return false;
        }
    }
    
    /**
     * Delete sport details from the database
     * 
     * @param detailId The ID of the detail record to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteSportDetail(int detailId) {
        String sql = "DELETE FROM sport_details WHERE detail_id = ?";
        logger.log(Level.INFO, "Deleting sport details with ID: {0}", detailId);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detailId);
            
            int affectedRows = stmt.executeUpdate();
            boolean success = affectedRows > 0;
            if (success) {
                logger.log(Level.INFO, "Successfully deleted sport details with ID: {0}", detailId);
            } else {
                logger.log(Level.WARNING, "No sport details were deleted for ID: {0}", detailId);
            }
            return success;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting sport details with ID: " + detailId, e);
            return false;
        }
    }
} 