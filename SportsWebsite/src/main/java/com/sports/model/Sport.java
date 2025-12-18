package com.sports.model;

import java.io.Serializable;

/**
 * Sport model class representing a sport in the system
 */
public class Sport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int sportId;
    private String sportName;
    private String description;
    
    // Default constructor
    public Sport() {
    }
    
    // Constructor with parameters
    public Sport(int sportId, String sportName, String description) {
        this.sportId = sportId;
        this.sportName = sportName;
        this.description = description;
    }
    
    // Getters and Setters
    public int getSportId() {
        return sportId;
    }
    
    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
    
    public String getSportName() {
        return sportName;
    }
    
    public void setSportName(String sportName) {
        this.sportName = sportName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public int hashCode() {
        return sportId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sport other = (Sport) obj;
        return sportId == other.sportId;
    }
    
    @Override
    public String toString() {
        return "Sport [sportId=" + sportId + ", sportName=" + sportName + "]";
    }
} 