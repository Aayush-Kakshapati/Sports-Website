package com.sports.bean;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import com.sports.dao.AdminDAO;
import com.sports.model.Admin;

@Named("userSession")
@SessionScoped
public class UserSessionBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private boolean isAdmin = false;
    private String username;
    private boolean loggedIn = false;
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public String login(String username, String password) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.validateLogin(username, password);
        if (admin != null) {
            this.username = admin.getUsername();
            this.loggedIn = true;
            this.isAdmin = true;
            return "sports?faces-redirect=true";
        }
        // Optionally, you can add a message for failed login here
        return null;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
} 