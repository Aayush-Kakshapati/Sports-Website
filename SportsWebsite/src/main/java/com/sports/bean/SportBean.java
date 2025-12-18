package com.sports.bean;

import com.sports.dao.SportDAO;
import com.sports.dao.SportDetailDAO;
import com.sports.model.Sport;
import com.sports.model.SportDetail;
import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage.Severity;

@Named("sportBean")
@SessionScoped
public class SportBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Sport> sports;
    private String searchTerm;
    private Sport newSport;
    private Sport selectedSport;
    private Integer selectedSportId;
    private SportDetail currentSportDetail;
    
    private final SportDAO sportDAO = new SportDAO();
    private final SportDetailDAO sportDetailDAO = new SportDetailDAO();
    
    public SportBean() {
        newSport = new Sport();
        loadSports();
    }
    
    public void loadSports() {
        sports = sportDAO.getAllSports();
    }
    
    public String searchSports() {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            loadSports();
            return null;
        }
        sports = sportDAO.searchSportsByName(searchTerm);
        return null;
    }
    
    public void selectSportForEditing(Sport sport) {
        this.selectedSport = sport;
        if (sport != null) {
            this.selectedSportId = sport.getSportId();
            SportDetail detail = sportDetailDAO.getDetailBySportId(sport.getSportId());
            if (detail != null) {
                this.currentSportDetail = detail;
            } else {
                this.currentSportDetail = new SportDetail();
                this.currentSportDetail.setSportId(sport.getSportId());
            }
        }
        addMessage(FacesMessage.SEVERITY_INFO, "sport.updated");
    }
    
    public void deleteSport() {
        if (selectedSportId != null) {
            try {
                // First delete any associated sport details
                SportDetail detail = sportDetailDAO.getDetailBySportId(selectedSportId);
                if (detail != null) {
                    sportDetailDAO.deleteSportDetail(detail.getDetailId());
                }
                
                // Then delete the sport
                boolean success = sportDAO.deleteSport(selectedSportId);
                if (success) {
                    loadSports();
                    addMessage(FacesMessage.SEVERITY_INFO, "message.sportDeleted");
                } else {
                    addMessage(FacesMessage.SEVERITY_ERROR, "message.sportDeleteFailed");
                }
            } catch (Exception e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "message.sportDeleteFailed");
                e.printStackTrace();
            } finally {
                selectedSportId = null;
            }
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "message.sportDeleteFailed");
        }
    }
    
    public void updateSport() {
        if (selectedSport != null) {
            boolean success = sportDAO.updateSport(selectedSport);
            if (success) {
                if (currentSportDetail != null) {
                    if (currentSportDetail.getDetailId() > 0) {
                        sportDetailDAO.updateSportDetail(currentSportDetail);
                    } else {
                        sportDetailDAO.addSportDetail(currentSportDetail);
                    }
                }
                loadSports();
                addMessage(FacesMessage.SEVERITY_INFO, "message.sportUpdated");
                selectedSport = null;
                currentSportDetail = null;
            } else {
                addMessage(FacesMessage.SEVERITY_ERROR, "message.sportUpdateFailed");
            }
        }
    }
    
    public String saveSport() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!context.isValidationFailed()) {
            if (newSport != null && newSport.getSportName() != null && !newSport.getSportName().trim().isEmpty()) {
                int sportId = sportDAO.addSport(newSport);
                if (sportId > 0) {
                    newSport.setSportId(sportId);
                    loadSports(); 
                    newSport = new Sport();
                    addMessage(FacesMessage.SEVERITY_INFO, "message.sportAdded");
                    return "admindashboard?faces-redirect=true";
                } else {
                    addMessage(FacesMessage.SEVERITY_ERROR, "message.sportAddFailed");
                }
            }
        }
        return null;
    }
    
    // Getters and Setters
    public List<Sport> getSports() {
        return sports;
    }
    
    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
    
    public String getSearchTerm() {
        return searchTerm;
    }
    
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    public Sport getNewSport() {
        return newSport;
    }
    
    public void setNewSport(Sport newSport) {
        this.newSport = newSport;
    }
    
    public Sport getSelectedSport() {
        return selectedSport;
    }
    
    public void setSelectedSport(Sport selectedSport) {
        this.selectedSport = selectedSport;
    }
    
    public Integer getSelectedSportId() {
        return selectedSportId;
    }
    
    public void setSelectedSportId(Integer selectedSportId) {
        this.selectedSportId = selectedSportId;
    }
    
    public SportDetail getCurrentSportDetail() {
        return currentSportDetail;
    }
    
    public void setCurrentSportDetail(SportDetail currentSportDetail) {
        this.currentSportDetail = currentSportDetail;
    }

    private void addMessage(Severity severity, String messageKey) {
        FacesContext context = FacesContext.getCurrentInstance();
        String message = context.getApplication().evaluateExpressionGet(context, 
            "#{msg['" + messageKey + "']}", String.class);
        context.addMessage(null, new FacesMessage(severity, message, null));
    }
} 