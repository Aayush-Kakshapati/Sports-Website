package com.sports.bean;

import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;

// Managed bean for contact form

@Named("contactBean")
@RequestScoped
public class ContactBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private FacesContext facesContext;
    
    private String name;
    private String email;
    private String subject;
    private String message;
    
    /**
     * Process the contact form submission
     */
    public void sendMessage() {
        try {
            
            // Add success message
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Success", "Thank you for your message! We will get back to you soon."));
            
            // Clear the form
            name = "";
            email = "";
            subject = "";
            message = "";
            
        } catch (Exception e) {
            // Add error message
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error", "An error occurred while sending your message. Please try again later."));
        }
    }
    
    // Getters and Setters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}