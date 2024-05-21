package com.dgu_csc.akomanager_back.dto;

import com.dgu_csc.akomanager_back.model.User;

public class UpdateUserRequest {
    private User updatedUser;
    private String password;

    // Getters and Setters
    public User getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
