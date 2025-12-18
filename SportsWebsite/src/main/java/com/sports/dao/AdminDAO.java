package com.sports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sports.model.Admin;
import com.sports.util.DatabaseConnection;

public class AdminDAO {
    
    public Admin validateLogin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getInt("admin_id"));
                    admin.setUsername(rs.getString("username"));
                    admin.setEmail(rs.getString("email"));
                    admin.setCreatedAt(rs.getTimestamp("created_at"));
                    admin.setLastLogin(rs.getTimestamp("last_login"));
                    
                    // Update last login time
                    updateLastLogin(admin.getAdminId());
                    
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private void updateLastLogin(int adminId) {
        String sql = "UPDATE admins SET last_login = ? WHERE admin_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(2, adminId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 