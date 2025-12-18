package com.sports.bean;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import com.sports.dao.SportDAO;
import com.sports.dao.SportDetailDAO;
import com.sports.model.Sport;
import com.sports.model.SportDetail;

/**
 * Managed bean for SportDetail operations
 */
@Named("sportDetailBean")
@RequestScoped
public class SportDetailBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SportDetailBean.class.getName());
    
    private SportDetailDAO sportDetailDAO;
    private SportDAO sportDAO;
    private SportDetail sportDetail;
    private Sport sport;
    private int sportId;
    private String name;
    private String description;
    private String rules;
    private String history;
    private String equipment;
    private String famousPlayers;
    
  
    // Initialize the bean
  
    @PostConstruct
    public void init() {
        sportDetailDAO = new SportDetailDAO();
        sportDAO = new SportDAO();
        sportDetail = new SportDetail();
        
        // Get sportId from request parameter
        String sportIdParam = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap()
            .get("sportId");
            
        logger.log(Level.INFO, "Initializing SportDetailBean with sportId: {0}", sportIdParam);
            
        if (sportIdParam != null && !sportIdParam.isEmpty()) {
            try {
                sportId = Integer.parseInt(sportIdParam);
                loadSportDetail();
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid sport ID: {0}", sportIdParam);
                addMessage(FacesMessage.SEVERITY_ERROR, "sport.invalidId");
            }
        }
    }
    
    //  Load sport details based on the sportId parameter
    public void loadSportDetail() {
        if (sportId > 0) {
            logger.log(Level.INFO, "Loading sport details for ID: {0}", sportId);
            
            sport = sportDAO.getSportById(sportId);
            
            if (sport != null) {
                logger.log(Level.INFO, "Found sport: {0}", sport.getSportName());
                SportDetail detail = sportDetailDAO.getDetailBySportId(sportId);
                
                if (detail != null) {
                    logger.log(Level.INFO, "Found sport details for: {0}", sport.getSportName());
                    sportDetail = detail;
                    // Copy values to individual fields for easier JSF binding
                    this.name = sport.getSportName();
                    this.description = sport.getDescription();
                    this.rules = sportDetail.getRules();
                    this.history = sportDetail.getHistory();
                    this.equipment = sportDetail.getEquipment();
                    this.famousPlayers = sportDetail.getFamousPlayers();
                } else {
                    logger.log(Level.WARNING, "No details found for sport ID: {0}", sportId);
                    // Create new empty details if none exist yet
                    sportDetail = new SportDetail();
                    sportDetail.setSportId(sportId);
                    this.name = sport.getSportName();
                    this.description = sport.getDescription();
                    this.rules = "";
                    this.history = "";
                    this.equipment = "";
                    this.famousPlayers = "";
                }
            } else {
                logger.log(Level.SEVERE, "Sport not found for ID: {0}", sportId);
            }
        }
    }
    
    /**
     * Save sport details (create or update)
     */
    public String saveSportDetail() {
        // Update sport description
        sport.setDescription(this.description);
        sportDAO.updateSport(sport);
        
        // Copy values from individual fields back to sportDetail
        sportDetail.setRules(this.rules);
        sportDetail.setHistory(this.history);
        sportDetail.setEquipment(this.equipment);
        sportDetail.setFamousPlayers(this.famousPlayers);
        
        if (sportDetail.getDetailId() > 0) {
            // Update existing details
            updateSportDetail();
        } else {
            // Create new details
            addSportDetail();
        }
        
        return "details?faces-redirect=true&sportId=" + sportId;
    }
    
    public void updateSportDetail() {
        if (sportDetailDAO.updateSportDetail(sportDetail)) {
            addMessage(FacesMessage.SEVERITY_INFO, "message.detailsUpdated");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "message.detailsUpdateFailed");
        }
    }

    public void addSportDetail() {
        sportDetail.setSportId(sportId);
        int detailId = sportDetailDAO.addSportDetail(sportDetail);
        
        if (detailId > 0) {
            sportDetail.setDetailId(detailId);
            addMessage(FacesMessage.SEVERITY_INFO, "sport.detailsAdded");
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "sport.detailsAddFailed");
        }
    }

    private void addMessage(FacesMessage.Severity severity, String messageKey) {
        FacesContext context = FacesContext.getCurrentInstance();
        String message = context.getApplication().evaluateExpressionGet(context, 
            "#{msg['" + messageKey + "']}", String.class);
        context.addMessage(null, new FacesMessage(severity, message, null));
    }
    
    // Getters and Setters
    public SportDetail getSportDetail() {
        return sportDetail;
    }
    
    public void setSportDetail(SportDetail sportDetail) {
        this.sportDetail = sportDetail;
    }
    
    public Sport getSport() {
        return sport;
    }
    
    public void setSport(Sport sport) {
        this.sport = sport;
    }
    
    public int getSportId() {
        return sportId;
    }
    
    public void setSportId(int sportId) {
        this.sportId = sportId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
} 