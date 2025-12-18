package com.sports.bean;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage.Severity;

import com.sports.dao.AdminDAO;
import com.sports.model.Admin;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String password;
    private boolean loggedIn;
    private Admin currentAdmin;
    
    public String login() {
        // Check for empty fields
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            addMessage(FacesMessage.SEVERITY_ERROR, "login.error.emptyFields");
            return null;
        }
        
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.validateLogin(username, password);
        
        if (admin != null) {
            loggedIn = true;
            currentAdmin = admin;
            return "/admindashboard.xhtml?faces-redirect=true";
        }
        
        loggedIn = false;
        currentAdmin = null;
        addMessage(FacesMessage.SEVERITY_ERROR, "login.error.invalidCredentials");
        return null;
    }
    
    private void addMessage(Severity severity, String messageKey) {
        FacesContext context = FacesContext.getCurrentInstance();
        String message = context.getApplication().evaluateExpressionGet(context, 
            "#{msg['" + messageKey + "']}", String.class);
        context.addMessage(null, new FacesMessage(severity, message, null));
    }
    
    public String logout() {
        loggedIn = false;
        currentAdmin = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
    // Getters and Setters
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
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public Admin getCurrentAdmin() {
        return currentAdmin;
    }
    
    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
} 