package org.example.sq.part1.SmartHomeController.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionUser {
    private Integer userId;
    private String username;
    private boolean authenticated;

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public boolean isAuthenticated() { return authenticated; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public void clear() {
        this.userId = null;
        this.username = null;
        this.authenticated = false;
    }

}
