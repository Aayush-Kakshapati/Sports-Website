package com.sports.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int adminId;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    
    public Admin() {
    }
    
    public Admin(int adminId, String username, String password, String email, 
                Timestamp createdAt, Timestamp lastLogin) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
    }
    
    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }
    
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    @Override
    public int hashCode() {
        return adminId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Admin other = (Admin) obj;
        return adminId == other.adminId;
    }
    
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", username=" + username + "]";
    }
} 