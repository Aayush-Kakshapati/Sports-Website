package com.sports.model;

import java.io.Serializable;

/**
 * Model class representing detailed information about a sport
 */
public class SportDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int detailId;
    private int sportId;
    private String rules;
    private String history;
    private String equipment;
    private String famousPlayers;
    
    // Default constructor
    public SportDetail() {
    }
    
    // Constructor with parameters
    public SportDetail(int detailId, int sportId, String rules, String history, String equipment, String famousPlayers) {
        this.detailId = detailId;
        this.sportId = sportId;
        this.rules = rules;
        this.history = history;
        this.equipment = equipment;
        this.famousPlayers = famousPlayers;
    }
    
    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }
    
    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }
    
    public int getSportId() {
        return sportId;
    }
    
    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
    
    public String getRules() {
        return rules;
    }
    
    public void setRules(String rules) {
        this.rules = rules;
    }
    
    public String getHistory() {
        return history;
    }
    
    public void setHistory(String history) {
        this.history = history;
    }
    
    public String getEquipment() {
        return equipment;
    }
    
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    public String getFamousPlayers() {
        return famousPlayers;
    }
    
    public void setFamousPlayers(String famousPlayers) {
        this.famousPlayers = famousPlayers;
    }
    
    @Override
    public int hashCode() {
        return detailId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SportDetail other = (SportDetail) obj;
        return detailId == other.detailId;
    }
    
    @Override
    public String toString() {
        return "SportDetail [detailId=" + detailId + ", sportId=" + sportId + "]";
    }
} 